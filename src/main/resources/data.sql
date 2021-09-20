CREATE TABLE `USER`(
    id INT PRIMARY KEY,
    name VARCHAR(255),
    document VARCHAR(255) UNIQUE,
    login VARCHAR(255),
    password VARCHAR(255)
);