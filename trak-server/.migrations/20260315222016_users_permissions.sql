-- Add migration script here
ALTER TABLE trak.public.users
    ADD permissions TEXT[] NOT NULL DEFAULT '{}'::TEXT[];