use crate::errors::{database, internal, parameter};
use anyhow::Result;
use argon2::password_hash::rand_core::OsRng;
use argon2::password_hash::{PasswordHashString, SaltString};
use argon2::{Argon2, PasswordHasher};
use sqlx::{query, PgPool};

#[derive(Clone, Debug)]
pub struct Auth {
    pub(crate) login: String,
}

impl Auth {
    pub async fn login_is_email(&self, pool: &PgPool) -> Result<bool> {
        let result = query!(
            "SELECT trak.public.auth.login_is_email FROM trak.public.auth WHERE trak.public.auth.login = $1",
            self.login
        )
            .fetch_one(pool)
            .await?;

        Ok(result.login_is_email.unwrap_or(false))
    }

    async fn password_hash_string(&self, pool: &PgPool) -> Result<PasswordHashString> {
        let result = query!(
            "SELECT trak.public.auth.password_hash FROM trak.public.auth WHERE trak.public.auth.login = $1",
            self.login
        )
            .fetch_one(pool)
            .await?;

        Ok(PasswordHashString::new(&*result.password_hash)?)
    }
}

pub async fn login_exists(login: &str, pool: &PgPool) -> Result<bool> {
    let result = query!(
        "SELECT EXISTS (SELECT 1 FROM trak.public.auth WHERE trak.public.auth.login = $1)",
        login
    )
        .fetch_one(pool)
        .await
        .map_err(|err| database::Error::GetUserAuthByLogin(login.to_string(), err))?;

    Ok(result.exists.unwrap_or(false))
}

pub async fn create_user_auth(
    id: &uuid::Uuid,
    auth: &Auth,
    password: &str,
    pool: &PgPool,
) -> Result<()> {
    if login_exists(&auth.login, pool).await? {
        return Err(parameter::Error::AuthExists(auth.login.clone()).into());
    }

    let argon = Argon2::default();
    let salt = SaltString::generate(&mut OsRng);
    let hash_string = argon
        .hash_password(password.as_ref(), &salt)
        .map_err(|err| internal::Error::Argon2(err))?
        .serialize();

    query!(
        "INSERT INTO trak.public.auth(id, login, password_hash) VALUES ($1, $2, $3)",
        id,
        auth.login.clone(),
        hash_string.to_string()
    )
        .execute(pool)
        .await
        .map_err(|err| database::Error::CreateUserAuth(id.to_string(), err))?;

    Ok(())
}

pub async fn get_user_auth(id: &uuid::Uuid, pool: &PgPool) -> Result<Auth> {
    let auth = query!(
        "SELECT trak.public.auth.login FROM trak.public.auth WHERE trak.public.auth.id = $1",
        id
    )
        .fetch_one(pool)
        .await
        .map_err(|err| database::Error::GetUserAuthById(id.to_string(), err))?;

    Ok(Auth { login: auth.login })
}
