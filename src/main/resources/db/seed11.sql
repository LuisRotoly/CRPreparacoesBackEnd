ALTER TABLE labor_or_bike_part_budget ADD default_value double;
ALTER TABLE labor_or_bike_part_budget_sketch ADD default_value double;
ALTER TABLE single_sale_rel_bike_part ADD default_value double;

UPDATE single_sale_rel_bike_part s INNER JOIN bike_part b ON s.bike_part_id = b.id_bike_part SET s.default_value = b.value;
UPDATE labor_or_bike_part_budget_sketch s INNER JOIN bike_part b ON s.name = b.name SET s.default_value = b.value;
UPDATE labor_or_bike_part_budget_sketch s INNER JOIN bike_service b ON s.name = b.name SET s.default_value = b.value;
UPDATE labor_or_bike_part_budget s INNER JOIN bike_part b ON s.name = b.name SET s.default_value = b.value;
UPDATE labor_or_bike_part_budget s INNER JOIN bike_service b ON s.name = b.name SET s.default_value = b.value;