create table dbo.product_category
(
    id BIGINT NOT NULL PRIMARY KEY IDENTITY (1,1),
    category_name VARCHAR(255) NULL DEFAULT NULL,
    );

create table dbo.product
(
    id             BIGINT NOT NULL PRIMARY KEY IDENTITY (1,1),
    sku            VARCHAR(255)   DEFAULT NULL,
    name           VARCHAR(255)   DEFAULT NULL,
    description    VARCHAR(255)   DEFAULT NULL,
    unit_price     DECIMAL(13, 2) DEFAULT NULL,
    image_url      VARCHAR(255)   DEFAULT NULL,
    active         BIT            DEFAULT 1,
    units_in_stock numeric(11)    DEFAULT NULL,
    date_created   DATETIME       DEFAULT NULL,
    last_updated   DATETIME       DEFAULT NULL,
    category_id    BIGINT NOT NULL,
);

-- INSERT INTO PRODUCT_CATEGORY(CATEGORY_NAME) VALUES ('BOOKS');
--
-- INSERT INTO PRODUCT (SKU, NAME, DESCRIPTION, IMAGE_URL, ACTIVE, UNITS_IN_STOCK,
--                      UNIT_PRICE, CATEGORY_ID,DATE_CREATED)
-- VALUES ('BOOK-TECH-1000', 'JavaScript - The Fun Parts', 'Learn JavaScript',
--         'assets/images/products/placeholder.png'
--            ,1,100,19.99,1, getdate());
--
-- INSERT INTO PRODUCT (SKU, NAME, DESCRIPTION, IMAGE_URL, ACTIVE, UNITS_IN_STOCK,
--                      UNIT_PRICE, CATEGORY_ID, DATE_CREATED)
-- VALUES ('BOOK-TECH-1001', 'Spring Framework Tutorial', 'Learn Spring',
--         'assets/images/products/placeholder.png'
--            ,1,100,29.99,1, getdate());
--
-- INSERT INTO PRODUCT (SKU, NAME, DESCRIPTION, IMAGE_URL, ACTIVE, UNITS_IN_STOCK,
--                      UNIT_PRICE, CATEGORY_ID, DATE_CREATED)
-- VALUES ('BOOK-TECH-1002', 'Kubernetes - Deploying Containers', 'Learn Kubernetes',
--         'assets/images/products/placeholder.png'
--            ,1,100,24.99,1, getdate());
--
-- INSERT INTO PRODUCT (SKU, NAME, DESCRIPTION, IMAGE_URL, ACTIVE, UNITS_IN_STOCK,
--                      UNIT_PRICE, CATEGORY_ID, DATE_CREATED)
-- VALUES ('BOOK-TECH-1003', 'Internet of Things (IoT) - Getting Started', 'Learn IoT',
--         'assets/images/products/placeholder.png'
--            ,1,100,29.99,1, getdate());
--
-- INSERT INTO PRODUCT (SKU, NAME, DESCRIPTION, IMAGE_URL, ACTIVE, UNITS_IN_STOCK,
--                      UNIT_PRICE, CATEGORY_ID, DATE_CREATED)
-- VALUES ('BOOK-TECH-1004', 'The Go Programming Language: A to Z', 'Learn Go',
--         'assets/images/products/placeholder.png'
--            ,1,100,24.99,1, getdate());
