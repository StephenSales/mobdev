-- Insert seed data into tblUser
INSERT INTO tblUser (username, email, password, firstname, lastname, aboutMe)
VALUES
('john_doe', 'john@example.com', 'password123', 'John', 'Doe', 'I love organizing events.'),
('jane_smith', 'jane@example.com', 'password123', 'Jane', 'Smith', 'Music enthusiast and event planner.'),
('alice_jones', 'alice@example.com', 'password123', 'Alice', 'Jones', 'Tech events are my specialty.'),
('bob_brown', 'bob@example.com', 'password123', 'Bob', 'Brown', 'Passionate about community meetups.'),
('charlie_clark', 'charlie@example.com', 'password123', 'Charlie', 'Clark', 'Organizer of educational workshops.');

-- Insert seed data into tblEvent
INSERT INTO tblEvent (name, description, location, event_date, price, organizer_id)
VALUES
('Tech Conference 2024', 'A conference about the latest in technology.', 'San Francisco, CA', '2024-07-15 09:00:00', 299.99, 1),
('Music Festival 2024', 'A festival featuring various music genres.', 'Austin, TX', '2024-08-20 12:00:00', 149.99, 2),
('AI Workshop', 'A workshop on artificial intelligence and machine learning.', 'New York, NY', '2024-09-10 10:00:00', 199.99, 3),
('Community Meetup', 'A local community meetup for networking and fun.', 'Chicago, IL', '2024-07-25 18:00:00', 0.00, 4),
('Educational Workshop', 'A workshop on educational methodologies and tools.', 'Boston, MA', '2024-08-05 09:00:00', 99.99, 5);

-- Insert seed data into tblParticipant
INSERT INTO tblParticipant (user_id, event_id)
VALUES
(1, 2),
(2, 1),
(3, 3),
(4, 4),
(5, 5),
(1, 3),
(2, 4),
(3, 5),
(4, 1),
(5, 2);

-- Insert seed data into tblTags
INSERT INTO tblTags (name)
VALUES
('Technology'),
('Music'),
('Education'),
('Community'),
('Workshop');

-- Insert seed data into tblEventTags
INSERT INTO tblEventTags (event_id, tag_id)
VALUES
(1, 1),
(2, 2),
(3, 1),
(3, 5),
(4, 4),
(5, 3),
(5, 5);

-- Insert seed data into tblRating
INSERT INTO tblRating (user_id, event_id, rating, message)
VALUES
(1, 2, 5, 'Amazing event! Loved the music.'),
(2, 1, 4, 'Very informative conference.'),
(3, 3, 5, 'Great workshop on AI.'),
(4, 4, 4, 'Nice community meetup.'),
(5, 5, 5, 'Educational and fun workshop.'),
(1, 3, 4, 'Good content, well-organized.'),
(2, 4, 3, 'Had a good time, but could be better.'),
(3, 5, 5, 'Very informative and engaging.'),
(4, 1, 4, 'Good conference, learned a lot.'),
(5, 2, 5, 'Loved the music and the vibe.');

-- Insert seed data into tblFollow
INSERT INTO tblFollow (follower_id, followed_id)
VALUES
(1, 2),
(2, 3),
(3, 4),
(4, 5),
(5, 1),
(1, 3),
(2, 4),
(3, 5),
(4, 1),
(5, 2);

-- Assume John created an event (id=1) and Jane created an event (id=2)
INSERT INTO tblNotification (user_id, message) VALUES
(1, 'The user you are following has posted a new event: Tech Conference'), -- Alice notified about John's event
(1, 'The user you are following has posted a new event: Tech Conference'), -- Bob notified about John's event
(1, 'The user you are following has posted a new event: Music Festival'); -- Alice notified about Jane's event
