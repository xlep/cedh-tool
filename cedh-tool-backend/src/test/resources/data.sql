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