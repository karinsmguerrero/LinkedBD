package data.files;

import configuration.Setting;
import data.structures.generics.DoubleList;
import data.structures.generics.DoubleNode;

import java.io.File;

public class Commit {

    private FileManager fm;

    public Commit(){
        new DBList().printAll();
    }

    public void commitList(DoubleList<TableList> mainList){
        commitListAux(mainList);
    }

    private void commitListAux(DoubleList<TableList> mainList) {
        DoubleNode<TableList> temp = mainList.getHead();
        for (int i = 0; i < mainList.getSize(); i++) {
            fm = new FileManager("", temp.getValue().getFileDB());
            String dbPath = Setting.getMainFolderPath() + "\\" + temp.getValue().getFileDB();
            System.out.println("dbPath commit list: " + dbPath);
            fm.createFolder(temp.getValue().getFileDB());

            System.out.println("Db files: " + new File(dbPath).listFiles());
            DoubleNode<FieldList> temp2 = temp.getValue().getFileList().getHead();
            for (int o = 0; o < temp.getValue().getFileList().getSize(); o++) {
                System.out.println("Writing to file: table name: " + temp2.getValue().getFileTable() + ", file db: " + temp2.getValue().getFileDB() + ", text: " + new JsonManager(temp2.getValue().getFileDB(),
                        temp2.getValue().getFileTable()).ListToJsonString(temp2.getValue().getObjectList(),
                        temp2.getValue().getFileTable()));
                fm.writeToFile(temp2.getValue().getFileTable(), temp2.getValue().getFileDB(),
                        new JsonManager(temp2.getValue().getFileDB(),
                                temp2.getValue().getFileTable()).ListToJsonString(temp2.getValue().getObjectList(),
                                temp2.getValue().getFileTable()));
                temp2 = temp2.getNext();
            }
            temp = temp.getNext();
        }

    }
}
