use crate::entities::auth;
use crate::entities::auth::create_user_auth;
use crate::errors::database;
use anyhow::Result;
use sqlx::query;
use uuid::Timestamp;

pub struct User {
    pub(crate) id: uuid::Uuid,
    pub(crate) name: String,
    pub(crate) auth: auth::Auth,
}

impl User {
    pub fn creation_date(&self) -> Option<Timestamp> {
        self.id.get_timestamp()
    }
}

pub async fn create_user(user: &User, password: &str, pool: &sqlx::PgPool) -> Result<()> {
    create_user_auth(&user.id, &user.auth, password, pool).await?;

    query!(
        "INSERT INTO trak.public.users(id, name) VALUES ($1, $2)",
        user.id,
        user.name
    )
        .execute(pool)
        .await
        .map_err(|err| database::Error::CreateUser(user.id.to_string(), err))?;

    Ok(())
}

pub async fn get_user(id: &uuid::Uuid, pool: &sqlx::PgPool) -> Result<User> {
    let user = query!(
        "SELECT trak.public.users.name FROM trak.public.users WHERE trak.public.users.id = $1;",
        id
    )
        .fetch_one(pool)
        .await
        .map_err(|err| database::Error::GetUser(id.to_string(), err))?;

    let auth = auth::get_user_auth(id, pool).await?;

    Ok(User {
        id: *id,
        name: user.name,
        auth,
    })
}
