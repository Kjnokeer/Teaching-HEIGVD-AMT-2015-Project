-- MySQL Script generated by MySQL Workbench
-- 10/02/15 09:57:49
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema moussaraser
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `moussaraser` ;

-- -----------------------------------------------------
-- Schema moussaraser
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `moussaraser` DEFAULT CHARACTER SET utf8 ;
USE `moussaraser` ;

-- -----------------------------------------------------
-- Table `moussaraser`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `moussaraser`.`users` ;

CREATE TABLE IF NOT EXISTS `moussaraser`.`users` (
  `useId` INT(11) NOT NULL AUTO_INCREMENT,
  `useEmail` VARCHAR(255) NOT NULL,
  `usePassword` CHAR(64) NOT NULL,
  `useFirstName` VARCHAR(25) NOT NULL,
  `useLastName` VARCHAR(25) NOT NULL,
  `useRegistrationDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`useId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `moussaraser`.`Role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `moussaraser`.`Role` ;

CREATE TABLE IF NOT EXISTS `moussaraser`.`Role` (
  `rolId` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`rolId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `moussaraser`.`ApiKey`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `moussaraser`.`ApiKey` ;

CREATE TABLE IF NOT EXISTS `moussaraser`.`ApiKey` (
  `apiId` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`apiId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `moussaraser`.`Application`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `moussaraser`.`Application` ;

CREATE TABLE IF NOT EXISTS `moussaraser`.`Application` (
  `appId` INT NOT NULL AUTO_INCREMENT,
  `ApiKey_apiId` INT NOT NULL,
  `users_useId` INT(11) NOT NULL,
  PRIMARY KEY (`appId`, `ApiKey_apiId`, `users_useId`),
  INDEX `fk_Application_ApiKey_idx` (`ApiKey_apiId` ASC),
  INDEX `fk_Application_users1_idx` (`users_useId` ASC),
  CONSTRAINT `fk_Application_ApiKey`
    FOREIGN KEY (`ApiKey_apiId`)
    REFERENCES `moussaraser`.`ApiKey` (`apiId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Application_users1`
    FOREIGN KEY (`users_useId`)
    REFERENCES `moussaraser`.`users` (`useId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `moussaraser`.`endUser`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `moussaraser`.`endUser` ;

CREATE TABLE IF NOT EXISTS `moussaraser`.`endUser` (
  `endId` INT NOT NULL AUTO_INCREMENT,
  `Application_appId` INT NOT NULL,
  PRIMARY KEY (`endId`, `Application_appId`),
  INDEX `fk_endUser_Application1_idx` (`Application_appId` ASC),
  CONSTRAINT `fk_endUser_Application1`
    FOREIGN KEY (`Application_appId`)
    REFERENCES `moussaraser`.`Application` (`appId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `moussaraser`.`users_has_Role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `moussaraser`.`users_has_Role` ;

CREATE TABLE IF NOT EXISTS `moussaraser`.`users_has_Role` (
  `users_useId` INT(11) NOT NULL,
  `Role_rolId` INT NOT NULL,
  PRIMARY KEY (`users_useId`, `Role_rolId`),
  INDEX `fk_users_has_Role_Role1_idx` (`Role_rolId` ASC),
  INDEX `fk_users_has_Role_users1_idx` (`users_useId` ASC),
  CONSTRAINT `fk_users_has_Role_users1`
    FOREIGN KEY (`users_useId`)
    REFERENCES `moussaraser`.`users` (`useId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_Role_Role1`
    FOREIGN KEY (`Role_rolId`)
    REFERENCES `moussaraser`.`Role` (`rolId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
