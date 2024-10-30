CREATE OR REPLACE FUNCTION public.uuid_v7() RETURNS uuid AS $$
select encode(
        substring(
            int8send(floor(t_ms)::int8)
            from 3
        ) || int2send(
            (7 << 12)::int2 | ((t_ms - floor(t_ms)) * 4096)::int2
        ) || substring(
            uuid_send(gen_random_uuid())
            from 9 for 8
        ),
        'hex'
    )::uuid
from (
        select extract(
                epoch
                from clock_timestamp()
            ) * 1000 as t_ms
    ) s $$ LANGUAGE sql volatile;