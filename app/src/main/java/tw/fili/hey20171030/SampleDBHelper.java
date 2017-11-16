package tw.fili.hey20171030;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SampleDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "Sample.db";  //名稱自取
    public static final int DB_VERSION = 1;  //以後有變更資料表格式時,增加之
    private static final String SQL_CREATE =
            "CREATE TABLE sample (" +
                    " name TEXT," +
                    " price INTEGER," +
                    " detail TEXT" +
                    ");";
    private static final String SQL_SAMPLE_DATA =
            "INSERT INTO sample (name,price,detail) VALUES " +
                    "(\"鉛筆\",10,\"不知道\")," +
                    "(\"橡皮擦\",12,\"還是不知道\")," +
                    "(\"筆記本\",20,\"還是還是不知道\");";
    private static final String SQL_DELETE =
            "DROP TABLE IF EXISTS sample";

    public SampleDBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE);
        db.execSQL(SQL_SAMPLE_DATA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL(SQL_DELETE);
        db.execSQL(SQL_CREATE);
        db.execSQL(SQL_SAMPLE_DATA);
    }
}
