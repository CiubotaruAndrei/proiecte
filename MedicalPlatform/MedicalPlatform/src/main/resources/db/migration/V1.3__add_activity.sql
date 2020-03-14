CREATE TABLE `activity` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `patient_id` INT NOT NULL,
  `start` DATETIME NOT NULL,
  `end` DATETIME NOT NULL,
  `activity` VARCHAR (45) NOT NULL,
  `unusual` TINYINT(1) NOT NULL,
  `recommendation` VARCHAR(255) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `p_fk`
    FOREIGN KEY (`patient_id`)
    REFERENCES `patient` (`id`))ENGINE=InnoDB DEFAULT CHARSET=UTF8;