CREATE TABLE t_txt_clf_data_samples
(
    id            serial  not null primary key,
    user_id       int  not null,
    classifier_id int  not null,
    text          TEXT not null,
    label         TEXT not null,

    FOREIGN KEY (user_id) REFERENCES t_users,
    foreign key (classifier_id) references t_classifiers

);

CREATE SEQUENCE sq_txt_clf_data_samples
    START WITH 100000
    INCREMENT BY 1;