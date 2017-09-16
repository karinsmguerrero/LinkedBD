package data.files;

import data.structures.generics.CircularDoubleList;

public class TableList {

    private String fileName;
    private CircularDoubleList<FieldList> fileList;
    private String fileTable, fileDB;

    public TableList(String fileName, String fileTable, String fileDB){
        this.fileName = fileName;
        this.fileTable = fileTable;
        this.fileDB = fileDB;
    }

    public String getFileTable() {
        return fileTable;
    }

    public String getFileDB(){
        return this.fileDB;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public CircularDoubleList<FieldList> getFileList() {
        return fileList;
    }

    /**
     * Crea un lista circular de tipo FieldList, que contiene una lista simple de tipo RowMaker
     * @see RowMaker
     * @see FieldList
     */
    private void setObjectList() {
        FileManager flMng = new FileManager("", fileDB);
        fileList = flMng.fileToList();
    }
}
