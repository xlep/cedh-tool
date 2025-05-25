-- empty test tournament for TournamentRepositoryTest
insert into tournament (id, name, mode)
values ('58e3de5f-1358-48e6-9268-4b5e451a495a', 'test tournament for TournamentRepositoryTest',
        'cEDH');


-- test tournament for player seating
-- test data tournament
insert into tournament (id, name, mode)
values ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'Summer cEDH II', 'cEDH');

-- test data players
-- Insert 60 players into H2 test database
INSERT INTO player (id, nickname, firstname, lastname)
VALUES ('22321b1c-cade-4e9e-866c-34610510922b', 'nick_1', 'James', 'Smith'),
       ('8dd9b669-929e-4fe2-8f95-3d64fa2d11d2', 'nick_2', 'John', 'Johnson'),
       ('ea070c1f-83cc-49ae-af54-95b661d07b3f', 'nick_3', 'Robert', 'Williams'),
       ('54256855-f77e-449b-ade3-c986168a56ca', 'nick_4', 'Michael', 'Brown'),
       ('328857fe-61b4-4295-9ed4-dbc395d7a747', 'nick_5', 'William', 'Jones'),
       ('12779ebe-7d53-46df-999c-e81ec6243a34', 'nick_7', 'Richard', 'Miller'),
       ('ce23deb8-83f0-4124-b37a-c3691defb150', 'nick_8', 'Joseph', 'Davis'),
       ('74ba2c67-0a35-43b6-98ee-628739759ece', 'nick_6', 'David', 'Garcia'),
       ('f38e7124-620e-4961-91ed-227535dabb4c', 'nick_9', 'Thomas', 'Rodriguez'),
       ('acbca5d1-18ad-49ec-b91b-34923037681d', 'nick_10', 'Charles', 'Martinez'),
       ('83a2ea79-2f8b-44f0-9017-a583f9e7797f', 'nick_11', 'Mary', 'Hernandez'),
       ('d9d0a2be-084e-4ac7-bb41-7812948a6df4', 'nick_12', 'Patricia', 'Lopez'),
       ('58a517cd-b73c-4bbe-a2d1-584face37da9', 'nick_13', 'Jennifer', 'Gonzalez'),
       ('c41489a7-0d36-457e-9973-d2ebbfb631d2', 'nick_14', 'Linda', 'Wilson'),
       ('a574c5d3-25b2-4a58-a877-7362ff0a52dc', 'nick_15', 'Elizabeth', 'Anderson'),
       ('73b8d869-175f-46e9-916b-96f754731997', 'nick_16', 'Barbara', 'Thomas'),
       ('0a19432f-509f-413a-9e4c-b25089b9b60c', 'nick_17', 'Susan', 'Taylor'),
       ('49063bb2-0a34-4d8d-9524-f6bfa6fa70f5', 'nick_18', 'Jessica', 'Moore'),
       ('2cc85517-c291-4e9a-b48f-11ed8e4bf12e', 'nick_19', 'Sarah', 'Jackson'),
       ('61b9e0e2-5d01-4b41-ad78-3fed33296b81', 'nick_20', 'Karen', 'Martin'),
       ('8fbc64f9-ae36-4151-994d-b2f5e6d3a381', 'nick_21', 'James', 'Smith'),
       ('dae368a0-3c8a-4786-b4e7-9135534469ba', 'nick_22', 'John', 'Johnson'),
       ('76caf60c-5f37-439f-b57e-264b91b3579a', 'nick_23', 'Robert', 'Williams'),
       ('46b49bcd-fa90-42a6-82f7-2df13a5ec749', 'nick_24', 'Michael', 'Brown'),
       ('99cfb36a-be39-4a1a-a361-f0480730fbe7', 'nick_25', 'William', 'Jones'),
       ('6ff783ed-7c1e-4975-9bc8-7dc9d5a9e202', 'nick_26', 'David', 'Garcia'),
       ('28324d70-bc41-43c2-96b6-a2eae0492b4d', 'nick_27', 'Richard', 'Miller'),
       ('f74315a0-2973-4730-b9dd-75db4a989d6b', 'nick_28', 'Joseph', 'Davis'),
       ('8407ca62-bd16-4976-9c00-12a8068383ec', 'nick_29', 'Thomas', 'Rodriguez'),
       ('6a6e77ed-ccf0-4a88-bb10-a17a0cc0ec9c', 'nick_30', 'Charles', 'Martinez'),
       ('4ab14f83-f137-4aff-8964-8c500981abdc', 'nick_31', 'Mary', 'Hernandez'),
       ('ef492cd7-030d-486a-8586-590049c5f50f', 'nick_32', 'Patricia', 'Lopez'),
       ('12bd3140-7c47-48a9-9ffe-f28898ffd3d8', 'nick_33', 'Jennifer', 'Gonzalez'),
       ('4c38acba-eebe-4461-9c7d-2676d0dbd722', 'nick_34', 'Linda', 'Wilson'),
       ('7824f5a5-88da-404d-9f18-fc31d7fbc16c', 'nick_35', 'Elizabeth', 'Anderson'),
       ('0f93294b-0aa3-47a9-8d90-b0c3faa67e50', 'nick_36', 'Barbara', 'Thomas'),
       ('b2ba3304-5da8-42a7-9282-b139c2bde3ea', 'nick_37', 'Susan', 'Taylor'),
       ('aa545aeb-bf8f-4e82-a6d0-7963ff73601b', 'nick_38', 'Jessica', 'Moore'),
       ('925b3c3d-2335-467f-8b62-308f7d263bfb', 'nick_39', 'Sarah', 'Jackson'),
       ('6ecea270-ef06-4eec-91f8-59ae7c993541', 'nick_40', 'Karen', 'Martin'),
       ('738d976a-0542-43ac-9cb7-cc01cbc9f259', 'nick_41', 'James', 'Smith'),
       ('ba7c3921-7b75-4036-992c-6236942012ea', 'nick_42', 'John', 'Johnson'),
       ('33a574b7-78c2-4558-ad1c-f235311ac3f8', 'nick_43', 'Robert', 'Williams'),
       ('d39a2de3-64c0-40fc-b756-209fe1ed009b', 'nick_44', 'Michael', 'Brown'),
       ('93314daa-f827-43de-abd5-2bb08e159967', 'nick_45', 'William', 'Jones'),
       ('2c4ce0a1-7ab3-48d8-bd08-d0cedacf095e', 'nick_46', 'David', 'Garcia'),
       ('3f5c0a54-c98f-4605-9ffe-f74acdd05558', 'nick_47', 'Richard', 'Miller'),
       ('89f510a4-7c70-4228-b709-7d6de3a74c0e', 'nick_48', 'Joseph', 'Davis'),
       ('44f4d1dd-d76f-4f53-8504-0a08db24cf79', 'nick_49', 'Thomas', 'Rodriguez'),
       ('ff046fdb-1bed-42ba-a624-e8a81f1a3682', 'nick_50', 'Charles', 'Martinez'),
       ('3ac97981-d361-46cb-ada6-1ca7ade9f38f', 'nick_51', 'Mary', 'Hernandez'),
       ('b0bd443e-f534-4c2d-8a23-38afbc70ec93', 'nick_52', 'Patricia', 'Lopez'),
       ('cd4aeaef-bd38-4ccd-84a1-7c9f5fbd6dc8', 'nick_53', 'Jennifer', 'Gonzalez'),
       ('1bca64de-1bea-4fdb-b0c2-b2c8a6eae2cc', 'nick_54', 'Linda', 'Wilson'),
       ('cde00fa1-2fc9-48f6-8c02-315e0a369a8a', 'nick_55', 'Elizabeth', 'Anderson'),
       ('db86c071-051b-4617-9a9c-bc7f593bd912', 'nick_56', 'Barbara', 'Thomas'),
       ('e2d93ab3-f18d-4154-ac8a-e05fb020911d', 'nick_57', 'Susan', 'Taylor'),
       ('9289505f-c16d-4ce0-9518-27da74f8ac3f', 'nick_58', 'Jessica', 'Moore'),
       ('fcc33a93-7270-46bb-994e-aecf7ccaf37f', 'nick_59', 'Sarah', 'Jackson'),
       ('ac27b7e0-626b-405d-aaad-80e6d9ac3274', 'nick_60', 'Karen', 'Martin');
-- end of player section


--Populate Summer cEDH II with players
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '22321b1c-cade-4e9e-866c-34610510922b', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '8dd9b669-929e-4fe2-8f95-3d64fa2d11d2', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'ea070c1f-83cc-49ae-af54-95b661d07b3f', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '54256855-f77e-449b-ade3-c986168a56ca', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '328857fe-61b4-4295-9ed4-dbc395d7a747', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '74ba2c67-0a35-43b6-98ee-628739759ece', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '12779ebe-7d53-46df-999c-e81ec6243a34', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'ce23deb8-83f0-4124-b37a-c3691defb150', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'f38e7124-620e-4961-91ed-227535dabb4c', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'acbca5d1-18ad-49ec-b91b-34923037681d', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '83a2ea79-2f8b-44f0-9017-a583f9e7797f', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'd9d0a2be-084e-4ac7-bb41-7812948a6df4', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '58a517cd-b73c-4bbe-a2d1-584face37da9', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'c41489a7-0d36-457e-9973-d2ebbfb631d2', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'a574c5d3-25b2-4a58-a877-7362ff0a52dc', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '73b8d869-175f-46e9-916b-96f754731997', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '0a19432f-509f-413a-9e4c-b25089b9b60c', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '49063bb2-0a34-4d8d-9524-f6bfa6fa70f5', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '2cc85517-c291-4e9a-b48f-11ed8e4bf12e', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '61b9e0e2-5d01-4b41-ad78-3fed33296b81', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '8fbc64f9-ae36-4151-994d-b2f5e6d3a381', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'dae368a0-3c8a-4786-b4e7-9135534469ba', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '76caf60c-5f37-439f-b57e-264b91b3579a', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '46b49bcd-fa90-42a6-82f7-2df13a5ec749', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '99cfb36a-be39-4a1a-a361-f0480730fbe7', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '6ff783ed-7c1e-4975-9bc8-7dc9d5a9e202', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '28324d70-bc41-43c2-96b6-a2eae0492b4d', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'f74315a0-2973-4730-b9dd-75db4a989d6b', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '8407ca62-bd16-4976-9c00-12a8068383ec', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '6a6e77ed-ccf0-4a88-bb10-a17a0cc0ec9c', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '4ab14f83-f137-4aff-8964-8c500981abdc', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'ef492cd7-030d-486a-8586-590049c5f50f', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '12bd3140-7c47-48a9-9ffe-f28898ffd3d8', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '4c38acba-eebe-4461-9c7d-2676d0dbd722', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '7824f5a5-88da-404d-9f18-fc31d7fbc16c', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '0f93294b-0aa3-47a9-8d90-b0c3faa67e50', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'b2ba3304-5da8-42a7-9282-b139c2bde3ea', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'aa545aeb-bf8f-4e82-a6d0-7963ff73601b', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '925b3c3d-2335-467f-8b62-308f7d263bfb', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '6ecea270-ef06-4eec-91f8-59ae7c993541', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '738d976a-0542-43ac-9cb7-cc01cbc9f259', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'ba7c3921-7b75-4036-992c-6236942012ea', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '33a574b7-78c2-4558-ad1c-f235311ac3f8', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'd39a2de3-64c0-40fc-b756-209fe1ed009b', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '93314daa-f827-43de-abd5-2bb08e159967', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '2c4ce0a1-7ab3-48d8-bd08-d0cedacf095e', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '3f5c0a54-c98f-4605-9ffe-f74acdd05558', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '89f510a4-7c70-4228-b709-7d6de3a74c0e', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '44f4d1dd-d76f-4f53-8504-0a08db24cf79', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'ff046fdb-1bed-42ba-a624-e8a81f1a3682', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '3ac97981-d361-46cb-ada6-1ca7ade9f38f', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'b0bd443e-f534-4c2d-8a23-38afbc70ec93', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'cd4aeaef-bd38-4ccd-84a1-7c9f5fbd6dc8', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '1bca64de-1bea-4fdb-b0c2-b2c8a6eae2cc', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'cde00fa1-2fc9-48f6-8c02-315e0a369a8a', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'db86c071-051b-4617-9a9c-bc7f593bd912', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'e2d93ab3-f18d-4154-ac8a-e05fb020911d', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', '9289505f-c16d-4ce0-9518-27da74f8ac3f', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'fcc33a93-7270-46bb-994e-aecf7ccaf37f', 1500.000, 'active'),
       ('e29fbe3f-1755-43cc-a27a-393ec6d80a09', 'ac27b7e0-626b-405d-aaad-80e6d9ac3274', 1500.000, 'active');


-- Test Tournamet BYE for pod generation
-- INSERT statement for the 'tournament' table
INSERT INTO tournament (id, name, mode)
VALUES ('5a6b7c8d-9e0f-1111-2222-333344455566', 'test tournament BYE', null);


-- INSERT statements for the 'player' table
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('4e4c2f42-4b3e-4d4a-9c8d-1a2b3c4d5e6f', 'ShadowBlade', 'Liam', 'Chen', NULL),
       ('a1b2c3d4-e5f6-7890-1234-567890abcdef', 'MysticFalcon', 'Olivia', 'Kim', NULL),
       ('b3c4d5e6-f7a8-9012-3456-7890abcdef01', 'DarkPhoenix', 'Noah', 'Davis', NULL),
       ('c5d6e7f8-a9b0-1234-5678-90abcdef0123', 'SwiftArrow', 'Emma', 'Garcia', NULL),
       ('d7e8f9a0-b1c2-3456-7890-abcdef012345', 'NightCrawler', 'Sophia', 'Rodriguez', NULL),
       ('e9f0a1b2-c3d4-5678-90ab-cdef01234567', 'Ironclad', 'Jackson', 'Martinez', NULL),
       ('fbcdef01-2345-6789-0abc-def012345678', 'SilverTongue', 'Ava', 'Hernandez', NULL),
       ('01234567-89ab-cdef-0123-456789abcdef', 'DragonHeart', 'Lucas', 'Lopez', NULL),
       ('12345678-9abcdef0-1234-567890abcdef', 'LoneWolf', 'Isabella', 'Gonzalez', NULL),
       ('23456789-abcdef01-2345-678901abcdef', 'GhostRider', 'Aiden', 'Perez', NULL),
       ('34567890-bcdef012-3456-789012abcdef', 'StormBringer', 'Mia', 'Sanchez', NULL),
       ('45678901-cdef0123-4567-890123abcdef', 'Firestarter', 'Ethan', 'Ramirez', NULL),
       ('56789012-def01234-5678-901234abcdef', 'WildCard', 'Charlotte', 'Torres', NULL),
       ('67890123-ef012345-6789-012345abcdef', 'ShadowStriker', 'Mason', 'Flores', NULL),
       ('78901234-f0123456-7890-123456abcdef', 'Thunderclap', 'Amelia', 'Rivera', NULL),
       ('89012345-01234567-8901-234567abcdef', 'QuickSilver', 'Logan', 'Gomez', NULL),
       ('90123456-12345678-9012-345678abcdef', 'Sunburst', 'Harper', 'Diaz', NULL),
       ('a0b1c2d3-e4f5-6789-0123-4567890abcde', 'BladeMaster', 'Benjamin', 'Reyes', NULL),
       ('b1c2d3e4-f5a6-7890-1234-567890abcdef', 'SilentHunter', 'Evelyn', 'Cruz', NULL),
       ('c2d3e4f5-a6b7-8901-2345-67890abcdef0', 'VoidWalker', 'Alexander', 'Morales', NULL),
       ('d3e4f5a6-b7c8-9012-3456-7890abcdef01', 'DreamWeaver', 'Abigail', 'Ortiz', NULL),
       ('e4f5a6b7-c8d9-0123-4567-890abcdef012', 'Spiritwalker', 'Daniel', 'Chavez', NULL),
       ('f5a6b7c8-d9e0-1234-5678-90abcdef0123', 'Nightshade', 'Emily', 'Guerrero', NULL),
       ('a6b7c8d9-e0f1-2345-6789-0abcdef01234', 'Starfall', 'Matthew', 'Delgado', NULL),
       ('b7c8d9e0-f1a2-3456-7890-1abcdef01234', 'IronWill', 'Grace', 'Ramos', NULL),
       ('c8d9e0f1-a2b3-4567-8901-2abcdef01234', 'Stormblade', 'David', 'Vasquez', null);

-- INSERT statements for the 'tournamentplayers' table
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('5a6b7c8d-9e0f-1111-2222-333344455566', '4e4c2f42-4b3e-4d4a-9c8d-1a2b3c4d5e6f', 0.000, 'active'),
       ('5a6b7c8d-9e0f-1111-2222-333344455566', 'a1b2c3d4-e5f6-7890-1234-567890abcdef', 0.000, 'active'),
       ('5a6b7c8d-9e0f-1111-2222-333344455566', 'b3c4d5e6-f7a8-9012-3456-7890abcdef01', 0.000, 'active'),
       ('5a6b7c8d-9e0f-1111-2222-333344455566', 'c5d6e7f8-a9b0-1234-5678-90abcdef0123', 0.000, 'active'),
       ('5a6b7c8d-9e0f-1111-2222-333344455566', 'd7e8f9a0-b1c2-3456-7890-abcdef012345', 0.000, 'active'),
       ('5a6b7c8d-9e0f-1111-2222-333344455566', 'e9f0a1b2-c3d4-5678-90ab-cdef01234567', 0.000, 'active'),
       ('5a6b7c8d-9e0f-1111-2222-333344455566', 'fbcdef01-2345-6789-0abc-def012345678', 0.000, 'active'),
       ('5a6b7c8d-9e0f-1111-2222-333344455566', '01234567-89ab-cdef-0123-456789abcdef', 0.000, 'active'),
       ('5a6b7c8d-9e0f-1111-2222-333344455566', '12345678-9abcdef0-1234-567890abcdef', 0.000, 'active'),
       ('5a6b7c8d-9e0f-1111-2222-333344455566', '23456789-abcdef01-2345-678901abcdef', 0.000, 'active'),
       ('5a6b7c8d-9e0f-1111-2222-333344455566', '34567890-bcdef012-3456-789012abcdef', 0.000, 'active'),
       ('5a6b7c8d-9e0f-1111-2222-333344455566', '45678901-cdef0123-4567-890123abcdef', 0.000, 'active'),
       ('5a6b7c8d-9e0f-1111-2222-333344455566', '56789012-def01234-5678-901234abcdef', 0.000, 'active'),
       ('5a6b7c8d-9e0f-1111-2222-333344455566', '67890123-ef012345-6789-012345abcdef', 0.000, 'active'),
       ('5a6b7c8d-9e0f-1111-2222-333344455566', '78901234-f0123456-7890-123456abcdef', 0.000, 'active'),
       ('5a6b7c8d-9e0f-1111-2222-333344455566', '89012345-01234567-8901-234567abcdef', 0.000, 'active'),
       ('5a6b7c8d-9e0f-1111-2222-333344455566', '90123456-12345678-9012-345678abcdef', 0.000, 'active'),
       ('5a6b7c8d-9e0f-1111-2222-333344455566', 'a0b1c2d3-e4f5-6789-0123-4567890abcde', 0.000, 'active'),
       ('5a6b7c8d-9e0f-1111-2222-333344455566', 'b1c2d3e4-f5a6-7890-1234-567890abcdef', 0.000, 'active'),
       ('5a6b7c8d-9e0f-1111-2222-333344455566', 'c2d3e4f5-a6b7-8901-2345-67890abcdef0', 0.000, 'active'),
       ('5a6b7c8d-9e0f-1111-2222-333344455566', 'd3e4f5a6-b7c8-9012-3456-7890abcdef01', 0.000, 'active'),
       ('5a6b7c8d-9e0f-1111-2222-333344455566', 'e4f5a6b7-c8d9-0123-4567-890abcdef012', 0.000, 'active'),
       ('5a6b7c8d-9e0f-1111-2222-333344455566', 'f5a6b7c8-d9e0-1234-5678-90abcdef0123', 0.000, 'active'),
       ('5a6b7c8d-9e0f-1111-2222-333344455566', 'a6b7c8d9-e0f1-2345-6789-0abcdef01234', 0.000, 'active'),
       ('5a6b7c8d-9e0f-1111-2222-333344455566', 'b7c8d9e0-f1a2-3456-7890-1abcdef01234', 0.000, 'active'),
       ('5a6b7c8d-9e0f-1111-2222-333344455566', 'c8d9e0f1-a2b3-4567-8901-2abcdef01234', 0.000, 'active');


-- test tournament for BYE calculation according to player score
-- Tournament
INSERT INTO tournament (id, name, mode)
VALUES ('28a471f7-2adb-45ed-b9db-1376b473786d', 'BYE last score test', 'cEDH');

---

