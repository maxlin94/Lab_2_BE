-- Insert dummy categories
INSERT INTO category (name, symbol, description)
VALUES
    ('Park', '🌳', 'A place for leisure and outdoor activities'),
    ('Museum', '🏛️', 'A place showcasing art, history, or science'),
    ('Restaurant', '🍽️', 'A place to enjoy meals and beverages'),
    ('Beach', '🏖️', 'A sandy or rocky shoreline near water');

-- Insert dummy locations
INSERT INTO location (name, category_id, user_id, public, description, coordinates)
VALUES
    ('Central Park', 1, 101, TRUE, 'A large urban park in New York City', POINT(40.785091, -73.968285)),
    ('Louvre Museum', 2, 102, TRUE, 'The world\'s largest art museum and a historic monument in Paris', POINT(48.860611, 2.337644)),
    ('Joe\'s Diner', 3, 103, TRUE, 'A cozy spot for classic American meals', POINT(34.052235, -118.243683)),
    ('Bondi Beach', 4, 104, FALSE, 'A popular beach and the name of the surrounding suburb in Sydney', POINT(-33.890842, 151.274292)),
    ('Golden Gate Park', 1, 105, TRUE, 'A large urban park with gardens, trails, and museums in San Francisco', POINT(37.769420, -122.486214)),
    ('Natural History Museum', 2, 106, TRUE, 'A museum with exhibits of dinosaurs, fossils, and natural history in London', POINT(51.496639, -0.176174)),
    ('Pasta Paradise', 3, 107, FALSE, 'A fine dining Italian restaurant in Rome', POINT(41.902782, 12.496366));