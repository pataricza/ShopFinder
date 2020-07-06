INSERT INTO address VALUES 
(nextval('address_id_seq'), 'Budapest', 'Váci utca', '88'),
(nextval('address_id_seq'), 'Budapest', 'Futár utca', '8')
ON CONFLICT DO NOTHING;

INSERT INTO open_hour VALUES
(nextval('open_hour_id_seq'), '08:00', '21:00'),
(nextval('open_hour_id_seq'), '08:00', '21:00')
ON CONFLICT DO NOTHING;

INSERT INTO open_hours VALUES
(nextval('open_hours_id_seq'), 1, 1, 1, 1),
(nextval('open_hours_id_seq'), 2, 2, 2, 2)
ON CONFLICT DO NOTHING;

INSERT INTO shop VALUES
(nextval('shop_id_seq'), 'Kisbolt', 1, 1, '/img/1.jpg'),
(nextval('shop_id_seq'), 'Nagybolt', 2, 2, NULL)
ON CONFLICT DO NOTHING;