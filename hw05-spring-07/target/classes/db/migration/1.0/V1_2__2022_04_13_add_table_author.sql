CREATE TABLE AUTHOR (
    id bigint not null primary key,
    name varchar,
    surname varchar
);
CREATE SEQUENCE AUTHOR_ID_SEQ;
ALTER TABLE AUTHOR ALTER COLUMN id SET DEFAULT nextval('AUTHOR_ID_SEQ');