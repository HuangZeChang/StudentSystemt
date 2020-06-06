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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import manger.student.studensystem.R;
import manger.student.studensystem.utils.MydatabaseHelper;

public class Manager_login_Activity extends AppCompatActivity{

    private EditText manager_name;//管理员用户名
    private EditText manager_pass;//管理员密码
    private Button btn_login;//登录按钮
    private TextView forgetNum;//忘记密码
    private TextView registe;//注册
    private MydatabaseHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.manager_login_layout);
        initViews();
        dbHelper=MydatabaseHelper.getInstance(this);

    }

    public  void initViews(){
        manager_name = findViewById(R.id.manager_login_name_input);
        manager_pass = findViewById(R.id.manager_login_pass_input);
        btn_login = findViewById(R.id.manager_login);
        forgetNum = findViewById(R.id.manager_login_activity_forgetNum);
        registe = findViewById(R.id.manager_login_activity_register);
        Intent intent=getIntent();
        String username=intent.getStringExtra("username");
        manager_name.setText(username);

    }

    //跳转到登录后的管理界面
    public void login(View v){
        String name_info=manager_name.getText().toString().trim();
        String pass_info=manager_pass.getText().toString().trim();
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        //通过输入的用户名查询数据库中的密码
        Cursor cursor=db.rawQuery("select password from manager where name=?",new String[]{name_info});
        String pi=null;
        if (cursor.moveToNext()){
            //获取密码
            pi=cursor.getString(cursor.getColumnIndex("password"));
        }
        //密码正确跳转到管理员界面
        if (pass_info.equals(pi)){
            Intent intent=new Intent(this,Manager_Activity.class);
            startActivity(intent);
            cursor.close();
        }else {
            Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_SHORT).show();

        }
    }

    //自定义AlertDialog用于注册
    public void register(View v){
        Intent intent=new Intent(this,Manager_register_activity.class);
        startActivity(intent);


    }
    //忘记密码监听
    public void forget(View v){
        Toast.makeText(this,"此功能暂不开放",Toast.LENGTH_LONG).show();
    }

}
