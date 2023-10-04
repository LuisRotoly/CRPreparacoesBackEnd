CREATE TABLE payment_format (
  id_payment_format bigint AUTO_INCREMENT PRIMARY KEY,
  type varchar(20)
);

insert into payment_format(id_payment_format, type) values (1, "Dinheiro");
insert into payment_format(id_payment_format, type) values (2, "Cartão de débito");
insert into payment_format(id_payment_format, type) values (3, "Cartão de crédito");
insert into payment_format(id_payment_format, type) values (4, "Pix");
insert into payment_format(id_payment_format, type) values (5, "Transferência");
insert into payment_format(id_payment_format, type) values (6, "Cheque");
insert into payment_format(id_payment_format, type) values (7, "Outro");

ALTER TABLE budget ADD kilometers_driven integer NOT NULL,
ADD notes varchar(255),
ADD payment_format_id bigint,
ADD FOREIGN KEY (payment_format_id) REFERENCES payment_format(id_payment_format);