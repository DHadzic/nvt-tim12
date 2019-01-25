insert into authority (name) values ('ADMIN_ROLE');
insert into authority (name) values ('PASSENGER_ROLE');
insert into authority (name) values ('VALIDATOR_ROLE');

insert into user (username, password,dtype) values ('taken_username', 'password','Passenger');
insert into user_authority (user_id, authority_id) values (1, 2); -- user has PASSENGER_ROLE
