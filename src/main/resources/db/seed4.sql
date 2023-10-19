ALTER TABLE budget ADD is_removed boolean NOT NULL,
ADD discount_percentage integer;

ALTER TABLE bike MODIFY COLUMN `year` varchar(4);
ALTER TABLE budget MODIFY COLUMN `year` varchar(4);

CREATE TABLE budget_sketch (
  id_budget_sketch bigint AUTO_INCREMENT PRIMARY KEY,
  client varchar(100) NOT NULL,
  plate varchar(8),
  bike varchar(100),
  phone varchar(14),
  notes varchar(255),
  created_at datetime,
  updated_at datetime
);

CREATE TABLE labor_or_bike_part_budget_sketch (
  id_labor_or_bike_part_budget_sketch bigint AUTO_INCREMENT PRIMARY KEY,
  name varchar(100),
  quantity integer,
  `value` double,
  budget_sketch_id bigint,
  FOREIGN KEY (budget_sketch_id) REFERENCES budget_sketch(id_budget_sketch)
);