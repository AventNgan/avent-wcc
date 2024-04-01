create schema if not exists avent_wcc;

use avent_wcc;

create table if not exists `postcode_lat_lng`
(
    `id`        int(11)        not null auto_increment,
    `postcode`  varchar(8)     not null unique,
    `latitude`  decimal(10, 7) null,
    `longitude` decimal(10, 7) null,
    `created_date` timestamp not null,
    `updated_date` timestamp not null,
    primary key (`id`)
) engine = myisam
  default charset = latin1
  auto_increment = 1;

CREATE INDEX ix_postcode ON postcode_lat_lng
    (postcode);

create table if not exists `postcode_request_log`
(
    `id`           int(11)        not null auto_increment,
    `request_id`   varchar(255)   not null,
    `postcode`     varchar(8)     not null,
    `latitude`     decimal(10, 7) null,
    `longitude`    decimal(10, 7) null,
    `created_date` timestamp      not null,
    `updated_date` timestamp     not null,
    primary key (`id`)
) engine = myisam
  default charset = latin1
  auto_increment = 1;

CREATE INDEX ix_request_id ON postcode_request_log
    (request_id);

create table if not exists `audit_trail`
(
    `id`           int(11)        not null auto_increment,
    `details`      text           not null,
    `username`   varchar(255)   not null,
    `created_date` timestamp      not null,
    `updated_date` timestamp     not null,
    primary key (`id`)
) engine = myisam
  default charset = latin1
  auto_increment = 1;

CREATE INDEX ix_username ON audit_trail
    (username);

create table if not exists `user_credential`
(
    `username`  varchar(255)   not null,
    `password`  varchar(255)   not null,
    `created_date` timestamp not null,
    `updated_date` timestamp not null
)

