CREATE TABLE finance_budget (
  id_finance_budget bigint AUTO_INCREMENT PRIMARY KEY,
  budget_id bigint,
  FOREIGN KEY (budget_id) REFERENCES budget(id_budget),
  `value` double,
  payment_format_id bigint,
  FOREIGN KEY (payment_format_id) REFERENCES payment_format(id_payment_format),
  paid_at datetime
);

ALTER TABLE bike_part ADD notes varchar(255);
UPDATE bike_part SET notes = "";

insert into status(id_status, code, description) values (6, "WAITING_PAYMENT", "Aguardando pagamento");