INSERT INTO category (name, symbol, description)
VALUES
    ('Park', '🌳', 'A place for leisure and outdoor activities'),
    ('Museum', '🏛️', 'A place showcasing art, history, or science'),
    ('Restaurant', '🍽️', 'A place to enjoy meals and beverages'),
    ('Beach', '🏖️', 'A sandy or rocky shoreline near water'),
    ('Library', '📚', 'A place to read and borrow books'),
    ('Cinema', '🎥', 'A place to watch movies and entertainment');

INSERT INTO location (name, category_id, user_id, public, description, coordinates)
VALUES
    -- Parks
    ('Greenfield Park', 1, 'user1', TRUE, 'A beautiful park with open green spaces.', ST_GeomFromText('POINT(45.5017 -73.5673)', 4326)),
    ('Springwood Park', 1, 'user2', TRUE, 'A quiet park with a small lake.', ST_GeomFromText('POINT(51.5074 -0.1276)', 4326)),
    ('Lakeview Park', 1, 'user3', FALSE, 'A park overlooking a large lake.', ST_GeomFromText('POINT(35.6895 139.6917)', 4326)),

    -- Museums
    ('Art House Museum', 2, 'user4', TRUE, 'An art museum showcasing modern and classic works.', ST_GeomFromText('POINT(48.8566 2.3522)', 4326)),
    ('History Hall', 2, 'user5', TRUE, 'A museum of local history.', ST_GeomFromText('POINT(38.9072 -77.0369)', 4326)),
    ('Fossil World Museum', 2, 'user6', FALSE, 'A museum dedicated to fossils and prehistoric life.', ST_GeomFromText('POINT(-33.9249 18.4241)', 4326)),

    -- Restaurants
    ('The Culinary Spot', 3, 'user7', TRUE, 'A fine dining restaurant with an extensive menu.', ST_GeomFromText('POINT(41.9028 12.4964)', 4326)),
    ('Sushi Haven', 3, 'user8', FALSE, 'A small sushi restaurant known for fresh seafood.', ST_GeomFromText('POINT(35.0116 135.7681)', 4326)),
    ('Burger Kingdom', 3, 'user9', TRUE, 'A fast-food spot famous for its giant burgers.', ST_GeomFromText('POINT(37.7749 -122.4194)', 4326)),

    -- Beaches
    ('Sunny Sands', 4, 'user10', FALSE, 'A quiet beach away from the crowds.', ST_GeomFromText('POINT(-27.4701 153.0260)', 4326)),
    ('Golden Coastline', 4, 'user11', TRUE, 'A vibrant beach with golden sands.', ST_GeomFromText('POINT(-15.7861 34.7617)', 4326)),
    ('Paradise Cove', 4, 'user12', TRUE, 'A secluded beach with crystal-clear waters.', ST_GeomFromText('POINT(-33.4489 -70.6472)', 4326)),

    -- Libraries
    ('Knowledge Hub', 5, 'user13', TRUE, 'A modern library with digital resources.', ST_GeomFromText('POINT(-37.8136 144.9631)', 4326)),
    ('Old Town Library', 5, 'user14', TRUE, 'A historic library in the city center.', ST_GeomFromText('POINT(-41.2865 174.7645)', 4326)),
    ('Future Library', 5, 'user15', FALSE, 'A futuristic library with innovative designs.', ST_GeomFromText('POINT(35.6762 139.6503)', 4326)),

    -- Cinemas
    ('Galaxy Cinema', 6, 'user16', TRUE, 'A large cinema with IMAX and 4D screens.', ST_GeomFromText('POINT(-22.9035 -43.2096)', 4326)),
    ('Starlight Screens', 6, 'user17', TRUE, 'An outdoor cinema under the stars.', ST_GeomFromText('POINT(-33.8688 151.2093)', 4326)),
    ('Urban Movieplex', 6, 'user18', FALSE, 'A downtown cinema with multiple screens.', ST_GeomFromText('POINT(41.8781 -87.6298)', 4326));
