DROP TABLE tbl_continent CASCADE;

CREATE TABLE tbl_continent
(
  continent_id SERIAL,
  continent character varying(30)  PRIMARY KEY
);

DROP TABLE tbl_countries CASCADE;

CREATE TABLE tbl_countries
(
  countries_id SERIAL PRIMARY KEY,
  name character varying(30),
  flag character varying(10),
  continent character varying(30)	
);


ALTER TABLE tbl_countries ADD FOREIGN KEY (continent) REFERENCES tbl_continent(continent);