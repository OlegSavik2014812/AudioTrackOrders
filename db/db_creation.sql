-- -----------------------------------------------------
-- Schema audio_orders
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `audio_orders`
  DEFAULT CHARACTER SET utf8;

USE `audio_orders`;

CREATE TABLE AuthInfo
(
  UserName VARCHAR(256) NOT NULL
    PRIMARY KEY,
  Password VARCHAR(256) NULL,
  CONSTRAINT UserName_UNIQUE
  UNIQUE (UserName)
);

CREATE TABLE User
(
  Id        BIGINT AUTO_INCREMENT,
  UserName  VARCHAR(256)                        NOT NULL,
  LastName  VARCHAR(256)                        NULL,
  FirstName VARCHAR(256)                        NULL,
  Role      ENUM ('ADMIN', 'CLIENT', 'UNKNOWN') NOT NULL,
  PRIMARY KEY (Id, UserName),
  CONSTRAINT fk_User_AuthInfo
  FOREIGN KEY (UserName) REFERENCES AuthInfo (UserName)
);

CREATE TABLE Discount
(
  Id       BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  Percent  DOUBLE    NOT NULL,
  DateFrom TIMESTAMP NULL,
  DateTo   TIMESTAMP NULL,
  IdUser   BIGINT    NOT NULL
);

CREATE INDEX fk_discount_user_id
  ON Discount (IdUser);

CREATE TABLE Feedback
(
  Id      BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  Comment VARCHAR(256) NULL,
  IdTrack BIGINT       NOT NULL,
  IdUser  BIGINT       NOT NULL,
  Created TIMESTAMP    NULL
);

CREATE INDEX fk_feedback_user_id
  ON Feedback (IdUser);

CREATE INDEX fk_feedback_track_id
  ON Feedback (IdTrack);

CREATE TABLE `Order`
(
  Id         BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  TotalPrice DECIMAL(13, 4)                              NULL,
  Status     ENUM ('SUBMITTED', 'REJECTED', 'COMPLETED') NOT NULL,
  IdUser     BIGINT                                      NOT NULL,
  Date       TIMESTAMP                                   NULL
);

CREATE INDEX fk_order_user_id
  ON `Order` (IdUser);


CREATE TABLE `audio_orders`.`package` (
  `Id`   BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(256) NULL     DEFAULT NULL,
  PRIMARY KEY (`Id`)
);

CREATE TABLE Track
(
  Id         BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  Track      VARCHAR(256)   NULL,
  Artist     VARCHAR(256)   NOT NULL,
  Album      VARCHAR(256)   NULL,
  Popularity INT            NULL,
  URI        VARCHAR(256)   NULL,
  Price      DECIMAL(13, 4) NULL,
  Duration   BIGINT         NULL
);

ALTER TABLE Feedback
  ADD CONSTRAINT fk_feedback_track_id
FOREIGN KEY (IdTrack) REFERENCES Track (Id);

CREATE TABLE TrackOrder
(
  Id      BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  IdTrack BIGINT NOT NULL,
  IdOrder BIGINT NOT NULL,
  CONSTRAINT fk_trackorder_track_id
  FOREIGN KEY (IdTrack) REFERENCES Track (Id),
  CONSTRAINT fk_trackorder_order_id
  FOREIGN KEY (IdOrder) REFERENCES `Order` (Id)
);
CREATE TABLE `audio_orders`.`packagetrack` (
  `Id`        BIGINT(20) NOT NULL AUTO_INCREMENT,
  `IdTrack`   BIGINT(20) NOT NULL,
  `IdPackage` BIGINT(20) NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `package_fk_idx` (`IdPackage` ASC),
  INDEX `track_fk_idx` (`IdTrack` ASC),
  CONSTRAINT `package_fk`
  FOREIGN KEY (`IdPackage`)
  REFERENCES `audio_orders`.`package` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `track_fk`
  FOREIGN KEY (`IdTrack`)
  REFERENCES `audio_orders`.`track` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);


CREATE INDEX fk_trackorder_track_id
  ON TrackOrder (IdTrack);

CREATE INDEX fk_trackorder_order_id
  ON TrackOrder (IdOrder);


CREATE INDEX fk_User_AuthInfo_idx
  ON User (UserName);

ALTER TABLE Discount
  ADD CONSTRAINT fk_discount_user_id
FOREIGN KEY (IdUser) REFERENCES User (Id);

ALTER TABLE Feedback
  ADD CONSTRAINT fk_feedback_user_id
FOREIGN KEY (IdUser) REFERENCES User (Id);

ALTER TABLE `Order`
  ADD CONSTRAINT fk_order_user_id
FOREIGN KEY (IdUser) REFERENCES User (Id);

