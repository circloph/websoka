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
    name varchar(180),
    role_id INT REFERENCES roles(id)
);

INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_CLIENT');
INSERT INTO users (login, password, name, role_id) VALUES ('test', '$2a$12$W4lg8KxcNczNbztRu.X9zu1SBWP4kk7ZPpKUmGm/9Cx0kWs1685pS', 'lolka', 1);
INSERT INTO users (login, password, name, role_id) VALUES ('lola', '$2a$12$W4lg8KxcNczNbztRu.X9zu1SBWP4kk7ZPpKUmGm/9Cx0kWs1685pS', 'lalka', 2);