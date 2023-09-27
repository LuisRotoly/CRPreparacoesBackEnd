CREATE TABLE bike_service (
  id_bike_service bigint AUTO_INCREMENT PRIMARY KEY,
  name varchar(100) NOT NULL,
  `value` double NOT NULL
);

CREATE TABLE status (
  id_status bigint AUTO_INCREMENT PRIMARY KEY,
  code varchar(20),
  description varchar(25)
);

insert into status(id_status, code, description) values (1, "WAITING_APPROVAL", "Aguardando aprovação");
insert into status(id_status, code, description) values (2, "CANCELED", "Cancelado");
insert into status(id_status, code, description) values (3, "WAITING_PARTS", "Esperando peças");
insert into status(id_status, code, description) values (4, "IN_PRODUCTION", "Em produção");
insert into status(id_status, code, description) values (5, "FINISHED", "Finalizado");

CREATE TABLE budget (
  id_budget bigint AUTO_INCREMENT PRIMARY KEY,
  client_id bigint,
  FOREIGN KEY (client_id) REFERENCES client(id_client),
  bike_name varchar(100) NOT NULL,
  bike_brand varchar(100) NOT NULL,
  engine_capacity integer,
  `year` varchar(9),
  plate varchar(8),
  total_value double,
  status_id bigint,
  FOREIGN KEY (status_id) REFERENCES status(id_status),
  created_at datetime,
  updated_at datetime
);

CREATE TABLE labor_or_bike_part_budget (
  id_labor_or_bike_part_budget bigint AUTO_INCREMENT PRIMARY KEY,
  name varchar(100),
  quantity integer,
  `value` double,
  budget_id bigint,
  FOREIGN KEY (budget_id) REFERENCES budget(id_budget)
);