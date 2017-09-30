package GUI;

import com.sun.rowset.internal.Row;
import data.files.*;
import data.structures.generics.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditionWindowController implements Initializable{

    SimpleList<RowMaker> rows;
    CircularDoubleList<FieldList> tables;
    DoubleNode<TableList> currentDB;

    @FXML
    private TextField  txtName, txtFK, txtPK, txtDefault;

    @FXML
    private Button btnCreateTable, btnAdd, btnDelete;

    @FXML
    private TableView<RowMakerTable> tblViewRows;

    @FXML
    private ChoiceBox<String> txtRequired, choiceBD,  txtType,tblChoice;

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
        txtType.getItems().addAll("String", "Int", "Boolean", "Date", "Double");
        setTableEditable();
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
        for (int i = 0; i < tables.getSize(); i++){
            if(temp.getValue().getFileName() == tbl){
                rows = temp.getValue().getObjectList();
                tblViewRows.setItems(getRow(rows));
            }
            temp = temp.getNext();
        }
    }

    private void createTable( ) throws IOException {
        DBList dbList = new DBList();

        //tableList.setFileName(txtTableName.getText());
        tableList.setFileDB(choiceBD.getValue());
        System.out.println("choiceDB value: " + choiceBD.getValue());
        dbList.getDbList().addNodeToTheTail(tableList);

        System.out.println("Table creada");
        /*//posible error
        URL treeMenuUrl = getClass().getResource("treeMenu.fxml");
        TreeView<String> treeMenu = FXMLLoader.load(treeMenuUrl);
        MainWindow.getRoot().setLeft(treeMenu);*/
    }

    @FXML
    private void btnCreateTable_click() throws IOException {
        createTable();
    }

    @FXML
    private void btnAdd_click(){
        rowMaker = new RowMaker();
        rowMaker.setColumnName(txtName.getText());
        rowMaker.setColumnType(txtType.getValue());
        rowMaker.setColumnFK(txtFK.getText());
        rowMaker.setColumnPK(txtPK.getText());
        if (txtRequired.getSelectionModel().getSelectedItem() == "true")
            rowMaker.setColumnRequired(true);
        else
            rowMaker.setColumnRequired(false);
        rowMaker.setColumnDefault(txtDefault.getText());
        fieldList = new FieldList(tableList.getFileName(), tableList.getFileName(), tableList.getFileDB());

        //tblViewRows.getItems().add(rowMaker);
        txtName.clear();
        txtFK.clear();
        txtPK.clear();
        txtRequired.getSelectionModel().clearSelection();
        txtDefault.clear();
        System.out.println("Row added");

    }

    private ObservableList<RowMaker> data = FXCollections.observableArrayList();


    public void addTable(SimpleList<RowMaker> list){
        addTableAux(list);
    }

    private void addTableAux(SimpleList<RowMaker> list) {
        //Name column
        TableColumn<RowMakerTable, String> nameColumn = new TableColumn<>("Nombre");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setCellFactory(EditCell.<RowMakerTable>forTableColumn());
        // updates the salary field on the PersonTableData object to the
        // committed value
        nameColumn.setOnEditCommit(event -> { final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
            ((RowMakerTable) event.getTableView().getItems().get(event.getTablePosition().getRow())).setName(value);
            tblViewRows.refresh(); });

        TableColumn<RowMakerTable, String> typeColumn = new TableColumn<>("Tipo de dato");
        typeColumn.setMinWidth(100);
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        typeColumn.setCellFactory(EditCell.<RowMakerTable>forTableColumn());
        typeColumn.setOnEditCommit(event -> { final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
            ((RowMakerTable) event.getTableView().getItems().get(event.getTablePosition().getRow())).setType(value);
            tblViewRows.refresh(); });

        TableColumn<RowMakerTable, String> fkColumn = new TableColumn<>("Llave foránea");
        fkColumn.setMinWidth(100);
        fkColumn.setCellValueFactory(new PropertyValueFactory<>("fk"));
        fkColumn.setCellFactory(EditCell.<RowMakerTable>forTableColumn());
        fkColumn.setOnEditCommit(event -> { final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
            ((RowMakerTable) event.getTableView().getItems().get(event.getTablePosition().getRow())).setFk(value);
            tblViewRows.refresh(); });

        TableColumn<RowMakerTable, String> pkColumn = new TableColumn<>("Llave primaria");
        pkColumn.setMinWidth(100);
        pkColumn.setCellValueFactory(new PropertyValueFactory<>("pk"));
        pkColumn.setCellFactory(EditCell.<RowMakerTable>forTableColumn());
        pkColumn.setOnEditCommit(event -> { final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
            ((RowMakerTable) event.getTableView().getItems().get(event.getTablePosition().getRow())).setPk(value);
            tblViewRows.refresh(); });

        TableColumn<RowMakerTable, String> requiredColumn = new TableColumn<>("Es requerido");
        requiredColumn.setMinWidth(100);
        requiredColumn.setCellValueFactory(new PropertyValueFactory<>("required"));

        TableColumn<RowMakerTable, String> defaultColumn = new TableColumn<>("Valor por defecto");
        defaultColumn.setMinWidth(100);
        defaultColumn.setCellValueFactory(new PropertyValueFactory<>("defaultVal"));
        defaultColumn.setCellFactory(EditCell.<RowMakerTable>forTableColumn());
        defaultColumn.setOnEditCommit(event -> { final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
            ((RowMakerTable) event.getTableView().getItems().get(event.getTablePosition().getRow())).setDefaultVal(value);
            tblViewRows.refresh(); });

        //añadir filas a la tabla
        tblViewRows.getItems().clear();
        tblViewRows.getColumns().clear();
        tblViewRows.setItems(getRow(list));
        tblViewRows.getColumns().addAll(nameColumn, typeColumn, fkColumn, pkColumn, requiredColumn, defaultColumn);

    }

    private ObservableList<RowMakerTable> getRow(SimpleList<RowMaker> list){
        ObservableList<RowMakerTable> tableRows = FXCollections.observableArrayList();
        Node<RowMaker> temp = list.getHead();
        while (temp != null){
            tableRows.add(new RowMakerTable(temp.getValue()));
            temp = temp.getNext();
        }
        return tableRows;
    }

    private void setTableEditable() {
        tblViewRows.setEditable(true);
        // allows the individual cells to be selected
        tblViewRows.getSelectionModel().cellSelectionEnabledProperty().set(true);
        // when character or numbers pressed it will start edit in editable
        // fields
        tblViewRows.setOnKeyPressed(event -> {
            if (event.getCode().isLetterKey() || event.getCode().isDigitKey()) {
                editFocusedCell();
            } else if (event.getCode() == KeyCode.RIGHT
                    || event.getCode() == KeyCode.TAB) {
                tblViewRows.getSelectionModel().selectNext();
                event.consume();
            } else if (event.getCode() == KeyCode.LEFT) {
                // work around due to
                // TableView.getSelectionModel().selectPrevious() due to a bug
                // stopping it from working on
                // the first column in the last row of the table
                selectPrevious();
                event.consume();
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void editFocusedCell() {
        final TablePosition<RowMakerTable, ?> focusedCell = tblViewRows.focusModelProperty().get().focusedCellProperty().get();
        tblViewRows.edit(focusedCell.getRow(), focusedCell.getTableColumn());
    }

    @SuppressWarnings("unchecked")
    private void selectPrevious() {
        if (tblViewRows.getSelectionModel().isCellSelectionEnabled()) {
            // in cell selection mode, we have to wrap around, going from
            // right-to-left, and then wrapping to the end of the previous line
            TablePosition<RowMakerTable, ?> pos = tblViewRows.getFocusModel()
                    .getFocusedCell();
            if (pos.getColumn() - 1 >= 0) {
                // go to previous row
                tblViewRows.getSelectionModel().select(pos.getRow(),
                        getTableColumn(pos.getTableColumn(), -1));
            } else if (pos.getRow() < tblViewRows.getItems().size()) {
                // wrap to end of previous row
                tblViewRows.getSelectionModel().select(pos.getRow() - 1,
                        tblViewRows.getVisibleLeafColumn(
                                tblViewRows.getVisibleLeafColumns().size() - 1));
            }
        } else {
            int focusIndex = tblViewRows.getFocusModel().getFocusedIndex();
            if (focusIndex == -1) {
                tblViewRows.getSelectionModel().select(tblViewRows.getItems().size() - 1);
            } else if (focusIndex > 0) {
                tblViewRows.getSelectionModel().select(focusIndex - 1);
            }
        }
    }

    private TableColumn<RowMakerTable, ?> getTableColumn(final TableColumn<RowMakerTable, ?> column, int offset) {
        int columnIndex = tblViewRows.getVisibleLeafIndex(column);
        int newColumnIndex = columnIndex + offset;
        return tblViewRows.getVisibleLeafColumn(newColumnIndex);
    }


    @FXML
    private void submit(final ActionEvent event) {
        if (allFieldsValid()) {
            final String name = txtName.getText();
            final String type = txtType.getValue();
            final String fK = txtFK.getText();
            final String pk = txtPK.getText();
            final boolean required = txtRequired.getValue().equals("true");
            final String def = txtDefault.getText();
            data.add(new RowMaker(name, type, fK, pk, required, def));
        }
    }

    private boolean allFieldsValid() {
        return !txtName.getText().isEmpty()
                && !txtType.getValue().isEmpty()
                && !txtFK.getText().isEmpty()
                && !txtPK.getText().isEmpty()
                && !txtRequired.getValue().isEmpty()
                && !txtDefault.getText().isEmpty();
    }

}
