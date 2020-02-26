INSERT INTO address VALUES 
(1, 'Budapest', 'Váci utca', '88'),
(2, 'Budapest', 'Futár utca', '8')
ON CONFLICT DO NOTHING;

INSERT INTO open_hour VALUES
(1, '08:00', '21:00'),
(2, '08:00', '21:00')
ON CONFLICT DO NOTHING;

INSERT INTO open_hours VALUES
(1, 1, 1, 1, 1),
(2, 2, 2, 2, 2)
ON CONFLICT DO NOTHING;

INSERT INTO shop VALUES
(1, '/img/1.jpg', 'Kisbolt', 1, 1),
(2, NULL, 'Nagybolt', 2, 2)
ON CONFLICT DO NOTHING;