package manger.student.studensystem.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import manger.student.studensystem.R;
import manger.student.studensystem.utils.MydatabaseHelper;
import manger.student.studensystem.utils.Student_Score_Adapter;
import manger.student.studensystem.utils.Student_info;

public class Student_total_score extends AppCompatActivity{
    private ListView total_score;
    private List<Student_info> student_score_list = new ArrayList<Student_info>();
    private MydatabaseHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.student_total_layout);
        dbHelper=MydatabaseHelper.getInstance(this);
        ListView lv_score = findViewById(R.id.lv_score);
        initInfo();
        Student_Score_Adapter adapter=new Student_Score_Adapter(this,R.layout.student_score_irem,student_score_list);
        lv_score.setAdapter(adapter);

    }
    //初始化数据
    public void initInfo(){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from student order by mathScore+chineseScore+englishScore desc",null);
        int i=0;
        while (cursor.moveToNext()){
            i++;
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String sex = cursor.getString(cursor.getColumnIndex("sex"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            int mathScore = cursor.getInt(cursor.getColumnIndex("mathScore"));
            int chineseScore = cursor.getInt(cursor.getColumnIndex("chineseScore"));
            int englishScore = cursor.getInt(cursor.getColumnIndex("englishScore"));
            //将排名插入数据库中
            db.execSQL("update student set ranking=? where id=?",new String [] {String.valueOf(i),id});
            student_score_list.add(new Student_info(chineseScore,englishScore,mathScore,id,name,sex,phone,password,i));
        }
        cursor.close();
    }

}
