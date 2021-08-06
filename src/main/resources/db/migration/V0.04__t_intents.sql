CREATE TABLE t_intents
(
    id                serial not null primary key,
    classifier_id     int    not null,
    intent_name       TEXT   not null,
    number_of_samples int    not null,

    FOREIGN KEY (classifier_id) REFERENCES t_classifiers,
    UNIQUE (classifier_id, intent_name)

);

CREATE SEQUENCE sq_intents
    START WITH 100000
    INCREMENT BY 1;
