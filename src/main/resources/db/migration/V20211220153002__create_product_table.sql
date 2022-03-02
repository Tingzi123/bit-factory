CREATE TABLE product (
  id BIGINT NOT NULL,
  name VARCHAR(64) NOT NULL,
  description VARCHAR(255),
  price DECIMAL,
  link VARCHAR(255),
  created_at DATETIME,
  updated_at DATETIME,
  PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;