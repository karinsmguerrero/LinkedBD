package GUI;

import data.files.DBList;
import data.files.TableList;

public class MainWindowController {

    private void createDB(String dbName){
        DBList dbList = new DBList();
        dbList.getDbList().addNodeToTheTail(new TableList("", dbName));
    }
}