-- liquibase formatted sql
-- changeset lnowogorski:20
create table users(
    id bigint generated always as identity not null,
    username varchar(50) unique not null,
    password varchar(500) not null
);

alter table users add created boolean default false;

-- liquibase formatted sql
-- changeset lnowogorski:21
create table authorities(
    username varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key(username) references users(username)
);

-- liquibase formatted sql
-- changeset lnowogorski:22
create unique index ix_auth_username on authorities(username, authority);