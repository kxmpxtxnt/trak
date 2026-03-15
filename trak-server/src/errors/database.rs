use thiserror::Error;

#[derive(Error, Debug)]
pub enum Error {
    #[error("Error while finding user by user id ({0}). - {1}")]
    GetUser(String, sqlx::error::Error),
    #[error("Error while finding auth by user id ({0}). - {1}")]
    GetUserAuthById(String, sqlx::error::Error),
    #[error("Error while finding auth by user login ({0}). - {1}")]
    GetUserAuthByLogin(String, sqlx::error::Error),
    #[error("Error while creating user with user id ({0}). - {1}")]
    CreateUser(String, sqlx::error::Error),
    #[error("Error while creating auth with user id ({0}). - {1}")]
    CreateUserAuth(String, sqlx::error::Error)
}
