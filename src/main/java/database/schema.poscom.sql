CREATE TABLE tbl_comments
(
    id              BIGINT(20)      NOT NULL
        primary key,
    post_id         BIGINT(20)      NOT NULL,
    user_id         BIGINT(20)      NOT NULL,
    description     VARCHAR(200)    NOT NULL,
    points          BIGINT(10)      NULL,

);

CREATE TABLE tbl_posts
(
    id                  BIGINT(20)      NOT NULL
        primary key,
    user_id             BIGINT(20)      NOT NULL,
    title               VARCHAR(20)     NOT NULL,
    description         VARCHAR(200)    NULL,
    image_url           VARCHAR(500)    NULL,
    points              BIGINT(10)      NULL,
    num_of_comments     BIGINT(10)      NULL
);

CREATE TABLE tbl_users
(
    id                  BIGINT(20)      NOT NULL
        primary key,
    profile_image_url   VARCHAR(500)    NULL,
    username            VARCHAR(20)     NOT NULL,
    profile_description VARCHAR(150)    NULL,
);
