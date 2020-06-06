package manger.student.studensystem.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import manger.student.studensystem.R;
import manger.student.studensystem.utils.MydatabaseHelper;

public class MainActivity extends AppCompatActivity {
    private long exit_time;
    private MydatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏,设置全屏模式
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        dbHelper=MydatabaseHelper.getInstance(this);
        dbHelper.getReadableDatabase();
    }
    public  void manager(View v){
        Intent intent = new Intent(this,Manager_login_Activity.class);
        startActivity(intent);
    }
    //跳转到学生登录界面
    public  void student(View v){
        Intent intent=new Intent(this,Student_login_Activity.class);
        startActivity(intent);
    }

    @Override
    //按两次back键退出
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //获取按键并比较两次按back的时间是否大于2s,大于不退出,否则不退出
        if(keyCode==KeyEvent.KEYCODE_BACK&&event.getAction()==KeyEvent.ACTION_DOWN){
            if(System.currentTimeMillis()-exit_time>2000){
                Toast.makeText(this,"在按一次退出",Toast.LENGTH_LONG).show();
                exit_time=System.currentTimeMillis();
            }else {
                finish();
                System.exit(0);
            }
            return  true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
