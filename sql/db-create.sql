-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema periodicals_system
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema periodicals_system
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `periodicals_system` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
-- -----------------------------------------------------
-- Schema periodicals_system
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema periodicals_system
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `periodicals_system` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `periodicals_system` ;

-- -----------------------------------------------------
-- Table `periodicals_system`.`user_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `periodicals_system`.`user_role` ;

CREATE TABLE IF NOT EXISTS `periodicals_system`.`user_role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `periodicals_system`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `periodicals_system`.`user` ;

CREATE TABLE IF NOT EXISTS `periodicals_system`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(16) NOT NULL,
  `email` VARCHAR(255) NULL,
  `password` VARCHAR(32) NOT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `blocked` BIT(1) NULL,
  `user_role_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_user_role_idx` (`user_role_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_user_role`
    FOREIGN KEY (`user_role_id`)
    REFERENCES `periodicals_system`.`user_role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `periodicals_system`.`topic`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `periodicals_system`.`topic` ;

CREATE TABLE IF NOT EXISTS `periodicals_system`.`topic` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `periodicals_system`.`publication`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `periodicals_system`.`publication` ;

CREATE TABLE IF NOT EXISTS `periodicals_system`.`publication` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `topic_id` INT NOT NULL,
  `price` DECIMAL(10,2) NULL,
  `text` VARCHAR(5000) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_publication_topic1_idx` (`topic_id` ASC) VISIBLE,
  CONSTRAINT `fk_publication_topic1`
    FOREIGN KEY (`topic_id`)
    REFERENCES `periodicals_system`.`topic` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `periodicals_system`.`account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `periodicals_system`.`account` ;

CREATE TABLE IF NOT EXISTS `periodicals_system`.`account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `amount` DECIMAL(10,2) NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_account_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_account_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `periodicals_system`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `periodicals_system`.`reader_publication`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `periodicals_system`.`reader_publication` ;

CREATE TABLE IF NOT EXISTS `periodicals_system`.`reader_publication` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `publication_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `price` DECIMAL(10,2) NULL,
  `subscribe_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_reader_publication_publication1_idx` (`publication_id` ASC) VISIBLE,
  INDEX `fk_reader_publication_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_reader_publication_publication1`
    FOREIGN KEY (`publication_id`)
    REFERENCES `periodicals_system`.`publication` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reader_publication_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `periodicals_system`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

USE `periodicals_system` ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO user (id, username, password, create_time, user_role_id)
VALUES (1,'Admin', '123', CURRENT_TIMESTAMP, 1);
