CREATE TABLE debit_payment (
  id_debit_payment bigint AUTO_INCREMENT PRIMARY KEY,
  description VARCHAR(50) NOT NULL,
  notes varchar(100),
  payment_format_id bigint,
  FOREIGN KEY (payment_format_id) REFERENCES payment_format(id_payment_format),
  `value` double NOT NULL,
  paid_at datetime NOT NULL
);

ALTER TABLE budget ADD is_paid boolean NOT NULL;