-- Players
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('00000000-0000-0000-0000-000000000001', 'Firebrand', 'Alice', 'Johnson', NULL),
       ('00000000-0000-0000-0000-000000000002', 'Nightshade', 'Bob', 'Williams', NULL),
       ('00000000-0000-0000-0000-000000000003', 'Ironclad', 'Charlie', 'Brown', NULL),
       ('00000000-0000-0000-0000-000000000004', 'Ghostwalker', 'Diana', 'Miller', NULL),
       ('00000000-0000-0000-0000-000000000005', 'Voidcaster', 'Ethan', 'Davis', NULL),
       ('00000000-0000-0000-0000-000000000006', 'Stormcaller', 'Fiona', 'Garcia', NULL),
       ('00000000-0000-0000-0000-000000000007', 'Blade Dancer', 'George', 'Rodriguez', NULL),
       ('00000000-0000-0000-0000-000000000008', 'Spiritbound', 'Hannah', 'Martinez', NULL),
       ('00000000-0000-0000-0000-000000000009', 'Dreamweaver', 'Isaac', 'Hernandez', NULL),
       ('00000000-0000-0000-0000-000000000010', 'Shadowfist', 'Jessica', 'Lopez', NULL),
       ('00000000-0000-0000-0000-000000000011', 'Silent Stalker', 'Kevin', 'Gonzalez', NULL),
       ('00000000-0000-0000-0000-000000000012', 'Thunderfury', 'Laura', 'Perez', NULL),
       ('00000000-0000-0000-0000-000000000013', 'Wildheart', 'Michael', 'Sanchez', NULL),
       ('00000000-0000-0000-0000-000000000014', 'Starfall', 'Natalie', 'Ramirez', NULL),
       ('00000000-0000-0000-0000-000000000015', 'Stoneguard', 'Oliver', 'Torres', NULL),
       ('00000000-0000-0000-0000-000000000016', 'Brightspark', 'Patricia', 'Flores', NULL),
       ('00000000-0000-0000-0000-000000000017', 'Darkwhisper', 'Quinn', 'Rivera', NULL),
       ('00000000-0000-0000-0000-000000000018', 'Swiftstrike', 'Rachel', 'Gomez', NULL),
       ('00000000-0000-0000-0000-000000000019', 'Soulrender', 'Samuel', 'Diaz', NULL),
       ('00000000-0000-0000-0000-000000000020', 'Grimlock', 'Tina', 'Reyes', NULL),
       ('00000000-0000-0000-0000-000000000021', 'Echo Weaver', 'Victor', 'Cruz', NULL),
       ('00000000-0000-0000-0000-000000000022', 'Sunstrider', 'Wendy', 'Morales', NULL),
       ('00000000-0000-0000-0000-000000000023', 'Coldsteel', 'Xavier', 'Ortiz', NULL),
       ('00000000-0000-0000-0000-000000000024', 'Bloodmoon', 'Yara', 'Chavez', NULL),
       ('00000000-0000-0000-0000-000000000025', 'Ironhide', 'Zackary', 'Guerrero', NULL),
       ('00000000-0000-0000-0000-000000000026', 'Voidbringer', 'Amy', 'Delgado', null);

---

-- Tournament Players (all 26 players linked to the tournament with score 0.000)
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('28a471f7-2adb-45ed-b9db-1376b473786d', '00000000-0000-0000-0000-000000000001', 0.000, 'active'),
       ('28a471f7-2adb-45ed-b9db-1376b473786d', '00000000-0000-0000-0000-000000000002', 0.000, 'active'),
       ('28a471f7-2adb-45ed-b9db-1376b473786d', '00000000-0000-0000-0000-000000000003', 0.000, 'active'),
       ('28a471f7-2adb-45ed-b9db-1376b473786d', '00000000-0000-0000-0000-000000000004', 0.000, 'active'),
       ('28a471f7-2adb-45ed-b9db-1376b473786d', '00000000-0000-0000-0000-000000000005', 0.000, 'active'),
       ('28a471f7-2adb-45ed-b9db-1376b473786d', '00000000-0000-0000-0000-000000000006', 0.000, 'active'),
       ('28a471f7-2adb-45ed-b9db-1376b473786d', '00000000-0000-0000-0000-000000000007', 0.000, 'active'),
       ('28a471f7-2adb-45ed-b9db-1376b473786d', '00000000-0000-0000-0000-000000000008', 0.000, 'active'),
       ('28a471f7-2adb-45ed-b9db-1376b473786d', '00000000-0000-0000-0000-000000000009', 0.000, 'active'),
       ('28a471f7-2adb-45ed-b9db-1376b473786d', '00000000-0000-0000-0000-000000000010', 0.000, 'active'),
       ('28a471f7-2adb-45ed-b9db-1376b473786d', '00000000-0000-0000-0000-000000000011', 0.000, 'active'),
       ('28a471f7-2adb-45ed-b9db-1376b473786d', '00000000-0000-0000-0000-000000000012', 0.000, 'active'),
       ('28a471f7-2adb-45ed-b9db-1376b473786d', '00000000-0000-0000-0000-000000000013', 0.000, 'active'),
       ('28a471f7-2adb-45ed-b9db-1376b473786d', '00000000-0000-0000-0000-000000000014', 0.000, 'active'),
       ('28a471f7-2adb-45ed-b9db-1376b473786d', '00000000-0000-0000-0000-000000000015', 0.000, 'active'),
       ('28a471f7-2adb-45ed-b9db-1376b473786d', '00000000-0000-0000-0000-000000000016', 0.000, 'active'),
       ('28a471f7-2adb-45ed-b9db-1376b473786d', '00000000-0000-0000-0000-000000000017', 0.000, 'active'),
       ('28a471f7-2adb-45ed-b9db-1376b473786d', '00000000-0000-0000-0000-000000000018', 0.000, 'active'),
       ('28a471f7-2adb-45ed-b9db-1376b473786d', '00000000-0000-0000-0000-000000000019', 0.000, 'active'),
       ('28a471f7-2adb-45ed-b9db-1376b473786d', '00000000-0000-0000-0000-000000000020', 0.000, 'active'),
       ('28a471f7-2adb-45ed-b9db-1376b473786d', '00000000-0000-0000-0000-000000000021', 0.000, 'active'),
       ('28a471f7-2adb-45ed-b9db-1376b473786d', '00000000-0000-0000-0000-000000000022', 0.000, 'active'),
       ('28a471f7-2adb-45ed-b9db-1376b473786d', '00000000-0000-0000-0000-000000000023', 0.000, 'active'),
       ('28a471f7-2adb-45ed-b9db-1376b473786d', '00000000-0000-0000-0000-000000000024', 0.000, 'active'),
       ('28a471f7-2adb-45ed-b9db-1376b473786d', '00000000-0000-0000-0000-000000000025', 0.000, 'active'),
       ('28a471f7-2adb-45ed-b9db-1376b473786d', '00000000-0000-0000-0000-000000000026', 0.000, 'active');

---

-- Pods for Round 1
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('10000000-0000-0000-0000-000000000001', '28a471f7-2adb-45ed-b9db-1376b473786d', 1, 1,
        'SWISS'),
       ('10000000-0000-0000-0000-000000000002', '28a471f7-2adb-45ed-b9db-1376b473786d', 2, 1,
        'SWISS'),
       ('10000000-0000-0000-0000-000000000003', '28a471f7-2adb-45ed-b9db-1376b473786d', 3, 1,
        'SWISS'),
       ('10000000-0000-0000-0000-000000000004', '28a471f7-2adb-45ed-b9db-1376b473786d', 4, 1,
        'SWISS'),
       ('10000000-0000-0000-0000-000000000005', '28a471f7-2adb-45ed-b9db-1376b473786d', 5, 1,
        'SWISS'),
       ('10000000-0000-0000-0000-000000000006', '28a471f7-2adb-45ed-b9db-1376b473786d', 6, 1,
        'SWISS'),
       ('10000000-0000-0000-0000-000000000007', '28a471f7-2adb-45ed-b9db-1376b473786d', 7, 1,
        'SWISS');
-- Bye pod for 2 players

-- Seats for Round 1
INSERT INTO seats (pod, player, seat, result)
VALUES ('10000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000001', 1, 'WIN'),
       ('10000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000002', 2, 'LOSS'),
       ('10000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000003', 3, 'LOSS'),
       ('10000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000004', 4, 'LOSS'),

       ('10000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000005', 1, 'WIN'),
       ('10000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000006', 2, 'LOSS'),
       ('10000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000007', 3, 'LOSS'),
       ('10000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000008', 4, 'LOSS'),

       ('10000000-0000-0000-0000-000000000003', '00000000-0000-0000-0000-000000000009', 1, 'WIN'),
       ('10000000-0000-0000-0000-000000000003', '00000000-0000-0000-0000-000000000010', 2, 'LOSS'),
       ('10000000-0000-0000-0000-000000000003', '00000000-0000-0000-0000-000000000011', 3, 'LOSS'),
       ('10000000-0000-0000-0000-000000000003', '00000000-0000-0000-0000-000000000012', 4, 'LOSS'),

       ('10000000-0000-0000-0000-000000000004', '00000000-0000-0000-0000-000000000013', 1, 'WIN'),
       ('10000000-0000-0000-0000-000000000004', '00000000-0000-0000-0000-000000000014', 2, 'LOSS'),
       ('10000000-0000-0000-0000-000000000004', '00000000-0000-0000-0000-000000000015', 3, 'LOSS'),
       ('10000000-0000-0000-0000-000000000004', '00000000-0000-0000-0000-000000000016', 4, 'LOSS'),

       ('10000000-0000-0000-0000-000000000005', '00000000-0000-0000-0000-000000000017', 1, 'WIN'),
       ('10000000-0000-0000-0000-000000000005', '00000000-0000-0000-0000-000000000018', 2, 'LOSS'),
       ('10000000-0000-0000-0000-000000000005', '00000000-0000-0000-0000-000000000019', 3, 'LOSS'),
       ('10000000-0000-0000-0000-000000000005', '00000000-0000-0000-0000-000000000020', 4, 'LOSS'),

       ('10000000-0000-0000-0000-000000000006', '00000000-0000-0000-0000-000000000021', 1, 'WIN'),
       ('10000000-0000-0000-0000-000000000006', '00000000-0000-0000-0000-000000000022', 2, 'LOSS'),
       ('10000000-0000-0000-0000-000000000006', '00000000-0000-0000-0000-000000000023', 3, 'LOSS'),
       ('10000000-0000-0000-0000-000000000006', '00000000-0000-0000-0000-000000000024', 4, 'LOSS'),

       ('10000000-0000-0000-0000-000000000007', '00000000-0000-0000-0000-000000000025', 1, 'BYE'),
       ('10000000-0000-0000-0000-000000000007', '00000000-0000-0000-0000-000000000026', 2, 'BYE');

---

-- Pods for Round 2
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('20000000-0000-0000-0000-000000000001', '28a471f7-2adb-45ed-b9db-1376b473786d', 1, 2,
        'SWISS'),
       ('20000000-0000-0000-0000-000000000002', '28a471f7-2adb-45ed-b9db-1376b473786d', 2, 2,
        'SWISS'),
       ('20000000-0000-0000-0000-000000000003', '28a471f7-2adb-45ed-b9db-1376b473786d', 3, 2,
        'SWISS'),
       ('20000000-0000-0000-0000-000000000004', '28a471f7-2adb-45ed-b9db-1376b473786d', 4, 2,
        'SWISS'),
       ('20000000-0000-0000-0000-000000000005', '28a471f7-2adb-45ed-b9db-1376b473786d', 5, 2,
        'SWISS'),
       ('20000000-0000-0000-0000-000000000006', '28a471f7-2adb-45ed-b9db-1376b473786d', 6, 2,
        'SWISS'),
       ('20000000-0000-0000-0000-000000000007', '28a471f7-2adb-45ed-b9db-1376b473786d', 7, 2,
        'SWISS');

-- Seats for Round 2
INSERT INTO seats (pod, player, seat, result)
VALUES ('20000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000001', 1, 'LOSS'),
       ('20000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000002', 2, 'WIN'),
       ('20000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000003', 3, 'LOSS'),
       ('20000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000004', 4, 'LOSS'),

       ('20000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000005', 1, 'LOSS'),
       ('20000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000006', 2, 'WIN'),
       ('20000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000007', 3, 'LOSS'),
       ('20000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000008', 4, 'LOSS'),

       ('20000000-0000-0000-0000-000000000003', '00000000-0000-0000-0000-000000000009', 1, 'LOSS'),
       ('20000000-0000-0000-0000-000000000003', '00000000-0000-0000-0000-000000000010', 2, 'WIN'),
       ('20000000-0000-0000-0000-000000000003', '00000000-0000-0000-0000-000000000011', 3, 'LOSS'),
       ('20000000-0000-0000-0000-000000000003', '00000000-0000-0000-0000-000000000012', 4, 'LOSS'),

       ('20000000-0000-0000-0000-000000000004', '00000000-0000-0000-0000-000000000013', 1, 'LOSS'),
       ('20000000-0000-0000-0000-000000000004', '00000000-0000-0000-0000-000000000014', 2, 'WIN'),
       ('20000000-0000-0000-0000-000000000004', '00000000-0000-0000-0000-000000000015', 3, 'LOSS'),
       ('20000000-0000-0000-0000-000000000004', '00000000-0000-0000-0000-000000000016', 4, 'LOSS'),

       ('20000000-0000-0000-0000-000000000005', '00000000-0000-0000-0000-000000000017', 1, 'LOSS'),
       ('20000000-0000-0000-0000-000000000005', '00000000-0000-0000-0000-000000000018', 2, 'WIN'),
       ('20000000-0000-0000-0000-000000000005', '00000000-0000-0000-0000-000000000019', 3, 'LOSS'),
       ('20000000-0000-0000-0000-000000000005', '00000000-0000-0000-0000-000000000020', 4, 'LOSS'),

       ('20000000-0000-0000-0000-000000000006', '00000000-0000-0000-0000-000000000021', 1, 'LOSS'),
       ('20000000-0000-0000-0000-000000000006', '00000000-0000-0000-0000-000000000022', 2, 'WIN'),
       ('20000000-0000-0000-0000-000000000006', '00000000-0000-0000-0000-000000000023', 3, 'LOSS'),
       ('20000000-0000-0000-0000-000000000006', '00000000-0000-0000-0000-000000000024', 4, 'LOSS'),

       ('20000000-0000-0000-0000-000000000007', '00000000-0000-0000-0000-000000000025', 1, 'BYE'),
       ('20000000-0000-0000-0000-000000000007', '00000000-0000-0000-0000-000000000026', 2, 'BYE');

---

-- test tournament BallonCon 4 anonymized (for score calculation)
-- tournament
INSERT INTO tournament (id, name, mode)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'BallonCon cEDH Test', null);

