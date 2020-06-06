package manger.student.studensystem.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import manger.student.studensystem.R;
import manger.student.studensystem.utils.MydatabaseHelper;



public class Student_login_Activity extends AppCompatActivity{

    private EditText login_ID;
    private EditText login_pass;
    private MydatabaseHelper dbHelper;
    private Button login;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.student_login_layout);
        initViews();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String student_ID=login_ID.getText().toString().trim();
                String student_pass=login_pass.getText().toString().trim();
                if (!TextUtils.isEmpty(student_ID)&&!TextUtils.isEmpty(student_pass)){
                    SQLiteDatabase db=dbHelper.getReadableDatabase();
                    Cursor cursor=db.rawQuery("select password from student where id=?",new String[]{student_ID});
                      if (cursor.moveToNext()){
                          String password=cursor.getString(cursor.getColumnIndex("password"));
                          if (password.equals(student_pass)){
                              //跳转到学生界面并将学生的ID传过去
                              Intent intent=new Intent(Student_login_Activity.this,Student_activity.class);
                               intent.putExtra("ID",student_ID);
                              startActivity(intent);
                              Toast.makeText(Student_login_Activity.this,"登录成功",Toast.LENGTH_LONG).show();
                          }else {
                              Toast.makeText(Student_login_Activity.this,"密码错误请重新输入!",Toast.LENGTH_LONG).show();
                          }
                      }else {
                          Toast.makeText(Student_login_Activity.this,"该学号尚未注册,请联系教务处!",Toast.LENGTH_LONG).show();
                      }
                }else {
                    Toast.makeText(Student_login_Activity.this,"学号, 密码不能为空!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public  void initViews(){
        login_ID = findViewById(R.id.student_login_ID_input);
        login_pass = findViewById(R.id.student_login_pass_input);
        login = findViewById(R.id.student_login);
        dbHelper = MydatabaseHelper.getInstance(this);
    }
}
