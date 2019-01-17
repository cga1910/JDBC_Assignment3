# JDBC_Assignment3

SQL code:
USE sqlandjava;
CREATE TABLE sqlandjava.owners (
  owner_id INT NOT NULL,
  person_id INT NOT NULL,
  car_id INT NOT NULL,
  PRIMARY KEY (owner_id)
  );
INSERT INTO sqlandjava.owners (owner_id, person_id, car_id) VALUES (1, 2, 3);
INSERT INTO sqlandjava.owners (owner_id, person_id, car_id) VALUES (2, 1, 4);
INSERT INTO sqlandjava.owners (owner_id, person_id, car_id) VALUES (3, 3, 2);
INSERT INTO sqlandjava.owners (owner_id, person_id, car_id) VALUES (4, 4, 1);
GRANT SELECT ON sqlandjava.owners TO user@localhost;
