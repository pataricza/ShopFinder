CREATE TABLE open_hour (
	id INTEGER PRIMARY KEY,
	open VARCHAR(255),
	close VARCHAR(255)
);

CREATE TABLE open_hours (
	id INTEGER PRIMARY KEY,
	monday_to_thursday_id INTEGER REFERENCES open_hour(id),
	friday_id INTEGER REFERENCES open_hour(id),
	saturday_id INTEGER REFERENCES open_hour(id),
	sunday_id INTEGER REFERENCES open_hour(id)
);

CREATE TABLE address (
	id INTEGER PRIMARY KEY,
	city VARCHAR(255),
	street VARCHAR(255),
	house_number VARCHAR(255)
);

CREATE TABLE shop (
	id INTEGER PRIMARY KEY,
	image_name VARCHAR(255),
	name VARCHAR(255),
	address_id INTEGER REFERENCES address(id),
	open_hours_id INTEGER
);