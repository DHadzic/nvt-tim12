insert into authority (name) values ('ADMIN_ROLE');
insert into authority (name) values ('PASSENGER_ROLE');

-- DODAVANJE User naslednice sa dtype-om i dodavanje role-ova njemu
insert into user (username, password,dtype) values ('user', '$2a$04$Amda.Gm4Q.ZbXz9wcohDHOhOBaNQAkSS1QO26Eh8Hovu3uzEpQvcq','Passenger');
insert into user_authority (user_id, authority_id) values (1, 2); -- user has PASSENGER_ROLE
insert into user_authority (user_id, authority_id) values (1, 1); -- user has PASSENGER_ROLE
