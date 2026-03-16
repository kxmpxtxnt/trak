use crate::routes::AppInject;
use axum::response::Redirect;
use axum::routing::get;
use axum::Router;

pub fn specs_router() -> Router<AppInject> {
    Router::new()
        .route("/openapi.yaml", get(|| async {
            Redirect::permanent("https://raw.githubusercontent.com/kxmpxtxnt/trak/refs/heads/main/trak-specifications/openapi.yaml")
        }))
}
