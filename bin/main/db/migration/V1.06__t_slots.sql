CREATE TABLE t_slots
(
    id    serial not null primary key,
    state_id integer not null,
    label text   not null,
    value text   not null,

    foreign key (state_id) REFERENCES t_state
);
