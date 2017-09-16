package data.files;

import data.structures.generics.SimpleList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import configuration.Setting;

public class JsonManager {
    private String DB;
    private String tableName;
    private FileManager fileManager;

    /**
     *
     * @param DB: ruta con la base de datos
     * @param tableName: nombre de la tabla
     */
    public JsonManager(String DB, String tableName){
        this.DB = Setting.getMainFolderPath() + "\\" + DB;
        this.tableName = tableName;
        this.fileManager = new FileManager(tableName, DB);
    }

    private void createJsonFile(){
        String filePath = DB + "\\" + tableName;
        JSONObject mainObject = new JSONObject();
        JSONArray rowsArray = new JSONArray();
        mainObject.put(tableName, rowsArray);
        fileManager.writeToFile(tableName, DB, mainObject.toJSONString());
    }

    public void createJSONFile(){
        createJsonFile();
    }

    /**
     *
     * @param key: JSON key name
     * @param type: data type
     * @param fk: includes fk
     * @param pk: includes pk
     * @param required: is requiered
     * @param defaultValue: value if not required
     * @see #addJsonObject(String, String, String, String, String, String)
     */
    private void addJsonObjectAux(String key, String type, String fk, String pk, String required, String defaultValue) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(this.DB + "\\" + this.tableName + ".json"));

        JSONObject jsonObject = (JSONObject) obj;
        // Now we try to take the data from "presentationSlides" array
        JSONArray rows = (JSONArray) jsonObject.get(this.tableName);

        JSONObject fieldName = new JSONObject();
        fieldName.put("Name", key);
        JSONObject fieldType = new JSONObject();
        fieldType.put("Type", type);
        JSONObject fieldFK = new JSONObject();
        fieldFK.put("Fk", fk);
        JSONObject fieldPK = new JSONObject();
        fieldPK.put("Pk", pk);
        JSONObject fieldIsRequired = new JSONObject();
        fieldIsRequired.put("Required", required);
        JSONObject fieldDefault = new JSONObject();
        fieldDefault.put("Default", defaultValue);

        JSONArray fieldsArray = new JSONArray();
        fieldsArray.add(fieldName);
        fieldsArray.add(fieldType);
        fieldsArray.add(fieldFK);
        fieldsArray.add(fieldPK);
        fieldsArray.add(fieldIsRequired);
        fieldsArray.add(fieldDefault);

        JSONObject row = new JSONObject();
        row.put("row", fieldsArray);
        rows.add(row);

        FileManager fm = new FileManager();
        JSONObject newJsonObject = new JSONObject();
        newJsonObject.put(this.tableName, rows);
        String path = this.DB + "\\" + this.tableName + ".json";
        fm.writeToExistentFile(path, newJsonObject.toJSONString());
    }

    public void addJsonObject(String key, String type, String FK, String PK, String required, String defaultValue) throws IOException, ParseException {
        addJsonObjectAux(key, type, FK, PK, required, defaultValue);
    }

    private void ListToJson(SimpleList<RowMaker> list){

    }

    private SimpleList<RowMaker> JsonToListAux() throws ParseException, IOException {
        SimpleList<RowMaker> list = new SimpleList<>();

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(this.DB + "\\" + this.tableName + ".json"));

        JSONObject jsonObject = (JSONObject) obj;
        JSONArray rows = (JSONArray) jsonObject.get(this.tableName);
        // Now we try to take the data from "rows" array
        Iterator iterator = rows.iterator();

        iterator.forEachRemaining(e -> {
            JSONObject row = (JSONObject) iterator.next();
            RowMaker rowMaker = fillRowMaker((JSONArray) row.get("row"));
            list.addNode(rowMaker);
        });

        return list;
    }

    public SimpleList<RowMaker> JsonToList(){
        try {
            return JsonToListAux();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private RowMaker fillRowMaker(JSONArray row){
        JSONObject name = (JSONObject) row.get(0);
        JSONObject type = (JSONObject) row.get(1);
        JSONObject fk = (JSONObject) row.get(2);
        JSONObject pk = (JSONObject) row.get(3);
        JSONObject required = (JSONObject) row.get(4);
        JSONObject defaultValue = (JSONObject) row.get(5);

        RowMaker rowMaker = new RowMaker();
        rowMaker.setColumnName(name.get("Name").toString());
        rowMaker.setColumnType(type.get("Type").toString());
        rowMaker.setColumnFK(fk.get("Fk").toString());
        rowMaker.setColumnPK(pk.get("Pk").toString());
        if(required.get("Required") == "true")
            rowMaker.setColumnRequired(true);
        else
            rowMaker.setColumnRequired(false);
        rowMaker.setColumnDefault(defaultValue.get("Default").toString());
        return rowMaker;
    }

}
