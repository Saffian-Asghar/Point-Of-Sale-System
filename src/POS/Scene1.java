package POS;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Scene1 {
    public TextField customerName;
    public TextField custNumber;
    public Button customerButton;

    public String getCustomerName() {
        return customerName.getText();
    }

    public String getCustNumber() {
        return custNumber.getText();
    }

    public void handleclick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene2.fxml"));
        Parent root = (Parent) loader.load();
        Controller myController = loader.getController();
        myController.setCustomerName(getCustomerName());
        Scene myScene = new Scene(root,1920,1018);

        myController.setCustomerName(getCustomerName());

        Stage primaryStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setTitle("Java Point of Sale System by Saffian Asghar");
        primaryStage.setMaximized(true);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }
}
