alter table user modify documentid BLOB;

insert into authority (name) values ('ADMIN_ROLE');
insert into authority (name) values ('PASSENGER_ROLE');
insert into authority (name) values ('VALIDATOR_ROLE');

-- DODAVANJE User naslednice sa dtype-om i dodavanje role-ova njemu
insert into user (username, password,dtype) values ('user', '$2a$04$Amda.Gm4Q.ZbXz9wcohDHOhOBaNQAkSS1QO26Eh8Hovu3uzEpQvcq','Passenger');
insert into user (username, password,dtype) values ('admin', '$2a$04$SwzgBrIJZhfnzOw7KFcdzOTiY6EFVwIpG7fkF/D1w26G1.fWsi.aK','Admin');
insert into user (username, password,dtype) values ('validator', '$2a$04$Amda.Gm4Q.ZbXz9wcohDHOhOBaNQAkSS1QO26Eh8Hovu3uzEpQvcq','Validator');
insert into user_authority (user_id, authority_id) values (1, 2); -- user has PASSENGER_ROLE
insert into user_authority (user_id, authority_id) values (2, 1); -- admin has ADMIN_ROLE
insert into user_authority (user_id, authority_id) values (3, 3); -- validator has VALIDATOR_ROLE

insert into pricelist (id, date_formed) values (1, '2017-12-18 13:17:17');
insert into pricelist_item (id, price, ticket_type, pricelist_id) values (1, 500, 1, 1);

insert into bus_station (id,lat,lng) values (999,'45.264054514190796','19.83022916394043');
insert into bus_station (id,lat,lng) values (998,'45.26042973161276','19.832632423217774');
insert into bus_station (id,lat,lng) values (997,'45.25148761176708','19.837610603149415');
insert into bus_station (id,lat,lng) values (996,'45.25252355951289','19.847634710688453');

insert into line(id,name) values(999,'8a');
insert into line(id,name) values(998,'8b');

insert into line_station_relation(line_id,station_id) values (999,999);
insert into line_station_relation(line_id,station_id) values (999,998);
insert into line_station_relation(line_id,station_id) values (999,997);
insert into line_station_relation(line_id,station_id) values (999,996);

insert into line_station_relation(line_id,station_id) values (998,998);
insert into line_station_relation(line_id,station_id) values (998,997);

insert into schedule_work_day(schedule_id,work_day) values 
(0,"13:30"),
(0,"14:30"),
(0,"15:30"),
(0,"16:30"),
(0,"17:30");


insert into schedule(id)  values (0);

insert into vehicle(id,line_id,schedule_id,name,type) values (999,null,0,'g11',0);
insert into vehicle(id,line_id,schedule_id,name,type) values (998,999,0,'g12',0);
insert into vehicle(id,line_id,schedule_id,name,type) values (997,998,0,'g13',0);


