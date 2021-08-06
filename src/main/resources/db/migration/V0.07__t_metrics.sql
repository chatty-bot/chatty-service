CREATE TABLE t_metrics
(
    id            serial not null primary key,
    classifier_id int    not null,
    intent_id     int    not null,
    intent_name   text   not null,
    precision     float  not null,
    training_type text   not null,

    FOREIGN KEY (classifier_id) REFERENCES t_classifiers,
    FOREIGN KEY (intent_id) REFERENCES t_intents
);

CREATE SEQUENCE sq_metrics
    START WITH 100000
    INCREMENT BY 1;
