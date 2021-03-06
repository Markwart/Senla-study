-- MySQL Script generated by MySQL Workbench
-- Sat Aug 10 18:07:15 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema DB-task-2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema DB-task-2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `DB-task-2` DEFAULT CHARACTER SET utf8 ;
USE `DB-task-2` ;

-- -----------------------------------------------------
-- Table `DB-task-2`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DB-task-2`.`product` (
  `maker` VARCHAR(50) NOT NULL,
  `model` VARCHAR(50) NOT NULL,
  `type` VARCHAR(50) NOT NULL,
  UNIQUE INDEX `model_UNIQUE` (`model` ASC) VISIBLE,
  PRIMARY KEY (`model`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DB-task-2`.`pc`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DB-task-2`.`pc` (
  `code` INT NOT NULL,
  `model` VARCHAR(50) NOT NULL,
  `speed` INT NOT NULL,
  `ram` INT NOT NULL,
  `hd` REAL NOT NULL,
  `cd` VARCHAR(10) NOT NULL,
  `price` DECIMAL NULL,
  PRIMARY KEY (`code`),
  INDEX `model_id_idx` (`model` ASC) VISIBLE,
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE,
  CONSTRAINT `pc_to_product(model)`
    FOREIGN KEY (`model`)
    REFERENCES `DB-task-2`.`product` (`model`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DB-task-2`.`laptop`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DB-task-2`.`laptop` (
  `code` INT NOT NULL,
  `model` VARCHAR(50) NOT NULL,
  `speed` INT NOT NULL,
  `ram` INT NOT NULL,
  `hd` REAL NOT NULL,
  `price` DECIMAL NULL,
  `screen` TINYINT(100) NOT NULL,
  UNIQUE INDEX `cade_UNIQUE` (`code` ASC) VISIBLE,
  PRIMARY KEY (`code`),
  INDEX `model_idx` (`model` ASC) VISIBLE,
  CONSTRAINT `laptop_to_product(model)`
    FOREIGN KEY (`model`)
    REFERENCES `DB-task-2`.`product` (`model`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DB-task-2`.`printer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DB-task-2`.`printer` (
  `code` INT NOT NULL,
  `model` VARCHAR(50) NOT NULL,
  `color` CHAR(1) NOT NULL,
  `type` VARCHAR(10) NOT NULL,
  `price` DECIMAL NULL,
  PRIMARY KEY (`code`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE,
  INDEX `model_idx` (`model` ASC) VISIBLE,
  CONSTRAINT `printer_to_product(model)`
    FOREIGN KEY (`model`)
    REFERENCES `DB-task-2`.`product` (`model`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
