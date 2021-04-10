CREATE DATABASE IF NOT EXISTS restful_music;
USE restful_music;

DROP TABLE IF EXISTS album;
CREATE TABLE album (
    isrc VARCHAR(12) NOT NULL,
    title VARCHAR(255) NOT NULL,
    year YEAR NOT NULL,
    artist VARCHAR(255) NOT NULL,
    PRIMARY KEY (isrc)
);

SELECT * FROM album