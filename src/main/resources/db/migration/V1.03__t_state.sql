CREATE TABLE t_state
(
    id             serial not null primary key,
    dialog_data_id int    not null,
    foreign key (dialog_data_id) references t_dialog_data
);
