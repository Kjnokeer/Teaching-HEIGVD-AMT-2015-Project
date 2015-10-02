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

-- -----------------------------------------------------
-- Schema moussaraser
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `moussaraser` DEFAULT CHARACTER SET utf8 ;
USE `moussaraser` ;

-- -----------------------------------------------------
-- Table `moussaraser`.`users`
-- -----------------------------------------------------
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
CREATE TABLE IF NOT EXISTS `moussaraser`.`Role` (
  `rolId` INT NOT NULL AUTO_INCREMENT,
  `rolName` VARCHAR(45) NOT NULL,
  `rolDescription` VARCHAR(255) NULL,
  PRIMARY KEY (`rolId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `moussaraser`.`ApiKey`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moussaraser`.`ApiKey` (
  `apiId` INT NOT NULL AUTO_INCREMENT,
  `apikey` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`apiId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `moussaraser`.`Application`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moussaraser`.`Application` (
  `appId` INT NOT NULL AUTO_INCREMENT,
  `appName` VARCHAR(45) NOT NULL,
  `appDescription` VARCHAR(255) NULL,
  `appState` TINYINT(1) NOT NULL,
  `appCreationDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ApiKey_apiId` INT NOT NULL,
  `users_useId` INT(11) NOT NULL,
  PRIMARY KEY (`appId`),
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
CREATE TABLE IF NOT EXISTS `moussaraser`.`endUser` (
  `endId` INT NOT NULL AUTO_INCREMENT,
  `Application_appId` INT NOT NULL,
  PRIMARY KEY (`endId`),
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

-- -----------------------------------------------------
-- Data for table `moussaraser`.`users`
-- -----------------------------------------------------
START TRANSACTION;
USE `moussaraser`;
INSERT INTO `moussaraser`.`users` (`useId`, `useEmail`, `usePassword`, `useFirstName`, `useLastName`, `useRegistrationDate`) VALUES (1, 'mathias.dolt@heig-vd.ch', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 'Mathias', 'Dolt', NULL);
INSERT INTO `moussaraser`.`users` (`useId`, `useEmail`, `usePassword`, `useFirstName`, `useLastName`, `useRegistrationDate`) VALUES (2, 'thibaud.duchoud@heig-vd.ch', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'Thibaud', 'Duchoud', NULL);
INSERT INTO `moussaraser`.`users` (`useId`, `useEmail`, `usePassword`, `useFirstName`, `useLastName`, `useRegistrationDate`) VALUES (3, 'jerome.moret@heig-vd.ch', 'd74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1', 'Jérôme', 'Moret', NULL);
INSERT INTO `moussaraser`.`users` (`useId`, `useEmail`, `usePassword`, `useFirstName`, `useLastName`, `useRegistrationDate`) VALUES (4, 'mario.ferreira@heig-vd.ch', 'fe2592b42a727e977f055947385b709cc82b16b9a87f88c6abf3900d65d0cdc3', 'Mario', 'Ferreira', NULL);

COMMIT;

