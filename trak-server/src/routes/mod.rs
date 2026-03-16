pub mod v1;
pub mod specs;

use crate::routes::v1::auth::auth_router;
use axum::Router;
use sqlx::PgPool;

pub fn v1_router() -> Router<AppInject> {
    Router::new().nest("/auth", auth_router())
}

#[derive(Clone)]
pub struct AppInject {
    pub pool: PgPool,
}
