CREATE TABLE `doctor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `birth_date` DATETIME NOT NULL,
  `gender` VARCHAR(10) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE `caregiver` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `birth_date` DATETIME NOT NULL,
  `gender` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE `patient` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `birth_date` DATETIME NOT NULL,
  `gender` VARCHAR(10) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `medical_record` VARCHAR(300) NOT NULL,
  `doctor_id` INT NOT NULL,
  `caregiver_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `doctor_fk`
    FOREIGN KEY (`doctor_id`)
    REFERENCES `doctor` (`id`),
  CONSTRAINT `caregiver_fk`
    FOREIGN KEY (`caregiver_id`)
    REFERENCES `caregiver` (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=UTF8;