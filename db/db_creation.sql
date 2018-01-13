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
  `Password` VARCHAR(256) NULL,
  PRIMARY KEY (`UserName`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `audio_orders`.`Discount`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audio_orders`.`Discount` (
  `Percent` DOUBLE NOT NULL,
  `DateBegin` TIMESTAMP(6) NULL,
  `DateEnd` TIMESTAMP(6) NULL,
  PRIMARY KEY (`Percent`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `audio_orders`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audio_orders`.`User` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `AuthInfo_UserName` VARCHAR(256) NOT NULL,
  `LastName` VARCHAR(256) NULL,
  `FirstName` VARCHAR(256) NULL,
  `Role` ENUM('ADMIN', 'CLIENT', 'UNKNOWN') NOT NULL,
  `Discount_percent` DOUBLE NOT NULL,
  PRIMARY KEY (`Id`, `AuthInfo_UserName`),
  INDEX `fk_User_AuthInfo1_idx` (`AuthInfo_UserName` ASC),
  INDEX `fk_User_Discount1_idx` (`Discount_percent` ASC),
  CONSTRAINT `fk_User_AuthInfo1`
    FOREIGN KEY (`AuthInfo_UserName`)
    REFERENCES `audio_orders`.`AuthInfo` (`UserName`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_User_Discount1`
    FOREIGN KEY (`Discount_percent`)
    REFERENCES `audio_orders`.`Discount` (`Percent`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `audio_orders`.`Track`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audio_orders`.`Track` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Track` VARCHAR(256) NULL,
  `Artist` VARCHAR(256) NOT NULL,
  `Album` VARCHAR(256) NULL,
  `Popularity` INT NULL,
  `URI` VARCHAR(256) NOT NULL,
  `Price` DOUBLE NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `uri_UNIQUE` (`URI` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `audio_orders`.`Order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audio_orders`.`Order` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `TotalPrice` VARCHAR(256) NULL,
  `Status` ENUM('SUBMITTED', 'REJECTED', 'COMPLETED') NOT NULL,
  `User_id` INT NOT NULL,
  `Date` TIMESTAMP(6) NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_Order_User1_idx` (`User_id` ASC),
  CONSTRAINT `fk_Order_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `audio_orders`.`User` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `audio_orders`.`TrackOrder`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audio_orders`.`TrackOrder` (
  `Track_id` INT NOT NULL,
  `Order_id` INT NOT NULL,
  INDEX `fk_TrackOrder_Track1_idx` (`Track_id` ASC),
  INDEX `fk_TrackOrder_Order1_idx` (`Order_id` ASC),
  CONSTRAINT `fk_TrackOrder_Track1`
    FOREIGN KEY (`Track_id`)
    REFERENCES `audio_orders`.`Track` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_TrackOrder_Order1`
    FOREIGN KEY (`Order_id`)
    REFERENCES `audio_orders`.`Order` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `audio_orders`.`TrackFeedback`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audio_orders`.`TrackFeedback` (
  `Comment` VARCHAR(256) NULL,
  `Date` TIMESTAMP(6) NOT NULL,
  `Track_id` INT NOT NULL,
  `User_Id` INT NOT NULL,
  INDEX `fk_TrackFeedback_Track1_idx` (`Track_id` ASC),
  INDEX `fk_TrackFeedback_User1_idx` (`User_Id` ASC),
  CONSTRAINT `fk_TrackFeedback_Track1`
    FOREIGN KEY (`Track_id`)
    REFERENCES `audio_orders`.`Track` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_TrackFeedback_User1`
    FOREIGN KEY (`User_Id`)
    REFERENCES `audio_orders`.`User` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
