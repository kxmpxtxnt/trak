use crate::entities::auth::{login_exists, Auth};
use crate::entities::user::{create_user, User};
use crate::errors::AppError;
use crate::routes::AppInject;
use anyhow::Result;
use axum::extract::State;
use axum::http::StatusCode;
use axum::response::IntoResponse;
use axum::Json;
use serde::{Deserialize, Serialize};

#[derive(Deserialize)]
pub struct RegisterBody {
    name: String,
    login: String,
    password: String,
}

#[derive(Serialize)]
pub struct RegisterResponse {
    id: String,
    login_is_mail: bool,
}

pub enum RegisterResult {
    Created(Json<RegisterResponse>),
    Forbidden,
    AlreadyExists,
}

impl IntoResponse for RegisterResult {
    fn into_response(self) -> axum::response::Response {
        match self {
            RegisterResult::Created(json) => (StatusCode::CREATED, json).into_response(),
            RegisterResult::Forbidden => StatusCode::FORBIDDEN.into_response(),
            RegisterResult::AlreadyExists => StatusCode::CONFLICT.into_response(),
        }
    }
}

pub async fn register_route(
    State(AppInject { pool }): State<AppInject>,
    Json(body): Json<RegisterBody>,
) -> Result<RegisterResult, AppError> {
    if login_exists(&*body.login, &pool).await? {
        return Ok(RegisterResult::AlreadyExists);
    }

    //TODO: Filter login name -> Forbidden

    let user = User {
        id: uuid::Uuid::now_v7(),
        name: body.name,
        auth: Auth { login: body.login },
    };

    create_user(&user, &body.password, &pool).await?;

    Ok(RegisterResult::Created(Json(RegisterResponse {
        id: user.id.to_string(),
        login_is_mail: user.auth.login_is_email(&pool).await?,
    })))
}
