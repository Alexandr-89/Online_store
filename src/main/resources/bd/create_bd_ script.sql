-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema online_store1
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema online_store1
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `online_store1` DEFAULT CHARACTER SET utf8 ;


-- -----------------------------------------------------
-- Table `mydb`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `online_store1`.`orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

USE `online_store1` ;

-- -----------------------------------------------------
-- Table `online_store1`.`products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `online_store1`.`products` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `manufacturer` VARCHAR(45) NULL,
  `description` TEXT NULL DEFAULT NULL,
  `price` DOUBLE NULL DEFAULT NULL,
  `count` INT NULL DEFAULT NULL,
  `status` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 55
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `online_store1`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `online_store1`.`users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NULL DEFAULT NULL,
  `password` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `role` VARCHAR(45) NULL DEFAULT NULL,
  `status` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `user_email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `online_store1`.`carts_products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `online_store1`.`carts_products` (
  `users_id` BIGINT NOT NULL,
  `products_id` BIGINT NOT NULL,
  `carts_products_count` BIGINT NOT NULL,
  `carts_products_sum` DOUBLE NULL,
  INDEX `fk_carts_users1_idx` (`users_id` ASC) VISIBLE,
  CONSTRAINT `fk_carts_product_products1`
    FOREIGN KEY (`products_id`)
    REFERENCES `online_store1`.`products` (`id`),
  CONSTRAINT `fk_carts_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `online_store1`.`users` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `online_store1`.`users_details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `online_store1`.`users_details` (
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `surname` VARCHAR(45) NULL DEFAULT NULL,
  `address` VARCHAR(45) NULL DEFAULT NULL,
  `phone` VARCHAR(45) NULL DEFAULT NULL,
  `users_id` BIGINT NOT NULL,
  PRIMARY KEY (`users_id`),
  INDEX `fk_users_details_users_idx` (`users_id` ASC) VISIBLE,
  CONSTRAINT `fk_users_details_users`
    FOREIGN KEY (`users_id`)
    REFERENCES `online_store1`.`users` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `online_store1`.`users_has_orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `online_store1`.`users_has_orders` (
  `users_id` BIGINT NOT NULL,
  `orders_id` INT NOT NULL,
  PRIMARY KEY (`users_id`, `orders_id`),
  INDEX `fk_users_has_orders_orders1_idx` (`orders_id` ASC) VISIBLE,
  INDEX `fk_users_has_orders_users1_idx` (`users_id` ASC) VISIBLE,
  CONSTRAINT `fk_users_has_orders_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `online_store1`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_orders_orders1`
    FOREIGN KEY (`orders_id`)
    REFERENCES `online_store1`.`orders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `online_store1`.`ordered_products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `online_store1`.`ordered_products` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `products_id` BIGINT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `manufacturer` VARCHAR(45) NULL DEFAULT NULL,
  `price` DOUBLE NULL DEFAULT NULL,
  `count` BIGINT NULL,
  `sum` DOUBLE NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 55
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `online_store1`.`ordered_products_has_orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `online_store1`.`ordered_products_has_orders` (
  `ordered_products_id` BIGINT NOT NULL,
  `orders_id` INT NOT NULL,
  PRIMARY KEY (`ordered_products_id`, `orders_id`),
  INDEX `fk_ordered_products_has_orders_orders1_idx` (`orders_id` ASC) VISIBLE,
  INDEX `fk_ordered_products_has_orders_ordered_products1_idx` (`ordered_products_id` ASC) VISIBLE,
  CONSTRAINT `fk_ordered_products_has_orders_ordered_products1`
    FOREIGN KEY (`ordered_products_id`)
    REFERENCES `online_store1`.`ordered_products` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ordered_products_has_orders_orders1`
    FOREIGN KEY (`orders_id`)
    REFERENCES `online_store1`.`orders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
