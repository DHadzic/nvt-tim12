insert into authority (name) values ('ADMIN_ROLE');
insert into authority (name) values ('PASSENGER_ROLE');

-- DODAVANJE User naslednice sa dtype-om i dodavanje role-ova njemu
insert into user (username, password,dtype) values ('user', '$2a$04$Amda.Gm4Q.ZbXz9wcohDHOhOBaNQAkSS1QO26Eh8Hovu3uzEpQvcq','Passenger');
insert into user (username, password,dtype) values ('admin', '$2a$04$SwzgBrIJZhfnzOw7KFcdzOTiY6EFVwIpG7fkF/D1w26G1.fWsi.aK','Admin');
insert into user_authority (user_id, authority_id) values (1, 2); -- user has PASSENGER_ROLE
insert into user_authority (user_id, authority_id) values (2, 1); -- admin has ADMIN_ROLE

insert into pricelist (id, date_formed) values (1, '2017-12-18 13:17:17');
insert into pricelist_item (id, price, ticket_type, pricelist_id) values (1, 500, 1, 1);