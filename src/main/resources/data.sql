CREATE TABLE IF NOT EXISTS  USERS(
      username varchar_ignorecase(50) not null primary key,
      password varchar_ignorecase(255) not null,
      enabled boolean not null);

CREATE TABLE IF NOT EXISTS AUTHORITIES (
      username varchar_ignorecase(60) not null,
      authority varchar_ignorecase(60) not null,
      constraint fk_authorities_users foreign key(username) references users(username));
      create unique index ix_auth_username on authorities (username,authority);

INSERT INTO USERS(username, password, enabled) VALUES
('user','$2a$04$Y/bW10aytUgRdeizHs9EV.fJ4GPLam/VD6j8VJb6Mcfy9qhTEzjjG', 1),
('admin','$2a$10$7MvpYuwhXJk24bEbqdzCfewpAIu5Lj.mQMVFQmPSF5ZkE1O.C5Yxq', 1);


INSERT INTO AUTHORITIES VALUES
('user','ROLE_USER'),
('admin','ROLE_ADMIN');


INSERT INTO RESTAURANT(NAME) VALUES
('MacDac'),
('Shawerma');

INSERT INTO MEAL(NAME, PRICE, TIMESTAMP, RESTAURANT_ID) VALUES
('cola', 1.99, '2018-12-23', 1),
('hamburger', 3.0, '2018-12-23', 1),
('potato', 2.99, '2018-12-23', 1),
('cola-light', 1.99, '2018-12-23', 2),
('hamburger-light', 3.0, '2018-12-23', 2),
('potato-light', 2.99, '2018-12-23', 2);

INSERT INTO VOTE(VOTE_DATE, RESTAURANT_ID, USER_NAME) VALUES
('2018-12-23', 1, 'user');
