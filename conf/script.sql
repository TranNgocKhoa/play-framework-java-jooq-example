CREATE DATABASE `library`;

use library;
alter database library CHARACTER SET utf8 COLLATE utf8_unicode_ci;
CREATE TABLE language
(
    id bigint (7) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    cd CHAR(2) NOT NULL,
    description nvarchar (50)
);

CREATE TABLE author
(
    id bigint (7) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    first_name nvarchar (50),
    last_name nvarchar (50) NOT NULL,
    date_of_birth DATE,
    year_of_birth int (7),
    distinguished int (1)
);

CREATE TABLE book
(
    id bigint (7) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    author_id bigint (7) NOT NULL,
    title nvarchar (400) NOT NULL,
    published_in int (7) NOT NULL,
    language_id bigint (7) NOT NULL,

    CONSTRAINT fk_book_author FOREIGN KEY (author_id) REFERENCES author (id),
    CONSTRAINT fk_book_language FOREIGN KEY (language_id) REFERENCES language (id)
);

CREATE TABLE book_store
(
    name nvarchar (400) NOT NULL UNIQUE
);

CREATE TABLE book_to_book_store
(
    name nvarchar (400) NOT NULL,
    book_id bigint NOT NULL,
    stock   int,

    PRIMARY KEY (name, book_id),
    CONSTRAINT fk_b2bs_book_store FOREIGN KEY (name) REFERENCES book_store (name) ON DELETE CASCADE,
    CONSTRAINT fk_b2bs_book FOREIGN KEY (book_id) REFERENCES book (id) ON DELETE CASCADE
);