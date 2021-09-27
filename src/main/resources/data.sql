SELECT 1;

--CREATE TABLE `USER`(
--    id BIGINT(20) NOT NULL PRIMARY KEY,
--    name VARCHAR(255),
--    document VARCHAR(255) NOT NULL UNIQUE,
--    login VARCHAR(255) NOT NULL UNIQUE,
--    password VARCHAR(255) NOT NULL
--);
--
--CREATE TABLE BOOK(
--    id BIGINT(20) NOT NULL PRIMARY KEY,
--    user_id BIGINT(20) NOT NULL,
--    date_from DATETIME NOT NULL,
--    date_to DATETIME NOT NULL,
--    request_date  DATETIME NOT NULL,
--    KEY `FK_USER_ID` (`user_id`),
--    CONSTRAINT `FK_USER_ID` FOREIGN KEY (`user_id`) REFERENCES `USER` (`id`)
--)