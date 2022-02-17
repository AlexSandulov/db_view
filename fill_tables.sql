-- Set params
set session my.number_of_orders = '100';
set session my.number_of_users = '100';
set session my.number_of_products = '100';
set session my.start_date = '2021-01-01 00:00:00';
set session my.end_date = '2022-02-01 00:00:00';

-- load the pgcrypto extension to gen_random_uuid ()
CREATE EXTENSION pgcrypto;

INSERT INTO products
select id, concat('Product ', id)
FROM GENERATE_SERIES(1, current_setting('my.number_of_products')::int) as id;

INSERT INTO users
select id
     , concat('User ', id)
FROM GENERATE_SERIES(1, current_setting('my.number_of_users')::int) as id;

INSERT INTO orders
select gen_random_uuid ()
     , round(CAST(float8 (random() * 10000) as numeric), 3)
     , TO_TIMESTAMP(start_date, 'YYYY-MM-DD HH24:MI:SS') +
        random()* (TO_TIMESTAMP(end_date, 'YYYY-MM-DD HH24:MI:SS')
            - TO_TIMESTAMP(start_date, 'YYYY-MM-DD HH24:MI:SS'))
     , floor(random() * (current_setting('my.number_of_products')::int) + 1)::int
	, floor(random() * (current_setting('my.number_of_users')::int) + 1)::int
FROM GENERATE_SERIES(1, current_setting('my.number_of_orders')::int) as id
   , current_setting('my.start_date') as start_date
   , current_setting('my.end_date') as end_date;