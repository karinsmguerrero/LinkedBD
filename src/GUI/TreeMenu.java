package GUI;

import data.files.FileManager;
import javafx.scene.control.*;

import java.io.File;

public class TreeMenu {

    private FileManager files;
    private File[] listOfFolders;

    private TreeView<String> treeMenu;
    private TreeItem<String> treeRoot;

    public TreeMenu(){
        files = new FileManager();
        listOfFolders = files.getListOfFolders();
    }

    public TreeView<String> createTree(){
        return createTreeAux();
    }

    private TreeView<String> createTreeAux(){
        //Crear la raiz del arbol
        treeRoot = new TreeItem<>();
        treeRoot.setExpanded(true);
        //Crear el arbol y esconder la raiz
        treeMenu = new TreeView<String>(treeRoot);
        treeMenu.setShowRoot(false);

        //Añadir las ramas desde un archivo
        addBranch(treeRoot);

        //Codigo de tutorial de oracle
        ContextMenu contextMenu = new ContextMenu();

        MenuItem item1 = new MenuItem("Crear nueva base de datos");
        item1.setOnAction(e -> System.out.println("Created"));
        PopUp popUpWindow = new PopUp();
        String path;

        MenuItem item2 = new MenuItem("Eliminar base de datos");
        item2.setOnAction(e -> System.out.println("Deleted"));
        contextMenu.getItems().addAll(item1, item2);

        treeMenu.setContextMenu(contextMenu);

        return treeMenu;
    }

    private void addBranch(TreeItem parent){
        for(File item: listOfFolders) {
            String itemName = item.getName();
            int pos = itemName.lastIndexOf("."); //Busca el último . de la cadena
            if (pos > 0) { //Si pos es -1 el caracter no existe
                itemName = itemName.substring(0, pos); //Corta la cadena
            }
            TreeItem<String> branch = createBranch(itemName, parent);
            System.out.println(item.getPath());
            FileManager subFolder = new FileManager("\\" + item.getName(), "");
            File[] subFolderList = subFolder.getListOfFolders();

            FileManager subItems = new FileManager(item.getPath(), "");
            File[] subItemDocs = subItems.getListOfFiles(item.getPath());
            for (File document : subItemDocs) {
                addLeave(branch, document);
            }

        }
    }

    private void addLeave(TreeItem parent, File item) {
        String itemName = item.getName();
        int pos = itemName.lastIndexOf("."); //Busca el último . de la cadena
        if (pos > 0) { //Si pos es -1 el caracter no existe
            itemName = itemName.substring(0, pos); //Corta la cadena
        }
        createBranch(itemName, parent);
    }

    //Create branches
    private TreeItem<String> createBranch(String title, TreeItem<String> parent) {
        TreeItem<String> item = new TreeItem<>(title);
        item.setExpanded(true);
        parent.getChildren().add(item);
        return item;
    }
}
