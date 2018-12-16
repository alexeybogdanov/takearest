CREATE TABLE USERS(
      username varchar_ignorecase(50) not null primary key,
      password varchar_ignorecase(255) not null,
      enabled boolean not null);

CREATE TABLE AUTHORITIES (
      username varchar_ignorecase(60) not null,
      authority varchar_ignorecase(60) not null,
      constraint fk_authorities_users foreign key(username) references users(username));
      create unique index ix_auth_username on authorities (username,authority);

INSERT INTO USERS(username, password, enabled) VALUES
('alex','$2a$04$Y/bW10aytUgRdeizHs9EV.fJ4GPLam/VD6j8VJb6Mcfy9qhTEzjjG', 1),
('admin','$2a$04$Y/bW10aytUgRdeizHs9EV.fJ4GPLam/VD6j8VJb6Mcfy9qhTEzjjG', 1);

INSERT INTO AUTHORITIES VALUES
('alex','USER'),
('admin','ADMIN');


INSERT INTO RESTAURANT(ID, NAME) VALUES
(1, 'MacDac'),
(2, 'Shawerma');

INSERT INTO MEAL(ID, NAME, PRICE, RESTAURANT_ID) VALUES
(1, 'cola', 1.99, 1),
(2, 'hamburger', 3.0, 1),
(3, 'potato', 2.99, 1),
(4, 'cola-light', 1.99, 2),
(5, 'hamburger-light', 3.0, 2),
(6, 'potato-light', 2.99, 2);