-- Add migration script here
CREATE UNLOGGED TABLE IF NOT EXISTS trak.public.access_tokens
(
    id              UUID PRIMARY KEY NOT NULL,
    token_hash      VARCHAR(32)      NOT NULL,
    expiration_date TIMESTAMP        NOT NULL,
    permissions     TEXT[]           NOT NULL DEFAULT '{}'::TEXT[]
);

CREATE INDEX idx_at_id ON trak.public.access_tokens (id);

CREATE OR REPLACE FUNCTION trak.public.fn_sync_token_permissions()
    RETURNS TRIGGER AS
$$
BEGIN
    SELECT trak.public.users.permissions
    INTO NEW.permissions
    FROM trak.public.users
    WHERE trak.public.users.id = NEW.id;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS trg_populate_permissions ON trak.public.access_tokens;
CREATE TRIGGER trg_populate_permissions
    BEFORE INSERT
    ON trak.public.access_tokens
    FOR EACH ROW
EXECUTE FUNCTION trak.public.fn_sync_token_permissions();