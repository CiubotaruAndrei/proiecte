CREATE TABLE `medication_monitor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `patient_id` INT NOT NULL,
  `med_name` VARCHAR(45) NOT NULL,
  `expected_time` VARCHAR(45) NOT NULL,
  `actual_time` VARCHAR(45) NOT NULL,
  `taken` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `p2_fk`
    FOREIGN KEY (`patient_id`)
    REFERENCES `patient` (`id`))ENGINE=InnoDB DEFAULT CHARSET=UTF8;