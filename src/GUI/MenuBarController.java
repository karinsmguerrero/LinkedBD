package GUI;

import data.files.DBList;
import data.files.TableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuBarController {

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu searchMenu, newMenu, helpMenu;

    @FXML
    private MenuItem dbMenuItem, tableMenuItem, aboutMenuItem;


    @FXML
    private void switchToCreteDB() {
        try {
            AnchorPane newDBWindow = FXMLLoader.load(getClass().getResource("NewBDWindow.fxml"));

            BorderPane border = MainWindow.getRoot();
            border.setCenter(newDBWindow);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void switchToCreateTable(){
        try {
            AnchorPane newDBWindow = FXMLLoader.load(getClass().getResource("NewTableWindow.fxml"));

            BorderPane border = MainWindow.getRoot();
            border.setCenter(newDBWindow);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}