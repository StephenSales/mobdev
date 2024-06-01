CREATE TABLE tblUser (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username TEXT NOT NULL,
    password TEXT NOT NULL,
    firstname TEXT,
    lastname TEXT,
    email TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE tblEvent (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name TEXT NOT NULL,
    description TEXT,
    organizer_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (organizer_id) REFERENCES tblUser(id)
);

CREATE TABLE tblParticipant (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    event_id BIGINT,
    joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES tblUser(id),
    FOREIGN KEY (event_id) REFERENCES tblEvent(id)
);

CREATE TABLE tblTags (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name TEXT NOT NULL
);

CREATE TABLE tblEventTags (
    event_id BIGINT,
    tag_id BIGINT,
    PRIMARY KEY (event_id, tag_id),
    FOREIGN KEY (event_id) REFERENCES tblEvent(id),
    FOREIGN KEY (tag_id) REFERENCES tblTags(id)
);

CREATE TABLE tblRating (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    event_id BIGINT,
    rating INT CHECK (rating >= 1 AND rating <= 5),
    message TEXT,
    rated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES tblUser(id),
    FOREIGN KEY (event_id) REFERENCES tblEvent(id)
);