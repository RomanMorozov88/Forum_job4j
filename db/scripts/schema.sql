create table users
(
    name     varchar(100) primary key,
    password varchar(100)
);

create table roles
(
    name varchar(100) primary key
);

create table users_roles
(
    urId      serial primary key,
    role_name varchar(100),
    user_name varchar(100),
    foreign key (user_name) references users (name),
    foreign key (role_name) references roles (name)
);

create table posts
(
    id          serial primary key,
    author      varchar(100),
    name        varchar(2000),
    description text,
    created     timestamp without time zone not null default now(),
    foreign key (author) references users (name)
);

insert into roles
values ('ROLE_ADMIN'),
       ('ROLE_USER');

-- Password: 123
insert into users
values ('Test_01', '$2a$10$C30c2OM997I9akJt0b3eouI0C4H1SZuCN22EjRiuCxlXQaYuDi1GC');

insert into users_roles (role_name, user_name)
values ('ROLE_USER', 'Test_01');

insert into posts (author, name, description)
values ('Test_01', 'Test_Post_01', 'Test_Post_Description_01');