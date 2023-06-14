create table if not exists news
(
    id               bigserial primary key,
    title            varchar(255) not null,
    text             text not null,
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

