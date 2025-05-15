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
   tournament_id UUID references tournament(id),
   player_id UUID references player(id),
   score NUMERIC(7,3),
   PRIMARY KEY (tournament_id, player_id)
);


CREATE TABLE pod
(
    id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tournament_id UUID NOT NULL,
    name          INTEGER,
    round         INTEGER,
    pod_type      VARCHAR (12),
    CONSTRAINT fk_tournament FOREIGN KEY (tournament_id) REFERENCES tournament (id)
);

CREATE TABLE seats (
   pod UUID REFERENCES pod(id),
   player UUID REFERENCES player(id),
   seat INTEGER,
   result TEXT CHECK (result IN ('win', 'loss', 'draw')),
   PRIMARY KEY (pod, player)
);


-- test data players
WITH ids(id) AS (
    SELECT UNNEST(ARRAY[
                  '22321b1c-cade-4e9e-866c-34610510922b',
                  '8dd9b669-929e-4fe2-8f95-3d64fa2d11d2',
                  'ea070c1f-83cc-49ae-af54-95b661d07b3f',
                  '54256855-f77e-449b-ade3-c986168a56ca',
                  '328857fe-61b4-4295-9ed4-dbc395d7a747',
                  '74ba2c67-0a35-43b6-98ee-628739759ece',
                  '12779ebe-7d53-46df-999c-e81ec6243a34',
                  'ce23deb8-83f0-4124-b37a-c3691defb150',
                  'f38e7124-620e-4961-91ed-227535dabb4c',
                  'acbca5d1-18ad-49ec-b91b-34923037681d',
                  '83a2ea79-2f8b-44f0-9017-a583f9e7797f',
                  'd9d0a2be-084e-4ac7-bb41-7812948a6df4',
                  '58a517cd-b73c-4bbe-a2d1-584face37da9',
                  'c41489a7-0d36-457e-9973-d2ebbfb631d2',
                  'a574c5d3-25b2-4a58-a877-7362ff0a52dc',
                  '73b8d869-175f-46e9-916b-96f754731997',
                  '0a19432f-509f-413a-9e4c-b25089b9b60c',
                  '49063bb2-0a34-4d8d-9524-f6bfa6fa70f5',
                  '2cc85517-c291-4e9a-b48f-11ed8e4bf12e',
                  '61b9e0e2-5d01-4b41-ad78-3fed33296b81',
                  '8fbc64f9-ae36-4151-994d-b2f5e6d3a381',
                  'dae368a0-3c8a-4786-b4e7-9135534469ba',
                  '76caf60c-5f37-439f-b57e-264b91b3579a',
                  '46b49bcd-fa90-42a6-82f7-2df13a5ec749',
                  '99cfb36a-be39-4a1a-a361-f0480730fbe7',
                  '6ff783ed-7c1e-4975-9bc8-7dc9d5a9e202',
                  '28324d70-bc41-43c2-96b6-a2eae0492b4d',
                  'f74315a0-2973-4730-b9dd-75db4a989d6b',
                  '8407ca62-bd16-4976-9c00-12a8068383ec',
                  '6a6e77ed-ccf0-4a88-bb10-a17a0cc0ec9c',
                  '4ab14f83-f137-4aff-8964-8c500981abdc',
                  'ef492cd7-030d-486a-8586-590049c5f50f',
                  '12bd3140-7c47-48a9-9ffe-f28898ffd3d8',
                  '4c38acba-eebe-4461-9c7d-2676d0dbd722',
                  '7824f5a5-88da-404d-9f18-fc31d7fbc16c',
                  '0f93294b-0aa3-47a9-8d90-b0c3faa67e50',
                  'b2ba3304-5da8-42a7-9282-b139c2bde3ea',
                  'aa545aeb-bf8f-4e82-a6d0-7963ff73601b',
                  '925b3c3d-2335-467f-8b62-308f7d263bfb',
                  '6ecea270-ef06-4eec-91f8-59ae7c993541',
                  '738d976a-0542-43ac-9cb7-cc01cbc9f259',
                  'ba7c3921-7b75-4036-992c-6236942012ea',
                  '33a574b7-78c2-4558-ad1c-f235311ac3f8',
                  'd39a2de3-64c0-40fc-b756-209fe1ed009b',
                  '93314daa-f827-43de-abd5-2bb08e159967',
                  '2c4ce0a1-7ab3-48d8-bd08-d0cedacf095e',
                  '3f5c0a54-c98f-4605-9ffe-f74acdd05558',
                  '89f510a4-7c70-4228-b709-7d6de3a74c0e',
                  '44f4d1dd-d76f-4f53-8504-0a08db24cf79',
                  'ff046fdb-1bed-42ba-a624-e8a81f1a3682',
                  '3ac97981-d361-46cb-ada6-1ca7ade9f38f',
                  'b0bd443e-f534-4c2d-8a23-38afbc70ec93',
                  'cd4aeaef-bd38-4ccd-84a1-7c9f5fbd6dc8',
                  '1bca64de-1bea-4fdb-b0c2-b2c8a6eae2cc',
                  'cde00fa1-2fc9-48f6-8c02-315e0a369a8a',
                  'db86c071-051b-4617-9a9c-bc7f593bd912',
                  'e2d93ab3-f18d-4154-ac8a-e05fb020911d',
                  '9289505f-c16d-4ce0-9518-27da74f8ac3f',
                  'fcc33a93-7270-46bb-994e-aecf7ccaf37f',
                  'ac27b7e0-626b-405d-aaad-80e6d9ac3274'
                      ]::uuid[])
), indexed_ids AS (
    SELECT id, row_number() OVER () AS rn FROM ids
),
     names AS (
         SELECT
             gs.i,
             'nick_' || gs.i AS nickname,
             firstnames[ceil(random() * array_length(firstnames, 1))] AS firstname,
             lastnames[ceil(random() * array_length(lastnames, 1))] AS lastname
         FROM generate_series(1, 60) AS gs(i),
              LATERAL (
                       SELECT
                           ARRAY['James', 'John', 'Robert', 'Michael', 'William', 'David', 'Richard', 'Joseph', 'Thomas', 'Charles',
                           'Mary', 'Patricia', 'Jennifer', 'Linda', 'Elizabeth', 'Barbara', 'Susan', 'Jessica', 'Sarah', 'Karen']
    AS firstnames,
    ARRAY['Smith', 'Johnson', 'Williams', 'Brown', 'Jones', 'Garcia', 'Miller', 'Davis', 'Rodriguez', 'Martinez',
    'Hernandez', 'Lopez', 'Gonzalez', 'Wilson', 'Anderson', 'Thomas', 'Taylor', 'Moore', 'Jackson', 'Martin']
    AS lastnames
    ) AS names
    )
