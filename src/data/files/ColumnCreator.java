package data.files;

public class ColumnCreator {
    private String columnName;
    private String columnType;
    private String columnFK;
    private boolean columnRequired;
    private String columnDefault;

    public ColumnCreator(){
        this.columnName = null;
        this.columnType = null;
        this.columnFK = null;
        this.columnRequired = false;
        this.columnDefault = null;
    }

    public ColumnCreator(String columnName, String columnType, String columnFK, boolean columnRequired, String columnDefault){
        this.columnName = columnName;
        this.columnType = columnType;
        this.columnFK = columnFK;
        this.columnRequired = columnRequired;
        this.columnDefault = columnDefault;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public void setColumnFK(String columnFK) {
        this.columnFK = columnFK;
    }

    public void setColumnRequired(boolean columnRequired) {
        this.columnRequired = columnRequired;
    }

    public void setColumnDefault(String columnDefault) {
        this.columnDefault = columnDefault;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public String getColumnFK() {
        return columnFK;
    }

    public boolean isColumnRequired() {
        return columnRequired;
    }

    public String getColumnDefault() {
        return columnDefault;
    }
}
