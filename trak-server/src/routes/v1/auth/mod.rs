use crate::routes::v1::auth::login::login_route;
use crate::routes::v1::auth::register::register_route;
use crate::routes::AppInject;
use axum::routing::{post, put};
use axum::Router;

pub mod login;
pub mod register;

pub fn auth_router() -> Router<AppInject> {
    Router::new()
        .route("/register", post(register_route))
        .route("/login", put(login_route))
}
