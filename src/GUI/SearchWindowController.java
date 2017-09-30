package GUI;

import data.files.*;
import data.structures.generics.DoubleNode;
import data.structures.generics.Node;
import data.structures.generics.SimpleList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SearchWindowController {

    @FXML
    private TextField txtName;

    @FXML
    private Button btnSearchName, btnRequired;

    @FXML
    private TableView<RowMaker> tblViewSearch;

    @FXML
    private ChoiceBox<String> choiceRequired;

    private ObservableList<String> result;

    private FieldList fieldList;
    private TableList tableList;


    @FXML
    private void search(){
        SimpleList<RowMaker> searchRessult = new SimpleList<>();
        DoubleNode<TableList> temp = DBList.getDbList().getHead();
        for(int i = 0; i < DBList.getDbList().getSize(); i++){
            if (temp.getValue().getFileList().getHead() != null) {
                DoubleNode<FieldList> tempTable = temp.getValue().getFileList().getHead();
                for (int x = 0; x < temp.getValue().getFileList().getSize(); x++) {
                    Node<RowMaker> tempField = tempTable.getValue().getObjectList().getHead();
                    for (int y = 0; y < tempTable.getValue().getObjectList().getSize(); y++){
                        if(tempField.getValue().getColumnName().equals(txtName.getText())){
                            searchRessult.addNode(tempField.getValue());
                        }
                        tempField = tempField.getNext();
                    }
                    tempTable = tempTable.getNext();
                }
            }
            temp = temp.getNext();
        }
        addTable(searchRessult);
        System.out.println("-------------------------------");
    }

    private boolean checkValid(){
        return !txtName.getText().isEmpty();
    }

    /**
     * Obtiene los datos de los textFields, los añade a un objeto de tipo RowMaker y agrega un nuevo nodo a la lista
     */
    private void addRow(){
        if(checkValid()) {

         //   fieldList.getObjectList().addNode(rowMaker);
            addTable(fieldList.getObjectList());
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
        tblViewSearch.setItems(getRow(list));
        tblViewSearch.getColumns().addAll(nameColumn, typeColumn, fkColumn, pkColumn, requiredColumn, defaultColumn);

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
