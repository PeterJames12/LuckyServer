
USE `luckydb`;

--------------------------------------------------
-- SPRING OAUTH 2 DATA TABLES:
--------------------------------------------------
--
-- Init oauth 2 client
--
LOCK TABLES `oauth_client_details` WRITE;
-- password = abc123
INSERT INTO `oauth_client_details` VALUES (1, 0, 'android_client','', '$2a$10$/FL5CXV81xqs.XxuJKpKw.cNHngTFAvWXqNkjRakuLAOh9ve0bPDu', 'read, write, trust', 'password,refresh_token', '', '', 60, NULL, '{}', '');
UNLOCK TABLES;