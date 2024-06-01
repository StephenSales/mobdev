-- Insert users
INSERT INTO tblUser (username, password, firstname, lastname, email) VALUES
('alice', 'password123', 'Alice', 'Smith', 'alice@example.com'),
('bob', 'securepassword', 'Bob', 'Brown', 'bob@example.com'),
('carol', 'mypassword', 'Carol', 'Jones', 'carol@example.com');

-- Insert events
INSERT INTO tblEvent (name, description, organizer_id) VALUES
('Music Concert', 'An amazing music concert', 1),
('Tech Conference', 'A conference on the latest in tech', 2),
('Art Exhibition', 'Exhibition of modern art', 3);

-- Insert participants
INSERT INTO tblParticipant (user_id, event_id) VALUES
(1, 1),
(2, 1),
(3, 2),
(1, 2),
(2, 3);

-- Insert tags
INSERT INTO tblTags (name) VALUES
('Music'),
('Technology'),
('Art'),
('Conference'),
('Exhibition');

-- Insert event tags
INSERT INTO tblEventTags (event_id, tag_id) VALUES
(1, 1),
(2, 2),
(2, 4),
(3, 3),
(3, 5);

-- Insert ratings
INSERT INTO tblRating (user_id, event_id, rating, message) VALUES
(1, 1, 5, 'Amazing event!'),
(2, 1, 4, 'Really enjoyed it.'),
(3, 2, 3, 'It was okay.'),
(1, 2, 4, 'Informative and interesting.'),
(2, 3, 5, 'Loved the art pieces!');