--player
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('c3f1fdda-ae40-4469-b9a8-b73445494455', 'PlayerOne', 'John', 'Smith', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('6bd2e618-fa23-4c19-a98e-90228a705db0', 'GamerTwo', 'Jane', 'Johnson', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('5e4e82dd-85de-4e2c-972d-e2f2f50d5473', 'Phoenix', 'Alex', 'Williams', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('f380fc01-efa8-4d13-88da-c5457cb4c6f7', 'Shadow', 'Sarah', 'Brown', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('728a96e0-b09b-4b5e-97e0-64243b9e3c80', 'DragonSlayer', 'Michael', 'Jones', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('3a7e92c6-6aff-4fca-b682-fca92750b4dd', 'NightHawk', 'Emily', 'Miller', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('1eec196f-eaff-42d1-8ef0-94372b6e9858', 'StarDust', 'David', 'Davis', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('38fba32b-c6c4-40e8-8bf7-b84eea7b74e4', 'WildCard', 'Olivia', 'Garcia', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('d5b0c609-7d69-4ca8-99f7-6a8b4366c61c', 'IronMan', 'Daniel', 'Rodriguez', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('b6190ed6-28a8-415e-bd9d-0a944444cfe0', 'StormBreaker', 'Sophia', 'Wilson', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('ea75fc9b-276b-4826-b4a1-645ad3f1cf29', 'Blaze', 'James', 'Martinez', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('e870d2b8-21bf-46d8-b7c9-d2cd27b693d5', 'DarkKnight', 'Ava', 'Anderson', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('c9153faa-f437-4b84-bbdf-464d26daac3e', 'Speedster', 'Robert', 'Taylor', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('0d88cae6-a3f8-4683-8ba8-8776b99bb74c', 'EagleEye', 'Mia', 'Thomas', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('63aebcf4-a90f-4e53-838f-a9f2bff0276d', 'Spectre', 'William', 'Hernandez', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('1002e89b-92ab-4808-8369-4482e2d61301', 'Rogue', 'Isabella', 'Moore', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('5ff28ef1-aa76-46f5-bd07-1e1bc62af078', 'Viper', 'Joseph', 'Martin', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('8ec67b91-0c7b-4555-887a-bd26b285fc51', 'Maverick', 'Charlotte', 'Jackson', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('c5ad4108-96a6-40c4-8706-8e14ccc39586', 'Titan', 'Charles', 'Thompson', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('3b037042-af74-4e80-b72e-f9d12a026f8c', 'Guardian', 'Amelia', 'White', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('a44fcd68-21a6-4b29-a68e-287191a07cc2', 'Sentinel', 'Thomas', 'Lopez', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('e64bd383-f074-4a86-b6ed-2e00ca12fb53', 'Nomad', 'Harper', 'Lee', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('34747c04-b5b3-4fb5-a54a-5fafe4697d64', 'Wanderer', 'Christopher', 'Gonzalez', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('78c5c822-6bc7-4f0e-9f55-f765e0230cb7', 'Oracle', 'Evelyn', 'Harris', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('eb08414f-1ca9-4cd0-8673-32dc7bc45722', 'Scout', 'Matthew', 'Clark', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('99dc6562-144c-4439-a0bf-a0dd70673637', 'Pathfinder', 'Abigail', 'Lewis', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('c19da3cb-dcf2-4f1c-9596-a2a58f9e6fc5', 'Navigator', 'Andrew', 'Robinson', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('4e266f55-6a10-470c-8b97-f91e39468905', 'Trailblazer', 'Ella', 'Walker', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('006f76da-e6da-4e78-894b-ccf094d333f6', 'Explorer', 'Anthony', 'Perez', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('45506cf3-5ef9-402c-808f-d490ce4cd97b', 'Voyager', 'Scarlett', 'Hall', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('21a30c65-0783-468d-8727-398ecf4dd52e', 'Pioneer', 'Paul', 'Young', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('2c8f0ee2-e25d-4774-9479-7bcc035526d3', 'Seeker', 'Grace', 'Allen', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('42a367f9-d235-431c-8de0-25bbea4bca94', 'Dreamer', 'Mark', 'Sanchez', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('0cecfa45-7720-4341-acea-4b070a09f681', 'Innovator', 'Chloe', 'Wright', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('74de865d-6ef9-496b-bbd1-bf59b4a77293', 'Visionary', 'Donald', 'King', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('500c9705-e66f-403a-b43f-e12b18427e08', 'Architect', 'Victoria', 'Scott', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('7af67bc2-d6b1-470b-8d25-1eb013a5d119', 'Builder', 'Steven', 'Green', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('1595c0a0-78ba-4ae7-9a14-b925c31f31ab', 'Creator', 'Madison', 'Baker', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('f0372977-ead0-48cd-a251-f3e8627d5d21', 'Designer', 'Edward', 'Adams', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('5965face-6243-4ed1-9657-f40da460c771', 'Engineer', 'Penelope', 'Nelson', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('50277c6a-6976-40c2-ba0c-103d83e49c52', 'Technician', 'Kevin', 'Carter', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('c240f849-9bea-4a3f-87d0-fd7efdc44004', 'Analyst', 'Layla', 'Mitchell', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('55b6ec61-f6f7-4320-9b24-cd5ebd168e60', 'Strategist', 'Brian', 'Roberts', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('a2858011-156a-4c6a-95b0-219dfae3a378', 'Planner', 'Nora', 'Phillips', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('9ca21c8a-5f8f-492d-b530-87051fac3c82', 'Coordinator', 'George', 'Campbell', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('a2ebbc3a-8f4b-42fc-8a6a-cba439805e0e', 'Manager', 'Hazel', 'Parker', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('7c7ad7f1-d83c-4904-8b38-e47ec8881fad', 'Leader', 'Ronald', 'Evans', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('f33c5f43-bcae-4dd0-beba-d264b413172b', 'Commander', 'Zoe', 'Turner', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('c4f9f93e-fb1f-4d69-a1dd-9032fc7014ae', 'Captain', 'Timothy', 'Stewart', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('bf56abda-1812-47ea-86b5-0cc4eb869596', 'Chief', 'Violet', 'Morris', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('de44ff27-a589-4730-94a7-757d6aa7b116', 'Director', 'Jason', 'Rogers', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('0c143c41-eda0-4584-b01a-57d3948f4bee', 'Overseer', 'Stella', 'Reed', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('1431a2d8-24e9-40fc-b4d0-eb8637b4a9ab', 'Supervisor', 'Jeffrey', 'Cook', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('d7cf8c00-ebbf-4aab-83f5-af4e522b350a', 'Foreman', 'Ruby', 'Morgan', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('62635dd9-cabb-45ca-b4e3-55d98dc0c884', 'Head', 'Ryan', 'Bell', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('3cef6826-d934-4b3f-a4c7-8e1a1c47eb0b', 'Principal', 'Alice', 'Murphy', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('49c75108-2252-4c99-9bcf-c4e4d153ee6f', 'Master', 'Gary', 'Bailey', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('e1124234-3c6d-418e-ab97-f23a5ddd73dc', 'Grandmaster', 'Ivy', 'Rivera', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('d2e0eb87-c9b7-4c49-9571-983cc0681153', 'Prodigy', 'Nicholas', 'Cooper', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('d2efce71-199b-4f60-856f-7446babcdf45', 'Expert', 'Daisy', 'Richardson', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('8e849922-36d4-42b9-982d-cde5cb97e3df', 'Virtuoso', 'Eric', 'Cox', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('ecd5833e-6814-4727-ad9b-d7b49321a3da', 'Ace', 'Jasmine', 'Howard', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('15b3e4d7-291b-4838-9fe9-20ee036bf272', 'Champion', 'Justin', 'Ward', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('c97ab6f7-cd67-4bc2-9a7b-492e05c6b746', 'Winner', 'Rosie', 'Torres', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('7656bd49-6cc9-41d3-8dc1-05152446b2bf', 'Victor', 'Scott', 'Peterson', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('9b4f2efb-675e-4ace-91cb-b9a2f9a4eadd', 'Conqueror', 'Willow', 'Gray', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('acbb8189-d9ab-411b-b549-4df8032942a2', 'Hero', 'Frank', 'Ramirez', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('12814d53-fab4-4803-b031-7e6f1a6a545a', 'Legend', 'Poppy', 'James', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('369310a4-c89f-498f-a083-5dfb5aa7dabe', 'Myth', 'Brenda', 'Watson', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('5b4f12e1-7556-4049-bdad-a47225882137', 'Oracle', 'Leo', 'Brooks', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('192439d0-334b-4476-a707-82eb7a3140cd', 'Wizard', 'Millie', 'Kelly', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('95856529-983f-48bf-99a5-0bcbe9e39439', 'Sorcerer', 'Peter', 'Sanders', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('d4cb537d-bfad-4740-9c75-2003275addd4', 'Enchanter', 'Sophie', 'Price', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('0b322a15-4881-4ec0-ad05-7d1e99ace392', 'Mage', 'Roger', 'Bennett', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('706f3960-9ba2-468e-bfbd-91d4a7ef5250', 'Alchemist', 'Luna', 'Wood', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('16ff8065-871d-40ab-9c8b-7997ff8e07d8', 'Mystic', 'Gerald', 'Barnes', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('3f65384c-f9c3-414f-90f3-a1f55d3a34c8', 'Seer', 'Piper', 'Ross', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('66aac8d2-fe46-465a-a5c0-00433b47a6f0', 'Prophet', 'Dennis', 'Henderson', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('954902cb-d99f-4b06-92dc-c40427bb380f', 'Sage', 'Ruby', 'Coleman', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('51353bae-b739-4fe2-94a8-d66dd2759a45', 'Guru', 'Walter', 'Jenkins', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('eba4c852-3fe8-424a-b284-b94a837a191f', 'Mentor', 'Hannah', 'Perry', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('d2828084-86fc-49b8-bdc7-ad822e599ae9', 'Guide', 'Louis', 'Powell', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('0b0b69d5-8762-4973-b08e-7926e37e173e', 'Teacher', 'Grace', 'Long', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('6de4807e-73df-4ddd-9500-f66cd694f807', 'Professor', 'Arthur', 'Patterson', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('0d2dc979-2a3c-4604-ab17-d498960ca2a8', 'Doctor', 'Eleanor', 'Hughes', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('1ebab4f3-61f4-47c4-919b-e5d50812c64d', 'Scholar', 'Henry', 'Flores', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('435b02d2-1322-48b3-9003-9ad71f4b989e', 'Student', 'Clara', 'Washington', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('650c91ae-e5fc-4270-b444-287fb9411ae2', 'Apprentice', 'Harry', 'Butler', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('195a26cd-d70c-4cac-a924-b3baab6c886d', 'Novice', 'Audrey', 'Simmons', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('28cd021e-5e43-489f-935f-f916dfd1c7d7', 'Beginner', 'Jack', 'Foster', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('d01b07dd-99dc-4b9f-8874-6c801b2ab28f', 'Rookie', 'Lucy', 'Gonzales', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('79e292e5-26f9-4cbd-b791-aee322fcf5e4', 'Freshman', 'Samuel', 'Bryant', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('3f89032f-c45e-44d8-947c-20d822a12ea4', 'Sophomore', 'Penelope', 'Russell', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('48861d2d-ba57-4aff-8e94-e7b4814d0bbd', 'Junior', 'Patrick', 'Griffin', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('ee571349-bdc7-4688-b4fa-7aff2493a979', 'Senior', 'Hannah', 'Diaz', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('c8cf4eb9-1267-489c-88ae-88d95d061954', 'Veteran', 'Oliver', 'Hayes', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('d01532a1-b05d-4cda-a092-2c51809aa7ee', 'Elder', 'Sophie', 'Myers', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('f4e3a4e1-58a7-4e48-8a69-0969160eade6', 'Ancestor', 'Leo', 'Ford', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('7c45cd3c-7707-4e98-b1ef-99712c54944a', 'Founder', 'Mia', 'Hamilton', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('5875eea0-e342-48dd-99f0-759114c4a2f9', 'Pioneer', 'Noah', 'Graham', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('f2243a7d-44d6-48a6-a9f3-9aeb1d245bbe', 'Discoverer', 'Amelia', 'Sullivan', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('2cb5667d-d717-4503-a628-46e97e5fa77b', 'Inventor', 'Liam', 'Wallace', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('f6b6e952-c020-4baa-b1ea-2c7280d7fbb0', 'Innovator', 'Charlotte', 'Woods', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('ccf04c01-be55-4ab7-9765-f85ec0549895', 'Creator', 'Ethan', 'Cole', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('e8e30811-1dd7-4708-a87c-916f7e755188', 'Architect', 'Olivia', 'West', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('a1be0e4d-309f-404e-82e2-c147b237216c', 'Engineer', 'Lucas', 'Jordan', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('882f45d6-b387-4461-a50a-4b01876c13e1', 'Builder', 'Ava', 'Owens', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('c3253fa7-c17c-4574-b9ac-122dcb685e25', 'Developer', 'Mason', 'Reynolds', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('c333179b-4b96-4b90-95be-fb78072b695b', 'Coder', 'Sophia', 'Fisher', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('18c13825-b378-4f46-bcee-1eaae8d3d276', 'Programmer', 'Logan', 'Ellis', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('4d4cdcf9-3aed-4a97-8fc5-37d921e15a8b', 'Hacker', 'Isabella', 'Harrison', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('4b5b625a-b5d6-4f73-bcb8-df0581dfdd8f', 'Cyber', 'Elijah', 'Gibson', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('7a074b5d-f3b8-42fe-875a-ab123e025daf', 'Digital', 'Mia', 'Mcdonald', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('e14dddec-0f85-46fb-8de2-7faf30ed2654', 'Virtual', 'James', 'Cruz', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('1af0dd37-5b10-4523-ac61-c9d4ca7ff194', 'Online', 'Harper', 'Ortiz', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('fa198306-4d98-4d10-b3ca-633e69f14b9e', 'Netizen', 'Benjamin', 'Ruiz', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('9260843f-6a9b-4c0b-99ad-7bd6e13c222e', 'Webmaster', 'Evelyn', 'Palmer', null);
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('7506e609-84b0-4016-966b-14e4ec647033', 'Admin', 'William', 'Kim', null);

-- tournamentplayers
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'c3f1fdda-ae40-4469-b9a8-b73445494455', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '6bd2e618-fa23-4c19-a98e-90228a705db0', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '5e4e82dd-85de-4e2c-972d-e2f2f50d5473', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'f380fc01-efa8-4d13-88da-c5457cb4c6f7', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '728a96e0-b09b-4b5e-97e0-64243b9e3c80', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '3a7e92c6-6aff-4fca-b682-fca92750b4dd', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '1eec196f-eaff-42d1-8ef0-94372b6e9858', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '38fba32b-c6c4-40e8-8bf7-b84eea7b74e4', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'd5b0c609-7d69-4ca8-99f7-6a8b4366c61c', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'b6190ed6-28a8-415e-bd9d-0a944444cfe0', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'ea75fc9b-276b-4826-b4a1-645ad3f1cf29', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'e870d2b8-21bf-46d8-b7c9-d2cd27b693d5', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'c9153faa-f437-4b84-bbdf-464d26daac3e', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '0d88cae6-a3f8-4683-8ba8-8776b99bb74c', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '63aebcf4-a90f-4e53-838f-a9f2bff0276d', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '1002e89b-92ab-4808-8369-4482e2d61301', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '5ff28ef1-aa76-46f5-bd07-1e1bc62af078', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '8ec67b91-0c7b-4555-887a-bd26b285fc51', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'c5ad4108-96a6-40c4-8706-8e14ccc39586', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '3b037042-af74-4e80-b72e-f9d12a026f8c', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'a44fcd68-21a6-4b29-a68e-287191a07cc2', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'e64bd383-f074-4a86-b6ed-2e00ca12fb53', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '34747c04-b5b3-4fb5-a54a-5fafe4697d64', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '78c5c822-6bc7-4f0e-9f55-f765e0230cb7', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'eb08414f-1ca9-4cd0-8673-32dc7bc45722', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '99dc6562-144c-4439-a0bf-a0dd70673637', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'c19da3cb-dcf2-4f1c-9596-a2a58f9e6fc5', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '4e266f55-6a10-470c-8b97-f91e39468905', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '006f76da-e6da-4e78-894b-ccf094d333f6', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '45506cf3-5ef9-402c-808f-d490ce4cd97b', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '21a30c65-0783-468d-8727-398ecf4dd52e', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '2c8f0ee2-e25d-4774-9479-7bcc035526d3', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '42a367f9-d235-431c-8de0-25bbea4bca94', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '0cecfa45-7720-4341-acea-4b070a09f681', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '74de865d-6ef9-496b-bbd1-bf59b4a77293', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '500c9705-e66f-403a-b43f-e12b18427e08', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '7af67bc2-d6b1-470b-8d25-1eb013a5d119', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '1595c0a0-78ba-4ae7-9a14-b925c31f31ab', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'f0372977-ead0-48cd-a251-f3e8627d5d21', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '5965face-6243-4ed1-9657-f40da460c771', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '50277c6a-6976-40c2-ba0c-103d83e49c52', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'c240f849-9bea-4a3f-87d0-fd7efdc44004', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '55b6ec61-f6f7-4320-9b24-cd5ebd168e60', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'a2858011-156a-4c6a-95b0-219dfae3a378', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '9ca21c8a-5f8f-492d-b530-87051fac3c82', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'a2ebbc3a-8f4b-42fc-8a6a-cba439805e0e', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '7c7ad7f1-d83c-4904-8b38-e47ec8881fad', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'f33c5f43-bcae-4dd0-beba-d264b413172b', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'c4f9f93e-fb1f-4d69-a1dd-9032fc7014ae', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'bf56abda-1812-47ea-86b5-0cc4eb869596', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'de44ff27-a589-4730-94a7-757d6aa7b116', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '0c143c41-eda0-4584-b01a-57d3948f4bee', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '1431a2d8-24e9-40fc-b4d0-eb8637b4a9ab', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'd7cf8c00-ebbf-4aab-83f5-af4e522b350a', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '62635dd9-cabb-45ca-b4e3-55d98dc0c884', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '3cef6826-d934-4b3f-a4c7-8e1a1c47eb0b', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '49c75108-2252-4c99-9bcf-c4e4d153ee6f', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'e1124234-3c6d-418e-ab97-f23a5ddd73dc', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'd2e0eb87-c9b7-4c49-9571-983cc0681153', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'd2efce71-199b-4f60-856f-7446babcdf45', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '8e849922-36d4-42b9-982d-cde5cb97e3df', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'ecd5833e-6814-4727-ad9b-d7b49321a3da', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '15b3e4d7-291b-4838-9fe9-20ee036bf272', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'c97ab6f7-cd67-4bc2-9a7b-492e05c6b746', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '7656bd49-6cc9-41d3-8dc1-05152446b2bf', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '9b4f2efb-675e-4ace-91cb-b9a2f9a4eadd', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'acbb8189-d9ab-411b-b549-4df8032942a2', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '12814d53-fab4-4803-b031-7e6f1a6a545a', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '369310a4-c89f-498f-a083-5dfb5aa7dabe', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '5b4f12e1-7556-4049-bdad-a47225882137', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '192439d0-334b-4476-a707-82eb7a3140cd', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '95856529-983f-48bf-99a5-0bcbe9e39439', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'd4cb537d-bfad-4740-9c75-2003275addd4', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '0b322a15-4881-4ec0-ad05-7d1e99ace392', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '706f3960-9ba2-468e-bfbd-91d4a7ef5250', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '16ff8065-871d-40ab-9c8b-7997ff8e07d8', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '3f65384c-f9c3-414f-90f3-a1f55d3a34c8', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '66aac8d2-fe46-465a-a5c0-00433b47a6f0', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '954902cb-d99f-4b06-92dc-c40427bb380f', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '51353bae-b739-4fe2-94a8-d66dd2759a45', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'eba4c852-3fe8-424a-b284-b94a837a191f', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'd2828084-86fc-49b8-bdc7-ad822e599ae9', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '0b0b69d5-8762-4973-b08e-7926e37e173e', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '6de4807e-73df-4ddd-9500-f66cd694f807', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '0d2dc979-2a3c-4604-ab17-d498960ca2a8', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '1ebab4f3-61f4-47c4-919b-e5d50812c64d', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '435b02d2-1322-48b3-9003-9ad71f4b989e', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '650c91ae-e5fc-4270-b444-287fb9411ae2', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '195a26cd-d70c-4cac-a924-b3baab6c886d', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '28cd021e-5e43-489f-935f-f916dfd1c7d7', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'd01b07dd-99dc-4b9f-8874-6c801b2ab28f', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '79e292e5-26f9-4cbd-b791-aee322fcf5e4', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '3f89032f-c45e-44d8-947c-20d822a12ea4', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '48861d2d-ba57-4aff-8e94-e7b4814d0bbd', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'ee571349-bdc7-4688-b4fa-7aff2493a979', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'c8cf4eb9-1267-489c-88ae-88d95d061954', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'd01532a1-b05d-4cda-a092-2c51809aa7ee', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'f4e3a4e1-58a7-4e48-8a69-0969160eade6', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '7c45cd3c-7707-4e98-b1ef-99712c54944a', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '5875eea0-e342-48dd-99f0-759114c4a2f9', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'f2243a7d-44d6-48a6-a9f3-9aeb1d245bbe', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '2cb5667d-d717-4503-a628-46e97e5fa77b', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'f6b6e952-c020-4baa-b1ea-2c7280d7fbb0', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'ccf04c01-be55-4ab7-9765-f85ec0549895', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'e8e30811-1dd7-4708-a87c-916f7e755188', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'a1be0e4d-309f-404e-82e2-c147b237216c', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '882f45d6-b387-4461-a50a-4b01876c13e1', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'c3253fa7-c17c-4574-b9ac-122dcb685e25', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'c333179b-4b96-4b90-95be-fb78072b695b', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '18c13825-b378-4f46-bcee-1eaae8d3d276', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '4d4cdcf9-3aed-4a97-8fc5-37d921e15a8b', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '4b5b625a-b5d6-4f73-bcb8-df0581dfdd8f', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '7a074b5d-f3b8-42fe-875a-ab123e025daf', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'e14dddec-0f85-46fb-8de2-7faf30ed2654', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '1af0dd37-5b10-4523-ac61-c9d4ca7ff194', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', 'fa198306-4d98-4d10-b3ca-633e69f14b9e', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '9260843f-6a9b-4c0b-99ad-7bd6e13c222e', null, 'active');
INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('7addec25-9af0-452f-9e01-6481892e545d', '7506e609-84b0-4016-966b-14e4ec647033', null, 'active');

-- pod
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('a8d4215b-e730-423a-b217-6ba0095e8b8f', '7addec25-9af0-452f-9e01-6481892e545d', 1, 1,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('84ed591f-17f2-4565-ad19-71b7bf56ee01', '7addec25-9af0-452f-9e01-6481892e545d', 1, 2,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('099b515d-18e1-4271-9360-3df4898ff1aa', '7addec25-9af0-452f-9e01-6481892e545d', 1, 3,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('be2d9392-5a00-40b4-b270-7442c6eb5044', '7addec25-9af0-452f-9e01-6481892e545d', 1, 4,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('7edd80c4-c32e-471e-8fce-9c6384ecc45a', '7addec25-9af0-452f-9e01-6481892e545d', 1, 5,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('92dc6d34-141d-45ca-82a2-11f054d3b6ca', '7addec25-9af0-452f-9e01-6481892e545d', 2, 1,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('d93f6148-b909-45a6-8b6c-2794ced63994', '7addec25-9af0-452f-9e01-6481892e545d', 2, 2,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('eb445665-cb90-4969-a94b-4dd28e4fb3ec', '7addec25-9af0-452f-9e01-6481892e545d', 2, 3,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('df494015-ac92-4a78-87db-bc5a771e28d6', '7addec25-9af0-452f-9e01-6481892e545d', 2, 4,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('719377ff-888f-4756-8a43-157a4708caf4', '7addec25-9af0-452f-9e01-6481892e545d', 2, 5,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('8edb7971-9269-42b4-8e9e-ee40514146f4', '7addec25-9af0-452f-9e01-6481892e545d', 3, 1,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('4e0a7c35-446a-4772-a5ad-92025fd76646', '7addec25-9af0-452f-9e01-6481892e545d', 3, 2,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('58ba1d1d-724d-4ba4-9c76-bd739abb87a3', '7addec25-9af0-452f-9e01-6481892e545d', 3, 3,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('f76d8903-42f1-4270-9875-a2dc712da8f9', '7addec25-9af0-452f-9e01-6481892e545d', 3, 4,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('67aad726-422c-4b2c-ad6b-6d9d5542d2c7', '7addec25-9af0-452f-9e01-6481892e545d', 3, 5,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('7e52edd0-81a8-4261-aed8-3c9396683514', '7addec25-9af0-452f-9e01-6481892e545d', 4, 1,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('3a4b3df9-9505-4552-a66f-8f9335139657', '7addec25-9af0-452f-9e01-6481892e545d', 4, 2,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('89856f36-9ad6-408c-a5be-081764ba0239', '7addec25-9af0-452f-9e01-6481892e545d', 4, 3,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('46da8bd9-e652-48c5-9238-a65974bcc565', '7addec25-9af0-452f-9e01-6481892e545d', 4, 4,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('3d411981-d7a1-4b1e-b4cb-1351d9f6ed02', '7addec25-9af0-452f-9e01-6481892e545d', 4, 5,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('0d68c503-1890-431f-bf8d-f4f2dbfe5bce', '7addec25-9af0-452f-9e01-6481892e545d', 5, 1,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('90cf4c75-742a-4c99-9e0b-030f482813dc', '7addec25-9af0-452f-9e01-6481892e545d', 5, 2,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('4b8063d5-c86a-4837-86ad-e99ad4bab9aa', '7addec25-9af0-452f-9e01-6481892e545d', 5, 3,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('ff14301b-8455-440c-8954-df3a53b51797', '7addec25-9af0-452f-9e01-6481892e545d', 5, 4,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('afa957b3-0d77-408c-9986-1ef0be2db829', '7addec25-9af0-452f-9e01-6481892e545d', 5, 5,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('eeee28a7-5e82-4515-8aa8-9ceca72fc1b4', '7addec25-9af0-452f-9e01-6481892e545d', 6, 1,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('4d9f9f41-f753-45ae-a7c9-2a76a53d9f83', '7addec25-9af0-452f-9e01-6481892e545d', 6, 2,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('aad8cc1c-8e4e-4550-87c1-b4fe587ecf0e', '7addec25-9af0-452f-9e01-6481892e545d', 6, 3,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('dbe9e55c-e47c-4085-b53c-e7a2fe32afde', '7addec25-9af0-452f-9e01-6481892e545d', 6, 4,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('aac32864-701a-4c51-b15b-55157dfe429a', '7addec25-9af0-452f-9e01-6481892e545d', 6, 5,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('3ea6ec92-274f-48b4-ad80-bbdf911219c2', '7addec25-9af0-452f-9e01-6481892e545d', 7, 1,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('b7077126-99d1-4322-ac79-e83326b166b8', '7addec25-9af0-452f-9e01-6481892e545d', 7, 2,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('46e9d069-53a8-4af4-981a-e0b2ec2ffc43', '7addec25-9af0-452f-9e01-6481892e545d', 7, 3,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('d4c5abbc-a997-444d-af99-02c527d8bb7d', '7addec25-9af0-452f-9e01-6481892e545d', 7, 4,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('bd56e03b-87e9-4a7d-94c3-6a2aa23b5f42', '7addec25-9af0-452f-9e01-6481892e545d', 7, 5,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('f41ab217-1068-47ff-b283-ba94a3e2372a', '7addec25-9af0-452f-9e01-6481892e545d', 8, 1,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('9e277191-09dd-41bc-bdbb-b8eeba471ead', '7addec25-9af0-452f-9e01-6481892e545d', 8, 2,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('2798854a-9e7f-442d-9f1e-6dbf8d45a234', '7addec25-9af0-452f-9e01-6481892e545d', 8, 3,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('666624a2-0d8c-4a4b-94b3-0ccb723db09f', '7addec25-9af0-452f-9e01-6481892e545d', 8, 4,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('ca93b060-777a-4b5b-bb34-5069a076643f', '7addec25-9af0-452f-9e01-6481892e545d', 8, 5,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('879bf9d3-7c8c-422f-b847-715f24a47d8c', '7addec25-9af0-452f-9e01-6481892e545d', 9, 1,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('7310b6be-da38-4fd7-a5fb-7f87ac4debad', '7addec25-9af0-452f-9e01-6481892e545d', 9, 2,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('3e2b83c2-8729-4012-849f-9a1939289f5e', '7addec25-9af0-452f-9e01-6481892e545d', 9, 3,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('5b320021-ebba-4f17-94ec-1077a4b83e9e', '7addec25-9af0-452f-9e01-6481892e545d', 9, 4,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('27f0f678-5324-44db-b188-ad05d1aee70f', '7addec25-9af0-452f-9e01-6481892e545d', 9, 5,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('e962bc06-5ad0-4415-8b80-9758f92b8d3c', '7addec25-9af0-452f-9e01-6481892e545d', 10, 1,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('9c8dc43b-e9f0-40bf-8e76-8aba95549610', '7addec25-9af0-452f-9e01-6481892e545d', 10, 2,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('733556cb-018c-4860-8889-a092968fb5ce', '7addec25-9af0-452f-9e01-6481892e545d', 10, 3,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('11d06991-6a64-4a09-a53a-c0abd6340eab', '7addec25-9af0-452f-9e01-6481892e545d', 10, 4,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('17e1ce03-f4fd-48d5-a33a-3a5291543bb1', '7addec25-9af0-452f-9e01-6481892e545d', 10, 5,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('3327e353-b734-496b-a7d2-765b6db14fff', '7addec25-9af0-452f-9e01-6481892e545d', 11, 1,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('904aa049-642e-4fce-b3cd-66fa32f48582', '7addec25-9af0-452f-9e01-6481892e545d', 11, 2,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('14554f89-118c-46fd-8aab-c9a88160856e', '7addec25-9af0-452f-9e01-6481892e545d', 11, 3,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('c40c099d-73c2-4030-84b6-fe88c9ac2c4f', '7addec25-9af0-452f-9e01-6481892e545d', 11, 4,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('3db1edf5-1da0-4afc-92de-24bd518bdf11', '7addec25-9af0-452f-9e01-6481892e545d', 11, 5,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('8c1ae7c2-5e5a-4ffc-b3d4-056c490ddd6d', '7addec25-9af0-452f-9e01-6481892e545d', 12, 1,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('018d3d90-d2da-4546-a2df-1429b7c862de', '7addec25-9af0-452f-9e01-6481892e545d', 12, 2,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('ed044702-a690-42ac-a3dc-25f2f9bea056', '7addec25-9af0-452f-9e01-6481892e545d', 12, 3,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('e8d8ad71-1de2-4034-a137-17bed9af2f3e', '7addec25-9af0-452f-9e01-6481892e545d', 12, 4,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('53f70275-0f92-4013-8c91-b288503094d8', '7addec25-9af0-452f-9e01-6481892e545d', 12, 5,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('216827f7-7efb-4087-a514-6ac811d26999', '7addec25-9af0-452f-9e01-6481892e545d', 13, 1,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('a1a9cb76-24ae-4ca7-9839-0a8618cc2db5', '7addec25-9af0-452f-9e01-6481892e545d', 13, 2,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('95556ee4-eb88-4a06-9c23-87d19000b0b1', '7addec25-9af0-452f-9e01-6481892e545d', 13, 3,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('ef322c79-fcab-44bd-87a8-f0986e9cc4d4', '7addec25-9af0-452f-9e01-6481892e545d', 13, 4,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('be3f6d5c-5f8a-4532-9c0b-07d75f39cceb', '7addec25-9af0-452f-9e01-6481892e545d', 13, 5,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('e47471ff-e78e-43df-9793-97a57d86aaf3', '7addec25-9af0-452f-9e01-6481892e545d', 14, 1,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('3e86f83e-8336-492a-b1f6-ea6757ca4240', '7addec25-9af0-452f-9e01-6481892e545d', 14, 2,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('7a6d987a-46fc-4d2b-af4e-9f605a8f9b07', '7addec25-9af0-452f-9e01-6481892e545d', 14, 3,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('eee36163-5170-4481-817a-7addad506613', '7addec25-9af0-452f-9e01-6481892e545d', 14, 4,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('1c41098f-5667-44a4-b039-047124d899dd', '7addec25-9af0-452f-9e01-6481892e545d', 14, 5,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('c08cb1ca-67b8-4e33-813e-31ef7851f8d6', '7addec25-9af0-452f-9e01-6481892e545d', 15, 1,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('0bd905eb-cb35-4c3a-9423-91e4ff59c316', '7addec25-9af0-452f-9e01-6481892e545d', 15, 2,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('e4afa736-0d7f-4dc6-a7c9-723225b8b621', '7addec25-9af0-452f-9e01-6481892e545d', 15, 3,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('423549cd-31d7-4284-8afa-f0cf76c702bb', '7addec25-9af0-452f-9e01-6481892e545d', 15, 4,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('a0f709fd-4774-482c-a404-8a77979bb171', '7addec25-9af0-452f-9e01-6481892e545d', 15, 5,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('692d1f6a-0d37-4f6e-8b04-7ab190333bd1', '7addec25-9af0-452f-9e01-6481892e545d', 16, 1,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('c728c15f-c4d7-4528-8e2d-a29c7ff983a6', '7addec25-9af0-452f-9e01-6481892e545d', 16, 2,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('1daa182a-0f90-42f3-9d3c-a144f6349007', '7addec25-9af0-452f-9e01-6481892e545d', 16, 3,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('4124e80b-a327-4592-af13-9a8b3056483b', '7addec25-9af0-452f-9e01-6481892e545d', 16, 4,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('8e9766c6-80d4-4be4-b124-0d57ac976e49', '7addec25-9af0-452f-9e01-6481892e545d', 16, 5,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('aed4fbfd-dea5-4c8e-8b55-19b795b7e586', '7addec25-9af0-452f-9e01-6481892e545d', 17, 1,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('4ce238cc-d3de-44bb-b78a-7df66d3e15df', '7addec25-9af0-452f-9e01-6481892e545d', 17, 2,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('55f62a20-5e24-4baa-93e1-22f83356593e', '7addec25-9af0-452f-9e01-6481892e545d', 17, 3,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('f7092b76-25ea-4679-b199-d20a417e2b17', '7addec25-9af0-452f-9e01-6481892e545d', 17, 4,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('23815db4-ec03-433a-b9df-c950c5c0150c', '7addec25-9af0-452f-9e01-6481892e545d', 17, 5,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('389d1b8f-adaf-4c36-904f-f282ff056441', '7addec25-9af0-452f-9e01-6481892e545d', 18, 1,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('b35740c3-4bf1-4019-80b7-a694cc089c61', '7addec25-9af0-452f-9e01-6481892e545d', 18, 2,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('7eb3bd52-9067-42f4-970f-f0b44257b384', '7addec25-9af0-452f-9e01-6481892e545d', 18, 3,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('52879511-ee2a-495c-9b49-015e95c14796', '7addec25-9af0-452f-9e01-6481892e545d', 18, 4,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('7fe1f1ee-821f-4196-abcd-09409eaf444f', '7addec25-9af0-452f-9e01-6481892e545d', 18, 5,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('f3c31575-ed40-4505-8e08-4b4cb71e4b95', '7addec25-9af0-452f-9e01-6481892e545d', 19, 1,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('43af1060-917f-4259-844b-26c4cea4a93b', '7addec25-9af0-452f-9e01-6481892e545d', 19, 2,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('d68ea49f-d2b4-47cd-bdfb-9a5b93529be7', '7addec25-9af0-452f-9e01-6481892e545d', 19, 3,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('89bd4fd6-a46a-4613-943b-8d69153088ae', '7addec25-9af0-452f-9e01-6481892e545d', 19, 4,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('a7600464-d4a8-4d8e-a00b-e4bc8ed83491', '7addec25-9af0-452f-9e01-6481892e545d', 19, 5,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('1f6187e0-9e53-49bf-9650-608ffb62eb83', '7addec25-9af0-452f-9e01-6481892e545d', 20, 1,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('3eecacf8-63d6-4e6f-a5de-4c6ff1d7fcd6', '7addec25-9af0-452f-9e01-6481892e545d', 20, 2,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('2694bd59-b919-483b-8726-330af3f3c785', '7addec25-9af0-452f-9e01-6481892e545d', 20, 3,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('b53e59b2-8a0e-447b-bf63-6ae4ede0a1e8', '7addec25-9af0-452f-9e01-6481892e545d', 20, 4,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('4f883ac1-56e0-4ce3-94df-d6203ec42549', '7addec25-9af0-452f-9e01-6481892e545d', 20, 5,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('b122f99a-3b00-45d3-a63f-d76278d86c9e', '7addec25-9af0-452f-9e01-6481892e545d', 21, 1,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('0eb6b531-afc2-4ac6-9800-d80c691fab53', '7addec25-9af0-452f-9e01-6481892e545d', 21, 2,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('4bb11ed5-b3af-4c85-9d22-18a37474737f', '7addec25-9af0-452f-9e01-6481892e545d', 21, 3,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('f4a7531d-b273-4407-897c-fa39c931320b', '7addec25-9af0-452f-9e01-6481892e545d', 21, 4,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('c1dbb41d-a553-453f-9764-019cafbf5fd8', '7addec25-9af0-452f-9e01-6481892e545d', 21, 5,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('b8e7f83f-a0ca-47fc-8aae-75e75291f5dd', '7addec25-9af0-452f-9e01-6481892e545d', 22, 1,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('c4021894-6ddc-45b9-a59a-f48ca87c284b', '7addec25-9af0-452f-9e01-6481892e545d', 22, 2,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('7184cf1f-a46a-4a86-b07e-65770e771a16', '7addec25-9af0-452f-9e01-6481892e545d', 22, 3,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('25e29a7e-7b1e-48b5-a661-77b3b69129a7', '7addec25-9af0-452f-9e01-6481892e545d', 22, 4,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('5b5ad1f0-9462-4faa-8a34-6c4779220424', '7addec25-9af0-452f-9e01-6481892e545d', 22, 5,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('9e10528a-74be-44c8-84f7-ba4430a10ecc', '7addec25-9af0-452f-9e01-6481892e545d', 23, 1,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('6d1256e9-7b7e-42dc-8dd9-9191853cc55e', '7addec25-9af0-452f-9e01-6481892e545d', 23, 2,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('236a03ca-584e-4aab-9d8f-5f5b0422e7e1', '7addec25-9af0-452f-9e01-6481892e545d', 23, 3,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('0e5d8acc-25e9-452d-9200-6106269ff8a2', '7addec25-9af0-452f-9e01-6481892e545d', 23, 4,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('31361a5c-3986-445c-b91f-1db3b6d15b2b', '7addec25-9af0-452f-9e01-6481892e545d', 23, 5,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('14fb940d-13d3-4c67-888e-e71d1c415e2c', '7addec25-9af0-452f-9e01-6481892e545d', 24, 1,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('795dfdd5-4074-4ed4-b948-82af341b29b6', '7addec25-9af0-452f-9e01-6481892e545d', 24, 2,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('f83e6021-6903-4197-b1f1-46aa3d46148f', '7addec25-9af0-452f-9e01-6481892e545d', 24, 3,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('a406e669-9587-4485-8dbc-9a2677c9d4bf', '7addec25-9af0-452f-9e01-6481892e545d', 24, 4,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('d2423ffc-d8a8-4973-9566-7a36d05c643b', '7addec25-9af0-452f-9e01-6481892e545d', 24, 5,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('b5b79855-a05b-45ce-b187-12856997cc01', '7addec25-9af0-452f-9e01-6481892e545d', 25, 1,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('8ddf1d79-c5bc-4dfd-b8d0-5950e47eace6', '7addec25-9af0-452f-9e01-6481892e545d', 25, 2,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('8da16f46-b97f-4cf7-900e-dedfbeddaf0f', '7addec25-9af0-452f-9e01-6481892e545d', 25, 3,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('293ca5a9-22a2-449c-a4b1-ee0727cafee0', '7addec25-9af0-452f-9e01-6481892e545d', 25, 4,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('de150c54-5827-4389-b1da-977465b81cbb', '7addec25-9af0-452f-9e01-6481892e545d', 25, 5,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('1b0569c0-93b8-4a8e-a7d8-b10d830d6008', '7addec25-9af0-452f-9e01-6481892e545d', 26, 1,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('72bd6985-a3dc-4ecb-baed-78ad79bdf5e6', '7addec25-9af0-452f-9e01-6481892e545d', 26, 2,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('f3b0d5a9-5cbf-424b-b7ea-19d189b8e2bf', '7addec25-9af0-452f-9e01-6481892e545d', 26, 3,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('d7b41041-d925-4bae-bb7d-821da99b5cd9', '7addec25-9af0-452f-9e01-6481892e545d', 26, 4,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('688a7dec-bc3f-4d27-a07a-b120c1279009', '7addec25-9af0-452f-9e01-6481892e545d', 26, 5,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('e8407a2d-804f-4119-93d8-2b99e7f08574', '7addec25-9af0-452f-9e01-6481892e545d', 27, 1,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('db5407e2-f5d4-4545-af20-e91f11f6c105', '7addec25-9af0-452f-9e01-6481892e545d', 27, 2,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('92041f1c-449a-4010-a9ee-eca5a59a1150', '7addec25-9af0-452f-9e01-6481892e545d', 27, 3,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('1d7e1ba6-f1f4-4243-b00e-e115888a873a', '7addec25-9af0-452f-9e01-6481892e545d', 27, 4,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('6f418467-9ddc-4419-8661-95de3697a9fb', '7addec25-9af0-452f-9e01-6481892e545d', 27, 5,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('f5cba574-1226-4ad8-b89c-f0d47770f278', '7addec25-9af0-452f-9e01-6481892e545d', 28, 1,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('fe209090-31df-40d8-8af0-d258f77e7d38', '7addec25-9af0-452f-9e01-6481892e545d', 28, 2,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('a68282ae-ee38-48c0-94d9-e38209c51a6d', '7addec25-9af0-452f-9e01-6481892e545d', 28, 3,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('22d1a4a8-da35-4e3b-ba91-d70a38ee6db1', '7addec25-9af0-452f-9e01-6481892e545d', 28, 4,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('96b2b9d9-a63d-4c8b-9cc3-67bc4f1fb790', '7addec25-9af0-452f-9e01-6481892e545d', 29, 1,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('a80e264a-3db3-4438-b0df-dacba9dc12c8', '7addec25-9af0-452f-9e01-6481892e545d', 29, 2,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('891c8cc1-d70b-4c2e-837b-f517f735b1b6', '7addec25-9af0-452f-9e01-6481892e545d', 29, 3,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('f4ab8c23-b050-4700-b19b-08317b8ed4fa', '7addec25-9af0-452f-9e01-6481892e545d', 29, 4,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('1af5bdc2-c3d4-4c07-a8b3-df95f02238c7', '7addec25-9af0-452f-9e01-6481892e545d', 30, 1,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('fcf9d59f-74a3-49a3-bca9-e77519449c0d', '7addec25-9af0-452f-9e01-6481892e545d', 30, 2,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('ab5019dd-2bd0-48d5-b613-a0630e1f7cf5', '7addec25-9af0-452f-9e01-6481892e545d', 30, 3,
        'SWISS');
INSERT INTO pod (id, tournament_id, name, round, type)
VALUES ('ca94022c-2abb-4be7-a880-c3c04b29e208', '7addec25-9af0-452f-9e01-6481892e545d', 30, 4,
        'SWISS');


-- seat
INSERT INTO seats (pod, player, seat, result)
VALUES ('a8d4215b-e730-423a-b217-6ba0095e8b8f', '7c7ad7f1-d83c-4904-8b38-e47ec8881fad', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('a8d4215b-e730-423a-b217-6ba0095e8b8f', '882f45d6-b387-4461-a50a-4b01876c13e1', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('a8d4215b-e730-423a-b217-6ba0095e8b8f', 'd01b07dd-99dc-4b9f-8874-6c801b2ab28f', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('a8d4215b-e730-423a-b217-6ba0095e8b8f', '369310a4-c89f-498f-a083-5dfb5aa7dabe', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('92dc6d34-141d-45ca-82a2-11f054d3b6ca', '49c75108-2252-4c99-9bcf-c4e4d153ee6f', 1, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('92dc6d34-141d-45ca-82a2-11f054d3b6ca', '0d2dc979-2a3c-4604-ab17-d498960ca2a8', 2, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('92dc6d34-141d-45ca-82a2-11f054d3b6ca', '7a074b5d-f3b8-42fe-875a-ab123e025daf', 3, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('92dc6d34-141d-45ca-82a2-11f054d3b6ca', '192439d0-334b-4476-a707-82eb7a3140cd', 4, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('8edb7971-9269-42b4-8e9e-ee40514146f4', '1ebab4f3-61f4-47c4-919b-e5d50812c64d', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('8edb7971-9269-42b4-8e9e-ee40514146f4', '12814d53-fab4-4803-b031-7e6f1a6a545a', 2, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('8edb7971-9269-42b4-8e9e-ee40514146f4', '0b322a15-4881-4ec0-ad05-7d1e99ace392', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('8edb7971-9269-42b4-8e9e-ee40514146f4', 'c19da3cb-dcf2-4f1c-9596-a2a58f9e6fc5', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('7e52edd0-81a8-4261-aed8-3c9396683514', 'd2e0eb87-c9b7-4c49-9571-983cc0681153', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('7e52edd0-81a8-4261-aed8-3c9396683514', '78c5c822-6bc7-4f0e-9f55-f765e0230cb7', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('7e52edd0-81a8-4261-aed8-3c9396683514', '1595c0a0-78ba-4ae7-9a14-b925c31f31ab', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('7e52edd0-81a8-4261-aed8-3c9396683514', 'e8e30811-1dd7-4708-a87c-916f7e755188', 4, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('0d68c503-1890-431f-bf8d-f4f2dbfe5bce', '42a367f9-d235-431c-8de0-25bbea4bca94', 1, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('0d68c503-1890-431f-bf8d-f4f2dbfe5bce', 'e870d2b8-21bf-46d8-b7c9-d2cd27b693d5', 2, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('0d68c503-1890-431f-bf8d-f4f2dbfe5bce', '706f3960-9ba2-468e-bfbd-91d4a7ef5250', 3, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('0d68c503-1890-431f-bf8d-f4f2dbfe5bce', 'c8cf4eb9-1267-489c-88ae-88d95d061954', 4, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('eeee28a7-5e82-4515-8aa8-9ceca72fc1b4', '48861d2d-ba57-4aff-8e94-e7b4814d0bbd', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('eeee28a7-5e82-4515-8aa8-9ceca72fc1b4', 'd01532a1-b05d-4cda-a092-2c51809aa7ee', 2, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('eeee28a7-5e82-4515-8aa8-9ceca72fc1b4', 'a2ebbc3a-8f4b-42fc-8a6a-cba439805e0e', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('eeee28a7-5e82-4515-8aa8-9ceca72fc1b4', '650c91ae-e5fc-4270-b444-287fb9411ae2', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('3ea6ec92-274f-48b4-ad80-bbdf911219c2', '74de865d-6ef9-496b-bbd1-bf59b4a77293', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('3ea6ec92-274f-48b4-ad80-bbdf911219c2', '1431a2d8-24e9-40fc-b4d0-eb8637b4a9ab', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('3ea6ec92-274f-48b4-ad80-bbdf911219c2', '500c9705-e66f-403a-b43f-e12b18427e08', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('3ea6ec92-274f-48b4-ad80-bbdf911219c2', '7506e609-84b0-4016-966b-14e4ec647033', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('f41ab217-1068-47ff-b283-ba94a3e2372a', '6de4807e-73df-4ddd-9500-f66cd694f807', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('f41ab217-1068-47ff-b283-ba94a3e2372a', '3cef6826-d934-4b3f-a4c7-8e1a1c47eb0b', 2, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('f41ab217-1068-47ff-b283-ba94a3e2372a', '435b02d2-1322-48b3-9003-9ad71f4b989e', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('f41ab217-1068-47ff-b283-ba94a3e2372a', '0cecfa45-7720-4341-acea-4b070a09f681', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('879bf9d3-7c8c-422f-b847-715f24a47d8c', 'd2828084-86fc-49b8-bdc7-ad822e599ae9', 1, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('879bf9d3-7c8c-422f-b847-715f24a47d8c', 'e64bd383-f074-4a86-b6ed-2e00ca12fb53', 2, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('879bf9d3-7c8c-422f-b847-715f24a47d8c', 'ee571349-bdc7-4688-b4fa-7aff2493a979', 3, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('879bf9d3-7c8c-422f-b847-715f24a47d8c', 'e14dddec-0f85-46fb-8de2-7faf30ed2654', 4, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('e962bc06-5ad0-4415-8b80-9758f92b8d3c', 'd2efce71-199b-4f60-856f-7446babcdf45', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('e962bc06-5ad0-4415-8b80-9758f92b8d3c', 'f33c5f43-bcae-4dd0-beba-d264b413172b', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('e962bc06-5ad0-4415-8b80-9758f92b8d3c', '62635dd9-cabb-45ca-b4e3-55d98dc0c884', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('e962bc06-5ad0-4415-8b80-9758f92b8d3c', '55b6ec61-f6f7-4320-9b24-cd5ebd168e60', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('3327e353-b734-496b-a7d2-765b6db14fff', 'd4cb537d-bfad-4740-9c75-2003275addd4', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('3327e353-b734-496b-a7d2-765b6db14fff', '2cb5667d-d717-4503-a628-46e97e5fa77b', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('3327e353-b734-496b-a7d2-765b6db14fff', '0c143c41-eda0-4584-b01a-57d3948f4bee', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('3327e353-b734-496b-a7d2-765b6db14fff', '0d88cae6-a3f8-4683-8ba8-8776b99bb74c', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('8c1ae7c2-5e5a-4ffc-b3d4-056c490ddd6d', 'd7cf8c00-ebbf-4aab-83f5-af4e522b350a', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('8c1ae7c2-5e5a-4ffc-b3d4-056c490ddd6d', '006f76da-e6da-4e78-894b-ccf094d333f6', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('8c1ae7c2-5e5a-4ffc-b3d4-056c490ddd6d', '5ff28ef1-aa76-46f5-bd07-1e1bc62af078', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('8c1ae7c2-5e5a-4ffc-b3d4-056c490ddd6d', '1002e89b-92ab-4808-8369-4482e2d61301', 4, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('216827f7-7efb-4087-a514-6ac811d26999', 'f6b6e952-c020-4baa-b1ea-2c7280d7fbb0', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('216827f7-7efb-4087-a514-6ac811d26999', '3f89032f-c45e-44d8-947c-20d822a12ea4', 2, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('216827f7-7efb-4087-a514-6ac811d26999', '15b3e4d7-291b-4838-9fe9-20ee036bf272', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('216827f7-7efb-4087-a514-6ac811d26999', '38fba32b-c6c4-40e8-8bf7-b84eea7b74e4', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('e47471ff-e78e-43df-9793-97a57d86aaf3', 'bf56abda-1812-47ea-86b5-0cc4eb869596', 1, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('e47471ff-e78e-43df-9793-97a57d86aaf3', 'f380fc01-efa8-4d13-88da-c5457cb4c6f7', 2, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('e47471ff-e78e-43df-9793-97a57d86aaf3', '5b4f12e1-7556-4049-bdad-a47225882137', 3, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('e47471ff-e78e-43df-9793-97a57d86aaf3', '5e4e82dd-85de-4e2c-972d-e2f2f50d5473', 4, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('c08cb1ca-67b8-4e33-813e-31ef7851f8d6', '1af0dd37-5b10-4523-ac61-c9d4ca7ff194', 1, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('c08cb1ca-67b8-4e33-813e-31ef7851f8d6', '6bd2e618-fa23-4c19-a98e-90228a705db0', 2, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('c08cb1ca-67b8-4e33-813e-31ef7851f8d6', '79e292e5-26f9-4cbd-b791-aee322fcf5e4', 3, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('c08cb1ca-67b8-4e33-813e-31ef7851f8d6', 'c240f849-9bea-4a3f-87d0-fd7efdc44004', 4, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('692d1f6a-0d37-4f6e-8b04-7ab190333bd1', '8e849922-36d4-42b9-982d-cde5cb97e3df', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('692d1f6a-0d37-4f6e-8b04-7ab190333bd1', '5965face-6243-4ed1-9657-f40da460c771', 2, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('692d1f6a-0d37-4f6e-8b04-7ab190333bd1', '50277c6a-6976-40c2-ba0c-103d83e49c52', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('692d1f6a-0d37-4f6e-8b04-7ab190333bd1', '8ec67b91-0c7b-4555-887a-bd26b285fc51', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('aed4fbfd-dea5-4c8e-8b55-19b795b7e586', '4d4cdcf9-3aed-4a97-8fc5-37d921e15a8b', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('aed4fbfd-dea5-4c8e-8b55-19b795b7e586', 'acbb8189-d9ab-411b-b549-4df8032942a2', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('aed4fbfd-dea5-4c8e-8b55-19b795b7e586', '34747c04-b5b3-4fb5-a54a-5fafe4697d64', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('aed4fbfd-dea5-4c8e-8b55-19b795b7e586', '9b4f2efb-675e-4ace-91cb-b9a2f9a4eadd', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('389d1b8f-adaf-4c36-904f-f282ff056441', '0b0b69d5-8762-4973-b08e-7926e37e173e', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('389d1b8f-adaf-4c36-904f-f282ff056441', '2c8f0ee2-e25d-4774-9479-7bcc035526d3', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('389d1b8f-adaf-4c36-904f-f282ff056441', '3f65384c-f9c3-414f-90f3-a1f55d3a34c8', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('389d1b8f-adaf-4c36-904f-f282ff056441', 'c5ad4108-96a6-40c4-8706-8e14ccc39586', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('f3c31575-ed40-4505-8e08-4b4cb71e4b95', 'a44fcd68-21a6-4b29-a68e-287191a07cc2', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('f3c31575-ed40-4505-8e08-4b4cb71e4b95', 'c9153faa-f437-4b84-bbdf-464d26daac3e', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('f3c31575-ed40-4505-8e08-4b4cb71e4b95', 'e1124234-3c6d-418e-ab97-f23a5ddd73dc', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('f3c31575-ed40-4505-8e08-4b4cb71e4b95', 'f0372977-ead0-48cd-a251-f3e8627d5d21', 4, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('1f6187e0-9e53-49bf-9650-608ffb62eb83', '954902cb-d99f-4b06-92dc-c40427bb380f', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('1f6187e0-9e53-49bf-9650-608ffb62eb83', 'f2243a7d-44d6-48a6-a9f3-9aeb1d245bbe', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('1f6187e0-9e53-49bf-9650-608ffb62eb83', 'eba4c852-3fe8-424a-b284-b94a837a191f', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('1f6187e0-9e53-49bf-9650-608ffb62eb83', 'a1be0e4d-309f-404e-82e2-c147b237216c', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('b122f99a-3b00-45d3-a63f-d76278d86c9e', 'c333179b-4b96-4b90-95be-fb78072b695b', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('b122f99a-3b00-45d3-a63f-d76278d86c9e', '66aac8d2-fe46-465a-a5c0-00433b47a6f0', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('b122f99a-3b00-45d3-a63f-d76278d86c9e', '3b037042-af74-4e80-b72e-f9d12a026f8c', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('b122f99a-3b00-45d3-a63f-d76278d86c9e', '728a96e0-b09b-4b5e-97e0-64243b9e3c80', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('b8e7f83f-a0ca-47fc-8aae-75e75291f5dd', '7af67bc2-d6b1-470b-8d25-1eb013a5d119', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('b8e7f83f-a0ca-47fc-8aae-75e75291f5dd', '18c13825-b378-4f46-bcee-1eaae8d3d276', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('b8e7f83f-a0ca-47fc-8aae-75e75291f5dd', '21a30c65-0783-468d-8727-398ecf4dd52e', 3, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('b8e7f83f-a0ca-47fc-8aae-75e75291f5dd', '4e266f55-6a10-470c-8b97-f91e39468905', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('9e10528a-74be-44c8-84f7-ba4430a10ecc', 'c3253fa7-c17c-4574-b9ac-122dcb685e25', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('9e10528a-74be-44c8-84f7-ba4430a10ecc', '1eec196f-eaff-42d1-8ef0-94372b6e9858', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('9e10528a-74be-44c8-84f7-ba4430a10ecc', 'ecd5833e-6814-4727-ad9b-d7b49321a3da', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('9e10528a-74be-44c8-84f7-ba4430a10ecc', 'a2858011-156a-4c6a-95b0-219dfae3a378', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('14fb940d-13d3-4c67-888e-e71d1c415e2c', 'ea75fc9b-276b-4826-b4a1-645ad3f1cf29', 1, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('14fb940d-13d3-4c67-888e-e71d1c415e2c', '4b5b625a-b5d6-4f73-bcb8-df0581dfdd8f', 2, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('14fb940d-13d3-4c67-888e-e71d1c415e2c', 'c97ab6f7-cd67-4bc2-9a7b-492e05c6b746', 3, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('14fb940d-13d3-4c67-888e-e71d1c415e2c', 'b6190ed6-28a8-415e-bd9d-0a944444cfe0', 4, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('b5b79855-a05b-45ce-b187-12856997cc01', 'ccf04c01-be55-4ab7-9765-f85ec0549895', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('b5b79855-a05b-45ce-b187-12856997cc01', '7656bd49-6cc9-41d3-8dc1-05152446b2bf', 2, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('b5b79855-a05b-45ce-b187-12856997cc01', '7c45cd3c-7707-4e98-b1ef-99712c54944a', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('b5b79855-a05b-45ce-b187-12856997cc01', '45506cf3-5ef9-402c-808f-d490ce4cd97b', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('1b0569c0-93b8-4a8e-a7d8-b10d830d6008', 'eb08414f-1ca9-4cd0-8673-32dc7bc45722', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('1b0569c0-93b8-4a8e-a7d8-b10d830d6008', '9260843f-6a9b-4c0b-99ad-7bd6e13c222e', 2, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('1b0569c0-93b8-4a8e-a7d8-b10d830d6008', 'c4f9f93e-fb1f-4d69-a1dd-9032fc7014ae', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('1b0569c0-93b8-4a8e-a7d8-b10d830d6008', '51353bae-b739-4fe2-94a8-d66dd2759a45', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('e8407a2d-804f-4119-93d8-2b99e7f08574', '9ca21c8a-5f8f-492d-b530-87051fac3c82', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('e8407a2d-804f-4119-93d8-2b99e7f08574', 'd5b0c609-7d69-4ca8-99f7-6a8b4366c61c', 2, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('e8407a2d-804f-4119-93d8-2b99e7f08574', 'f4e3a4e1-58a7-4e48-8a69-0969160eade6', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('e8407a2d-804f-4119-93d8-2b99e7f08574', '3a7e92c6-6aff-4fca-b682-fca92750b4dd', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('f5cba574-1226-4ad8-b89c-f0d47770f278', '195a26cd-d70c-4cac-a924-b3baab6c886d', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('f5cba574-1226-4ad8-b89c-f0d47770f278', '16ff8065-871d-40ab-9c8b-7997ff8e07d8', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('f5cba574-1226-4ad8-b89c-f0d47770f278', '63aebcf4-a90f-4e53-838f-a9f2bff0276d', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('f5cba574-1226-4ad8-b89c-f0d47770f278', '99dc6562-144c-4439-a0bf-a0dd70673637', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('96b2b9d9-a63d-4c8b-9cc3-67bc4f1fb790', '95856529-983f-48bf-99a5-0bcbe9e39439', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('96b2b9d9-a63d-4c8b-9cc3-67bc4f1fb790', '5875eea0-e342-48dd-99f0-759114c4a2f9', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('96b2b9d9-a63d-4c8b-9cc3-67bc4f1fb790', '28cd021e-5e43-489f-935f-f916dfd1c7d7', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('96b2b9d9-a63d-4c8b-9cc3-67bc4f1fb790', 'c3f1fdda-ae40-4469-b9a8-b73445494455', 4, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('1af5bdc2-c3d4-4c07-a8b3-df95f02238c7', 'de44ff27-a589-4730-94a7-757d6aa7b116', 0, 'bye');
INSERT INTO seats (pod, player, seat, result)
VALUES ('1af5bdc2-c3d4-4c07-a8b3-df95f02238c7', 'fa198306-4d98-4d10-b3ca-633e69f14b9e', 0, 'bye');
INSERT INTO seats (pod, player, seat, result)
VALUES ('84ed591f-17f2-4565-ad19-71b7bf56ee01', 'a2858011-156a-4c6a-95b0-219dfae3a378', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('84ed591f-17f2-4565-ad19-71b7bf56ee01', 'fa198306-4d98-4d10-b3ca-633e69f14b9e', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('84ed591f-17f2-4565-ad19-71b7bf56ee01', 'e64bd383-f074-4a86-b6ed-2e00ca12fb53', 3, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('84ed591f-17f2-4565-ad19-71b7bf56ee01', '42a367f9-d235-431c-8de0-25bbea4bca94', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('d93f6148-b909-45a6-8b6c-2794ced63994', '50277c6a-6976-40c2-ba0c-103d83e49c52', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('d93f6148-b909-45a6-8b6c-2794ced63994', 'de44ff27-a589-4730-94a7-757d6aa7b116', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('d93f6148-b909-45a6-8b6c-2794ced63994', '5875eea0-e342-48dd-99f0-759114c4a2f9', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('d93f6148-b909-45a6-8b6c-2794ced63994', 'ea75fc9b-276b-4826-b4a1-645ad3f1cf29', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('4e0a7c35-446a-4772-a5ad-92025fd76646', 'c8cf4eb9-1267-489c-88ae-88d95d061954', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('4e0a7c35-446a-4772-a5ad-92025fd76646', 'd2efce71-199b-4f60-856f-7446babcdf45', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('4e0a7c35-446a-4772-a5ad-92025fd76646', '7af67bc2-d6b1-470b-8d25-1eb013a5d119', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('4e0a7c35-446a-4772-a5ad-92025fd76646', 'd4cb537d-bfad-4740-9c75-2003275addd4', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('3a4b3df9-9505-4552-a66f-8f9335139657', 'c3f1fdda-ae40-4469-b9a8-b73445494455', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('3a4b3df9-9505-4552-a66f-8f9335139657', '21a30c65-0783-468d-8727-398ecf4dd52e', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('3a4b3df9-9505-4552-a66f-8f9335139657', 'f33c5f43-bcae-4dd0-beba-d264b413172b', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('3a4b3df9-9505-4552-a66f-8f9335139657', '4b5b625a-b5d6-4f73-bcb8-df0581dfdd8f', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('90cf4c75-742a-4c99-9e0b-030f482813dc', 'f0372977-ead0-48cd-a251-f3e8627d5d21', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('90cf4c75-742a-4c99-9e0b-030f482813dc', 'c4f9f93e-fb1f-4d69-a1dd-9032fc7014ae', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('90cf4c75-742a-4c99-9e0b-030f482813dc', 'bf56abda-1812-47ea-86b5-0cc4eb869596', 3, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('90cf4c75-742a-4c99-9e0b-030f482813dc', 'ccf04c01-be55-4ab7-9765-f85ec0549895', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('4d9f9f41-f753-45ae-a7c9-2a76a53d9f83', 'e8e30811-1dd7-4708-a87c-916f7e755188', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('4d9f9f41-f753-45ae-a7c9-2a76a53d9f83', '3b037042-af74-4e80-b72e-f9d12a026f8c', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('4d9f9f41-f753-45ae-a7c9-2a76a53d9f83', '16ff8065-871d-40ab-9c8b-7997ff8e07d8', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('4d9f9f41-f753-45ae-a7c9-2a76a53d9f83', 'd01532a1-b05d-4cda-a092-2c51809aa7ee', 4, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('b7077126-99d1-4322-ac79-e83326b166b8', '192439d0-334b-4476-a707-82eb7a3140cd', 1, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('b7077126-99d1-4322-ac79-e83326b166b8', '7c45cd3c-7707-4e98-b1ef-99712c54944a', 2, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('b7077126-99d1-4322-ac79-e83326b166b8', '78c5c822-6bc7-4f0e-9f55-f765e0230cb7', 3, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('b7077126-99d1-4322-ac79-e83326b166b8', '954902cb-d99f-4b06-92dc-c40427bb380f', 4, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('9e277191-09dd-41bc-bdbb-b8eeba471ead', 'e14dddec-0f85-46fb-8de2-7faf30ed2654', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('9e277191-09dd-41bc-bdbb-b8eeba471ead', '7a074b5d-f3b8-42fe-875a-ab123e025daf', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('9e277191-09dd-41bc-bdbb-b8eeba471ead', '882f45d6-b387-4461-a50a-4b01876c13e1', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('9e277191-09dd-41bc-bdbb-b8eeba471ead', '95856529-983f-48bf-99a5-0bcbe9e39439', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('7310b6be-da38-4fd7-a5fb-7f87ac4debad', 'c19da3cb-dcf2-4f1c-9596-a2a58f9e6fc5', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('7310b6be-da38-4fd7-a5fb-7f87ac4debad', 'a1be0e4d-309f-404e-82e2-c147b237216c', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('7310b6be-da38-4fd7-a5fb-7f87ac4debad', '3cef6826-d934-4b3f-a4c7-8e1a1c47eb0b', 3, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('7310b6be-da38-4fd7-a5fb-7f87ac4debad', '49c75108-2252-4c99-9bcf-c4e4d153ee6f', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('9c8dc43b-e9f0-40bf-8e76-8aba95549610', '9b4f2efb-675e-4ace-91cb-b9a2f9a4eadd', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('9c8dc43b-e9f0-40bf-8e76-8aba95549610', '15b3e4d7-291b-4838-9fe9-20ee036bf272', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('9c8dc43b-e9f0-40bf-8e76-8aba95549610', '1eec196f-eaff-42d1-8ef0-94372b6e9858', 3, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('9c8dc43b-e9f0-40bf-8e76-8aba95549610', '0d2dc979-2a3c-4604-ab17-d498960ca2a8', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('904aa049-642e-4fce-b3cd-66fa32f48582', '3a7e92c6-6aff-4fca-b682-fca92750b4dd', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('904aa049-642e-4fce-b3cd-66fa32f48582', '5965face-6243-4ed1-9657-f40da460c771', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('904aa049-642e-4fce-b3cd-66fa32f48582', '2cb5667d-d717-4503-a628-46e97e5fa77b', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('904aa049-642e-4fce-b3cd-66fa32f48582', '1431a2d8-24e9-40fc-b4d0-eb8637b4a9ab', 4, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('018d3d90-d2da-4546-a2df-1429b7c862de', '8ec67b91-0c7b-4555-887a-bd26b285fc51', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('018d3d90-d2da-4546-a2df-1429b7c862de', 'c97ab6f7-cd67-4bc2-9a7b-492e05c6b746', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('018d3d90-d2da-4546-a2df-1429b7c862de', 'f2243a7d-44d6-48a6-a9f3-9aeb1d245bbe', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('018d3d90-d2da-4546-a2df-1429b7c862de', '1af0dd37-5b10-4523-ac61-c9d4ca7ff194', 4, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('a1a9cb76-24ae-4ca7-9839-0a8618cc2db5', 'c5ad4108-96a6-40c4-8706-8e14ccc39586', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('a1a9cb76-24ae-4ca7-9839-0a8618cc2db5', '0b322a15-4881-4ec0-ad05-7d1e99ace392', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('a1a9cb76-24ae-4ca7-9839-0a8618cc2db5', 'd01b07dd-99dc-4b9f-8874-6c801b2ab28f', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('a1a9cb76-24ae-4ca7-9839-0a8618cc2db5', '48861d2d-ba57-4aff-8e94-e7b4814d0bbd', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('3e86f83e-8336-492a-b1f6-ea6757ca4240', '1595c0a0-78ba-4ae7-9a14-b925c31f31ab', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('3e86f83e-8336-492a-b1f6-ea6757ca4240', '3f65384c-f9c3-414f-90f3-a1f55d3a34c8', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('3e86f83e-8336-492a-b1f6-ea6757ca4240', 'f380fc01-efa8-4d13-88da-c5457cb4c6f7', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('3e86f83e-8336-492a-b1f6-ea6757ca4240', '1ebab4f3-61f4-47c4-919b-e5d50812c64d', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('0bd905eb-cb35-4c3a-9423-91e4ff59c316', '79e292e5-26f9-4cbd-b791-aee322fcf5e4', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('0bd905eb-cb35-4c3a-9423-91e4ff59c316', 'd5b0c609-7d69-4ca8-99f7-6a8b4366c61c', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('0bd905eb-cb35-4c3a-9423-91e4ff59c316', 'f6b6e952-c020-4baa-b1ea-2c7280d7fbb0', 3, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('0bd905eb-cb35-4c3a-9423-91e4ff59c316', 'd7cf8c00-ebbf-4aab-83f5-af4e522b350a', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('c728c15f-c4d7-4528-8e2d-a29c7ff983a6', 'c9153faa-f437-4b84-bbdf-464d26daac3e', 1, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('c728c15f-c4d7-4528-8e2d-a29c7ff983a6', '12814d53-fab4-4803-b031-7e6f1a6a545a', 2, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('c728c15f-c4d7-4528-8e2d-a29c7ff983a6', 'c333179b-4b96-4b90-95be-fb78072b695b', 3, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('c728c15f-c4d7-4528-8e2d-a29c7ff983a6', '0b0b69d5-8762-4973-b08e-7926e37e173e', 4, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('4ce238cc-d3de-44bb-b78a-7df66d3e15df', '650c91ae-e5fc-4270-b444-287fb9411ae2', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('4ce238cc-d3de-44bb-b78a-7df66d3e15df', '0cecfa45-7720-4341-acea-4b070a09f681', 2, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('4ce238cc-d3de-44bb-b78a-7df66d3e15df', '706f3960-9ba2-468e-bfbd-91d4a7ef5250', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('4ce238cc-d3de-44bb-b78a-7df66d3e15df', '8e849922-36d4-42b9-982d-cde5cb97e3df', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('b35740c3-4bf1-4019-80b7-a694cc089c61', '0d88cae6-a3f8-4683-8ba8-8776b99bb74c', 1, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('b35740c3-4bf1-4019-80b7-a694cc089c61', 'a2ebbc3a-8f4b-42fc-8a6a-cba439805e0e', 2, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('b35740c3-4bf1-4019-80b7-a694cc089c61', '3f89032f-c45e-44d8-947c-20d822a12ea4', 3, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('b35740c3-4bf1-4019-80b7-a694cc089c61', '7c7ad7f1-d83c-4904-8b38-e47ec8881fad', 4, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('43af1060-917f-4259-844b-26c4cea4a93b', 'b6190ed6-28a8-415e-bd9d-0a944444cfe0', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('43af1060-917f-4259-844b-26c4cea4a93b', 'ecd5833e-6814-4727-ad9b-d7b49321a3da', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('43af1060-917f-4259-844b-26c4cea4a93b', '435b02d2-1322-48b3-9003-9ad71f4b989e', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('43af1060-917f-4259-844b-26c4cea4a93b', '2c8f0ee2-e25d-4774-9479-7bcc035526d3', 4, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('3eecacf8-63d6-4e6f-a5de-4c6ff1d7fcd6', '38fba32b-c6c4-40e8-8bf7-b84eea7b74e4', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('3eecacf8-63d6-4e6f-a5de-4c6ff1d7fcd6', '45506cf3-5ef9-402c-808f-d490ce4cd97b', 2, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('3eecacf8-63d6-4e6f-a5de-4c6ff1d7fcd6', '63aebcf4-a90f-4e53-838f-a9f2bff0276d', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('3eecacf8-63d6-4e6f-a5de-4c6ff1d7fcd6', 'd2828084-86fc-49b8-bdc7-ad822e599ae9', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('0eb6b531-afc2-4ac6-9800-d80c691fab53', '1002e89b-92ab-4808-8369-4482e2d61301', 1, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('0eb6b531-afc2-4ac6-9800-d80c691fab53', 'c240f849-9bea-4a3f-87d0-fd7efdc44004', 2, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('0eb6b531-afc2-4ac6-9800-d80c691fab53', '9260843f-6a9b-4c0b-99ad-7bd6e13c222e', 3, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('0eb6b531-afc2-4ac6-9800-d80c691fab53', 'c3253fa7-c17c-4574-b9ac-122dcb685e25', 4, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('c4021894-6ddc-45b9-a59a-f48ca87c284b', '34747c04-b5b3-4fb5-a54a-5fafe4697d64', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('c4021894-6ddc-45b9-a59a-f48ca87c284b', 'ee571349-bdc7-4688-b4fa-7aff2493a979', 2, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('c4021894-6ddc-45b9-a59a-f48ca87c284b', '006f76da-e6da-4e78-894b-ccf094d333f6', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('c4021894-6ddc-45b9-a59a-f48ca87c284b', 'eb08414f-1ca9-4cd0-8673-32dc7bc45722', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('6d1256e9-7b7e-42dc-8dd9-9191853cc55e', '51353bae-b739-4fe2-94a8-d66dd2759a45', 1, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('6d1256e9-7b7e-42dc-8dd9-9191853cc55e', 'eba4c852-3fe8-424a-b284-b94a837a191f', 2, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('6d1256e9-7b7e-42dc-8dd9-9191853cc55e', 'a44fcd68-21a6-4b29-a68e-287191a07cc2', 3, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('6d1256e9-7b7e-42dc-8dd9-9191853cc55e', '74de865d-6ef9-496b-bbd1-bf59b4a77293', 4, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('795dfdd5-4074-4ed4-b948-82af341b29b6', '5b4f12e1-7556-4049-bdad-a47225882137', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('795dfdd5-4074-4ed4-b948-82af341b29b6', 'f4e3a4e1-58a7-4e48-8a69-0969160eade6', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('795dfdd5-4074-4ed4-b948-82af341b29b6', 'e870d2b8-21bf-46d8-b7c9-d2cd27b693d5', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('795dfdd5-4074-4ed4-b948-82af341b29b6', 'd2e0eb87-c9b7-4c49-9571-983cc0681153', 4, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('8ddf1d79-c5bc-4dfd-b8d0-5950e47eace6', '728a96e0-b09b-4b5e-97e0-64243b9e3c80', 1, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('8ddf1d79-c5bc-4dfd-b8d0-5950e47eace6', '55b6ec61-f6f7-4320-9b24-cd5ebd168e60', 2, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('8ddf1d79-c5bc-4dfd-b8d0-5950e47eace6', '9ca21c8a-5f8f-492d-b530-87051fac3c82', 3, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('8ddf1d79-c5bc-4dfd-b8d0-5950e47eace6', '195a26cd-d70c-4cac-a924-b3baab6c886d', 4, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('72bd6985-a3dc-4ecb-baed-78ad79bdf5e6', '5e4e82dd-85de-4e2c-972d-e2f2f50d5473', 1, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('72bd6985-a3dc-4ecb-baed-78ad79bdf5e6', '28cd021e-5e43-489f-935f-f916dfd1c7d7', 2, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('72bd6985-a3dc-4ecb-baed-78ad79bdf5e6', '500c9705-e66f-403a-b43f-e12b18427e08', 3, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('72bd6985-a3dc-4ecb-baed-78ad79bdf5e6', '7656bd49-6cc9-41d3-8dc1-05152446b2bf', 4, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('db5407e2-f5d4-4545-af20-e91f11f6c105', '4e266f55-6a10-470c-8b97-f91e39468905', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('db5407e2-f5d4-4545-af20-e91f11f6c105', '5ff28ef1-aa76-46f5-bd07-1e1bc62af078', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('db5407e2-f5d4-4545-af20-e91f11f6c105', '0c143c41-eda0-4584-b01a-57d3948f4bee', 3, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('db5407e2-f5d4-4545-af20-e91f11f6c105', '4d4cdcf9-3aed-4a97-8fc5-37d921e15a8b', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('fe209090-31df-40d8-8af0-d258f77e7d38', '7506e609-84b0-4016-966b-14e4ec647033', 1, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('fe209090-31df-40d8-8af0-d258f77e7d38', 'e1124234-3c6d-418e-ab97-f23a5ddd73dc', 2, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('fe209090-31df-40d8-8af0-d258f77e7d38', '6bd2e618-fa23-4c19-a98e-90228a705db0', 3, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('fe209090-31df-40d8-8af0-d258f77e7d38', '66aac8d2-fe46-465a-a5c0-00433b47a6f0', 4, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('a80e264a-3db3-4438-b0df-dacba9dc12c8', '369310a4-c89f-498f-a083-5dfb5aa7dabe', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('a80e264a-3db3-4438-b0df-dacba9dc12c8', '99dc6562-144c-4439-a0bf-a0dd70673637', 2, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('a80e264a-3db3-4438-b0df-dacba9dc12c8', 'acbb8189-d9ab-411b-b549-4df8032942a2', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('a80e264a-3db3-4438-b0df-dacba9dc12c8', '6de4807e-73df-4ddd-9500-f66cd694f807', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('fcf9d59f-74a3-49a3-bca9-e77519449c0d', '62635dd9-cabb-45ca-b4e3-55d98dc0c884', 0, 'bye');
INSERT INTO seats (pod, player, seat, result)
VALUES ('fcf9d59f-74a3-49a3-bca9-e77519449c0d', '18c13825-b378-4f46-bcee-1eaae8d3d276', 0, 'bye');
INSERT INTO seats (pod, player, seat, result)
VALUES ('099b515d-18e1-4271-9360-3df4898ff1aa', '99dc6562-144c-4439-a0bf-a0dd70673637', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('099b515d-18e1-4271-9360-3df4898ff1aa', '5875eea0-e342-48dd-99f0-759114c4a2f9', 2, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('099b515d-18e1-4271-9360-3df4898ff1aa', 'c3253fa7-c17c-4574-b9ac-122dcb685e25', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('099b515d-18e1-4271-9360-3df4898ff1aa', '9ca21c8a-5f8f-492d-b530-87051fac3c82', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('eb445665-cb90-4969-a94b-4dd28e4fb3ec', '55b6ec61-f6f7-4320-9b24-cd5ebd168e60', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('eb445665-cb90-4969-a94b-4dd28e4fb3ec', '7c7ad7f1-d83c-4904-8b38-e47ec8881fad', 2, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('eb445665-cb90-4969-a94b-4dd28e4fb3ec', '6bd2e618-fa23-4c19-a98e-90228a705db0', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('eb445665-cb90-4969-a94b-4dd28e4fb3ec', '5b4f12e1-7556-4049-bdad-a47225882137', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('58ba1d1d-724d-4ba4-9c76-bd739abb87a3', 'f2243a7d-44d6-48a6-a9f3-9aeb1d245bbe', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('58ba1d1d-724d-4ba4-9c76-bd739abb87a3', 'd4cb537d-bfad-4740-9c75-2003275addd4', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('58ba1d1d-724d-4ba4-9c76-bd739abb87a3', '3b037042-af74-4e80-b72e-f9d12a026f8c', 3, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('58ba1d1d-724d-4ba4-9c76-bd739abb87a3', 'f6b6e952-c020-4baa-b1ea-2c7280d7fbb0', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('89856f36-9ad6-408c-a5be-081764ba0239', '1431a2d8-24e9-40fc-b4d0-eb8637b4a9ab', 1, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('89856f36-9ad6-408c-a5be-081764ba0239', '195a26cd-d70c-4cac-a924-b3baab6c886d', 2, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('89856f36-9ad6-408c-a5be-081764ba0239', 'e1124234-3c6d-418e-ab97-f23a5ddd73dc', 3, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('89856f36-9ad6-408c-a5be-081764ba0239', '38fba32b-c6c4-40e8-8bf7-b84eea7b74e4', 4, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('4b8063d5-c86a-4837-86ad-e99ad4bab9aa', 'ecd5833e-6814-4727-ad9b-d7b49321a3da', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('4b8063d5-c86a-4837-86ad-e99ad4bab9aa', '006f76da-e6da-4e78-894b-ccf094d333f6', 2, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('4b8063d5-c86a-4837-86ad-e99ad4bab9aa', '3cef6826-d934-4b3f-a4c7-8e1a1c47eb0b', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('4b8063d5-c86a-4837-86ad-e99ad4bab9aa', '1595c0a0-78ba-4ae7-9a14-b925c31f31ab', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('aad8cc1c-8e4e-4550-87c1-b4fe587ecf0e', 'a1be0e4d-309f-404e-82e2-c147b237216c', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('aad8cc1c-8e4e-4550-87c1-b4fe587ecf0e', '4d4cdcf9-3aed-4a97-8fc5-37d921e15a8b', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('aad8cc1c-8e4e-4550-87c1-b4fe587ecf0e', '6de4807e-73df-4ddd-9500-f66cd694f807', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('aad8cc1c-8e4e-4550-87c1-b4fe587ecf0e', '7506e609-84b0-4016-966b-14e4ec647033', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('46e9d069-53a8-4af4-981a-e0b2ec2ffc43', '66aac8d2-fe46-465a-a5c0-00433b47a6f0', 1, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('46e9d069-53a8-4af4-981a-e0b2ec2ffc43', '95856529-983f-48bf-99a5-0bcbe9e39439', 2, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('46e9d069-53a8-4af4-981a-e0b2ec2ffc43', '0b0b69d5-8762-4973-b08e-7926e37e173e', 3, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('46e9d069-53a8-4af4-981a-e0b2ec2ffc43', 'c97ab6f7-cd67-4bc2-9a7b-492e05c6b746', 4, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('2798854a-9e7f-442d-9f1e-6dbf8d45a234', 'e14dddec-0f85-46fb-8de2-7faf30ed2654', 1, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('2798854a-9e7f-442d-9f1e-6dbf8d45a234', 'e8e30811-1dd7-4708-a87c-916f7e755188', 2, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('2798854a-9e7f-442d-9f1e-6dbf8d45a234', 'f0372977-ead0-48cd-a251-f3e8627d5d21', 3, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('2798854a-9e7f-442d-9f1e-6dbf8d45a234', '5965face-6243-4ed1-9657-f40da460c771', 4, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('3e2b83c2-8729-4012-849f-9a1939289f5e', 'ccf04c01-be55-4ab7-9765-f85ec0549895', 1, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('3e2b83c2-8729-4012-849f-9a1939289f5e', 'e64bd383-f074-4a86-b6ed-2e00ca12fb53', 2, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('3e2b83c2-8729-4012-849f-9a1939289f5e', '9260843f-6a9b-4c0b-99ad-7bd6e13c222e', 3, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('3e2b83c2-8729-4012-849f-9a1939289f5e', 'c333179b-4b96-4b90-95be-fb78072b695b', 4, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('733556cb-018c-4860-8889-a092968fb5ce', 'c240f849-9bea-4a3f-87d0-fd7efdc44004', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('733556cb-018c-4860-8889-a092968fb5ce', '9b4f2efb-675e-4ace-91cb-b9a2f9a4eadd', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('733556cb-018c-4860-8889-a092968fb5ce', '21a30c65-0783-468d-8727-398ecf4dd52e', 3, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('733556cb-018c-4860-8889-a092968fb5ce', '42a367f9-d235-431c-8de0-25bbea4bca94', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('14554f89-118c-46fd-8aab-c9a88160856e', '0d2dc979-2a3c-4604-ab17-d498960ca2a8', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('14554f89-118c-46fd-8aab-c9a88160856e', '4b5b625a-b5d6-4f73-bcb8-df0581dfdd8f', 2, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('14554f89-118c-46fd-8aab-c9a88160856e', '5ff28ef1-aa76-46f5-bd07-1e1bc62af078', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('14554f89-118c-46fd-8aab-c9a88160856e', 'de44ff27-a589-4730-94a7-757d6aa7b116', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('ed044702-a690-42ac-a3dc-25f2f9bea056', 'd01b07dd-99dc-4b9f-8874-6c801b2ab28f', 1, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('ed044702-a690-42ac-a3dc-25f2f9bea056', 'acbb8189-d9ab-411b-b549-4df8032942a2', 2, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('ed044702-a690-42ac-a3dc-25f2f9bea056', 'c19da3cb-dcf2-4f1c-9596-a2a58f9e6fc5', 3, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('ed044702-a690-42ac-a3dc-25f2f9bea056', '18c13825-b378-4f46-bcee-1eaae8d3d276', 4, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('95556ee4-eb88-4a06-9c23-87d19000b0b1', '728a96e0-b09b-4b5e-97e0-64243b9e3c80', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('95556ee4-eb88-4a06-9c23-87d19000b0b1', '15b3e4d7-291b-4838-9fe9-20ee036bf272', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('95556ee4-eb88-4a06-9c23-87d19000b0b1', '882f45d6-b387-4461-a50a-4b01876c13e1', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('95556ee4-eb88-4a06-9c23-87d19000b0b1', '50277c6a-6976-40c2-ba0c-103d83e49c52', 4, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('7a6d987a-46fc-4d2b-af4e-9f605a8f9b07', '45506cf3-5ef9-402c-808f-d490ce4cd97b', 1, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('7a6d987a-46fc-4d2b-af4e-9f605a8f9b07', 'f380fc01-efa8-4d13-88da-c5457cb4c6f7', 2, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('7a6d987a-46fc-4d2b-af4e-9f605a8f9b07', '369310a4-c89f-498f-a083-5dfb5aa7dabe', 3, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('7a6d987a-46fc-4d2b-af4e-9f605a8f9b07', 'eb08414f-1ca9-4cd0-8673-32dc7bc45722', 4, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('e4afa736-0d7f-4dc6-a7c9-723225b8b621', '0c143c41-eda0-4584-b01a-57d3948f4bee', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('e4afa736-0d7f-4dc6-a7c9-723225b8b621', 'c8cf4eb9-1267-489c-88ae-88d95d061954', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('e4afa736-0d7f-4dc6-a7c9-723225b8b621', '3f89032f-c45e-44d8-947c-20d822a12ea4', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('e4afa736-0d7f-4dc6-a7c9-723225b8b621', '74de865d-6ef9-496b-bbd1-bf59b4a77293', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('1daa182a-0f90-42f3-9d3c-a144f6349007', '62635dd9-cabb-45ca-b4e3-55d98dc0c884', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('1daa182a-0f90-42f3-9d3c-a144f6349007', 'd7cf8c00-ebbf-4aab-83f5-af4e522b350a', 2, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('1daa182a-0f90-42f3-9d3c-a144f6349007', 'c3f1fdda-ae40-4469-b9a8-b73445494455', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('1daa182a-0f90-42f3-9d3c-a144f6349007', '7c45cd3c-7707-4e98-b1ef-99712c54944a', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('55f62a20-5e24-4baa-93e1-22f83356593e', '500c9705-e66f-403a-b43f-e12b18427e08', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('55f62a20-5e24-4baa-93e1-22f83356593e', 'a2858011-156a-4c6a-95b0-219dfae3a378', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('55f62a20-5e24-4baa-93e1-22f83356593e', '51353bae-b739-4fe2-94a8-d66dd2759a45', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('55f62a20-5e24-4baa-93e1-22f83356593e', '8e849922-36d4-42b9-982d-cde5cb97e3df', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('7eb3bd52-9067-42f4-970f-f0b44257b384', '7656bd49-6cc9-41d3-8dc1-05152446b2bf', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('7eb3bd52-9067-42f4-970f-f0b44257b384', 'c5ad4108-96a6-40c4-8706-8e14ccc39586', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('7eb3bd52-9067-42f4-970f-f0b44257b384', 'fa198306-4d98-4d10-b3ca-633e69f14b9e', 3, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('7eb3bd52-9067-42f4-970f-f0b44257b384', 'bf56abda-1812-47ea-86b5-0cc4eb869596', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('d68ea49f-d2b4-47cd-bdfb-9a5b93529be7', '706f3960-9ba2-468e-bfbd-91d4a7ef5250', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('d68ea49f-d2b4-47cd-bdfb-9a5b93529be7', '28cd021e-5e43-489f-935f-f916dfd1c7d7', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('d68ea49f-d2b4-47cd-bdfb-9a5b93529be7', '3f65384c-f9c3-414f-90f3-a1f55d3a34c8', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('d68ea49f-d2b4-47cd-bdfb-9a5b93529be7', 'f4e3a4e1-58a7-4e48-8a69-0969160eade6', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('2694bd59-b919-483b-8726-330af3f3c785', '63aebcf4-a90f-4e53-838f-a9f2bff0276d', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('2694bd59-b919-483b-8726-330af3f3c785', 'd2e0eb87-c9b7-4c49-9571-983cc0681153', 2, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('2694bd59-b919-483b-8726-330af3f3c785', 'b6190ed6-28a8-415e-bd9d-0a944444cfe0', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('2694bd59-b919-483b-8726-330af3f3c785', '192439d0-334b-4476-a707-82eb7a3140cd', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('4bb11ed5-b3af-4c85-9d22-18a37474737f', '48861d2d-ba57-4aff-8e94-e7b4814d0bbd', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('4bb11ed5-b3af-4c85-9d22-18a37474737f', '16ff8065-871d-40ab-9c8b-7997ff8e07d8', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('4bb11ed5-b3af-4c85-9d22-18a37474737f', '3a7e92c6-6aff-4fca-b682-fca92750b4dd', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('4bb11ed5-b3af-4c85-9d22-18a37474737f', '79e292e5-26f9-4cbd-b791-aee322fcf5e4', 4, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('7184cf1f-a46a-4a86-b07e-65770e771a16', 'd01532a1-b05d-4cda-a092-2c51809aa7ee', 1, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('7184cf1f-a46a-4a86-b07e-65770e771a16', '1ebab4f3-61f4-47c4-919b-e5d50812c64d', 2, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('7184cf1f-a46a-4a86-b07e-65770e771a16', 'f33c5f43-bcae-4dd0-beba-d264b413172b', 3, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('7184cf1f-a46a-4a86-b07e-65770e771a16', 'c9153faa-f437-4b84-bbdf-464d26daac3e', 4, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('236a03ca-584e-4aab-9d8f-5f5b0422e7e1', '2c8f0ee2-e25d-4774-9479-7bcc035526d3', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('236a03ca-584e-4aab-9d8f-5f5b0422e7e1', 'e870d2b8-21bf-46d8-b7c9-d2cd27b693d5', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('236a03ca-584e-4aab-9d8f-5f5b0422e7e1', '1af0dd37-5b10-4523-ac61-c9d4ca7ff194', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('236a03ca-584e-4aab-9d8f-5f5b0422e7e1', 'd5b0c609-7d69-4ca8-99f7-6a8b4366c61c', 4, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('f83e6021-6903-4197-b1f1-46aa3d46148f', '435b02d2-1322-48b3-9003-9ad71f4b989e', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('f83e6021-6903-4197-b1f1-46aa3d46148f', '954902cb-d99f-4b06-92dc-c40427bb380f', 2, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('f83e6021-6903-4197-b1f1-46aa3d46148f', '0d88cae6-a3f8-4683-8ba8-8776b99bb74c', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('f83e6021-6903-4197-b1f1-46aa3d46148f', 'c4f9f93e-fb1f-4d69-a1dd-9032fc7014ae', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('8da16f46-b97f-4cf7-900e-dedfbeddaf0f', '0b322a15-4881-4ec0-ad05-7d1e99ace392', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('8da16f46-b97f-4cf7-900e-dedfbeddaf0f', '2cb5667d-d717-4503-a628-46e97e5fa77b', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('8da16f46-b97f-4cf7-900e-dedfbeddaf0f', 'eba4c852-3fe8-424a-b284-b94a837a191f', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('8da16f46-b97f-4cf7-900e-dedfbeddaf0f', 'd2efce71-199b-4f60-856f-7446babcdf45', 4, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('f3b0d5a9-5cbf-424b-b7ea-19d189b8e2bf', '8ec67b91-0c7b-4555-887a-bd26b285fc51', 1, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('f3b0d5a9-5cbf-424b-b7ea-19d189b8e2bf', '1002e89b-92ab-4808-8369-4482e2d61301', 2, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('f3b0d5a9-5cbf-424b-b7ea-19d189b8e2bf', '7a074b5d-f3b8-42fe-875a-ab123e025daf', 3, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('f3b0d5a9-5cbf-424b-b7ea-19d189b8e2bf', '12814d53-fab4-4803-b031-7e6f1a6a545a', 4, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('92041f1c-449a-4010-a9ee-eca5a59a1150', 'ea75fc9b-276b-4826-b4a1-645ad3f1cf29', 1, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('92041f1c-449a-4010-a9ee-eca5a59a1150', 'ee571349-bdc7-4688-b4fa-7aff2493a979', 2, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('92041f1c-449a-4010-a9ee-eca5a59a1150', 'a2ebbc3a-8f4b-42fc-8a6a-cba439805e0e', 3, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('92041f1c-449a-4010-a9ee-eca5a59a1150', '5e4e82dd-85de-4e2c-972d-e2f2f50d5473', 4, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('a68282ae-ee38-48c0-94d9-e38209c51a6d', '650c91ae-e5fc-4270-b444-287fb9411ae2', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('a68282ae-ee38-48c0-94d9-e38209c51a6d', '4e266f55-6a10-470c-8b97-f91e39468905', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('a68282ae-ee38-48c0-94d9-e38209c51a6d', 'd2828084-86fc-49b8-bdc7-ad822e599ae9', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('a68282ae-ee38-48c0-94d9-e38209c51a6d', 'a44fcd68-21a6-4b29-a68e-287191a07cc2', 4, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('891c8cc1-d70b-4c2e-837b-f517f735b1b6', '0cecfa45-7720-4341-acea-4b070a09f681', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('891c8cc1-d70b-4c2e-837b-f517f735b1b6', '1eec196f-eaff-42d1-8ef0-94372b6e9858', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('891c8cc1-d70b-4c2e-837b-f517f735b1b6', '49c75108-2252-4c99-9bcf-c4e4d153ee6f', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('891c8cc1-d70b-4c2e-837b-f517f735b1b6', '78c5c822-6bc7-4f0e-9f55-f765e0230cb7', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('ab5019dd-2bd0-48d5-b613-a0630e1f7cf5', '7af67bc2-d6b1-470b-8d25-1eb013a5d119', 0, 'bye');
INSERT INTO seats (pod, player, seat, result)
VALUES ('ab5019dd-2bd0-48d5-b613-a0630e1f7cf5', '34747c04-b5b3-4fb5-a54a-5fafe4697d64', 0, 'bye');
INSERT INTO seats (pod, player, seat, result)
VALUES ('be2d9392-5a00-40b4-b270-7442c6eb5044', '21a30c65-0783-468d-8727-398ecf4dd52e', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('be2d9392-5a00-40b4-b270-7442c6eb5044', '5ff28ef1-aa76-46f5-bd07-1e1bc62af078', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('be2d9392-5a00-40b4-b270-7442c6eb5044', '195a26cd-d70c-4cac-a924-b3baab6c886d', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('be2d9392-5a00-40b4-b270-7442c6eb5044', '62635dd9-cabb-45ca-b4e3-55d98dc0c884', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('df494015-ac92-4a78-87db-bc5a771e28d6', '38fba32b-c6c4-40e8-8bf7-b84eea7b74e4', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('df494015-ac92-4a78-87db-bc5a771e28d6', 'f33c5f43-bcae-4dd0-beba-d264b413172b', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('df494015-ac92-4a78-87db-bc5a771e28d6', '51353bae-b739-4fe2-94a8-d66dd2759a45', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('df494015-ac92-4a78-87db-bc5a771e28d6', '728a96e0-b09b-4b5e-97e0-64243b9e3c80', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('f76d8903-42f1-4270-9875-a2dc712da8f9', '0b0b69d5-8762-4973-b08e-7926e37e173e', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('f76d8903-42f1-4270-9875-a2dc712da8f9', '63aebcf4-a90f-4e53-838f-a9f2bff0276d', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('f76d8903-42f1-4270-9875-a2dc712da8f9', 'e64bd383-f074-4a86-b6ed-2e00ca12fb53', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('f76d8903-42f1-4270-9875-a2dc712da8f9', '8ec67b91-0c7b-4555-887a-bd26b285fc51', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('46da8bd9-e652-48c5-9238-a65974bcc565', '3cef6826-d934-4b3f-a4c7-8e1a1c47eb0b', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('46da8bd9-e652-48c5-9238-a65974bcc565', 'a2ebbc3a-8f4b-42fc-8a6a-cba439805e0e', 2, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('46da8bd9-e652-48c5-9238-a65974bcc565', '2cb5667d-d717-4503-a628-46e97e5fa77b', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('46da8bd9-e652-48c5-9238-a65974bcc565', 'c5ad4108-96a6-40c4-8706-8e14ccc39586', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('ff14301b-8455-440c-8954-df3a53b51797', '0d88cae6-a3f8-4683-8ba8-8776b99bb74c', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('ff14301b-8455-440c-8954-df3a53b51797', 'fa198306-4d98-4d10-b3ca-633e69f14b9e', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('ff14301b-8455-440c-8954-df3a53b51797', '1ebab4f3-61f4-47c4-919b-e5d50812c64d', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('ff14301b-8455-440c-8954-df3a53b51797', 'ea75fc9b-276b-4826-b4a1-645ad3f1cf29', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('dbe9e55c-e47c-4085-b53c-e7a2fe32afde', 'c4f9f93e-fb1f-4d69-a1dd-9032fc7014ae', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('dbe9e55c-e47c-4085-b53c-e7a2fe32afde', '50277c6a-6976-40c2-ba0c-103d83e49c52', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('dbe9e55c-e47c-4085-b53c-e7a2fe32afde', '7c7ad7f1-d83c-4904-8b38-e47ec8881fad', 3, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('dbe9e55c-e47c-4085-b53c-e7a2fe32afde', '16ff8065-871d-40ab-9c8b-7997ff8e07d8', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('d4c5abbc-a997-444d-af99-02c527d8bb7d', 'd2828084-86fc-49b8-bdc7-ad822e599ae9', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('d4c5abbc-a997-444d-af99-02c527d8bb7d', '5b4f12e1-7556-4049-bdad-a47225882137', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('d4c5abbc-a997-444d-af99-02c527d8bb7d', '95856529-983f-48bf-99a5-0bcbe9e39439', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('d4c5abbc-a997-444d-af99-02c527d8bb7d', 'd01b07dd-99dc-4b9f-8874-6c801b2ab28f', 4, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('666624a2-0d8c-4a4b-94b3-0ccb723db09f', 'd5b0c609-7d69-4ca8-99f7-6a8b4366c61c', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('666624a2-0d8c-4a4b-94b3-0ccb723db09f', '6de4807e-73df-4ddd-9500-f66cd694f807', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('666624a2-0d8c-4a4b-94b3-0ccb723db09f', '1eec196f-eaff-42d1-8ef0-94372b6e9858', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('666624a2-0d8c-4a4b-94b3-0ccb723db09f', '48861d2d-ba57-4aff-8e94-e7b4814d0bbd', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('5b320021-ebba-4f17-94ec-1077a4b83e9e', 'c97ab6f7-cd67-4bc2-9a7b-492e05c6b746', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('5b320021-ebba-4f17-94ec-1077a4b83e9e', '3a7e92c6-6aff-4fca-b682-fca92750b4dd', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('5b320021-ebba-4f17-94ec-1077a4b83e9e', '435b02d2-1322-48b3-9003-9ad71f4b989e', 3, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('5b320021-ebba-4f17-94ec-1077a4b83e9e', 'e14dddec-0f85-46fb-8de2-7faf30ed2654', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('11d06991-6a64-4a09-a53a-c0abd6340eab', 'f0372977-ead0-48cd-a251-f3e8627d5d21', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('11d06991-6a64-4a09-a53a-c0abd6340eab', '3f89032f-c45e-44d8-947c-20d822a12ea4', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('11d06991-6a64-4a09-a53a-c0abd6340eab', 'd7cf8c00-ebbf-4aab-83f5-af4e522b350a', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('11d06991-6a64-4a09-a53a-c0abd6340eab', 'f2243a7d-44d6-48a6-a9f3-9aeb1d245bbe', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('c40c099d-73c2-4030-84b6-fe88c9ac2c4f', '1af0dd37-5b10-4523-ac61-c9d4ca7ff194', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('c40c099d-73c2-4030-84b6-fe88c9ac2c4f', 'c3f1fdda-ae40-4469-b9a8-b73445494455', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('c40c099d-73c2-4030-84b6-fe88c9ac2c4f', '66aac8d2-fe46-465a-a5c0-00433b47a6f0', 3, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('c40c099d-73c2-4030-84b6-fe88c9ac2c4f', '1002e89b-92ab-4808-8369-4482e2d61301', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('e8d8ad71-1de2-4034-a137-17bed9af2f3e', 'eba4c852-3fe8-424a-b284-b94a837a191f', 1, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('e8d8ad71-1de2-4034-a137-17bed9af2f3e', '3b037042-af74-4e80-b72e-f9d12a026f8c', 2, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('e8d8ad71-1de2-4034-a137-17bed9af2f3e', '45506cf3-5ef9-402c-808f-d490ce4cd97b', 3, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('e8d8ad71-1de2-4034-a137-17bed9af2f3e', '9b4f2efb-675e-4ace-91cb-b9a2f9a4eadd', 4, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('ef322c79-fcab-44bd-87a8-f0986e9cc4d4', 'c3253fa7-c17c-4574-b9ac-122dcb685e25', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('ef322c79-fcab-44bd-87a8-f0986e9cc4d4', 'd2efce71-199b-4f60-856f-7446babcdf45', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('ef322c79-fcab-44bd-87a8-f0986e9cc4d4', '2c8f0ee2-e25d-4774-9479-7bcc035526d3', 3, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('ef322c79-fcab-44bd-87a8-f0986e9cc4d4', '0d2dc979-2a3c-4604-ab17-d498960ca2a8', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('eee36163-5170-4481-817a-7addad506613', '74de865d-6ef9-496b-bbd1-bf59b4a77293', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('eee36163-5170-4481-817a-7addad506613', '6bd2e618-fa23-4c19-a98e-90228a705db0', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('eee36163-5170-4481-817a-7addad506613', '4e266f55-6a10-470c-8b97-f91e39468905', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('eee36163-5170-4481-817a-7addad506613', 'f380fc01-efa8-4d13-88da-c5457cb4c6f7', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('423549cd-31d7-4284-8afa-f0cf76c702bb', '369310a4-c89f-498f-a083-5dfb5aa7dabe', 1, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('423549cd-31d7-4284-8afa-f0cf76c702bb', 'e1124234-3c6d-418e-ab97-f23a5ddd73dc', 2, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('423549cd-31d7-4284-8afa-f0cf76c702bb', 'ee571349-bdc7-4688-b4fa-7aff2493a979', 3, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('423549cd-31d7-4284-8afa-f0cf76c702bb', '5875eea0-e342-48dd-99f0-759114c4a2f9', 4, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('4124e80b-a327-4592-af13-9a8b3056483b', '9ca21c8a-5f8f-492d-b530-87051fac3c82', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('4124e80b-a327-4592-af13-9a8b3056483b', '5965face-6243-4ed1-9657-f40da460c771', 2, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('4124e80b-a327-4592-af13-9a8b3056483b', '15b3e4d7-291b-4838-9fe9-20ee036bf272', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('4124e80b-a327-4592-af13-9a8b3056483b', '650c91ae-e5fc-4270-b444-287fb9411ae2', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('f7092b76-25ea-4679-b199-d20a417e2b17', '78c5c822-6bc7-4f0e-9f55-f765e0230cb7', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('f7092b76-25ea-4679-b199-d20a417e2b17', '18c13825-b378-4f46-bcee-1eaae8d3d276', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('f7092b76-25ea-4679-b199-d20a417e2b17', '3f65384c-f9c3-414f-90f3-a1f55d3a34c8', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('f7092b76-25ea-4679-b199-d20a417e2b17', 'a2858011-156a-4c6a-95b0-219dfae3a378', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('52879511-ee2a-495c-9b49-015e95c14796', 'f6b6e952-c020-4baa-b1ea-2c7280d7fbb0', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('52879511-ee2a-495c-9b49-015e95c14796', 'd01532a1-b05d-4cda-a092-2c51809aa7ee', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('52879511-ee2a-495c-9b49-015e95c14796', '1431a2d8-24e9-40fc-b4d0-eb8637b4a9ab', 3, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('52879511-ee2a-495c-9b49-015e95c14796', 'c8cf4eb9-1267-489c-88ae-88d95d061954', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('89bd4fd6-a46a-4613-943b-8d69153088ae', '5e4e82dd-85de-4e2c-972d-e2f2f50d5473', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('89bd4fd6-a46a-4613-943b-8d69153088ae', '9260843f-6a9b-4c0b-99ad-7bd6e13c222e', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('89bd4fd6-a46a-4613-943b-8d69153088ae', 'acbb8189-d9ab-411b-b549-4df8032942a2', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('89bd4fd6-a46a-4613-943b-8d69153088ae', '954902cb-d99f-4b06-92dc-c40427bb380f', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('b53e59b2-8a0e-447b-bf63-6ae4ede0a1e8', '882f45d6-b387-4461-a50a-4b01876c13e1', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('b53e59b2-8a0e-447b-bf63-6ae4ede0a1e8', 'c240f849-9bea-4a3f-87d0-fd7efdc44004', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('b53e59b2-8a0e-447b-bf63-6ae4ede0a1e8', '500c9705-e66f-403a-b43f-e12b18427e08', 3, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('b53e59b2-8a0e-447b-bf63-6ae4ede0a1e8', 'd2e0eb87-c9b7-4c49-9571-983cc0681153', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('f4a7531d-b273-4407-897c-fa39c931320b', '8e849922-36d4-42b9-982d-cde5cb97e3df', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('f4a7531d-b273-4407-897c-fa39c931320b', 'c19da3cb-dcf2-4f1c-9596-a2a58f9e6fc5', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('f4a7531d-b273-4407-897c-fa39c931320b', 'd4cb537d-bfad-4740-9c75-2003275addd4', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('f4a7531d-b273-4407-897c-fa39c931320b', '28cd021e-5e43-489f-935f-f916dfd1c7d7', 4, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('25e29a7e-7b1e-48b5-a661-77b3b69129a7', '79e292e5-26f9-4cbd-b791-aee322fcf5e4', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('25e29a7e-7b1e-48b5-a661-77b3b69129a7', 'a44fcd68-21a6-4b29-a68e-287191a07cc2', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('25e29a7e-7b1e-48b5-a661-77b3b69129a7', '0cecfa45-7720-4341-acea-4b070a09f681', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('25e29a7e-7b1e-48b5-a661-77b3b69129a7', 'ecd5833e-6814-4727-ad9b-d7b49321a3da', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('0e5d8acc-25e9-452d-9200-6106269ff8a2', '12814d53-fab4-4803-b031-7e6f1a6a545a', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('0e5d8acc-25e9-452d-9200-6106269ff8a2', 'b6190ed6-28a8-415e-bd9d-0a944444cfe0', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('0e5d8acc-25e9-452d-9200-6106269ff8a2', '49c75108-2252-4c99-9bcf-c4e4d153ee6f', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('0e5d8acc-25e9-452d-9200-6106269ff8a2', '006f76da-e6da-4e78-894b-ccf094d333f6', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('a406e669-9587-4485-8dbc-9a2677c9d4bf', '7c45cd3c-7707-4e98-b1ef-99712c54944a', 1, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('a406e669-9587-4485-8dbc-9a2677c9d4bf', 'c333179b-4b96-4b90-95be-fb78072b695b', 2, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('a406e669-9587-4485-8dbc-9a2677c9d4bf', 'bf56abda-1812-47ea-86b5-0cc4eb869596', 3, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('a406e669-9587-4485-8dbc-9a2677c9d4bf', '99dc6562-144c-4439-a0bf-a0dd70673637', 4, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('293ca5a9-22a2-449c-a4b1-ee0727cafee0', 'eb08414f-1ca9-4cd0-8673-32dc7bc45722', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('293ca5a9-22a2-449c-a4b1-ee0727cafee0', 'a1be0e4d-309f-404e-82e2-c147b237216c', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('293ca5a9-22a2-449c-a4b1-ee0727cafee0', '55b6ec61-f6f7-4320-9b24-cd5ebd168e60', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('293ca5a9-22a2-449c-a4b1-ee0727cafee0', '7af67bc2-d6b1-470b-8d25-1eb013a5d119', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('d7b41041-d925-4bae-bb7d-821da99b5cd9', '192439d0-334b-4476-a707-82eb7a3140cd', 1, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('d7b41041-d925-4bae-bb7d-821da99b5cd9', '1595c0a0-78ba-4ae7-9a14-b925c31f31ab', 2, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('d7b41041-d925-4bae-bb7d-821da99b5cd9', '4d4cdcf9-3aed-4a97-8fc5-37d921e15a8b', 3, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('d7b41041-d925-4bae-bb7d-821da99b5cd9', 'ccf04c01-be55-4ab7-9765-f85ec0549895', 4, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('1d7e1ba6-f1f4-4243-b00e-e115888a873a', 'de44ff27-a589-4730-94a7-757d6aa7b116', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('1d7e1ba6-f1f4-4243-b00e-e115888a873a', 'c9153faa-f437-4b84-bbdf-464d26daac3e', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('1d7e1ba6-f1f4-4243-b00e-e115888a873a', '706f3960-9ba2-468e-bfbd-91d4a7ef5250', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('1d7e1ba6-f1f4-4243-b00e-e115888a873a', 'e8e30811-1dd7-4708-a87c-916f7e755188', 4, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('22d1a4a8-da35-4e3b-ba91-d70a38ee6db1', 'f4e3a4e1-58a7-4e48-8a69-0969160eade6', 1, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('22d1a4a8-da35-4e3b-ba91-d70a38ee6db1', '7656bd49-6cc9-41d3-8dc1-05152446b2bf', 2, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('22d1a4a8-da35-4e3b-ba91-d70a38ee6db1', '0c143c41-eda0-4584-b01a-57d3948f4bee', 3, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('22d1a4a8-da35-4e3b-ba91-d70a38ee6db1', '34747c04-b5b3-4fb5-a54a-5fafe4697d64', 4, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('f4ab8c23-b050-4700-b19b-08317b8ed4fa', '4b5b625a-b5d6-4f73-bcb8-df0581dfdd8f', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('f4ab8c23-b050-4700-b19b-08317b8ed4fa', '7a074b5d-f3b8-42fe-875a-ab123e025daf', 2, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('f4ab8c23-b050-4700-b19b-08317b8ed4fa', 'e870d2b8-21bf-46d8-b7c9-d2cd27b693d5', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('f4ab8c23-b050-4700-b19b-08317b8ed4fa', '0b322a15-4881-4ec0-ad05-7d1e99ace392', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('7edd80c4-c32e-471e-8fce-9c6384ecc45a', '99dc6562-144c-4439-a0bf-a0dd70673637', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('7edd80c4-c32e-471e-8fce-9c6384ecc45a', '3f65384c-f9c3-414f-90f3-a1f55d3a34c8', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('7edd80c4-c32e-471e-8fce-9c6384ecc45a', '1eec196f-eaff-42d1-8ef0-94372b6e9858', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('7edd80c4-c32e-471e-8fce-9c6384ecc45a', 'ee571349-bdc7-4688-b4fa-7aff2493a979', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('719377ff-888f-4756-8a43-157a4708caf4', '28cd021e-5e43-489f-935f-f916dfd1c7d7', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('719377ff-888f-4756-8a43-157a4708caf4', '006f76da-e6da-4e78-894b-ccf094d333f6', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('719377ff-888f-4756-8a43-157a4708caf4', '9260843f-6a9b-4c0b-99ad-7bd6e13c222e', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('719377ff-888f-4756-8a43-157a4708caf4', '4b5b625a-b5d6-4f73-bcb8-df0581dfdd8f', 4, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('67aad726-422c-4b2c-ad6b-6d9d5542d2c7', 'e64bd383-f074-4a86-b6ed-2e00ca12fb53', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('67aad726-422c-4b2c-ad6b-6d9d5542d2c7', 'eb08414f-1ca9-4cd0-8673-32dc7bc45722', 2, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('67aad726-422c-4b2c-ad6b-6d9d5542d2c7', '7c7ad7f1-d83c-4904-8b38-e47ec8881fad', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('67aad726-422c-4b2c-ad6b-6d9d5542d2c7', 'c3253fa7-c17c-4574-b9ac-122dcb685e25', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('3d411981-d7a1-4b1e-b4cb-1351d9f6ed02', '34747c04-b5b3-4fb5-a54a-5fafe4697d64', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('3d411981-d7a1-4b1e-b4cb-1351d9f6ed02', '5965face-6243-4ed1-9657-f40da460c771', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('3d411981-d7a1-4b1e-b4cb-1351d9f6ed02', 'f2243a7d-44d6-48a6-a9f3-9aeb1d245bbe', 3, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('3d411981-d7a1-4b1e-b4cb-1351d9f6ed02', '0d88cae6-a3f8-4683-8ba8-8776b99bb74c', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('afa957b3-0d77-408c-9986-1ef0be2db829', '954902cb-d99f-4b06-92dc-c40427bb380f', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('afa957b3-0d77-408c-9986-1ef0be2db829', '8ec67b91-0c7b-4555-887a-bd26b285fc51', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('afa957b3-0d77-408c-9986-1ef0be2db829', '15b3e4d7-291b-4838-9fe9-20ee036bf272', 3, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('afa957b3-0d77-408c-9986-1ef0be2db829', 'd7cf8c00-ebbf-4aab-83f5-af4e522b350a', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('aac32864-701a-4c51-b15b-55157dfe429a', '5875eea0-e342-48dd-99f0-759114c4a2f9', 1, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('aac32864-701a-4c51-b15b-55157dfe429a', '3f89032f-c45e-44d8-947c-20d822a12ea4', 2, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('aac32864-701a-4c51-b15b-55157dfe429a', '650c91ae-e5fc-4270-b444-287fb9411ae2', 3, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('aac32864-701a-4c51-b15b-55157dfe429a', '78c5c822-6bc7-4f0e-9f55-f765e0230cb7', 4, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('bd56e03b-87e9-4a7d-94c3-6a2aa23b5f42', '16ff8065-871d-40ab-9c8b-7997ff8e07d8', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('bd56e03b-87e9-4a7d-94c3-6a2aa23b5f42', 'a44fcd68-21a6-4b29-a68e-287191a07cc2', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('bd56e03b-87e9-4a7d-94c3-6a2aa23b5f42', '38fba32b-c6c4-40e8-8bf7-b84eea7b74e4', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('bd56e03b-87e9-4a7d-94c3-6a2aa23b5f42', 'de44ff27-a589-4730-94a7-757d6aa7b116', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('ca93b060-777a-4b5b-bb34-5069a076643f', '51353bae-b739-4fe2-94a8-d66dd2759a45', 1, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('ca93b060-777a-4b5b-bb34-5069a076643f', '2c8f0ee2-e25d-4774-9479-7bcc035526d3', 2, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('ca93b060-777a-4b5b-bb34-5069a076643f', 'c333179b-4b96-4b90-95be-fb78072b695b', 3, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('ca93b060-777a-4b5b-bb34-5069a076643f', 'f6b6e952-c020-4baa-b1ea-2c7280d7fbb0', 4, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('27f0f678-5324-44db-b188-ad05d1aee70f', '0b322a15-4881-4ec0-ad05-7d1e99ace392', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('27f0f678-5324-44db-b188-ad05d1aee70f', '45506cf3-5ef9-402c-808f-d490ce4cd97b', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('27f0f678-5324-44db-b188-ad05d1aee70f', '6bd2e618-fa23-4c19-a98e-90228a705db0', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('27f0f678-5324-44db-b188-ad05d1aee70f', '3cef6826-d934-4b3f-a4c7-8e1a1c47eb0b', 4, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('17e1ce03-f4fd-48d5-a33a-3a5291543bb1', '1595c0a0-78ba-4ae7-9a14-b925c31f31ab', 1, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('17e1ce03-f4fd-48d5-a33a-3a5291543bb1', '7a074b5d-f3b8-42fe-875a-ab123e025daf', 2, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('17e1ce03-f4fd-48d5-a33a-3a5291543bb1', '3a7e92c6-6aff-4fca-b682-fca92750b4dd', 3, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('17e1ce03-f4fd-48d5-a33a-3a5291543bb1', 'd2efce71-199b-4f60-856f-7446babcdf45', 4, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('3db1edf5-1da0-4afc-92de-24bd518bdf11', 'a2858011-156a-4c6a-95b0-219dfae3a378', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('3db1edf5-1da0-4afc-92de-24bd518bdf11', 'c4f9f93e-fb1f-4d69-a1dd-9032fc7014ae', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('3db1edf5-1da0-4afc-92de-24bd518bdf11', '74de865d-6ef9-496b-bbd1-bf59b4a77293', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('3db1edf5-1da0-4afc-92de-24bd518bdf11', '12814d53-fab4-4803-b031-7e6f1a6a545a', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('53f70275-0f92-4013-8c91-b288503094d8', '1002e89b-92ab-4808-8369-4482e2d61301', 1, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('53f70275-0f92-4013-8c91-b288503094d8', 'd4cb537d-bfad-4740-9c75-2003275addd4', 2, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('53f70275-0f92-4013-8c91-b288503094d8', 'd01532a1-b05d-4cda-a092-2c51809aa7ee', 3, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('53f70275-0f92-4013-8c91-b288503094d8', 'a1be0e4d-309f-404e-82e2-c147b237216c', 4, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('be3f6d5c-5f8a-4532-9c0b-07d75f39cceb', 'c8cf4eb9-1267-489c-88ae-88d95d061954', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('be3f6d5c-5f8a-4532-9c0b-07d75f39cceb', '4d4cdcf9-3aed-4a97-8fc5-37d921e15a8b', 2, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('be3f6d5c-5f8a-4532-9c0b-07d75f39cceb', '55b6ec61-f6f7-4320-9b24-cd5ebd168e60', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('be3f6d5c-5f8a-4532-9c0b-07d75f39cceb', 'c9153faa-f437-4b84-bbdf-464d26daac3e', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('1c41098f-5667-44a4-b039-047124d899dd', 'f380fc01-efa8-4d13-88da-c5457cb4c6f7', 1, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('1c41098f-5667-44a4-b039-047124d899dd', '7c45cd3c-7707-4e98-b1ef-99712c54944a', 2, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('1c41098f-5667-44a4-b039-047124d899dd', 'ea75fc9b-276b-4826-b4a1-645ad3f1cf29', 3, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('1c41098f-5667-44a4-b039-047124d899dd', '706f3960-9ba2-468e-bfbd-91d4a7ef5250', 4, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('a0f709fd-4774-482c-a404-8a77979bb171', '6de4807e-73df-4ddd-9500-f66cd694f807', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('a0f709fd-4774-482c-a404-8a77979bb171', '195a26cd-d70c-4cac-a924-b3baab6c886d', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('a0f709fd-4774-482c-a404-8a77979bb171', 'd2828084-86fc-49b8-bdc7-ad822e599ae9', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('a0f709fd-4774-482c-a404-8a77979bb171', '1af0dd37-5b10-4523-ac61-c9d4ca7ff194', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('8e9766c6-80d4-4be4-b124-0d57ac976e49', '7af67bc2-d6b1-470b-8d25-1eb013a5d119', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('8e9766c6-80d4-4be4-b124-0d57ac976e49', 'c3f1fdda-ae40-4469-b9a8-b73445494455', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('8e9766c6-80d4-4be4-b124-0d57ac976e49', 'e14dddec-0f85-46fb-8de2-7faf30ed2654', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('8e9766c6-80d4-4be4-b124-0d57ac976e49', '1431a2d8-24e9-40fc-b4d0-eb8637b4a9ab', 4, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('23815db4-ec03-433a-b9df-c950c5c0150c', '66aac8d2-fe46-465a-a5c0-00433b47a6f0', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('23815db4-ec03-433a-b9df-c950c5c0150c', '435b02d2-1322-48b3-9003-9ad71f4b989e', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('23815db4-ec03-433a-b9df-c950c5c0150c', 'c19da3cb-dcf2-4f1c-9596-a2a58f9e6fc5', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('23815db4-ec03-433a-b9df-c950c5c0150c', '7656bd49-6cc9-41d3-8dc1-05152446b2bf', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('7fe1f1ee-821f-4196-abcd-09409eaf444f', 'd01b07dd-99dc-4b9f-8874-6c801b2ab28f', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('7fe1f1ee-821f-4196-abcd-09409eaf444f', 'e870d2b8-21bf-46d8-b7c9-d2cd27b693d5', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('7fe1f1ee-821f-4196-abcd-09409eaf444f', 'e1124234-3c6d-418e-ab97-f23a5ddd73dc', 3, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('7fe1f1ee-821f-4196-abcd-09409eaf444f', '0b0b69d5-8762-4973-b08e-7926e37e173e', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('a7600464-d4a8-4d8e-a00b-e4bc8ed83491', '18c13825-b378-4f46-bcee-1eaae8d3d276', 1, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('a7600464-d4a8-4d8e-a00b-e4bc8ed83491', 'c97ab6f7-cd67-4bc2-9a7b-492e05c6b746', 2, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('a7600464-d4a8-4d8e-a00b-e4bc8ed83491', '0c143c41-eda0-4584-b01a-57d3948f4bee', 3, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('a7600464-d4a8-4d8e-a00b-e4bc8ed83491', '369310a4-c89f-498f-a083-5dfb5aa7dabe', 4, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('4f883ac1-56e0-4ce3-94df-d6203ec42549', 'e8e30811-1dd7-4708-a87c-916f7e755188', 1, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('4f883ac1-56e0-4ce3-94df-d6203ec42549', 'a2ebbc3a-8f4b-42fc-8a6a-cba439805e0e', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('4f883ac1-56e0-4ce3-94df-d6203ec42549', '500c9705-e66f-403a-b43f-e12b18427e08', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('4f883ac1-56e0-4ce3-94df-d6203ec42549', 'ccf04c01-be55-4ab7-9765-f85ec0549895', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('c1dbb41d-a553-453f-9764-019cafbf5fd8', 'd2e0eb87-c9b7-4c49-9571-983cc0681153', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('c1dbb41d-a553-453f-9764-019cafbf5fd8', '3b037042-af74-4e80-b72e-f9d12a026f8c', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('c1dbb41d-a553-453f-9764-019cafbf5fd8', '2cb5667d-d717-4503-a628-46e97e5fa77b', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('c1dbb41d-a553-453f-9764-019cafbf5fd8', '79e292e5-26f9-4cbd-b791-aee322fcf5e4', 4, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('5b5ad1f0-9462-4faa-8a34-6c4779220424', '62635dd9-cabb-45ca-b4e3-55d98dc0c884', 1, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('5b5ad1f0-9462-4faa-8a34-6c4779220424', '48861d2d-ba57-4aff-8e94-e7b4814d0bbd', 2, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('5b5ad1f0-9462-4faa-8a34-6c4779220424', 'acbb8189-d9ab-411b-b549-4df8032942a2', 3, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('5b5ad1f0-9462-4faa-8a34-6c4779220424', 'eba4c852-3fe8-424a-b284-b94a837a191f', 4, 'draw');
INSERT INTO seats (pod, player, seat, result)
VALUES ('31361a5c-3986-445c-b91f-1db3b6d15b2b', 'bf56abda-1812-47ea-86b5-0cc4eb869596', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('31361a5c-3986-445c-b91f-1db3b6d15b2b', '0cecfa45-7720-4341-acea-4b070a09f681', 2, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('31361a5c-3986-445c-b91f-1db3b6d15b2b', '192439d0-334b-4476-a707-82eb7a3140cd', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('31361a5c-3986-445c-b91f-1db3b6d15b2b', 'd5b0c609-7d69-4ca8-99f7-6a8b4366c61c', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('d2423ffc-d8a8-4973-9566-7a36d05c643b', 'c5ad4108-96a6-40c4-8706-8e14ccc39586', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('d2423ffc-d8a8-4973-9566-7a36d05c643b', '5b4f12e1-7556-4049-bdad-a47225882137', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('d2423ffc-d8a8-4973-9566-7a36d05c643b', '50277c6a-6976-40c2-ba0c-103d83e49c52', 3, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('d2423ffc-d8a8-4973-9566-7a36d05c643b', 'c240f849-9bea-4a3f-87d0-fd7efdc44004', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('de150c54-5827-4389-b1da-977465b81cbb', '95856529-983f-48bf-99a5-0bcbe9e39439', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('de150c54-5827-4389-b1da-977465b81cbb', '8e849922-36d4-42b9-982d-cde5cb97e3df', 2, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('de150c54-5827-4389-b1da-977465b81cbb', '5e4e82dd-85de-4e2c-972d-e2f2f50d5473', 3, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('de150c54-5827-4389-b1da-977465b81cbb', '21a30c65-0783-468d-8727-398ecf4dd52e', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('688a7dec-bc3f-4d27-a07a-b120c1279009', '4e266f55-6a10-470c-8b97-f91e39468905', 1, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('688a7dec-bc3f-4d27-a07a-b120c1279009', '728a96e0-b09b-4b5e-97e0-64243b9e3c80', 2, 'win');
INSERT INTO seats (pod, player, seat, result)
VALUES ('688a7dec-bc3f-4d27-a07a-b120c1279009', 'fa198306-4d98-4d10-b3ca-633e69f14b9e', 3, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('688a7dec-bc3f-4d27-a07a-b120c1279009', 'f0372977-ead0-48cd-a251-f3e8627d5d21', 4, 'loss');
INSERT INTO seats (pod, player, seat, result)
VALUES ('6f418467-9ddc-4419-8661-95de3697a9fb', '63aebcf4-a90f-4e53-838f-a9f2bff0276d', 0, 'bye');
INSERT INTO seats (pod, player, seat, result)
VALUES ('6f418467-9ddc-4419-8661-95de3697a9fb', '882f45d6-b387-4461-a50a-4b01876c13e1', 0, 'bye');

-- Testdata for TournamentPlayerStatuse');
INSERT INTO player (id, nickname, firstname, lastname, email)
VALUES ('6e0f80a4-2e91-4e4b-9d41-3e5f2b8f7c9a', 'DragonSlayer', 'Arthur', 'Pendragon',
        'arthur.p@example.com'),
       ('710f2392-0b71-4874-8538-f7fa11cf2a28', 'ShadowStriker', 'Lila', 'Chen',
        'lila.c@example.com'),
       ('0cd0998e-e235-4783-9ddd-0307985796b8', 'MysticMage', 'Elara', 'Vance',
        'elara.v@example.com'),
       ('425077e9-a7a7-4fc1-83d1-54636a6bacfc', 'Ironclad', 'Bjorn', 'Ironside',
        'bjorn.i@example.com'),
       ('06a96ce1-c9e0-41e1-a031-174de1380c30', 'Swiftfoot', 'Chloe', 'Decker',
        'chloe.d@example.com'),
       ('8b4a2d0b-47c5-4091-91a1-087e4a3d40d7', 'NightBlade', 'Ren', 'Sakurai',
        'ren.s@example.com'),
       ('3692448c-928a-4548-8c01-a4d0a05fb3ef', 'Sunfire', 'Sophia', 'Gonzales',
        'sophia.g@example.com'),
       ('5b23d685-4124-435b-b826-8cc8e8d5f8a1', 'BoulderBro', 'Magnus', 'Stone',
        'magnus.s@example.com'),
       ('1e70cc4a-decc-46f8-959d-c15798cbc28f', 'StarGazer', 'Luna', 'Moon', 'luna.m@example.com'),
       ('8e1e0232-a8eb-4449-8359-bf7a39a9c6d7', 'Thunderstrike', 'Kai', 'Storm',
        'kai.s@example.com');
INSERT INTO tournament (id, name, mode)
VALUES ('1a2b3c4d-5e6f-47a8-8b9c-0d1e2f3a4b5c', 'Unified Grand Tournament', 'Commander');

INSERT INTO tournamentplayers (tournament_id, player_id, score, status)
VALUES ('1a2b3c4d-5e6f-47a8-8b9c-0d1e2f3a4b5c', '6e0f80a4-2e91-4e4b-9d41-3e5f2b8f7c9a', 1500.000,
        'active'),       -- DragonSlayer
       ('1a2b3c4d-5e6f-47a8-8b9c-0d1e2f3a4b5c', '710f2392-0b71-4874-8538-f7fa11cf2a28', 0.000,
        'registered'),   -- ShadowStriker
       ('1a2b3c4d-5e6f-47a8-8b9c-0d1e2f3a4b5c', '0cd0998e-e235-4783-9ddd-0307985796b8', 1200.500,
        'active'),       -- MysticMage
       ('1a2b3c4d-5e6f-47a8-8b9c-0d1e2f3a4b5c', '425077e9-a7a7-4fc1-83d1-54636a6bacfc', 800.000,
        'dropped'),      -- Ironclad
       ('1a2b3c4d-5e6f-47a8-8b9c-0d1e2f3a4b5c', '06a96ce1-c9e0-41e1-a031-174de1380c30', 0.000,
        'registered'),   -- Swiftfoot
       ('1a2b3c4d-5e6f-47a8-8b9c-0d1e2f3a4b5c', '8b4a2d0b-47c5-4091-91a1-087e4a3d40d7', 50.000,
        'disqualified'), -- NightBlade
       ('1a2b3c4d-5e6f-47a8-8b9c-0d1e2f3a4b5c', '3692448c-928a-4548-8c01-a4d0a05fb3ef', 1350.250,
        'active'),       -- Sunfire
       ('1a2b3c4d-5e6f-47a8-8b9c-0d1e2f3a4b5c', '5b23d685-4124-435b-b826-8cc8e8d5f8a1', 0.000,
        'registered'),   -- BoulderBro
       ('1a2b3c4d-5e6f-47a8-8b9c-0d1e2f3a4b5c', '1e70cc4a-decc-46f8-959d-c15798cbc28f', 900.000,
        'dropped'),      -- StarGazer
       ('1a2b3c4d-5e6f-47a8-8b9c-0d1e2f3a4b5c', '8e1e0232-a8eb-4449-8359-bf7a39a9c6d7', 1100.000,
        'active'); -- ThunderstrikeThunderstrike