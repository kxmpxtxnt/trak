use serde::Serialize;

#[derive(Serialize)]
pub struct Token {
    token: String,
    expiration_date: u32
}