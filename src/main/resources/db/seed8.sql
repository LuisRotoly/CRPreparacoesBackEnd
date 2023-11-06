ALTER TABLE client ADD street varchar(100),
ADD district varchar(70);

UPDATE client SET street = "";
UPDATE client SET district = "";

ALTER TABLE budget ADD problems varchar(255);

UPDATE budget SET problems = "";