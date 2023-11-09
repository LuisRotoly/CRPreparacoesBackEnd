ALTER TABLE client ADD street varchar(100),
ADD district varchar(70);

UPDATE client SET street = "";
UPDATE client SET district = "";

ALTER TABLE budget ADD problems varchar(255);

UPDATE budget SET problems = "";

ALTER TABLE finance_budget ADD notes varchar(100);

UPDATE finance_budget SET notes = "";