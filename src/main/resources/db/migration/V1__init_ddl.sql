
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
  `password` VARCHAR(256) NOT NULL,
  `wallet` VARCHAR(255) NULL,
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
  wallet_newbie VARCHAR(255),
  wallet_experienced VARCHAR(255),
  wallet_professional VARCHAR(255),
  payment_token VARCHAR(255),
  version BIGINT(20)
);
CREATE UNIQUE INDEX wallet_id_uindex ON wallet (id);

INSERT INTO luckydb.wallet (wallet_newbie, wallet_experienced, wallet_professional,payment_token)
VALUES ('0xbADA6A89904D26E6a1C950d63e4ba27FE81B4829',
        '0xD00Ede3745d80F885d0B5bf71C80BD70034949a1',
        '0x90B4F43b617bE3A5D947389921EE25f1f7c39A07',
        'bwVWnVpApArVBZaBhiiT');

--
-- Table structure for table `transaction_helper`
--

create table transaction_helper
(
  id bigint auto_increment
    primary key,
  newbie_key varchar(255) null,
  experinced_key varchar(255) null,
  professinal_key varchar(255) null,
  version bigint null,
  constraint transaction_helper_id_uindex
  unique (id)
)
;

INSERT INTO luckydb.transaction_helper (newbie_key, experinced_key, professinal_key)
VALUES ('45011486745383349022177747511271840274488129481166194675961184862743256395463',
        '60519587378760102136937969982456925448408430677102026929324810446819317644546',
        '18421872506324793142967855904780638493365101708850749274883395753524590819077');

--
-- Table structure for table `transaction_history`
--

create table transaction_history
(
  id bigint auto_increment
    primary key,
  version bigint null,
  data VARCHAR(255) NULL,
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

--
-- Table structure for table `out_transaction`
--

create table out_transaction
(
  id bigint auto_increment
    primary key,
  version bigint null,
  data TIMESTAMP DEFAULT current_timestamp(),
  ether VARCHAR(255) null,
  winner_address varchar(255) null
)
;

--
-- Table structure for table `jackpot`
--

CREATE TABLE jackpot (
  id BIGINT AUTO_INCREMENT
  PRIMARY KEY,
  vesion BIGINT NULL,
  address VARCHAR(255) NULL,
  count BIGINT NULL
)
;

--
-- Table structure for table `founders`
--

CREATE TABLE founders (
  id BIGINT AUTO_INCREMENT
  PRIMARY KEY,
  version BIGINT NULL,
  username VARCHAR(255) NULL,
  address VARCHAR(255) NULL,
  added_date TIMESTAMP DEFAULT current_timestamp()
);


