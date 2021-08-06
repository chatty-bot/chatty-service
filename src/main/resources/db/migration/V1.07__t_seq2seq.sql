CREATE TABLE t_seq2seq
(
    id           serial  not null primary key,
    seq2seq_name text    not null unique,
    file_name    text    not null unique,
    train        boolean default false,
    user_id      integer not null,


    FOREIGN KEY (user_id) references t_users

);
