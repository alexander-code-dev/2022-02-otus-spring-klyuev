CREATE TABLE COMMENT (
                             id bigint not null primary key,
                             book_id bigint,
                             comment varchar
);
CREATE SEQUENCE COMMENT_ID_SEQ;
ALTER TABLE COMMENT ALTER COLUMN id SET DEFAULT nextval('COMMENT_ID_SEQ');