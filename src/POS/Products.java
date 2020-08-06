package POS;

import Connectivity.ConnectionClass;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class Products {
    String productName;
    String productBrand;
    String productStatus;
    String desc;
    int productPrice;
    int qtyStock;
    int id;

    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();

    public Products(int id){
        this.id = id;
        String sql = sqlGenerator(id);
        this.productName = getProductName(sql);
        this.productBrand = getProductBrand(sql);
        this.productPrice = getProductPrice(sql);
        this.productStatus = getProductStatus(sql);
        this.qtyStock = getProductStock(sql);
        this.desc = getProductDescription(sql);
    }


    // DEFAULT GETTERS AND SETTERS
    // These will be used by the TableView
    // Because tableview requires a Property to be returned so it expects conventionally named getters and setters

    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public int getQtyStock() {
        return qtyStock;
    }


    public String sqlGenerator(int id){
        String sql = String.format("SELECT * FROM products JOIN brands WHERE prod_id = %d AND products.prod_brand = brands.brand_id",id);
        return sql;
    }


    // Get Prod Name
    public String getProductName(String sql){
        try {
            Statement statement = connection.createStatement();
            ResultSet getProdName = statement.executeQuery(sql);
            if (getProdName.next()){
                this.productName = getProdName.getString("prod_name");
                return getProdName.getString("prod_name");
            }
            else
                return "Error";
        }
        catch (Exception e){
            return "Exception";
        }
    }
    // Set Prod Name

    // Get Prod Price
    public int getProductPrice(String sql){
        try {
            Statement statement = connection.createStatement();
            ResultSet myresult = statement.executeQuery(sql);
            if (myresult.next()){
                this.productPrice = myresult.getInt("prod_price");
                return myresult.getInt("prod_price");
            }
            else
                return 0;
        }
        catch (Exception e){
            return 0;
        }
    }

    // Set Prod Price

    // Get Prod Brand
    public String getProductBrand(String sql){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()){
                this.productBrand = resultSet.getString("brand_name");
                return resultSet.getString("brand_name");
            }
            else
                return "Error";
        }
        catch (Exception e){
            return "Exception";
        }
    }
    // Set Prod Brand

    // Get Prod Quantity in Stock
    public int getProductStock(String sql){
        try {
            Statement statement = connection.createStatement();
            ResultSet myresult = statement.executeQuery(sql);
            if (myresult.next()){
                this.qtyStock = myresult.getInt("prod_price");
                return myresult.getInt("prod_price");
            }
            else
                return 0;
        }
        catch (Exception e){
            return 0;
        }
    }

    // Set Prod Quantity in Stock

    // Get Prod Status
    public String getProductStatus(String sql){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()){
                this.productStatus = resultSet.getString("prod_status");
                return resultSet.getString("prod_status");
            }
            else
                return "Error";
        }
        catch (Exception e){
            return "Exception";
        }
    }
    // Set Prod Status

    // Get Prod Description
    public String getProductDescription(String sql){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()){
                this.productStatus = resultSet.getString("prod_description");
                return resultSet.getString("prod_description");
            }
            else
                return "Error";
        }
        catch (Exception e){
            return "Exception";
        }
    }
    // Set Prod Description
}
