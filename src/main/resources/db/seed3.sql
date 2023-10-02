ALTER TABLE client DROP address;
ALTER TABLE client ADD cep varchar(9),
ADD address_number varchar(6),
ADD optional_phone varchar(14),
ADD notes varchar(255);

ALTER TABLE bike_part ADD profit_percentage INTEGER;
ALTER TABLE bike_part DROP FOREIGN KEY bike_part_ibfk_1,
DROP COLUMN bike_id;

CREATE TABLE bike_part_rel_bike (
  id_bike_part_rel_bike bigint AUTO_INCREMENT PRIMARY KEY,
  bike_id bigint,
  FOREIGN KEY (bike_id) REFERENCES bike(id_bike),
  bike_part_id bigint,
  FOREIGN KEY (bike_part_id) REFERENCES bike_part(id_bike_part)
);