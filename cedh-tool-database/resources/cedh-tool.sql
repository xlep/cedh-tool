DROP DATABASE IF EXISTS cedhtool;
CREATE DATABASE cedhtool;

-- connect to new db
\c cedhtool;

-- grant necessary privileges to user
GRANT ALL PRIVILEGES ON DATABASE cedhtool TO cedh;
GRANT ALL PRIVILEGES on SCHEMA public to cedh;

ALTER DEFAULT PRIVILEGES
IN SCHEMA public
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO cedh;

-- table schema
CREATE TABLE player (
    playerid uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    nickname VARCHAR,
    firstname VARCHAR,
    lastname VARCHAR
);

-- test data
INSERT INTO player (playerid, nickname, firstname, lastname)
VALUES
    ('01969317-ed10-719f-8e38-79246ef3da3f', 'Don', 'Donald', 'Duck'),
    ('01969318-8ded-7d09-b9a3-5fd88be2e0f8', 'DD', 'Daisy', 'Duck');