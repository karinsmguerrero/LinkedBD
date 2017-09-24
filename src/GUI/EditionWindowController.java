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
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditionWindowController implements Initializable{

    SimpleList<RowMaker> rows;
    CircularDoubleList<FieldList> tables;
    DoubleNode<TableList> currentDB;

    @FXML
    private TextField txtTableName, txtName, txtType, txtFK, txtPK, txtDefault;

    @FXML
    private Button btnCreateTable, btnAdd, btnDelete;

    @FXML
    private TableView<RowMaker> tblViewRows;

    @FXML
    private ChoiceBox<String> txtRequired, choiceBD, tblChoice;

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
        choiceBD.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> selectDB(choiceBD.getValue())));
        txtRequired.getItems().addAll("true", "false");
        txtRequired.setValue("true");
    }

    @FXML
    private void selectDB(String db){
        tblChoice.getItems().clear();
        DoubleNode<TableList> temp = list.getDbList().getHead();
        for (int i = 0; i < list.getDbList().getSize(); i++){
            if (temp.getValue().getFileName() == db){
                currentDB = temp;
                tables = temp.getValue().getFileList();
                DoubleNode<FieldList> temp2 = temp.getValue().getFileList().getHead();
                for(int n = 0; n < temp.getValue().getFileList().getSize(); n++){
                    tblChoice.getItems().add(temp2.getValue().getFileName());
                    tblChoice.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> selectTable(tblChoice.getValue())));
                    temp2 = temp2.getNext();
                }
                tblChoice.setValue(tblChoice.getItems().get(0));
                addTable(list.getDbList().getHead().getValue().getFileList().getHead().getValue().getObjectList());
                break;
            }
            temp = temp.getNext();
        }
    }

    private void selectTable(String tbl){
        //tblViewRows.getItems().clear();
        DoubleNode<FieldList> temp = tables.getHead();
        System.out.println("loading rows...");
        for (int i = 0; i < tables.getSize(); i++){
            if(temp.getValue().getFileName() == tbl){
                System.out.println("found ya");
                rows = temp.getValue().getObjectList();
                tblViewRows.setItems(getRow(rows));
            }
            temp = temp.getNext();
        }
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

    public void addTable(SimpleList<RowMaker> list){
        addTableAux(list);
    }

    private void addTableAux(SimpleList<RowMaker> list) {

        //Name column
        TableColumn<RowMaker, String> nameColumn = new TableColumn<>("Nombre");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("columnName"));

        TableColumn<RowMaker, Double> typeColumn = new TableColumn<>("Tipo de dato");
        typeColumn.setMinWidth(100);
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("columnType"));

        TableColumn<RowMaker, String> fkColumn = new TableColumn<>("Llave foránea");
        fkColumn.setMinWidth(100);
        fkColumn.setCellValueFactory(new PropertyValueFactory<>("columnFK"));

        TableColumn<RowMaker, String> pkColumn = new TableColumn<>("Llave primaria");
        pkColumn.setMinWidth(100);
        pkColumn.setCellValueFactory(new PropertyValueFactory<>("columnPK"));

        TableColumn<RowMaker, String> requiredColumn = new TableColumn<>("Es requerido");
        requiredColumn.setMinWidth(100);
        requiredColumn.setCellValueFactory(new PropertyValueFactory<>("columnRequired"));

        TableColumn<RowMaker, String> defaultColumn = new TableColumn<>("Valor por defecto");
        defaultColumn.setMinWidth(100);
        defaultColumn.setCellValueFactory(new PropertyValueFactory<>("columnDefault"));

        //añadir filas a la tabla
        tblViewRows.getItems().clear();
        tblViewRows.getColumns().clear();
        tblViewRows.setItems(getRow(list));
        tblViewRows.getColumns().addAll(nameColumn, typeColumn, fkColumn, pkColumn, requiredColumn, defaultColumn);

    }

    private ObservableList<RowMaker> getRow(SimpleList<RowMaker> list){
        ObservableList<RowMaker> tableRows = FXCollections.observableArrayList();
        Node<RowMaker> temp = list.getHead();
        while (temp != null){
            tableRows.add(temp.getValue());
            temp = temp.getNext();
        }
        return tableRows;
    }
}
