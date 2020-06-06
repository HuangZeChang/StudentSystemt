package manger.student.studensystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import manger.student.studensystem.R;

public class Manager_Activity extends AppCompatActivity implements View.OnClickListener {
    private Button select;//查询学生信息按钮
    private Button add;//添加学生信息按钮
    private Button order;//查看总成绩排名按钮
    private TextView forceOffline;//强制下线

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.manager_layout);
        initViews();
        initEvent();
    }

    //获取控件
    public void initViews(){
        select = (Button) findViewById(R.id.manager_activity_select);
        add = (Button) findViewById(R.id.manager_activity_add);
        order = (Button) findViewById(R.id.manager_activity_order);
        forceOffline = (TextView) findViewById(R.id.manager_activity_forceOffline);
    }

    //设置监听
    public void initEvent(){
        select.setOnClickListener(this);
        add.setOnClickListener(this);
        order.setOnClickListener(this);
        forceOffline.setOnClickListener(this);
    }


    //设置具体监听点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.manager_activity_select:
               Intent intent1=new Intent(this,Select_student_info.class);
               startActivity(intent1);
                break;
            case  R.id.manager_activity_add:
                Intent intent2 =new Intent(this,Add_student_info.class);
                intent2.putExtra("haveData","false");
                startActivity(intent2);
                break;
            case R.id.manager_activity_order:
                Intent intent3 =new Intent(this,Student_total_score.class);
                startActivity(intent3);
                break;
            case R.id.manager_activity_forceOffline:
                Toast.makeText(this,"此功能暂不开放",Toast.LENGTH_LONG).show();
                break;
        }
    }
}
