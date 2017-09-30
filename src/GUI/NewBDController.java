package GUI;

import data.files.DBList;
import data.files.TableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;


public class NewBDController extends WindowUtility{

    @FXML
    private TextField txtDB;

    @FXML
    private Button btnCreateBD;

    private void createDB( ) throws IOException {
        if(!txtDB.getText().isEmpty()) {
            DBList.getDbList().addNodeToTheTail(new TableList(txtDB.getText(), txtDB.getText()));
            reloadTree();
        }
    }

    @FXML
    private void btnCreateBD_click() throws IOException {
        createDB();
    }
}
