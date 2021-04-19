CREATE
DATABASE IF NOT EXISTS spring_security;

CREATE TABLE IF NOT EXISTS user
(
    id
    INT
    NOT
    NULL
    AUTO_INCREMENT,
    username
    VARCHAR
(
    255
) NOT NULL,
    password VARCHAR
(
    255
) NOT NULL,
    role ENUM
(
    'ADMIN',
    'USER'
) NOT NULL,
    PRIMARY KEY
(
    id
)
    );

INSERT INTO user(username, password, role)
VALUES ('frank', '$2y$12$yHwHXWgwJZSc0S2pxBbQa.jdsN3IwZQUta5A4PYengj8Mm5DWIWcG', 'ADMIN');
INSERT INTO user(username, password, role)
VALUES ('henry', '$2y$12$DqvSeQM1/stE97h8aYAK..W9hZMKFOY6L81S2QUDxsKzGUsrxVQ.e', 'USER');
INSERT INTO user(username, password, role)
VALUES ('bean', '$2y$12$cqPU8pQTrnBzQSKfOxk2.O8rX4MGZygBbFEOyhJ4KM61OmGTwTHbm', 'USER');