pub mod entities;
pub mod errors;
pub mod persistence;
pub mod routes;
pub mod util;

use crate::persistence::postgres::init_pool;
use crate::routes::specs::specs_router;
use crate::routes::{v1_router, AppInject};
use crate::util::logging::init_logging;
use anyhow::Result;
use axum::Router;
use std::env::var;
use tracing::info; 

#[tokio::main]
pub async fn main() -> Result<()> {
    let debug: bool = var("TRAK_DEBUG")
        .unwrap_or("false".to_string())
        .parse()
        .unwrap_or(false);

    let _ = init_logging(debug)?;

    let bind_addr = var("TRAK_SERVER_ADDR").unwrap_or("127.0.0.1".to_string());
    let bind_port = var("TRAK_SERVER_PORT").unwrap_or("8642".to_string());

    let router = Router::new()
        .nest("/api/specs/", specs_router())
        .nest("/api/v1/", v1_router())
        .with_state(AppInject {
            pool: init_pool().await?,
        });

    let listener = tokio::net::TcpListener::bind(format!("{}:{}", bind_addr, bind_port)).await?;

    let addr = listener.local_addr()?;
    info!("Listening on {}:{}", addr.ip(), addr.port());

    axum::serve(listener, router).await?;
    Ok(())
}
