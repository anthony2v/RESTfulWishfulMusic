CREATE DATABASE IF NOT EXISTS restful_music;
USE restful_music;

DROP TABLE IF EXISTS album;
CREATE TABLE album (
    id VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    year YEAR NOT NULL,
    artist VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS user;
CREATE TABLE user (
    id VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS userAlbum;
CREATE TABLE userAlbum (
    user_id VARCHAR(255) NOT NULL,
    album_id VARCHAR(255) NOT NULL
);

SELECT * FROM album;

SELECT * FROM user;