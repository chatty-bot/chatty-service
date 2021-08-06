CREATE TABLE t_dialog_data
(
    id                serial not null primary key,
    dialog_id         int    not null,
    turn              int    not null,
    transcript        text,
    system_transcript text,
    issuer            text,
    foreign key (dialog_id) references t_dialogs
);
