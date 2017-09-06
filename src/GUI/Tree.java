package GUI;

import data.files.FileManager;
import javafx.scene.control.*;

import java.io.File;

public class Tree {

    private FileManager files;
    private File[] listOfFolders;

    private TreeView<String> treeMenu;
    private TreeItem<String> treeRoot;

    public Tree(){
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
        /*treeMenu.getSelectionModel()
                .selectedItemProperty()
                .addListener((v, oldValue, newValue) -> {
                    if (newValue != null) {
                        String path = treeMenu.getSelectionModel().getSelectedItem().getParent().getValue();
                        path += treeMenu.getSelectionModel().getSelectedItem().getValue();
                        FileManager fm = new FileManager("C:\\Users\\karin\\Desktop\\PruebaBD");
                        fm.createFolder("Holi" + path);
                    }
                });*/

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
            FileManager subFolder = new FileManager("\\" + item.getName());
            File[] subFolderList = subFolder.getListOfFolders();


            addSubBranch(branch, subFolderList);

        }
    }

    private void addSubBranch(TreeItem parent, File[] List) {
        for (File subItem : List) {
            String itemName = subItem.getName();
            int pos = itemName.lastIndexOf("."); //Busca el último . de la cadena
            if (pos > 0) { //Si pos es -1 el caracter no existe
                itemName = itemName.substring(0, pos); //Corta la cadena
            }
            TreeItem<String> branch = createBranch(itemName, parent);

            FileManager subFolder = new FileManager(subItem.getPath());
            File[] subFolderDocs = subFolder.getListOfFiles(subItem.getPath());
            for (File document : subFolderDocs) {
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
