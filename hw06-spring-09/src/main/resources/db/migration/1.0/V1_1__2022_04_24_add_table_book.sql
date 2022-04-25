CREATE TABLE BOOK (
    id bigint not null primary key,
    name varchar,
    description_id varchar,
    page_volume bigint,
    book_release_year bigint,
    author_id bigint,
    genre_id bigint
);
CREATE SEQUENCE BOOK_ID_SEQ;
ALTER TABLE BOOK ALTER COLUMN id SET DEFAULT nextval('BOOK_ID_SEQ');