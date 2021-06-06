# SQL for creating tables in database

DBMS : MySQL    
Version: 8.0.23

```
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema caps
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema caps
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `caps` DEFAULT CHARACTER SET utf8 ;
USE `caps` ;

-- -----------------------------------------------------
-- Table `caps`.`code`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caps`.`code` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `areacode` INT NULL DEFAULT NULL,
  `areaname_kor` VARCHAR(100) NULL DEFAULT NULL,
  `areaname_eng` VARCHAR(45) NULL DEFAULT NULL,
  `listcount` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 232
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `caps`.`final_place`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caps`.`final_place` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(1000) NULL DEFAULT NULL,
  `address1` VARCHAR(1000) NULL DEFAULT NULL,
  `area_code` INT NULL DEFAULT NULL,
  `first_image` VARCHAR(2000) NULL DEFAULT NULL,
  `first_image2` VARCHAR(2000) NULL DEFAULT NULL,
  `map_x` VARCHAR(100) NULL DEFAULT NULL,
  `map_y` VARCHAR(100) NULL DEFAULT NULL,
  `overview` LONGTEXT NULL DEFAULT NULL,
  `cat1` VARCHAR(100) NULL DEFAULT NULL,
  `cat2` VARCHAR(100) NULL DEFAULT NULL,
  `cat3` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `caps`.`words`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caps`.`words` (
  `id` INT NOT NULL,
  `word_kor` VARCHAR(100) NULL DEFAULT NULL,
  `word_eng` VARCHAR(100) NULL DEFAULT NULL,
  `audio` LONGTEXT NULL DEFAULT NULL,
  `image` LONGTEXT NULL DEFAULT NULL,
  `pronunciation_kor` VARCHAR(100) NULL DEFAULT NULL,
  `pronunciation_eng` VARCHAR(100) NULL DEFAULT NULL,
  `part_of_speech` VARCHAR(40) NULL DEFAULT NULL,
  `meaning_kor1` VARCHAR(1000) NULL DEFAULT NULL,
  `meaning_eng1` VARCHAR(1000) NULL DEFAULT NULL,
  `meaning_kor2` VARCHAR(1000) NULL DEFAULT NULL,
  `meaning_eng2` VARCHAR(1000) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `caps`.`final_mapping`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caps`.`final_mapping` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `place_id` INT NOT NULL,
  `word_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_final_mapping_final_place1_idx` (`place_id` ASC) VISIBLE,
  INDEX `fk_final_mapping_words1_idx` (`word_id` ASC) VISIBLE,
  CONSTRAINT `fk_final_mapping_final_place1`
    FOREIGN KEY (`place_id`)
    REFERENCES `caps`.`final_place` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_final_mapping_words1`
    FOREIGN KEY (`word_id`)
    REFERENCES `caps`.`words` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 10192
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `caps`.`final_place_korean`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caps`.`final_place_korean` (
  `id` INT NOT NULL,
  `kor_overview` LONGTEXT NULL DEFAULT NULL,
  `place_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_final_place_korean_final_place_idx` (`place_id` ASC) VISIBLE,
  CONSTRAINT `fk_final_place_korean_final_place`
    FOREIGN KEY (`place_id`)
    REFERENCES `caps`.`final_place` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `caps`.`todays_place`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caps`.`todays_place` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `place1_id` INT NULL DEFAULT NULL,
  `place2_id` INT NULL DEFAULT NULL,
  `place3_id` INT NULL DEFAULT NULL,
  `created_at` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 41
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `caps`.`home`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caps`.`home` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created_at` DATETIME NULL DEFAULT NULL,
  `word_id` INT NOT NULL,
  `todays_place_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_home_todays_place1_idx` (`todays_place_id` ASC) VISIBLE,
  CONSTRAINT `fk_home_todays_place1`
    FOREIGN KEY (`todays_place_id`)
    REFERENCES `caps`.`todays_place` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 51
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `caps`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caps`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(500) NOT NULL,
  `nickname` VARCHAR(45) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(20) NOT NULL,
  `last_login_at` DATETIME NULL DEFAULT NULL,
  `week_attendance` INT NULL DEFAULT NULL,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 59
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `caps`.`map_file`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caps`.`map_file` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `file_name` VARCHAR(255) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `size` INT NOT NULL,
  `original_name` VARCHAR(255) NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `file_name_UNIQUE` (`file_name` ASC) VISIBLE,
  INDEX `fk_map_file_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_map_file_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `caps`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 22
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `caps`.`my_place`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caps`.`my_place` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(45) NULL DEFAULT NULL,
  `diary` VARCHAR(1000) NULL DEFAULT NULL,
  `user_id` INT NOT NULL,
  `place_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_my_place_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_my_place_final_place1_idx` (`place_id` ASC) VISIBLE,
  CONSTRAINT `fk_my_place_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `caps`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_my_place_final_place1`
    FOREIGN KEY (`place_id`)
    REFERENCES `caps`.`final_place` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 58
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `caps`.`my_test_result`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caps`.`my_test_result` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NOT NULL,
  `correct_number` INT NULL DEFAULT NULL,
  `wrong_number` INT NULL DEFAULT NULL,
  `total_number` INT NULL DEFAULT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_my_test_result_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_my_test_result_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `caps`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 23
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `caps`.`my_word_folder`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caps`.`my_word_folder` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `folder_name` VARCHAR(100) NULL DEFAULT NULL,
  `word_count` INT NULL DEFAULT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_my_word_folder_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_my_word_folder_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `caps`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 93
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `caps`.`my_word`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caps`.`my_word` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `word_status` VARCHAR(45) NULL DEFAULT NULL,
  `word_id` INT NOT NULL,
  `my_word_folder_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_my_word_words1_idx` (`word_id` ASC) VISIBLE,
  INDEX `fk_my_word_my_word_folder1_idx` (`my_word_folder_id` ASC) VISIBLE,
  CONSTRAINT `fk_my_word_words1`
    FOREIGN KEY (`word_id`)
    REFERENCES `caps`.`words` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_my_word_my_word_folder1`
    FOREIGN KEY (`my_word_folder_id`)
    REFERENCES `caps`.`my_word_folder` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 111
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `caps`.`region_color`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caps`.`region_color` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `areacode` VARCHAR(45) NULL DEFAULT NULL,
  `region_color` VARCHAR(45) NULL DEFAULT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_region_color_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_region_color_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `caps`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 145
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

```
