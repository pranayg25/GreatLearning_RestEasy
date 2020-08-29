DROP DATABASE IF EXISTS `greatlearning_resteasy`;

CREATE SCHEMA `greatlearning_resteasy` ;

USE `greatlearning_resteasy` ;

CREATE TABLE `greatlearning_resteasy`.`user` (
  `id` VARCHAR(256) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `greatlearning_resteasy`.`dish` (
  `id` VARCHAR(256) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `calories` INT NOT NULL,
  PRIMARY KEY (`id`));

  CREATE TABLE `greatlearning_resteasy`.`vendor` (
  `id` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `greatlearning_resteasy`.`vendor_dish` (
  `id` VARCHAR(45) NOT NULL,
  `vendorId` VARCHAR(45) NOT NULL,
  `dishId` VARCHAR(45) NOT NULL,
  `price` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `vendorId_idx` (`vendorId` ASC) VISIBLE,
  INDEX `dishId_idx` (`dishId` ASC) VISIBLE,
  CONSTRAINT `vendorId`
    FOREIGN KEY (`vendorId`)
    REFERENCES `greatlearning_resteasy`.`vendor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `dishId`
    FOREIGN KEY (`dishId`)
    REFERENCES `greatlearning_resteasy`.`dish` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

    
CREATE TABLE `greatlearning_resteasy`.`customer_order` (
  `id` VARCHAR(45) NOT NULL,
  `customerId` VARCHAR(45) NOT NULL,
  `orderDate` TIMESTAMP NOT NULL,
  `totalAmount` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `customerId_idx` (`customerId` ASC) VISIBLE,
  CONSTRAINT `customerId`
    FOREIGN KEY (`customerId`)
    REFERENCES `greatlearning_resteasy`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `greatlearning_resteasy`.`orderline` (
  `id` VARCHAR(45) NOT NULL,
  `vendorDishId` VARCHAR(45) NOT NULL,
  `quantity` INT NOT NULL,
  `orderId` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `vendorDishId_idx` (`vendorDishId` ASC) VISIBLE,
  INDEX `orderId_idx` (`orderId` ASC) VISIBLE,
  CONSTRAINT `vendorDishId`
    FOREIGN KEY (`vendorDishId`)
    REFERENCES `greatlearning_resteasy`.`vendor_dish` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `orderorderId`
	  FOREIGN KEY (`orderId`)
	  REFERENCES `greatlearning_resteasy`.`customer_order` (`id`)
	  ON DELETE NO ACTION
	  ON UPDATE NO ACTION);





