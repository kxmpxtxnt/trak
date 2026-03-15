use anyhow::Result;
use std::env::var;
use tracing_subscriber::{fmt, prelude::*, Registry};

pub fn init_logging(debug: bool) -> Result<()> {
    let level = if debug { tracing::Level::DEBUG } else { tracing::Level::INFO };

    let max_logs: usize = var("TRAK_MAX_LOG_FILES")
        .unwrap_or("28".to_string())
        .parse()
        .expect("TRAK_MAX_LOG_FILES must be a number.");

    let file_appender = tracing_appender::rolling::Builder::new()
        .rotation(tracing_appender::rolling::Rotation::DAILY)
        .filename_prefix("trak.log")
        .max_log_files(max_logs)
        .build("./logs")?;

    let (non_blocking_appender, _) = tracing_appender::non_blocking(file_appender);

    let subscriber = Registry::default()
        .with(fmt::layer().with_ansi(true).with_writer(std::io::stdout))
        .with(fmt::layer().with_ansi(false).with_writer(non_blocking_appender))
        .with(tracing_subscriber::filter::LevelFilter::from_level(level));

    tracing::subscriber::set_global_default(subscriber).expect("Setting default subscriber failed");

    Ok(())
}
