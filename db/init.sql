USE `audio_orders`;

INSERT INTO AuthInfo (UserName, Password) VALUES ('admin', '1234');
INSERT INTO AuthInfo (UserName, Password) VALUES ('client', '4321');

INSERT INTO User (UserName, LastName, FirstName, Role) VALUES ('admin', 'demo_admin', 'demo_admin', 'ADMIN');
INSERT INTO User (UserName, LastName, FirstName, Role) VALUES ('client', 'demo_client', 'demo_client', 'CLIENT');
