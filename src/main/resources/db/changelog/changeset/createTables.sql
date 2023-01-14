--liquibase formatted sql
--changeset kruglov:createTables

CREATE TABLE roles (
    id serial PRIMARY KEY,
    name varchar(100)
);

CREATE TABLE users (
    id serial PRIMARY KEY,
    login varchar(180),
    password varchar(180),
    firstname varchar(180),
    lastname varchar(100),
    status varchar(100),
    biography varchar(180),
    photoname varchar(180),
    role_id INT REFERENCES roles(id)
);

CREATE TABLE chat_rooms (
    id serial PRIMARY KEY,
    chat_id varchar(180),
    host_id varchar(180),
    slave_id varchar(180)
);

CREATE TABLE messages (
    id serial PRIMARY KEY,
    chat_id varchar(180),
    sender_id varchar(180),
    recipient_id varchar(180),
    senderName varchar(180),
    recipientName varchar(180),
    content varchar(180),
    "timestamp" timestamp without time zone
);

INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_CLIENT');
INSERT INTO users (login, password, firstname, role_id) VALUES ('test', '$2a$12$W4lg8KxcNczNbztRu.X9zu1SBWP4kk7ZPpKUmGm/9Cx0kWs1685pS', 'test', 1);
INSERT INTO users (login, password, firstname, role_id) VALUES ('lola', '$2a$12$W4lg8KxcNczNbztRu.X9zu1SBWP4kk7ZPpKUmGm/9Cx0kWs1685pS', 'lola', 2);
INSERT INTO users (login, password, firstname, role_id) VALUES ('cola', '$2a$12$W4lg8KxcNczNbztRu.X9zu1SBWP4kk7ZPpKUmGm/9Cx0kWs1685pS', 'lila', 2);
INSERT INTO users (login, password, firstname, role_id) VALUES ('zheka', '$2a$12$W4lg8KxcNczNbztRu.X9zu1SBWP4kk7ZPpKUmGm/9Cx0kWs1685pS', 'pola', 2);