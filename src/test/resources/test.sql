alter table user modify documentid BLOB;

insert into authority (name) values ('ADMIN_ROLE');
insert into authority (name) values ('PASSENGER_ROLE');
insert into authority (name) values ('VALIDATOR_ROLE');

insert into user (username, password,dtype) values ('taken_username', '$2a$04$Amda.Gm4Q.ZbXz9wcohDHOhOBaNQAkSS1QO26Eh8Hovu3uzEpQvcq','Passenger');
insert into user_authority (user_id, authority_id) values (1, 2); -- user has PASSENGER_ROLE

insert into bus_station (id,lat,lng) values (999,'45.264054514190796','19.83022916394043');
insert into bus_station (id,lat,lng) values (998,'45.26042973161276','19.832632423217774');

insert into line (id,name) values (999,'taken_line');

insert into user (username, password, dtype, type, documentid) values ('pName', '$2a$04$Amda.Gm4Q.ZbXz9wcohDHOhOBaNQAkSS1QO26Eh8Hovu3uzEpQvcq','Passenger', 1, 'data:image/png;base64,///9DuZm95tr3/Pu55NgZq4Ty+/Tk9PAnr4owso49tpbW8OnE6N7g8+6u4NJWvqBJu5x4zbSl3c2X18WO1MB+zrdvya+W18TR7uWh2spjxKgAqoFYBRDgAAALv0lEQVR4nOWd6ZajIBCFXUtcaJWoUTvL+7/laMwCxgUEDOm5P/tkEr4BKWqhtGzdijJ3UOa3DsIJRk7rP/8Waf99S9cXe56X+p1IUzh3IbAGAXr8qWhI/6G0+7SugWgh9IIgJuTcop+fH4QfXFMCfPtMeyYkDgItlOoJgyhyz+fGQShZYmM4E4Sc5nx2oyhQPh7FhEF0+q2bA8aLMzc9mxgfmvr3pBpSJaEXp8fmgnEiCPdSgvGlOaaxyuWqjjBOqzbE1na8O6SFw7ZKY2XjUkQYn0hYYBBdmtMCwEVIToogVR1q7MpiNBmHV4uvlujB1k9gHC2A31rc6xINw+jRsJY79F+wH23kjrb2TcROiljbMj3iCnSTdtqxsIvbwq8e6AloXLKt/AKE4YVSXaY4N5V4LKStxyCBNGR50GcFmdeTwKIwoSeulx1x3mjREdRZ9GMcKgCj/xBNLCYSXmIgsRBp++SkL/YM4KfYoTkYuQGPQIvgQFUUXoGwnYI/LM4jqhZ+QSvQkOHNvNKqFn4CbzEgfiGmFnJj5Nsah1o7FCaPYM9lqdxRVC4wHXjcYyoZlmgtWa0VgkNNVMsFoxGguEBpsJVstGY57wawBXEOcJM7PNBKtwPuw/SxjUZrlLy8L1rEs8R+iR4tOjFlJB5tbpHGH2FdvoS1DMrdMZwu9ao71m1+k0oRFhQ0HNBRknCb3q+wAtQNXkozhJmO6Y/FQnCFNewuj4');
insert into user (username, password, dtype) values ('vName', '$2a$04$Amda.Gm4Q.ZbXz9wcohDHOhOBaNQAkSS1QO26Eh8Hovu3uzEpQvcq','Validator');