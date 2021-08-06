CREATE TABLE t_dst
(
    id        serial  not null primary key,
    dst_name  text    not null unique,
    file_name text    not null unique,
    train     boolean default false,
    user_id   integer not null,


    FOREIGN KEY (user_id) references t_users

);
