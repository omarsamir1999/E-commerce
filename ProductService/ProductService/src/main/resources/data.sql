-- Dummy Data --
INSERT INTO Brand (brand_name, brand_description, country_of_origin)
VALUES ('Apple', 'Technology company', 'United States'),
       ('Sony', 'Electronics and entertainment', 'Japan'),
       ('IKEA', 'Furniture and home decor', 'Sweden'),
       ('Penguin', 'Book publishing', 'United Kingdom'),
       ('LEGO', 'Toy manufacturing', 'Denmark');

INSERT INTO Category (category_name, category_description)
VALUES ('Electronics', 'Electronics and gadgets category'),
       ('Clothing', 'Clothing and fashion category'),
       ('Furniture', 'Home and office furniture category'),
       ('Books', 'Books and literature category'),
       ('Toys', 'Toys and games category');

INSERT INTO Product (product_name, product_description, product_status, image_path, price, color, material, brand_id,
                     category_id)
VALUES ('Smartphone X', 'High-end smartphone', 'ACTIVE', '/images/smartphone.jpg', 699.99, 'Black', 'Metal', 1, 1),
       ('Laptop Pro', 'Powerful laptop for professionals', 'ACTIVE', '/images/laptop.jpg', 1299.99, 'Silver',
        'Aluminum', 2, 2),
       ('Sofa Set', 'Comfortable living room sofa', 'ACTIVE', '/images/sofa.jpg', 799.99, 'Gray', 'Fabric', 3, 3),
       ('Harry Potter Book', 'Best-selling fantasy novel', 'ACTIVE', '/images/book.jpg', 19.99, 'Various', 'Paper', 4,
        4),
       ('Toy Train Set', 'Children''s toy train set', 'ACTIVE', '/images/toy.jpg', 49.99, 'Multicolor', 'Plastic', 5,
        5);
