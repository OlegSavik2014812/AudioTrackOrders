-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema audio_orders
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema audio_orders
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `audio_orders` DEFAULT CHARACTER SET utf8 ;
USE `audio_orders` ;

-- -----------------------------------------------------
-- Table `audio_orders`.`AuthInfo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audio_orders`.`AuthInfo` (
  `UserName` VARCHAR(256) NOT NULL,
  `Password` VARCHAR(256) NULL DEFAULT NULL,
  PRIMARY KEY (`UserName`),
  UNIQUE INDEX `UserName_UNIQUE` (`UserName` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `audio_orders`.`Discount`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audio_orders`.`Discount` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `Percent` DOUBLE NOT NULL,
  `DateBegin` TIMESTAMP(6) NOT NULL,
  `DateEnd` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(),
  PRIMARY KEY (`Id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `audio_orders`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audio_orders`.`User` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `UserName` VARCHAR(256) NOT NULL,
  `LastName` VARCHAR(256) NULL DEFAULT NULL,
  `FirstName` VARCHAR(256) NULL DEFAULT NULL,
  `Role` ENUM('ADMIN', 'CLIENT', 'UNKNOWN') NOT NULL,
  `IdDiscount` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`, `UserName`),
  INDEX `fk_User_AuthInfo_idx` (`UserName` ASC),
  INDEX `fk_User_Discount1_idx` (`IdDiscount` ASC),
  CONSTRAINT `fk_User_AuthInfo`
    FOREIGN KEY (`UserName`)
    REFERENCES `audio_orders`.`AuthInfo` (`UserName`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_Discount1`
    FOREIGN KEY (`IdDiscount`)
    REFERENCES `audio_orders`.`Discount` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `audio_orders`.`Track`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audio_orders`.`Track` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `Track` VARCHAR(256) NULL DEFAULT NULL,
  `Artist` VARCHAR(256) NOT NULL,
  `Album` VARCHAR(256) NULL DEFAULT NULL,
  `Popularity` INT(11) NULL DEFAULT NULL,
  `URI` VARCHAR(256) NULL,
  `Price` DOUBLE NULL DEFAULT NULL,
  `Duration` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `audio_orders`.`Purchase`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audio_orders`.`Purchase` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `TotalPrice` DOUBLE NULL DEFAULT NULL,
  `Status` ENUM('SUBMITTED', 'REJECTED', 'COMPLETED') NOT NULL,
  `IdUser` INT(11) NOT NULL,
  `Date` TIMESTAMP(6) NOT NULL,
  INDEX `fk_Purchase_User1_idx` (`IdUser` ASC),
  PRIMARY KEY (`Id`),
  CONSTRAINT `fk_Purchase_User1`
    FOREIGN KEY (`IdUser`)
    REFERENCES `audio_orders`.`User` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `audio_orders`.`TrackOrder`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audio_orders`.`TrackOrder` (
  `IdTrack` INT(11) NOT NULL,
  `IdPurchase` INT(11) NOT NULL,
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Id`),
  INDEX `fk_TrackOrder_Purchase1_idx` (`IdPurchase` ASC),
  INDEX `fk_TrackOrder_Track1_idx` (`IdTrack` ASC),
  CONSTRAINT `fk_TrackOrder_Purchase1`
    FOREIGN KEY (`IdPurchase`)
    REFERENCES `audio_orders`.`Purchase` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TrackOrder_Track1`
    FOREIGN KEY (`IdTrack`)
    REFERENCES `audio_orders`.`Track` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `audio_orders`.`TrackFeedback`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audio_orders`.`TrackFeedback` (
  `Comment` VARCHAR(256) NULL DEFAULT NULL,
  `IdTrack` INT(11) NOT NULL,
  `IdUser` INT(11) NOT NULL,
  `Date` TIMESTAMP(6) NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(),
  `Id` INT NOT NULL AUTO_INCREMENT,
  INDEX `fk_TrackFeedback_Track1_idx` (`IdTrack` ASC),
  INDEX `fk_TrackFeedback_User1_idx` (`IdUser` ASC),
  PRIMARY KEY (`Id`),
  CONSTRAINT `fk_TrackFeedback_Track1`
    FOREIGN KEY (`IdTrack`)
    REFERENCES `audio_orders`.`Track` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TrackFeedback_User1`
    FOREIGN KEY (`IdUser`)
    REFERENCES `audio_orders`.`User` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
