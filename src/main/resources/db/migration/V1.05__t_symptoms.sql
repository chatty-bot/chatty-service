CREATE TABLE t_symptoms
(
    id                serial not null primary key,
    symptom_name      text   not null,
    symptom_frequency text   not null,
    illness_name      text   not null
);
