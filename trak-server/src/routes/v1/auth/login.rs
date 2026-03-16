use crate::entities::auth::{get_user_auth, login_exists};
use crate::entities::token::Token;
use crate::errors::AppError;
use crate::routes::AppInject;
use anyhow::Result;
use argon2::{Argon2, PasswordHash, PasswordVerifier};
use axum::extract::State;
use axum::http::StatusCode;
use axum::response::{IntoResponse, Response};
use axum::Json;
use serde::{Deserialize, Serialize};
use std::str::FromStr;
use tokio::fs::remove_file;
use uuid::Uuid;

#[derive(Deserialize)]
pub struct LoginBody {
    login: String,
    password: String,
}

#[derive(Serialize)]
pub struct LoginResponse {
    id: String,
    access_token: Token,
    refresh_token: Token,
}

pub enum LoginResult {
    LoggedIn(Json<LoginResponse>),
    Unauthorized,
}

impl IntoResponse for LoginResult {
    fn into_response(self) -> Response {
        match self {
            LoginResult::LoggedIn(json) => (StatusCode::ACCEPTED, json).into_response(),
            LoginResult::Unauthorized => StatusCode::UNAUTHORIZED.into_response(),
        }
    }
}

pub async fn login_route(
    State(AppInject { pool }): State<AppInject>,
    Json(body): Json<LoginBody>,
) -> Result<LoginResult, AppError> {
    if !login_exists(&*body.login, &pool).await? {
        return Ok(LoginResult::Unauthorized);
    }

    let auth = get_user_auth(&Uuid::from_str(&*body.login)?, &pool).await?;
    let password_hash = auth.password_hash_string(&pool).await?;

    let parsed = PasswordHash::new(password_hash.as_str())?;
    if !Argon2::default()
        .verify_password(body.password.as_bytes(), &parsed)
        .is_ok()
    {
        return Ok(LoginResult::Unauthorized);
    }

    Ok(LoginResult::LoggedIn(Json(LoginResponse {
        id: auth.user_id(&pool).await?.to_string(),
        access_token: /* TODO */,
        refresh_token: /* TODO */
    })))
}
