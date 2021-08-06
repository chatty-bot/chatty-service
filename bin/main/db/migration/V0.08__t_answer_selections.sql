CREATE TABLE t_answer_selections
(
    id                    serial not null primary key,
    answer_selection_name TEXT   not null unique,
    user_id               int    not null,

    FOREIGN KEY (user_id) REFERENCES t_users
);

CREATE SEQUENCE sq_answer_selections
    START WITH 100000
    INCREMENT BY 1;
