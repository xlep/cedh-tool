DROP DATABASE IF EXISTS cedhtool;
CREATE DATABASE cedhtool;

-- connect to new db
\c cedhtool;

-- grant necessary privileges to user
GRANT ALL PRIVILEGES ON DATABASE cedhtool TO cedh;
GRANT ALL PRIVILEGES on SCHEMA public to cedh;

ALTER DEFAULT PRIVILEGES
IN SCHEMA public
GRANT SELECT, INSERT, UPDATE, DELETE, REFERENCES ON TABLES TO cedh;

-- table schema
CREATE TABLE player (
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    nickname VARCHAR,
    firstname VARCHAR,
    lastname VARCHAR,
    email VARCHAR
);

CREATE TABLE tournament (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR,
    mode VARCHAR
);

CREATE TABLE tournamentplayers (
   tournament UUID references tournament(id),
   player UUID references player(id),
   score NUMERIC(7,3),
   PRIMARY KEY (tournament, player)
);


CREATE TABLE pod (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name INTEGER,
    round INTEGER
);

CREATE TABLE seats (
    pod UUID references pod(id),
    player UUID references player(id),
    seat INTEGER,
    result CHARACTER(1),
    PRIMARY KEY (pod, player)
);


-- test data
INSERT INTO player (id, nickname, firstname, lastname)
VALUES
    ('01969317-ed10-719f-8e38-79246ef3da3f', 'Morgan', 'Morgan', 'le Fay'),
    ('01969318-8ded-7d09-b9a3-5fd88be2e0f8', 'Dr Strange', 'Stephen', 'Strange'),
    ('cc73f87c-8beb-4e34-ab38-a07a73493c05', 'Harry', 'Harry', 'Dresden'),
    ('8722f858-4906-4730-9c2f-8c96dd3c6458', 'HP', 'Harry Potter', 'Potter');

INSERT INTO pod (id, name, round)
VALUES
    ('0aaaa7b1-2b6a-4aec-af76-837807a10567', 5, 1);

INSERT INTO seats (pod, player, seat, result)
VALUES
    ('0aaaa7b1-2b6a-4aec-af76-837807a10567', '01969318-8ded-7d09-b9a3-5fd88be2e0f8', 1, 'W'),
    ('0aaaa7b1-2b6a-4aec-af76-837807a10567', '01969317-ed10-719f-8e38-79246ef3da3f', 2, 'L'),
    ('0aaaa7b1-2b6a-4aec-af76-837807a10567', 'cc73f87c-8beb-4e34-ab38-a07a73493c05', 3, 'L'),
    ('0aaaa7b1-2b6a-4aec-af76-837807a10567', '8722f858-4906-4730-9c2f-8c96dd3c6458', 4, 'L');