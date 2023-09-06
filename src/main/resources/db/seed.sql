CREATE TABLE client (
  id_client bigint AUTO_INCREMENT PRIMARY KEY,
  name varchar(100) NOT NULL,
  cpfcnpj varchar(14) NOT NULL UNIQUE,
  address varchar(150),
  phone varchar(12) NOT NULL,
  nickname varchar(100),
  created_at datetime,
  updated_at datetime
);

CREATE TABLE bike (
  id_bike bigint AUTO_INCREMENT PRIMARY KEY,
  name varchar(100) NOT NULL,
  brand varchar(100),
  engine_capacity integer,
  `year` varchar(9),
  created_at datetime,
  updated_at datetime
);

CREATE TABLE part (
  id_part bigint AUTO_INCREMENT PRIMARY KEY,
  name varchar(100) NOT NULL,
  `value` double NOT NULL,
  stock_quantity integer NOT NULL,
  created_at datetime,
  updated_at datetime,
  bike_id bigint,
  FOREIGN KEY (bike_id) REFERENCES bike(id_bike)
);

CREATE TABLE client_bike (
  id_client_bike bigint AUTO_INCREMENT PRIMARY KEY,
  client_id bigint,
  FOREIGN KEY (client_id) REFERENCES client(id_client),
  bike_id bigint,
  FOREIGN KEY (bike_id) REFERENCES bike(id_bike),
  plate varchar(7) NOT NULL UNIQUE,
  created_at datetime,
  updated_at datetime
);

CREATE TABLE supplier (
  id_role bigint AUTO_INCREMENT PRIMARY KEY,
  name varchar(100) NOT NULL,
  phone varchar(12) NOT NULL,
  notes varchar(255),
  created_at datetime,
  updated_at datetime
);