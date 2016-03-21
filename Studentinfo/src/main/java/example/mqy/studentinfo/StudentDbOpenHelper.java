package example.mqy.studentinfo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MQY on 2016/3/21.
 */
public class StudentDbOpenHelper extends SQLiteOpenHelper {
    public static final String CREATE_STUDENTS = "create table students("
            +"id integer primary key autoincrement, "
            +"name text, "
            +"sex text )";
    public StudentDbOpenHelper(Context context,String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STUDENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
