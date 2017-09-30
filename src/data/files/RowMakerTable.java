package data.files;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class RowMakerTable {

        private SimpleStringProperty name;
        private SimpleStringProperty type;
        private SimpleStringProperty fk;
        private SimpleStringProperty pk;
        private SimpleBooleanProperty required;
        private SimpleStringProperty defaultVal;

        public RowMakerTable(RowMaker rows) {
            this.name = new SimpleStringProperty(rows.getColumnName());
            this.type = new SimpleStringProperty(rows.getColumnType());
            this.fk = new SimpleStringProperty(rows.getColumnFK());
            this.pk = new SimpleStringProperty(rows.getColumnPK());
            this.required = new SimpleBooleanProperty(rows.isColumnRequired());
            this.defaultVal = new SimpleStringProperty(rows.getColumnDefault());
        }

        public RowMakerTable( String name, String type, String fk, String pk, boolean required, String defaultVal) {
            this.name = new SimpleStringProperty(name);
            this.type = new SimpleStringProperty(type);
            this.fk = new SimpleStringProperty(fk);
            this.pk = new SimpleStringProperty(pk);
            this.required = new SimpleBooleanProperty(required);
            this.defaultVal = new SimpleStringProperty(defaultVal);
        }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getFk() {
        return fk.get();
    }

    public SimpleStringProperty fkProperty() {
        return fk;
    }

    public void setFk(String fk) {
        this.fk.set(fk);
    }

    public String getPk() {
        return pk.get();
    }

    public SimpleStringProperty pkProperty() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk.set(pk);
    }

    public boolean isRequired() {
        return required.get();
    }

    public SimpleBooleanProperty requiredProperty() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required.set(required);
    }

    public String getDefaultVal() {
        return defaultVal.get();
    }

    public SimpleStringProperty defaultValProperty() {
        return defaultVal;
    }

    public void setDefaultVal(String defaultVal) {
        this.defaultVal.set(defaultVal);
    }
}
