package GUI;

import data.files.DBList;
import data.files.TableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;

import java.io.IOException;
import java.net.URL;


public class NewBDController {

    @FXML
    private TextField txtDB;

    @FXML
    private Button btnCreateBD;

    private void createDB( ) throws IOException {
        DBList dbList = new DBList();
        dbList.getDbList().addNodeToTheTail(new TableList(txtDB.getText(), txtDB.getText()));
        System.out.println("BD creada");
        //posible error
        URL treeMenuUrl = getClass().getResource("treeMenu.fxml");
        TreeView<String> treeMenu = FXMLLoader.load(treeMenuUrl);
        MainWindow.getRoot().setLeft(treeMenu);
    }

    @FXML
    private void btnCreateBD_click() throws IOException {
        createDB();
    }
}
