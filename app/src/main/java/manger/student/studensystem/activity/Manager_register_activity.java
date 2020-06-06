package manger.student.studensystem.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import manger.student.studensystem.R;
import manger.student.studensystem.utils.MydatabaseHelper;


public class Manager_register_activity extends AppCompatActivity{

    private EditText code;
    private EditText name;
    private EditText firstPassword;
    private EditText secondPassword;
    private MydatabaseHelper dbHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.manager_register_layout);
        initViews();

    }
    public void initViews(){
        code = findViewById(R.id.manafer_register_info);
        name = findViewById(R.id.manager_register_name);
        firstPassword = findViewById(R.id.manager_register_first_password);
        secondPassword = findViewById(R.id.manager_register_second_password);
    }
    public void register(View v){
        String codeinfo=code.getText().toString().trim();
        //注册码要为10086
        if (codeinfo.equals("10086")){
            String nameinfo=name.getText().toString().trim();
            String firstpassinfo=firstPassword.getText().toString().trim();
            String secondpassinfo=secondPassword.getText().toString().trim();
            //拿到一个数据库对象
            dbHelper=MydatabaseHelper.getInstance(this);
            SQLiteDatabase db=dbHelper.getReadableDatabase();
            //检测密码是否是六个纯数字
            if (firstpassinfo.matches("[0-9]{6}")){
                //判断两次密码是否相同
                if (firstpassinfo.equals(secondpassinfo)){
                    Cursor cursor=db.rawQuery("select name from manager where name=? ",
                            new String[]{nameinfo});
                    //判断用户名是否存在
                    if(cursor.moveToNext()){
                        Toast.makeText(this,"用户名已存在",Toast.LENGTH_LONG).show();
                    }else {
                        //将用户名,密码存入数据库
                        db.execSQL("insert into manager (name,password) values(?,?)",
                                new String[]{nameinfo,firstpassinfo});
                        Intent intent=new Intent(this,Manager_login_Activity.class);
                        intent.putExtra("username",nameinfo);
                        startActivity(intent);
                        Toast.makeText(this,"注册成功!",Toast.LENGTH_LONG).show();
                    }
                }else {

                    Toast.makeText(this,"两次密码不相同",Toast.LENGTH_LONG).show();
                }
            }else {
                Toast.makeText(this,"密码为6个纯数字",Toast.LENGTH_LONG).show();

            }
        }else {
            Toast.makeText(this,"你输入的注册码错误，请重新输入！",Toast.LENGTH_LONG).show();
        }

    }


}
