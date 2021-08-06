CREATE TABLE t_classifiers
(
    id              serial      not null primary key,
    classifier_name VARCHAR(32) NOT NULL UNIQUE,
    user_id         int      not null,


    foreign key (user_id) references t_users

);

CREATE SEQUENCE sq_classifiers
    START WITH 100000
    INCREMENT BY 1;