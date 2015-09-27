ALTER TABLE `algonz`.`comunidad` ADD COLUMN `IdUsuario` INT(10) NULL COMMENT 'Identificador del usuario responsable de la comunidad'  AFTER `TE_OBSERVACIONES` ;


ALTER TABLE `algonz`.`comunidad` 
  ADD CONSTRAINT `FK_COMUNIDAD_USUARIO`
  FOREIGN KEY (`IdUsuario` )
  REFERENCES `algonz`.`usuario` (`IdUsuario` )
  ON DELETE RESTRICT
  ON UPDATE RESTRICT
, ADD INDEX `ID_USUARIO` (`IdUsuario` ASC) ;
