use thiserror::Error;

#[derive(Error, Debug)]
pub enum Error {
    #[error("Error while interacting with argon2. - {0}")]
    Argon2(argon2::password_hash::Error),
}