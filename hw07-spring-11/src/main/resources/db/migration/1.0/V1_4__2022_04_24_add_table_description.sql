CREATE TABLE DESCRIPTION (
    id bigint not null primary key,
    description varchar
);
CREATE SEQUENCE DESCRIPTION_ID_SEQ;
ALTER TABLE DESCRIPTION ALTER COLUMN id SET DEFAULT nextval('DESCRIPTION_ID_SEQ');