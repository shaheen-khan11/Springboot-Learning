-- Create the Country table
CREATE TABLE Country (
    id INT PRIMARY KEY,
    countryname VARCHAR(50),
    countrycapital VARCHAR(50)
);

-- Insert records into the Country table
INSERT INTO Country (id, countryname, countrycapital) VALUES (1, 'India', 'Delhi');
INSERT INTO Country (id, countryname, countrycapital) VALUES (2, 'USA', 'Washington');
