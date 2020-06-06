package manger.student.studensystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import manger.student.studensystem.R;
import manger.student.studensystem.utils.MydatabaseHelper;


public class Student_detailed_info extends AppCompatActivity {

    private TextView name;
    private TextView sex;
    private TextView id;
    private TextView phone;
    private TextView math;
    private TextView chinese;
    private TextView english;
    private TextView order;
    private Intent oldData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.student_detailed_info_layout);
        initViews();
        initinfo();
    }

    //取得控件
    public void initViews(){
        name =findViewById(R.id.add_name);
        sex  = findViewById(R.id.add_sex);
        id   = findViewById(R.id.add_id);
        phone = findViewById(R.id.add_phone);
        math  = findViewById(R.id.add_math);
        chinese = findViewById(R.id.add_chinese);
        english = findViewById(R.id.add_english);
        order = findViewById(R.id.add_order);
        oldData = getIntent();



    }
    //显示学生详细信息
    public void initinfo(){
        String oldName =oldData.getStringExtra("name");
        name.setText(oldName);
        String oldSex = oldData.getStringExtra("sex");
        sex.setText(oldSex);
        String oldId = oldData.getStringExtra("id");
        id.setText(oldId);
        String oldPhone = oldData.getStringExtra("phone");
        phone.setText(oldPhone);
        int mathScore = oldData.getIntExtra("mathScore", 0);
        math.setText(String.valueOf(mathScore));
        int chineseScore = oldData.getIntExtra("chineseScore", 0);
        chinese.setText(String.valueOf(chineseScore));
        int englishScore = oldData.getIntExtra("englishScore", 0);
        english.setText(String.valueOf(englishScore));
        int ranking =oldData.getIntExtra("ranking",0);
        order.setText(String.valueOf(ranking));
    }

}
