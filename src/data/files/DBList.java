package data.files;

import data.structures.generics.DoubleList;

public class DBList {
    private String fileName;
    private DoubleList<TableList> dbList;

    public DBList(String fileName){
        this.fileName = fileName;
        setDbList();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public DoubleList<TableList> getDbList() {
        return dbList;
    }

    /**
     * Crea un lista doble de tipo TableList, que contiene una lista circular de tipo FieldList
     * @see TableList
     * @see FieldList
     */
    private void setDbList() {
        FileManager flMng = new FileManager();
        dbList = flMng.folderToList();
    }
}
