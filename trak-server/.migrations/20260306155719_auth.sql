-- Add migration script here
CREATE TABLE IF NOT EXISTS trak.public.auth
(
    id            UUID PRIMARY KEY UNIQUE NOT NULL,
    login         TEXT                    NOT NULL,
    password_hash TEXT                    NOT NULL,

    login_is_email     BOOLEAN GENERATED ALWAYS AS
        (login ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$') STORED
);