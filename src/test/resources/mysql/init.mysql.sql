CREATE TABLE bit_factory.user (
  user_id BIGINT NOT NULL AUTO_INCREMENT,
  username VARCHAR(64) NOT NULL,
  password VARCHAR(64) NOT NULL,
  PRIMARY KEY (user_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into user values(1,"cctv","123");