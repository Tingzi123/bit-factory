CREATE TABLE role (
  role_id BIGINT NOT NULL AUTO_INCREMENT,
  role_name VARCHAR(64) NOT NULL,
  role_desc VARCHAR(255),
  PRIMARY KEY (role_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;