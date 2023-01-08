CREATE TABLE GENRE (
    id bigint not null primary key,
    name varchar
);
CREATE SEQUENCE GENRE_ID_SEQ;
ALTER TABLE GENRE ALTER COLUMN id SET DEFAULT nextval('GENRE_ID_SEQ');