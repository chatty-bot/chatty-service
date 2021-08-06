CREATE TABLE t_users
(
    id        serial not null primary key,
    user_name TEXT   not null unique,
    password  TEXT   not null,
    email     TEXT   not null

);


CREATE SEQUENCE sq_users
    START WITH 100000
    INCREMENT BY 1;