CREATE TABLE single_sale (
  id_single_sale bigint AUTO_INCREMENT PRIMARY KEY,
  client VARCHAR(100) NOT NULL,
  created_at datetime NOT NULL
);

CREATE TABLE single_sale_rel_bike_part (
  id_single_sale_rel_bike_part bigint AUTO_INCREMENT PRIMARY KEY,
  quantity integer,
  `value` double,
  bike_part_id bigint,
  FOREIGN KEY (bike_part_id) REFERENCES bike_part(id_bike_part),
  single_sale_id bigint,
  FOREIGN KEY (single_sale_id) REFERENCES single_sale(id_single_sale)
);