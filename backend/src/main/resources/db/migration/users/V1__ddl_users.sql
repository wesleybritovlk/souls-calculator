CREATE TABLE IF NOT EXISTS tb_users (
    id UUID PRIMARY KEY DEFAULT public.uuidv7(),
    username VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password TEXT NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMPTZ
);

CREATE TABLE IF NOT EXISTS tb_users_roles (
    user_id UUID,
    role_id UUID,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES tb_users(id),
    FOREIGN KEY (role_id) REFERENCES public.tb_roles(id)
);
