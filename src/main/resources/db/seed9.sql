CREATE TABLE single_sale (
  id_single_sale bigint AUTO_INCREMENT PRIMARY KEY,
  client VARCHAR(100) NOT NULL,
  created_at datetime NOT NULL
);

CREATE TABLE labor_or_bike_part_single_sale (
  id_labor_or_bike_part_budget bigint AUTO_INCREMENT PRIMARY KEY,
  name varchar(100),
  quantity integer,
  `value` double,
  single_sale_id bigint,
  FOREIGN KEY (single_sale_id) REFERENCES single_sale(id_single_sale)
);