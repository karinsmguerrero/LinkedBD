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

import javax.annotation.processing.RoundEnvironment;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewTableController implements Initializable{

    @FXML
    private TextField txtTableName, txtName, txtFK, txtPK, txtDefault;

    @FXML
    private Button btnCreateTable, btnAdd;

    @FXML
    private TableView<RowMaker> tblViewRows;

    @FXML
    private ChoiceBox<String> txtRequired, choiceBD, choiceType;

    @FXML
    private Label lblAlert;

    private FieldList fieldList;
    private TableList tableList;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DoubleNode<TableList> temp = DBList.getDbList().getHead();
        for (int i = 0; i < DBList.getDbList().getSize(); i++){
            choiceBD.getItems().add(temp.getValue().getFileName());
            temp = temp.getNext();
        }
        choiceBD.setValue(choiceBD.getItems().get(0));
        txtRequired.getItems().addAll("true", "false");
        choiceType.getItems().addAll("String", "Int", "Boolean", "Date", "Double");
    }

    private void createTable( ) throws IOException {
        /*Estructura para agregar nodos
        DBList.DoubleList -> DoubleList.Add(TableList) -> TableList.CircularList -> CircularList.Add(FieldList) ->
        FieldList.SimpleList -> SimpleList.add(RowMaker)
        */

        tblViewRows.getItems().clear();
        tblViewRows.getColumns().clear();

        SimpleList<RowMaker> simpleList = new SimpleList<>();

        fieldList = new FieldList();
        fieldList.setFileDB(choiceBD.getValue());
        fieldList.setFileName(txtTableName.getText());
        fieldList.setFileTable(txtTableName.getText());
        fieldList.setObjectList(simpleList);

        DoubleNode<TableList> temp = DBList.getDbList().getHead();
        for (int i = 0; i < DBList.getDbList().getSize(); i++){
          if(temp.getValue().getFileDB().equals(choiceBD.getValue())){
              temp.getValue().getFileList().insertNodeToTail(fieldList);
          }
          temp = temp.getNext();
        }
        System.out.println("Tabla creada");
        DBList.printAll();

    }

    @FXML
    private void btnCreateTable_click() throws IOException {
        createTable();
    }

    @FXML
    private void btnAdd_click(){
        addRow();
    }

    /**
     * Obtiene los datos de los textFields, los añade a un objeto de tipo RowMaker y agrega un nuevo nodo a la lista
     */
    private void addRow(){
        if(checkValid()) {
            lblAlert.setText("");

            RowMaker rowMaker = new RowMaker();
            rowMaker.setColumnName(txtName.getText());
            rowMaker.setColumnType(choiceType.getValue());
            rowMaker.setColumnFK(txtFK.getText());
            rowMaker.setColumnPK(txtPK.getText());
            if (txtRequired.getSelectionModel().getSelectedItem().equals("true"))
                rowMaker.setColumnRequired(true);
            else
                rowMaker.setColumnRequired(false);
                rowMaker.setColumnDefault(txtDefault.getText());

            fieldList.getObjectList().addNode(rowMaker);
            addTable(fieldList.getObjectList());

            txtName.clear();
            choiceType.getSelectionModel().clearSelection();
            txtFK.clear();
            txtPK.clear();
            txtRequired.getSelectionModel().clearSelection();
            txtDefault.clear();

            System.out.println("Row added");
        }
        else {
            lblAlert.setText("Datos insuficientes");
        }
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

        TableColumn<RowMaker, String> fkColumn = new TableColumn<>("Tipo especial");
        fkColumn.setMinWidth(100);
        fkColumn.setCellValueFactory(new PropertyValueFactory<>("columnFK"));

        TableColumn<RowMaker, String> pkColumn = new TableColumn<>("Tipo especial");
        pkColumn.setMinWidth(100);
        pkColumn.setCellValueFactory(new PropertyValueFactory<>("columnPK"));

        TableColumn<RowMaker, String> requiredColumn = new TableColumn<>("Es requerido");
        requiredColumn.setMinWidth(100);
        requiredColumn.setCellValueFactory(new PropertyValueFactory<>("columnRequired"));

        TableColumn<RowMaker, String> defaultColumn = new TableColumn<>("Valor por defecto");
        defaultColumn.setMinWidth(100);
        defaultColumn.setCellValueFactory(new PropertyValueFactory<>("columnDefault"));

        //añadir filas a la tabla
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

    private boolean checkValid(){
        return !txtName.getText().isEmpty() && !choiceType.getValue().isEmpty() &&
                !txtFK.getText().isEmpty() && !txtPK.getText().isEmpty() &&
                !txtRequired.getValue().isEmpty() && !txtDefault.getText().isEmpty() &&
                !txtTableName.getText().isEmpty();
    }
}
