package manger.student.studensystem.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import manger.student.studensystem.R;
import manger.student.studensystem.utils.MydatabaseHelper;
import manger.student.studensystem.utils.StudentAdapter;
import manger.student.studensystem.utils.Student_info;


public class Select_student_info extends AppCompatActivity{

    private MydatabaseHelper dbHelper;
    private List<Student_info> student_list = new ArrayList<Student_info>();
    private ListView lv_student;
    private StudentAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.student_info_layout);
        dbHelper = MydatabaseHelper.getInstance(this);
        lv_student = findViewById(R.id.lv_student);
        initStudent();
        adapter=new StudentAdapter(Select_student_info.this,R.layout.student_info_item,student_list);
        lv_student.setAdapter(adapter);
        //ListView点击事件
        lv_student.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                //捕获学生的实例
                final Student_info student_info=student_list.get(position);
                AlertDialog.Builder builder=new AlertDialog.Builder(Select_student_info.this);
                LayoutInflater factory=LayoutInflater.from(Select_student_info.this);
                //加载AlertDialog自定义布局
                View textEntryView = factory.inflate(R.layout.student_lv_dialog,null);
                builder.setView(textEntryView);
                builder.setTitle("请选择需要的操作");

                //查看学生详细信息按钮
                Button select_button=textEntryView.findViewById(R.id.student_info_select);
                select_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(Select_student_info.this,Student_detailed_info.class);
                        intent.putExtra("name", student_info.getName());
                        intent.putExtra("sex", student_info.getSex());
                        intent.putExtra("id", student_info.getId());
                        intent.putExtra("phone", student_info.getPhone());
                        intent.putExtra("mathScore", student_info.getMathScore());
                        intent.putExtra("chineseScore", student_info.getChineseScore());
                        intent.putExtra("englishScore", student_info.getEnglishScore());
                        intent.putExtra("ranking",student_info.getOrder());
                        startActivity(intent);
                        //再次弹出一个alertDialog，用于显示详细学生信息


                    }
                });


                //删除学生信息按钮
                Button delete_button=textEntryView.findViewById(R.id.student_info_delete);
                delete_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder delete_builder=new AlertDialog.Builder(Select_student_info.this);
                       delete_builder.setTitle("警告! !!!") ;
                       delete_builder.setMessage("您将删除该学生的信息,此操作不可以逆,请谨慎操作!");
                       delete_builder.setNegativeButton("取消", null);
                       delete_builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               SQLiteDatabase db=dbHelper.getReadableDatabase();
                               db.execSQL("delete from student where id=?",new String[]{student_info.getId()});
                               student_list.remove(position);//在List中移除学生实例
                               adapter.notifyDataSetChanged();//刷新listview列表
                           }

                       });
                       delete_builder.create().show();
                    }
                });
                //修改学生信息,通过intent传递旧学生信息
                      Button update_button =textEntryView.findViewById(R.id.student_info_update);
                      update_button.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {
                              Intent intent=new Intent(Select_student_info.this,Add_student_info.class);
                              intent.putExtra("haveData","true");
                              intent.putExtra("name", student_info.getName());
                              intent.putExtra("sex", student_info.getSex());
                              intent.putExtra("id", student_info.getId());
                              intent.putExtra("phone", student_info.getPhone());
                              intent.putExtra("password", student_info.getPassword());
                              intent.putExtra("mathScore", student_info.getMathScore());
                              intent.putExtra("chineseScore", student_info.getChineseScore());
                              intent.putExtra("englishScore", student_info.getEnglishScore());
                              startActivity(intent);

                          }
                      });
                      builder.create().show();
            }
        });

    }


    //从数据库中检索所有学生信息并初始化学生信息
    public void initStudent(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from student order by id",null);
        while (cursor.moveToNext()){
            String id=cursor.getString(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String sex = cursor.getString(cursor.getColumnIndex("sex"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            int mathScore = cursor.getInt(cursor.getColumnIndex("mathScore"));
            int chineseScore = cursor.getInt(cursor.getColumnIndex("chineseScore"));
            int englishScore = cursor.getInt(cursor.getColumnIndex("englishScore"));
            int order=cursor.getInt(cursor.getColumnIndex("ranking"));
        student_list.add(new Student_info(chineseScore,englishScore,mathScore,id,name,sex,phone,password,order));
        }
        cursor.close();
    }
}
