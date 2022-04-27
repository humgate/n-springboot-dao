create table customers (
                           id serial primary key,
                           name varchar(255) not null,
                           surname varchar(255) not null ,
                           age integer not null,
                           phone_number bigint
);

create table orders (
                        id serial primary key,
                        date date,
                        customer_id integer references customers(id),
                        product_name varchar(255) not null,
                        amount integer not null
);

