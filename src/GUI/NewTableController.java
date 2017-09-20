package GUI;

import data.files.DBList;
import data.files.FieldList;
import data.files.RowMaker;
import data.files.TableList;
import data.structures.generics.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewTableController implements Initializable{

    @FXML
    private TextField txtTableName, txtName, txtType, txtFK, txtPK, txtDefault;

    @FXML
    private Button btnCreateTable, btnAdd, btnDelete;

    @FXML
    private TableView<RowMaker> tblViewRows;

    @FXML
    private ChoiceBox<String> txtRequired, choiceBD;

    DBList list = new DBList();
    TableList tableList;
    RowMaker rowMaker;
    FieldList fieldList;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DoubleNode<TableList> temp = list.getDbList().getHead();
        for (int i = 0; i < list.getDbList().getSize(); i++){
            choiceBD.getItems().add(temp.getValue().getFileName());
            System.out.println(choiceBD.getItems());
            temp = temp.getNext();
        }
        choiceBD.setValue(choiceBD.getItems().get(0));
        txtRequired.getItems().addAll("true", "false");
        txtRequired.setValue(txtRequired.getItems().get(0));
    }

    private void createTable( ) throws IOException {
        DBList dbList = new DBList();

        tableList.setFileName(txtTableName.getText());
        tableList.setFileDB(choiceBD.getValue());
        System.out.println("choiceDB value: " + choiceBD.getValue());
        dbList.getDbList().addNodeToTheTail(tableList);

        System.out.println("Table creada");
        //posible error
        URL treeMenuUrl = getClass().getResource("treeMenu.fxml");
        TreeView<String> treeMenu = FXMLLoader.load(treeMenuUrl);
        MainWindow.getRoot().setLeft(treeMenu);
    }

    @FXML
    private void btnCreateTable_click() throws IOException {
        createTable();
    }

    @FXML
    private void btnAdd_click(){
        rowMaker = new RowMaker();
        rowMaker.setColumnName(txtName.getText());
        rowMaker.setColumnType(txtType.getText());
        rowMaker.setColumnFK(txtFK.getText());
        rowMaker.setColumnPK(txtPK.getText());
        if (txtRequired.getSelectionModel().getSelectedItem() == "true")
            rowMaker.setColumnRequired(true);
        else
            rowMaker.setColumnRequired(false);
        rowMaker.setColumnDefault(txtDefault.getText());
        fieldList = new FieldList(tableList.getFileName(), tableList.getFileName(), tableList.getFileDB());

        tblViewRows.getItems().add(rowMaker);
        txtName.clear();
        txtType.clear();
        txtFK.clear();
        txtPK.clear();
        txtRequired.getSelectionModel().clearSelection();
        txtDefault.clear();
        System.out.println("Row added");
    }

    //Get all of the products
    /*private ObservableList<RowMaker> getRow(){
        ObservableList<RowMaker> tableRows = FXCollections.observableArrayList();
        int i = 0;
        Node<RowMaker> temp = this.list.getHead();
        while (temp != null){
            tableRows.add(temp.getValue());
            temp = temp.getNext();
        }
        return tableRows;
    }*/
}
