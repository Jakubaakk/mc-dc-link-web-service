CREATE TABLE user (
    id BINARY(16) PRIMARY KEY,
    minecraft_nickname varchar(13) UNIQUE,
    discord_id int(18) UNIQUE,
    verified boolean NOT NULL DEFAULT false
)
