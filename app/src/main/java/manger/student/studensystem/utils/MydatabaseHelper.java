package manger.student.studensystem.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MydatabaseHelper  extends SQLiteOpenHelper {
    private  static  MydatabaseHelper instance;
    //创建管理员表
    public static final String CREATE_MANAGER="create table manager(id integer primary key autoincrement,name text ,password text)";
    //创建学生表
    public static final String CREATE_STUDENT="create table student(id integer primary key autoincrement,name text ,sex text,phone text,password text,mathScore integer,chineseScore integer,englishScore integer)";

    private MydatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public  void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MANAGER);
        db.execSQL(CREATE_STUDENT);
        db.execSQL("alter table student add  column ranking integer");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public  static MydatabaseHelper getInstance(Context context){
        if (instance==null){
            instance=new MydatabaseHelper(context,"studen_tmanager.db",null,3);

        }
        return instance;
    }
}
