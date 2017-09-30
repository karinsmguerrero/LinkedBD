package GUI.contextMenu;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
/**
 * Codigo tomado de https://stackoverflow.com/questions/35045725/creating-a-custom-tree-with-javafx por fabian
 */
public class MyContextMenu {
    private ContextMenu contextMenu, DBContextMenu;
    private MenuItem add, addField, updateTable;
    private MenuItem delete, deleteAll, deleteField;

    public MyContextMenu() {
        this.add = new MenuItem("Añadir tabla");
        this.delete = new MenuItem("Eliminar tabla");
        this.addField = new MenuItem("Añadir campos");
        this.deleteAll = new MenuItem("Eliminar todos los campos");
        this.deleteField = new MenuItem("Eliminar campos");
        this.updateTable = new MenuItem("Actualizar tabla");
        this.contextMenu = new ContextMenu(add, delete);
        this.DBContextMenu = new ContextMenu(addField, deleteField, deleteAll, updateTable);
    }

    public ContextMenu getContextMenu() {
        return contextMenu;
    }

    public ContextMenu getDBContextMenu() {
        return DBContextMenu;
    }

    public MenuItem getAddField() {
        return addField;
    }

    public MenuItem getUpdateTable() {
        return updateTable;
    }

    public MenuItem getDeleteAll() {
        return deleteAll;
    }

    public MenuItem getDeleteField() {
        return deleteField;
    }

    public MenuItem getAdd() {
        return add;
    }

    public MenuItem getDelete() {
        return delete;
    }

    /**
     * This method prevents memory leak by setting all actionListeners to null.
     */
    public void freeActionListeners() {
        this.add.setOnAction(null);
        this.delete.setOnAction(null);
        this.updateTable.setOnAction(null);
        this.deleteAll.setOnAction(null);
    }

}
