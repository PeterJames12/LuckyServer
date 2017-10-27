
USE `luckydb`;

--------------------------------------------------
-- SPRING OAUTH 2 DATA TABLES:
--------------------------------------------------
--
-- Table structure for table `oauth_client_details`
--
create table `oauth_client_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `version` BIGINT(20) NOT NULL DEFAULT 0,
  client_id VARCHAR(256),
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER(32),
  refresh_token_validity INTEGER(32),
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256),
  PRIMARY KEY (`id`)
);
--ENGINE = InnoDB
--DEFAULT CHARACTER SET = utf8;



--
-- Table structure for table `oauth_client_token`
--
create table `oauth_client_token` (
  token_id VARCHAR(256),
  token BLOB,
  authentication_id VARCHAR(256),
  user_name VARCHAR(256),
  client_id VARCHAR(256)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


--
-- Table structure for table `oauth_refresh_token`
--
create table `oauth_refresh_token` (
  token_id VARCHAR(256),
  token BLOB,
  authentication BLOB
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;



--
-- Table structure for table `oauth_access_token`
--
create table `oauth_access_token` (
  token_id VARCHAR(256),
  token BLOB,
  authentication_id VARCHAR(256),
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication BLOB,
  refresh_token VARCHAR(256)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;



--
-- Table structure for table `oauth_code`
--
create table `oauth_code` (
  code VARCHAR(256), authentication BLOB
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

--------------------------------------------------
-- USER DATA TABLES:
--------------------------------------------------

--
-- Table structure for table `user`
--
CREATE TABLE `user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `version` BIGINT(20) NOT NULL DEFAULT 0,
  `email` VARCHAR(50) NOT NULL UNIQUE,
  `wallet` VARCHAR(256) NOT NULL,
  `password` VARCHAR(256) NOT NULL,
  `enabled` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

--
-- Table structure for table `role`
--
CREATE TABLE `role`(
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `version` BIGINT(20) NOT NULL DEFAULT 0,
  `name` VARCHAR(50) NOT NULL,
  `user_id` BIGINT(20) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `role_user_cs`
  FOREIGN KEY (`user_id`)
  REFERENCES `user` (`id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

--
-- Table structure for table `email_code`
--

create table email_code
(
  id bigint auto_increment
    primary key,
  email varchar(66) null,
  code int null,
  version bigint null,
  constraint email_code_id_uindex
  unique (id)
);

--
-- Table structure for table `wallet`
--

CREATE TABLE wallet
(
  id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  wallet_junior VARCHAR(255),
  wallet_middle VARCHAR(255),
  wallet_senior VARCHAR(255),
  version BIGINT(20)
);
CREATE UNIQUE INDEX wallet_id_uindex ON wallet (id);

--
-- Table structure for table `transaction_helper`
--

create table transaction_helper
(
  id bigint auto_increment
    primary key,
  private_key varchar(255) null,
  version bigint null,
  constraint transaction_helper_id_uindex
  unique (id)
)
;

--
-- Table structure for table `transaction_history`
--

create table transaction_history
(
  id bigint auto_increment
    primary key,
  version bigint null,
  data tinyblob null,
  ether varchar(255) null,
  transaction_hash VARCHAR(255) NULL,
  sender_address varchar(255) null
)
;

--
-- Table structure for table `newbie`
--

create table newbie
(
  id bigint auto_increment
    primary key,
  version bigint null,
  address varchar(255) null
)
;

--
-- Table structure for table `experienced`
--

create table experienced
(
  id bigint auto_increment
    primary key,
  version bigint null,
  address varchar(255) null
)
;

--
-- Table structure for table `professional`
--

create table professional
(
  id bigint auto_increment
    primary key,
  version bigint null,
  address varchar(255) null
)
;

