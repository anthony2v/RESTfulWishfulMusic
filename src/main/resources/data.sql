CREATE DATABASE IF NOT EXISTS restful_music;
USE restful_music;

DROP TABLE IF EXISTS album;
CREATE TABLE album (
    isrc VARCHAR(12) NOT NULL,
    title VARCHAR(255) NOT NULL,
    content_description VARCHAR(510) DEFAULT 'No description available.',
    year YEAR NOT NULL,
    artist_first_name VARCHAR(255) NOT NULL,
    artist_last_name VARCHAR(255) NOT NULL,
    cover_image LONGBLOB NOT NULL,
    cover_image_type VARCHAR(255) NOT NULL
);