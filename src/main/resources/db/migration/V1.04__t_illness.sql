CREATE TABLE t_illness
(
    id           serial not null primary key,
    illness_name text   not null unique
);
