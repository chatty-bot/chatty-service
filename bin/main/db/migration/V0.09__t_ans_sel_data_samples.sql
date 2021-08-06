CREATE TABLE t_ans_sel_data_samples
(
    id                  serial not null primary key,
    user_id             int    not null,
    answer_selection_id int    not null,
    text                TEXT   not null,
    response            TEXT   not null,
    intent_name         TEXT   not null,

    FOREIGN KEY (user_id) REFERENCES t_users,

    foreign key (answer_selection_id) references t_answer_selections

);

CREATE SEQUENCE sq_ans_sel_data_samples
    START WITH 100000
    INCREMENT BY 1;