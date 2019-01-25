insert into authority (name) values ('ADMIN_ROLE');
insert into authority (name) values ('PASSENGER_ROLE');
insert into authority (name) values ('VALIDATOR_ROLE');

insert into user (username, password,dtype) values ('taken_username', 'password','Passenger');
insert into user_authority (user_id, authority_id) values (1, 2); -- user has PASSENGER_ROLE

insert into bus_station (id,lat,lng) values (999,'45.264054514190796','19.83022916394043');
insert into bus_station (id,lat,lng) values (998,'45.26042973161276','19.832632423217774');

insert into line (id,name) values (999,'taken_line')
