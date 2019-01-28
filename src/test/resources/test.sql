insert into authority (name) values ('ADMIN_ROLE');
insert into authority (name) values ('PASSENGER_ROLE');
insert into authority (name) values ('VALIDATOR_ROLE');

insert into user (username, password,dtype) values ('taken_username', '$2a$04$Amda.Gm4Q.ZbXz9wcohDHOhOBaNQAkSS1QO26Eh8Hovu3uzEpQvcq','Passenger');
insert into user_authority (user_id, authority_id) values (1, 2); -- user has PASSENGER_ROLE

insert into bus_station (id,lat,lng) values (999,'45.264054514190796','19.83022916394043');
insert into bus_station (id,lat,lng) values (998,'45.26042973161276','19.832632423217774');
insert into bus_station (id,lat,lng) values (40,'45.264064514190796','19.83026916394043');
insert into bus_station (id,lat,lng) values (41,'45.2640645124190796','19.83026916694043');

insert into line (id,name) values (999,'taken_line');
insert into line (id,name) values (40,'to_delete');
insert into line (id,name) values (41,'to_delete2');
insert into line (id,name) values (20,'line_to_get');

insert into line_station_relation(line_id,station_id) values (999,40);
insert into vehicle(id,line_id,start_to_end,at_station,type) values (999,40,true,0,0);
insert into vehicle(id,line_id,start_to_end,at_station,type) values (998,40,true,0,1);
insert into vehicle(id,line_id,start_to_end,at_station,type) values (997,40,true,0,2);
insert into vehicle(id,line_id,start_to_end,at_station,type) values (996,20,true,2,2);
insert into vehicle(id,line_id,start_to_end,at_station,type) values (995,20,true,2,1);
insert into vehicle(id,line_id,start_to_end,at_station,type) values (994,20,true,2,0);
