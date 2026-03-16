-- Add migration script here
CREATE TABLE IF NOT EXISTS trak.public.users
(
    id   UUID PRIMARY KEY UNIQUE NOT NULL,
    name TEXT                    NOT NULL
);