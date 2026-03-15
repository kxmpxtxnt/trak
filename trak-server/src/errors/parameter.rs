use thiserror::Error;

#[derive(Error, Debug)]
pub enum Error {
    #[error("Auth with login ({0}) does already exists.")]
    AuthExists(String),
}
