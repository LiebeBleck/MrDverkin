CREATE TABLE IF NOT EXISTS door (
    id INT AUTO_INCREMENT PRIMARY KEY,
    door_name VARCHAR(255),
    price DECIMAL(10, 2)
    );
-- Вставка дверей в справочник (таблицу door)
INSERT INTO door (door_name, price)
VALUES ('Wooden Door', 200.0);

INSERT INTO door (door_name, price)
VALUES ('Glass Door', 150.0);

INSERT INTO door (door_name, price)
VALUES ('Steel Door', 300.0);

INSERT INTO door (door_name, price)
VALUES ('Metal Door', 250.0);

INSERT INTO door (door_name, price)
VALUES ('Sliding Door', 180.0);

INSERT INTO door (door_name, price)
VALUES ('French Door', 350.0);
