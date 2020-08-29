INSERT INTO `greatlearning_resteasy`.`user` (`id`, `name`, `username`, `password`, `role`) VALUES ('0', 'admin', 'admin', 'admin', 'ADMIN');
INSERT INTO `greatlearning_resteasy`.`user` (`id`, `name`, `username`, `password`, `role`) VALUES ('1', 'pranay', 'pranay', 'pranay', 'CUSTOMER');


INSERT INTO `greatlearning_resteasy`.`vendor` (`id`, `name`) VALUES ('1', 'McDonalds');
INSERT INTO `greatlearning_resteasy`.`vendor` (`id`, `name`) VALUES ('2', 'Pizza hut');
INSERT INTO `greatlearning_resteasy`.`vendor` (`id`, `name`) VALUES ('3', 'Fasoos');

INSERT INTO `greatlearning_resteasy`.`dish` (`id`, `name`, `calories`) VALUES ('1', 'Burger', '500');
INSERT INTO `greatlearning_resteasy`.`dish` (`id`, `name`, `calories`) VALUES ('2', 'Pizza', '1000');
INSERT INTO `greatlearning_resteasy`.`dish` (`id`, `name`, `calories`) VALUES ('3', 'Pizza roll', '1050');

INSERT INTO `greatlearning_resteasy`.`vendor_dish` (`id`, `vendorId`, `dishId`, `price`) VALUES ('1', '1', '1', '100');
INSERT INTO `greatlearning_resteasy`.`vendor_dish` (`id`, `vendorId`, `dishId`, `price`) VALUES ('2', '2', '2', '500');
INSERT INTO `greatlearning_resteasy`.`vendor_dish` (`id`, `vendorId`, `dishId`, `price`) VALUES ('3', '2', '3', '700');
INSERT INTO `greatlearning_resteasy`.`vendor_dish` (`id`, `vendorId`, `dishId`, `price`) VALUES ('4', '3', '2', '600');
INSERT INTO `greatlearning_resteasy`.`vendor_dish` (`id`, `vendorId`, `dishId`, `price`) VALUES ('5', '3', '3', '800');
