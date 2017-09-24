package GUI;

import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.util.StringConverter;

public class CustomTreeCell extends TextFieldTreeCell<String> {

    private MyContextMenu contextMenu;

    public CustomTreeCell(MyContextMenu contextMenu, StringConverter<String> converter) {
        super(converter);
        if (contextMenu == null) {
            throw new NullPointerException();
        }
        this.contextMenu = contextMenu;
        this.setOnContextMenuRequested(evt -> {
            prepareContextMenu(getTreeItem());
            evt.consume();
        });
    }

    private void prepareContextMenu(TreeItem<String> item) {
        if(item.isLeaf()) {
            MenuItem deleteField = contextMenu.getDeleteField();
            boolean root = item.getParent() == null;
            if (!root) {
                deleteField.setOnAction(evt -> {
                    item.getParent().getChildren().remove(item);
                    contextMenu.freeActionListeners();
                });
            }
            deleteField.setDisable(root);
            contextMenu.getAddField().setOnAction(evt -> {
                        item.getChildren().add(new TreeItem<>("new item"));
                        contextMenu.freeActionListeners();
            });
            contextMenu.getDeleteAll().setOnAction(e -> System.out.println("Delete all"));
        }
        else {
            MenuItem delete = contextMenu.getDelete();
            boolean root = item.getParent() == null;
            if (!root) {
                delete.setOnAction(evt -> {
                    item.getParent().getChildren().remove(item);
                    contextMenu.freeActionListeners();
                    System.out.println("Action on non leave");
                });
            }
            delete.setDisable(root);
            contextMenu.getAdd().setOnAction(evt -> {
                item.getChildren().add(new TreeItem<>("new item"));
                contextMenu.freeActionListeners();
            });
        }
    }

    @Override
    public void updateItem(String item, boolean empty) {

        super.updateItem(item, empty);
        if (!empty) {
            setContextMenu("nocontext".equals(item) ? null : contextMenu.getDBContextMenu());
            setEditable(!"noedit".equals(item));
        }
    }

}
