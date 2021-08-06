CREATE TABLE t_ans_sel_datasets
(
    id                serial not null primary key,
    answer_selection_id     int    not null,
    file_name         TEXT   not null,
    number_of_samples int,

    FOREIGN KEY (answer_selection_id) REFERENCES t_answer_selections,
    CONSTRAINT unique_key_ans_sel_dataset UNIQUE (answer_selection_id, file_name)
);


CREATE SEQUENCE sq_ans_sel_datasets
    START WITH 100000
    INCREMENT BY 1;