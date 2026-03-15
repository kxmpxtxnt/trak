use anyhow::Result;
use sqlx::postgres::PgPoolOptions;
use sqlx::{migrate, PgPool};
use std::env::var;
use tracing::info;

pub async fn init_pool() -> Result<PgPool> {
    let pg_url: String = var("TRAK_PG_CONNECTION_URL").expect("TRAK_PG_CONNECTION_URL is required.");

    let pg_max_connections: u32 = var("TRAK_PG_POOL_MAX_CONNECTION")
        .unwrap_or("5".to_string())
        .parse()
        .expect("PG_POOL_MAX_CONNECTION must be a number.");

    info!("{pg_url}");

    let pool = PgPoolOptions::new()
        .max_connections(pg_max_connections)
        .connect(&*pg_url)
        .await?;

    run_migrations(&pool).await?;

    Ok(pool)
}

pub async fn run_migrations(pool: &PgPool) -> Result<()> {
    Ok(migrate!("./.migrations").run(pool).await?)
}
