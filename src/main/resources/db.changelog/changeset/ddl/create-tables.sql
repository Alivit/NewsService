create table if not exists news
(
    id               bigserial primary key,
    title            varchar(255) not null,
    text             text not null,
    username         varchar(255) not null,
    update_date_news timestamp(6),
    create_date_news timestamp(6)
);

create table if not exists comments
(
    id                  bigserial primary key,
    username            varchar(255) not null,
    text                varchar(255) not null,
    update_date_comment timestamp(6),
    create_date_comment timestamp(6),
    news_id             bigint not null,
    constraint fk_news_comments foreign key (news_id) references news (id) on delete cascade on update no action
);

create table if not exists users
(
    id               bigserial primary key,
    username         varchar(255) not null,
    password         varchar(255) not null,
    date_of_registry timestamp(6),
    active           boolean default true

);

create table if not exists roles
(
    user_id bigint not null,
    roles   varchar(255) not null,
    constraint fk_users_roles foreign key (user_id) references users (id) on delete cascade on update no action
);

