package manger.student.studensystem.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import manger.student.studensystem.R;
import manger.student.studensystem.utils.MydatabaseHelper;



public class Add_student_info extends AppCompatActivity {

    private EditText name;
    private EditText sex;
    private EditText id;
    private EditText phone;
    private EditText password;
    private EditText math;
    private EditText chinese;
    private EditText english;
    private MydatabaseHelper dbHelper;
    private String oldID;//用于防治修改信息时将ID也修改了，而原始的有该ID的学生信息还保存在数据库中
    private Intent oldData;//从修改学生数据界面跳转过来的旧数据

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.add_student_layout);
        initViews();
        oldData = getIntent();
        if (oldData.getStringExtra("haveData").equals("true")){
            initInfo();
        }

    }

    //获取View
    public    void initViews(){
        name = (EditText) findViewById(R.id.add_name);
        sex = (EditText) findViewById(R.id.add_sex);
        id = (EditText) findViewById(R.id.add_id);
        phone = (EditText) findViewById(R.id.add_phone);
        password = (EditText) findViewById(R.id.add_password);
        math = (EditText) findViewById(R.id.add_math);
        chinese = (EditText) findViewById(R.id.add_chinese);
        english = (EditText) findViewById(R.id.add_english);
        dbHelper = MydatabaseHelper.getInstance(this);

    }

    //按下确定后,修改好的数据插入数据库
    public void query(View v){
         //sex不能为空否则程序崩溃，因为在StudentAdapter中有一个ImageView要设置图片
        //要求id,name,sex都不能为空
        String id_ = id.getText().toString().trim();
        String name_ = name.getText().toString().trim();
        String sex_ = sex.getText().toString().trim();
        String password_ = password.getText().toString().trim();
        String phone_ = phone.getText().toString().trim();
        String mathScore = math.getText().toString().trim();
        String chineseScore = chinese.getText().toString().trim();
        String englishScore = english.getText().toString().trim();

       if(!TextUtils.isEmpty(id_)&&!TextUtils.isEmpty(name_)
               &&!TextUtils.isEmpty(sex_)&&!TextUtils.isEmpty(password_)){

           if (sex_.matches("[男|女]")){

               SQLiteDatabase db=dbHelper.getReadableDatabase();
               db.beginTransaction();//开启事务
                db.execSQL("delete from student where id=?",new String[]{oldID});
                Cursor cursor=db.rawQuery("select * from student where id=?",new String[]{id_});
                if (cursor.moveToNext()){
                    Toast.makeText(this,"该学号已有学生注册,请重新输入!",Toast.LENGTH_LONG).show();
                }else {
                    db.execSQL("insert into student(id,name,sex,phone,password,mathScore,chineseScore,englishScore)" +
                            " values (?,?,?,?,?,?,?,?)",new String[]{id_, name_, sex_,phone_,
                            password_,mathScore, chineseScore, englishScore,});
                    db.setTransactionSuccessful();//事务成功
                    db.endTransaction();//结束事务
                    Intent intent=new Intent(this,Manager_Activity.class);
                    startActivity(intent);
                }
            }else {
                Toast.makeText(this,"请输入正确的性别!",Toast.LENGTH_LONG).show();

            }
       }else {
           Toast.makeText(this,"姓名,性别,学号,密码不能为空!",Toast.LENGTH_LONG).show();
       }
    }
    //按下取消按钮后跳关闭此页面
    public void cancle(View v){
        finish();

    }

    //恢复旧数据
    public void initInfo(){
         String oldName =oldData.getStringExtra("name");
         name.setText(oldName);
        String oldSex = oldData.getStringExtra("sex");
        sex.setText(oldSex);
        String oldId = oldData.getStringExtra("id");
        oldID = oldId;
        id.setText(oldId);
        String oldPhone = oldData.getStringExtra("phone");
        phone.setText(oldPhone);
        String oldPassword = oldData.getStringExtra("password");
        password.setText(oldPassword);
        int mathScore = oldData.getIntExtra("mathScore", 0);
        math.setText(String.valueOf(mathScore));
        int chineseScore = oldData.getIntExtra("chineseScore", 0);
        chinese.setText(String.valueOf(chineseScore));
        int englishScore = oldData.getIntExtra("englishScore", 0);
        english.setText(String.valueOf(englishScore));
    }
}
