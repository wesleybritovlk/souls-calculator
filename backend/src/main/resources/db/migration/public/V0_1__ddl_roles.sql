CREATE TABLE IF NOT EXISTS public.tb_roles (
    id UUID PRIMARY KEY DEFAULT public.uuid_v7(),
    name VARCHAR(12) NOT NULL CHECK (
        name IN ('ADMIN', 'USER')
    ),
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);