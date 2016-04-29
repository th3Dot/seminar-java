CREATE TABLE AUTHOR (
    id BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    firstname VARCHAR(50),
    surname VARCHAR(50),
    description VARCHAR(50),
    nationality VARCHAR(50),
    dateofbirth DATE
);

CREATE TABLE BOOK (
    id BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(50),
    isbn VARCHAR(50),
    published DATE,
    author_id BIGINT,
    FOREIGN KEY (author_id)
    REFERENCES AUTHOR (id)
);
