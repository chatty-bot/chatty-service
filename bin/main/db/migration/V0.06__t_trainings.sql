CREATE TABLE t_trainings
(
    id                  serial    not null primary key,
    classifier_id       int,
    answer_selection_id int,
    training_type       text      not null,
    training_status     text      not null,
    started_at          timestamp not null,

    FOREIGN KEY (classifier_id) REFERENCES t_classifiers
);
CREATE SEQUENCE sq_trainings
    START WITH 100000
    INCREMENT BY 1;