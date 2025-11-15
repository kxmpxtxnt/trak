mod users;

use anyhow::Result;
use axum::Router;
use std::env::var;
use tracing::debug;

#[tokio::main]
pub async fn main() -> Result<()> {
    tracing::subscriber::set_global_default(tracing_subscriber::fmt().finish())?;

    let bind_addr = var("TRAK_SERVER_ADDR").unwrap_or("127.0.0.1".to_string());
    let bind_port = var("TRAK_SERVER_PORT").unwrap_or("8642".to_string());

    let router = Router::new();
    let listener = tokio::net::TcpListener::bind(format!("{}:{}", bind_addr, bind_port)).await?;

    let addr = listener.local_addr()?;
    debug!("Listening on {}:{}", addr.ip(), addr.port());

    axum::serve(listener, router).await?;

    Ok(())
}