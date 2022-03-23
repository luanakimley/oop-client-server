
DROP DATABASE IF EXISTS `badminton_db`;
CREATE DATABASE `badminton_db`;
USE `badminton_db`;
DROP TABLE IF EXISTS `player`;
CREATE TABLE `player` (
                        `player_id` INT NOT NULL AUTO_INCREMENT,
                        `name` VARCHAR(50) NOT NULL,
                        `nationality` VARCHAR(50) NOT NULL,
                        `date_of_birth` DATE NOT NULL,
                        `height` DECIMAL(10,2) NOT NULL,
                        `sector` ENUM('MENS_DOUBLE', 'WOMENS_DOUBLE', 'MENS_SINGLES', 'WOMENS_SINGLE', 'MIXED_DOUBLES') NOT NULL,
                        `world_rank` INT NOT NULL,
                        PRIMARY KEY  (`player_id`)
);

INSERT INTO `player` VALUES (null, "Kevin Sanjaya Sukamuljo", "Indonesia", "1995-08-02", 1.70, "MENS_DOUBLE", 1),
                        (null, "Viktor Axelsen", "Denmark", "1994-01-04", 1.94, "MENS_SINGLES", 1),
                        (null, "Tai Tzu Ying", "Chinese Taipei", "1994-06-20", 1.63, "WOMENS_SINGLE", 1),
                        (null, "Praveen Jordan", "Indonesia", "1993-04-26", 1.63, "MIXED_DOUBLES", 5),
                        (null, "Carolina Marin", "Spain", "1993-06-15", 1.72, "WOMENS_SINGLE", 6),
                        (null, "Chou Tien Chen", "Chinese Taipei", "1990-01-08", 1.8, "MENS_SINGLES", 4),
                        (null, "Anders Antonsen", "Denmark", "1997-04-27", 1.83, "MENS_SINGLES", 3),
                        (null, "Chen Qing Chen", "China", "1997-06-23", 1.64, "WOMENS_DOUBLE", 1),
                        (null, "Lee Zii Jia", "Malaysia", "1998-03-29", 1.86, "MENS_SINGLES", 7),
                        (null, "Greysia Polii", "Indonesia", "1987-08-11", 1.6, "WOMENS_DOUBLE", 6);