INSERT INTO player (id, nickname, firstname, lastname)
SELECT indexed_ids.id, names.nickname, names.firstname, names.lastname
FROM names
         JOIN indexed_ids ON names.i = indexed_ids.rn;
-- end of player section

-- test data tournament
insert into tournament (id, name, mode)
values ('e29fbe3f-1755-43cc-a27a-393ec6d80a09' ,'Summer cEDH II', 'cEDH');

--Populate Summer cEDH II with players
INSERT INTO tournamentplayers (tournament_id, player_id, score)
VALUES
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '22321b1c-cade-4e9e-866c-34610510922b', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '8dd9b669-929e-4fe2-8f95-3d64fa2d11d2', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'ea070c1f-83cc-49ae-af54-95b661d07b3f', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '54256855-f77e-449b-ade3-c986168a56ca', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '328857fe-61b4-4295-9ed4-dbc395d7a747', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '74ba2c67-0a35-43b6-98ee-628739759ece', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '12779ebe-7d53-46df-999c-e81ec6243a34', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'ce23deb8-83f0-4124-b37a-c3691defb150', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'f38e7124-620e-4961-91ed-227535dabb4c', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'acbca5d1-18ad-49ec-b91b-34923037681d', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '83a2ea79-2f8b-44f0-9017-a583f9e7797f', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'd9d0a2be-084e-4ac7-bb41-7812948a6df4', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '58a517cd-b73c-4bbe-a2d1-584face37da9', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'c41489a7-0d36-457e-9973-d2ebbfb631d2', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'a574c5d3-25b2-4a58-a877-7362ff0a52dc', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '73b8d869-175f-46e9-916b-96f754731997', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '0a19432f-509f-413a-9e4c-b25089b9b60c', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '49063bb2-0a34-4d8d-9524-f6bfa6fa70f5', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '2cc85517-c291-4e9a-b48f-11ed8e4bf12e', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '61b9e0e2-5d01-4b41-ad78-3fed33296b81', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '8fbc64f9-ae36-4151-994d-b2f5e6d3a381', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'dae368a0-3c8a-4786-b4e7-9135534469ba', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '76caf60c-5f37-439f-b57e-264b91b3579a', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '46b49bcd-fa90-42a6-82f7-2df13a5ec749', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '99cfb36a-be39-4a1a-a361-f0480730fbe7', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '6ff783ed-7c1e-4975-9bc8-7dc9d5a9e202', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '28324d70-bc41-43c2-96b6-a2eae0492b4d', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'f74315a0-2973-4730-b9dd-75db4a989d6b', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '8407ca62-bd16-4976-9c00-12a8068383ec', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '6a6e77ed-ccf0-4a88-bb10-a17a0cc0ec9c', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '4ab14f83-f137-4aff-8964-8c500981abdc', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'ef492cd7-030d-486a-8586-590049c5f50f', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '12bd3140-7c47-48a9-9ffe-f28898ffd3d8', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '4c38acba-eebe-4461-9c7d-2676d0dbd722', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '7824f5a5-88da-404d-9f18-fc31d7fbc16c', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '0f93294b-0aa3-47a9-8d90-b0c3faa67e50', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'b2ba3304-5da8-42a7-9282-b139c2bde3ea', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'aa545aeb-bf8f-4e82-a6d0-7963ff73601b', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '925b3c3d-2335-467f-8b62-308f7d263bfb', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '6ecea270-ef06-4eec-91f8-59ae7c993541', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '738d976a-0542-43ac-9cb7-cc01cbc9f259', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'ba7c3921-7b75-4036-992c-6236942012ea', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '33a574b7-78c2-4558-ad1c-f235311ac3f8', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'd39a2de3-64c0-40fc-b756-209fe1ed009b', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '93314daa-f827-43de-abd5-2bb08e159967', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '2c4ce0a1-7ab3-48d8-bd08-d0cedacf095e', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '3f5c0a54-c98f-4605-9ffe-f74acdd05558', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '89f510a4-7c70-4228-b709-7d6de3a74c0e', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '44f4d1dd-d76f-4f53-8504-0a08db24cf79', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'ff046fdb-1bed-42ba-a624-e8a81f1a3682', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '3ac97981-d361-46cb-ada6-1ca7ade9f38f', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'b0bd443e-f534-4c2d-8a23-38afbc70ec93', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'cd4aeaef-bd38-4ccd-84a1-7c9f5fbd6dc8', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '1bca64de-1bea-4fdb-b0c2-b2c8a6eae2cc', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'cde00fa1-2fc9-48f6-8c02-315e0a369a8a', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'db86c071-051b-4617-9a9c-bc7f593bd912', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'e2d93ab3-f18d-4154-ac8a-e05fb020911d', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '9289505f-c16d-4ce0-9518-27da74f8ac3f', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'fcc33a93-7270-46bb-994e-aecf7ccaf37f', 1000.000),
    ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'ac27b7e0-626b-405d-aaad-80e6d9ac3274', 1000.000);
-- end of tournamentplayer section

--INSERT INTO pod (id, name, round)
--VALUES
--    ('0aaaa7b1-2b6a-4aec-af76-837807a10567', 5, 1);

--INSERT INTO seats (pod, player, seat, result)
--VALUES
--    ('0aaaa7b1-2b6a-4aec-af76-837807a10567', '01969318-8ded-7d09-b9a3-5fd88be2e0f8', 1, 'W'),
--    ('0aaaa7b1-2b6a-4aec-af76-837807a10567', '01969317-ed10-719f-8e38-79246ef3da3f', 2, 'L'),
--    ('0aaaa7b1-2b6a-4aec-af76-837807a10567', 'cc73f87c-8beb-4e34-ab38-a07a73493c05', 3, 'L'),
--    ('0aaaa7b1-2b6a-4aec-af76-837807a10567', '8722f858-4906-4730-9c2f-8c96dd3c6458', 4, 'L');