CREATE TABLE IF NOT EXISTS products (
                                       product_id INT NOT NULL,
                                       name varchar(250) NOT NULL,
                                       PRIMARY KEY (product_id)
);

CREATE TABLE IF NOT EXISTS users (
                                     user_id INT NOT NULL,
                                     name varchar(250) NOT NULL,
                                     email varchar(250) NOT NULL,
                                     PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS orders (
                                    order_id varchar(200) NOT NULL,
                                    amount DECIMAL(20,3) NOT NULL,
                                    date_sale TIMESTAMP,
                                    product_id INT NOT NULL,
                                    user_id INT NOT NULL,
                                    PRIMARY KEY (order_id),
                                    CONSTRAINT fk_product
                                        FOREIGN KEY(product_id)
                                            REFERENCES products(product_id),
                                    CONSTRAINT fk_user
                                        FOREIGN KEY(user_id)
                                            REFERENCES users(user_id)
);