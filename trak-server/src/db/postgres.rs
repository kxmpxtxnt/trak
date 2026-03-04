use anyhow::Result;
use sqlx::{PgPool, Pool, Postgres};
use std::process::exit;
use tracing::log::error;

pub async fn create_postgres_pool(jdbc_url: &str) -> Result<Pool<Postgres>> {
    let pool = PgPool::connect(jdbc_url)
        .await
        .inspect(|err| {
            error!(
                "Unable to establish a connection to the database. {:?}",
                err
            )
        })
        .map_err(|_| exit(0))?;

    Ok(pool)
}
