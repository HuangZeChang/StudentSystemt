package manger.student.studensystem.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import manger.student.studensystem.R;
import manger.student.studensystem.utils.MydatabaseHelper;


public class Student_activity extends AppCompatActivity {

    private String student_id;
    private MydatabaseHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.student_activity_layout);
        Intent intent=getIntent();
        student_id = intent.getStringExtra("ID");
        dbHelper = MydatabaseHelper.getInstance(this);
    }

    //查询个人信息
    public void select(View v){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("个人信息");
        StringBuilder sb=new StringBuilder();
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from student where id=?",new String[]{student_id});
        while (cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            int mathScore = cursor.getInt(cursor.getColumnIndex("mathScore"));
            int chineseScore = cursor.getInt(cursor.getColumnIndex("chineseScore"));
            int englishScore = cursor.getInt(cursor.getColumnIndex("englishScore"));
            int ranking = cursor.getInt(cursor.getColumnIndex("ranking"));

            sb.append("姓名：" + name + "\n");
            sb.append("学号：" + id + "\n");
            sb.append("手机号：" + phone + "\n");
            sb.append("密码：" + password + "\n");
            sb.append("MySQL：" + mathScore + "\n");
            sb.append("web前端：" + chineseScore + "\n");
            sb.append("JAVA编程：" + englishScore + "\n");
            int sum = mathScore + chineseScore + englishScore;//总成绩
            sb.append("总成绩：" + sum + "\n");
            sb.append("名次：" + ranking + "\n");
        }
        cursor.close();;
        builder.setMessage(sb.toString());
        builder.create().show();
    }

    //更改密码
    public void update(View v){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        LayoutInflater factory=LayoutInflater.from(this);
        final View textEntryView=factory.inflate(R.layout.student_change_pass_layout,null);
        builder.setView(textEntryView);
        builder.setTitle("修改密码");
        builder.setNegativeButton("取消",null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            EditText first=textEntryView.findViewById(R.id.student_change_password);
            EditText second=textEntryView.findViewById(R.id.student_change_password_second_password);

            @Override
            public void onClick(DialogInterface dialog, int which) {
               String first_pass=first.getText().toString().trim();
                String second_pass=second.getText().toString().trim();
                if (!TextUtils.isEmpty(first_pass)&&!TextUtils.isEmpty(second_pass)){

                    if (first_pass.matches("[0-9]{6}")&&second_pass.matches("[0-9]{6}")){

                        if (second_pass.equals(first_pass)){
                            SQLiteDatabase db=dbHelper.getReadableDatabase();
                            db.execSQL("update student set password=? where id=?",new String[]{second_pass,student_id});
                            Toast.makeText(Student_activity.this,"密码修改成功!",Toast.LENGTH_LONG).show();
                            Intent intent =new Intent(Student_activity.this,Student_login_Activity.class);
                                startActivity(intent);
                            Toast.makeText(Student_activity.this,"请重新登录!",Toast.LENGTH_LONG).show();

                        }else {
                            Toast.makeText(Student_activity.this,"两次密码不一致!",Toast.LENGTH_LONG).show();

                        }
                    }else {
                        Toast.makeText(Student_activity.this,"密码必须为6位纯数字!",Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(Student_activity.this,"密码不能为空!",Toast.LENGTH_LONG).show();
                }

            }
        });
        builder.create().show();

    }

    //安全退出
    public void exit(View v){
            finish();
    }


}
