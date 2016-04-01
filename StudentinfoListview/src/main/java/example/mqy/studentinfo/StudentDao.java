package example.mqy.studentinfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import example.mqy.studentinfo.domain.Student;

/**
 * Created by MQY on 2016/3/21.
 */
public class StudentDao {
    StudentDbOpenHelper dbHelper;
    public StudentDao(Context context){
        dbHelper = new StudentDbOpenHelper(context,"information.db",null,1);
    }
    public void add(Student st){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",st.getName());
        values.put("sex",st.getSex());
        db.insert("students",null,values);
        values.clear();
    }
    public List<Student> getAll(){
        List<Student> list = new ArrayList<Student>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("students", null, null, null, null, null, null);
        Student st;
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String sex = cursor.getString(cursor.getColumnIndex("sex"));
            st = new Student();
            st.setId(String.valueOf(id));
            st.setName(name);
            st.setSex(sex);
            list.add(st);
        }
            return list;


    }
}
