-- Point of Sales system database
-- M Saffian Asghar
-- 03/08/2020


--
-- Table structure for brands
--
CREATE TABLE Brands(
    brand_id INT(11) PRIMARY KEY AUTO_INCREMENT,
    brand_name VARCHAR(60) NOT NULL,
    brand_qt_stock INT(11),
    brand_stock_sales INT(11),
    brand_description TEXT

)ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Table structure customer
--
CREATE TABLE Customers(
    cus_id INT(11) PRIMARY KEY AUTO_INCREMENT,
    cus_name VARCHAR(60) NOT NULL,
    cus_mobile INT(11)

)ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Table structure for Products
--

CREATE TABLE Products(
    prod_id INT(11) PRIMARY KEY AUTO_INCREMENT,
    prod_name VARCHAR(60) NOT NULL,
    prod_brand INT(11) NOT NULL,
    prod_price INT(11) NOT NULL,
    prod_qt_stock INT(11),
    prod_status VARCHAR(13),
    prod_description TEXT,
    FOREIGN KEY (prod_brand) REFERENCES Brands(brand_id)
    

)ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for order information
--
CREATE TABLE Orders_info(
    order_info_id INT(11) PRIMARY KEY AUTO_INCREMENT,
    order_prod_id INT(11),
    order_prod_amt INT(11),
    FOREIGN KEY (order_prod_id) REFERENCES Products(prod_id)
    

)ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for Orders
--
CREATE TABLE Orders(
    order_id INT(11) PRIMARY KEY AUTO_INCREMENT,
    order_customer INT(11),
    order_amount INT(11),
    order_info_id INT(11),
    order_date date,
    FOREIGN KEY (order_customer) REFERENCES Customers(cus_id),
    FOREIGN KEY (order_info_id) REFERENCES Orders_info(order_info_id)
        

)ENGINE=InnoDB DEFAULT CHARSET=latin1;

