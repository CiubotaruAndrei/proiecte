CREATE TABLE `medication` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `side_effects` VARCHAR(250) NOT NULL,
  `dosage` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE `medication_plan` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `patient_id` INT NOT NULL,
  `start` DATETIME NOT NULL,
  `end` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `patient_fk`
    FOREIGN KEY (`patient_id`)
    REFERENCES `patient` (`id`))ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE `intake_med` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `medplan_id` INT NOT NULL,
  `medication_id` INT NOT NULL,
  `intake_moments` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `medication_fk`
    FOREIGN KEY (`medication_id`)
    REFERENCES `medication` (`id`),
  CONSTRAINT `medplan_fk`
    FOREIGN KEY (`medplan_id`)
    REFERENCES `medication_plan` (`id`))ENGINE=InnoDB DEFAULT CHARSET=UTF8;