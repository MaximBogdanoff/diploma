-- liquibase formatted sql

CREATE TABLE IF NOT EXISTS comments(

    id SERIAL NOT NULL,
    text VARCHAR(32) NOT NULL,
    dateTime BIGINT,
    author_id INT NOT NULL,
    ad_id INT NOT NULL,

    PRIMARY KEY (id),

    FOREIGN KEY (author_id) REFERENCES users(id),
    FOREIGN KEY (ad_id) REFERENCES ads(id)
)