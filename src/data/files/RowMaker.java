package data.files;

public class RowMaker {
    private String columnName;
    private String columnType;
    private String columnFK, columnPK;
    private boolean columnRequired;
    private String columnDefault;

    public RowMaker(){
        this.columnName = null;
        this.columnType = null;
        this.columnFK = this.columnPK = null;
        this.columnRequired = false;
        this.columnDefault = null;
    }

    public RowMaker(String columnName, String columnType, String columnFK, String columnPK, boolean columnRequired, String columnDefault){
        this.columnName = columnName;
        this.columnType = columnType;
        this.columnFK = columnFK;
        this.columnPK = columnPK;
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

    public String getColumnPK() {
        return columnPK;
    }

    public void setColumnPK(String columnPK) {
        this.columnPK = columnPK;
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
