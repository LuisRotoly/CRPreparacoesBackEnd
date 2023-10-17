ALTER TABLE budget ADD is_removed boolean NOT NULL,
ADD discount_percentage integer;

ALTER TABLE bike MODIFY COLUMN `year` varchar(4);
ALTER TABLE budget MODIFY COLUMN `year` varchar(4);