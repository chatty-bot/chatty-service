CREATE TABLE t_txt_clf_datasets
(
    id                serial not null primary key,
    classifier_id     int    not null,
    file_name         TEXT   not null,
    number_of_samples int,

    FOREIGN KEY (classifier_id) REFERENCES t_classifiers,
    CONSTRAINT unique_key_txt_clf_dataset UNIQUE (classifier_id, file_name)
);


CREATE SEQUENCE sq_txt_clf_datasets
    START WITH 100000
    INCREMENT BY 1;