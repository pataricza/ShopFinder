CREATE TABLE IF NOT EXISTS open_hour (
	id SERIAL PRIMARY KEY,
	open VARCHAR(255),
	close VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS open_hours (
	id SERIAL PRIMARY KEY,
	monday_to_thursday_id INTEGER REFERENCES open_hour(id),
	friday_id INTEGER REFERENCES open_hour(id),
	saturday_id INTEGER REFERENCES open_hour(id),
	sunday_id INTEGER REFERENCES open_hour(id)
);

CREATE TABLE IF NOT EXISTS address (
	id SERIAL PRIMARY KEY,
	city VARCHAR(255),
	street VARCHAR(255),
	house_number VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS shop (
	id SERIAL PRIMARY KEY,
	name VARCHAR(255),
	address_id INTEGER REFERENCES address(id),
	open_hours_id INTEGER,
	image_name VARCHAR(255)
);