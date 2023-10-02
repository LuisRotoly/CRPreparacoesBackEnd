ALTER TABLE client DROP address;
ALTER TABLE client ADD cep varchar(9),
ADD address_number varchar(6),
ADD optional_phone varchar(14),
ADD notes varchar(255);

