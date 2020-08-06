package POS;


import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Text subtotal;
    public Text tax;
    public Text customerName;
    public Text total;
    public TableView mainTable;
    public TableColumn table_id;
    public TableColumn product_name;
    public TableColumn  product_brand;
    public TableColumn product_quantity;
    public TableColumn  product_price;
    public TextField idInput;
    public ObservableList<Products> list = FXCollections.observableArrayList();


    public void setCustomerName(String name){
        customerName.setText(name);
    }




    public int subtotalAmount = 0;


    public String sqlGenerator(int id){
        String sql = String.format("SELECT * FROM products JOIN brands WHERE prod_id = %d AND products.prod_brand = brands.brand_id",id);
        return sql;
    }

    public void setSubTotal(int totalAmount){

        subtotalAmount += totalAmount;
        subtotal.setText("Rs "+ String.valueOf(subtotalAmount));

    }

    public void setTotal(){
        DecimalFormat f = new DecimalFormat("##.00");

        total.setText("Rs "+ String.valueOf(f.format(subtotalAmount*1.16)));
    }

    public void setTax(){
        DecimalFormat f = new DecimalFormat("##.00");

        tax.setText("Rs "+ String.valueOf(f.format(subtotalAmount*0.16)));

    }

    // This action event is caused by the field in which you put in the id of the product

    public void onEnter(ActionEvent ae) {
        int id = Integer.parseInt(String.valueOf(idInput.getCharacters()));
        Products showProd = new Products(id);

        table_id.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Products, Products>, ObservableValue<Products>>() {
            @Override public ObservableValue<Products> call(TableColumn.CellDataFeatures<Products, Products> p) {
                return new ReadOnlyObjectWrapper(p.getValue());
            }
        });

        table_id.setCellFactory(new Callback<TableColumn<Products, Products>, TableCell<Products, Products>>() {
            @Override public TableCell<Products, Products> call(TableColumn<Products, Products> param) {
                return new TableCell<Products, Products>() {
                    @Override protected void updateItem(Products item, boolean empty) {
                        super.updateItem(item, empty);

                        if (this.getTableRow() != null && item != null) {
                            setText(this.getTableRow().getIndex()+"");
                        } else {
                            setText("");
                        }
                    }
                };
            }
        });
        table_id.setSortable(false);
        product_name.setCellValueFactory(new PropertyValueFactory<Products, String>("productName"));
        product_brand.setCellValueFactory(new PropertyValueFactory<Products, String>("productBrand"));
        product_price.setCellValueFactory(new PropertyValueFactory<Products, Integer>("productPrice"));

        list.add(
                new Products(id)
        );
        // Getting the product
        //mainTable.getItems().add(new Products(id));

        mainTable.setItems(list);
        setSubTotal(showProd.productPrice);
        setTotal();
        setTax();
        System.out.println(list);



        idInput.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
