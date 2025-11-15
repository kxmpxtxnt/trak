use serde::{Deserialize, Serialize};

#[derive(Clone, Debug, Serialize, Deserialize)]
pub struct User {
    pub(crate) id: String,
    pub(crate) personal: Personal,
    pub(crate) biology: Biology,
}

#[derive(Clone, Debug, Serialize, Deserialize)]
pub struct Personal {
    pub(crate) first_name: String,
    pub(crate) last_name: String,
}

#[derive(Clone, Debug, Serialize, Deserialize)]
pub struct Biology {
    pub(crate) age: u8,
    pub(crate) weight: f32,
    pub(crate) height: f32,
    pub(crate) able_to_menstruate: bool,
}