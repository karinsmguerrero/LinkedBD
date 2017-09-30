package GUI;

import data.files.FieldList;
import data.files.RowMaker;
import data.structures.generics.Node;
import data.structures.generics.SimpleList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class TableView {

    javafx.scene.control.TableView<RowMaker> table;
    TextField nameInput, typeInput, fkInput, pkInput, defaultInput;
    ChoiceBox requiredChoice;
    SimpleList<RowMaker> list;

    public TableView(SimpleList<RowMaker> list ){
        this.list = list;
    }

    public HBox addFooter(){
        return addFooterAux();
    }

    public VBox addTable(){
        return addTableAux();
    }

    private VBox addTableAux() {

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

        table = new javafx.scene.control.TableView<>();
        //añadir filas a la tabla
        table.setItems(getRow());
        table.getColumns().addAll(nameColumn, typeColumn, fkColumn, pkColumn, requiredColumn, defaultColumn);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, addFooter());
        VBox.setVgrow(table, Priority.ALWAYS);
        return vBox;
    }

    private HBox addFooterAux(){
        //Name input
        nameInput = new TextField();
        nameInput.setPromptText("Nombre");
        nameInput.setMinWidth(100);

        typeInput = new TextField();
        typeInput.setPromptText("Tipo de dato");

        fkInput = new TextField();
        fkInput.setPromptText("Tipo especial");

        pkInput = new TextField();
        pkInput.setPromptText("Tipo especial");

        requiredChoice = new ChoiceBox<String>();
        requiredChoice.getItems().addAll("true", "false");
        requiredChoice.setValue("false");

        defaultInput = new TextField();
        defaultInput.setPromptText("Valor por defecto");

        //Button
        Button addButton = new Button("Añadir");
        Button deleteButton = new Button("Eliminar");
        deleteButton.setOnAction(e -> deleteButtonClicked());

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(nameInput, typeInput, fkInput, pkInput, requiredChoice, defaultInput, addButton, deleteButton);
        return hBox;
    }
    //Add button clicked
    private void addButtonClicked(String name, String type, String fk, String pk, boolean required, String def, FieldList fieldList){
        RowMaker rowMaker = new RowMaker();
        rowMaker.setColumnName(name);
        rowMaker.setColumnType(type);
        rowMaker.setColumnFK(fk);
        rowMaker.setColumnPK(pk);

        rowMaker.setColumnRequired(required);

        rowMaker.setColumnDefault(def);

        fieldList.getObjectList().addNode(rowMaker);
    }

    //Delete button clicked
    private void deleteButtonClicked(){
        ObservableList<RowMaker> rowSelected, allRows;
        allRows = table.getItems();
        rowSelected = table.getSelectionModel().getSelectedItems();

        rowSelected.forEach(allRows::remove);
        list.deleteByContent(rowSelected.get(0));
    }

    //Get all of the products
    private ObservableList<RowMaker> getRow(){
        ObservableList<RowMaker> tableRows = FXCollections.observableArrayList();
        int i = 0;
        Node<RowMaker> temp = this.list.getHead();
        while (temp != null){
            tableRows.add(temp.getValue());
            temp = temp.getNext();
        }
        return tableRows;
    }

}
