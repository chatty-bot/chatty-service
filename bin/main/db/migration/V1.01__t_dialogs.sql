CREATE TABLE t_dialogs
(
    id              serial  not null primary key,
    is_finished     boolean not null default false,
    requires_action text    not null
);
