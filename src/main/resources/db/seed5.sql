ALTER TABLE client_bike ADD `year` varchar(4) NOT NULL;

ALTER TABLE bike DROP `year`, DROP engine_capacity;

ALTER TABLE client ADD birth_date varchar(8);