use axum::Router;
use axum::routing::post;
use crate::routes::AppInject;
use crate::routes::v1::auth::register::register_route;

pub mod register;

pub fn auth_router() -> Router<AppInject> {
    Router::new().route("/register", post(register_route))
}