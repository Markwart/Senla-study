-- -----------------------------------------------------
-- Data for table `DB-task-2`.`product`
-- -----------------------------------------------------
START TRANSACTION;
USE `DB-task-2`;
INSERT INTO `DB-task-2`.`product` (`maker`, `model`, `type`) VALUES ('Samsung', 'PC-X1', 'pc');
INSERT INTO `DB-task-2`.`product` (`maker`, `model`, `type`) VALUES ('Sony', 'L-S1', 'laptop');
INSERT INTO `DB-task-2`.`product` (`maker`, `model`, `type`) VALUES ('Asus', 'PR-A1', 'printer');
INSERT INTO `DB-task-2`.`product` (`maker`, `model`, `type`) VALUES ('Samsung', 'PR-X2', 'printer');
INSERT INTO `DB-task-2`.`product` (`maker`, `model`, `type`) VALUES ('Sony', 'PC-S1', 'pc');
INSERT INTO `DB-task-2`.`product` (`maker`, `model`, `type`) VALUES ('Asus', 'L-A2', 'laptop');
INSERT INTO `DB-task-2`.`product` (`maker`, `model`, `type`) VALUES ('Sony', 'PR-S2', 'printer');
INSERT INTO `DB-task-2`.`product` (`maker`, `model`, `type`) VALUES ('Asus', 'PC-A1', 'pc');
INSERT INTO `DB-task-2`.`product` (`maker`, `model`, `type`) VALUES ('Asus', 'L-A1', 'laptop');
INSERT INTO `DB-task-2`.`product` (`maker`, `model`, `type`) VALUES ('Asus', 'PC-A2', 'pc');
INSERT INTO `DB-task-2`.`product` (`maker`, `model`, `type`) VALUES ('Samsung', 'PC-X2', 'pc');
INSERT INTO `DB-task-2`.`product` (`maker`, `model`, `type`) VALUES ('Samsung', 'PC-X3', 'pc');

COMMIT;


-- -----------------------------------------------------
-- Data for table `DB-task-2`.`pc`
-- -----------------------------------------------------
START TRANSACTION;
USE `DB-task-2`;
INSERT INTO `DB-task-2`.`pc` (`code`, `model`, `speed`, `ram`, `hd`, `cd`, `price`) VALUES (1, 'PC-X1', 3600, 2048, 1000, '12x', 750);
INSERT INTO `DB-task-2`.`pc` (`code`, `model`, `speed`, `ram`, `hd`, `cd`, `price`) VALUES (2, 'PC-S1', 4200, 4096, 750, '24x', 1200);
INSERT INTO `DB-task-2`.`pc` (`code`, `model`, `speed`, `ram`, `hd`, `cd`, `price`) VALUES (3, 'PC-A1', 2600, 4096, 500, '12x', 450);
INSERT INTO `DB-task-2`.`pc` (`code`, `model`, `speed`, `ram`, `hd`, `cd`, `price`) VALUES (4, 'PC-A2', 2900, 2048, 500, '8x', 500);
INSERT INTO `DB-task-2`.`pc` (`code`, `model`, `speed`, `ram`, `hd`, `cd`, `price`) VALUES (5, 'PC-X2', 4200, 4096, 1500, '24x', 1500);
INSERT INTO `DB-task-2`.`pc` (`code`, `model`, `speed`, `ram`, `hd`, `cd`, `price`) VALUES (6, 'PC-X3', 4300, 4096, 2000, '24x', 2000);

COMMIT;


-- -----------------------------------------------------
-- Data for table `DB-task-2`.`laptop`
-- -----------------------------------------------------
START TRANSACTION;
USE `DB-task-2`;
INSERT INTO `DB-task-2`.`laptop` (`code`, `model`, `speed`, `ram`, `hd`, `price`, `screen`) VALUES (1, 'L-S1', 700, 1024, 250, 300, 11);
INSERT INTO `DB-task-2`.`laptop` (`code`, `model`, `speed`, `ram`, `hd`, `price`, `screen`) VALUES (2, 'L-A2', 2600, 4096, 500, 1200, 15);
INSERT INTO `DB-task-2`.`laptop` (`code`, `model`, `speed`, `ram`, `hd`, `price`, `screen`) VALUES (3, 'L-A1', 1200, 2048, 750, 1100, 13);

COMMIT;


-- -----------------------------------------------------
-- Data for table `DB-task-2`.`printer`
-- -----------------------------------------------------
START TRANSACTION;
USE `DB-task-2`;
INSERT INTO `DB-task-2`.`printer` (`code`, `model`, `color`, `type`, `price`) VALUES (1, 'PR-A1', 'y', 'Laser', 1100);
INSERT INTO `DB-task-2`.`printer` (`code`, `model`, `color`, `type`, `price`) VALUES (2, 'PR-X2', 'y', 'Jet', 500);
INSERT INTO `DB-task-2`.`printer` (`code`, `model`, `color`, `type`, `price`) VALUES (3, 'PR-S2', 'n', 'Matrix', 300);

COMMIT;

