package manger.student.studensystem.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import manger.student.studensystem.R;


public class Student_Score_Adapter extends ArrayAdapter<Student_info> {
    private  int resourceId;
    public Student_Score_Adapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.resourceId=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Student_info student_info= getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder=new ViewHolder();
            viewHolder.student_order=view.findViewById(R.id.student_score_order);
            viewHolder.student_name=view.findViewById(R.id.student_score_name);
            viewHolder.student_id=view.findViewById(R.id.student_score_id);
            viewHolder.student_score=view.findViewById(R.id.student_score_total);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        int order=student_info.getOrder();
        viewHolder.student_order.setText(String.valueOf(order));
        viewHolder.student_name.setText(student_info.getName());
        viewHolder.student_id.setText(student_info.getId());
        int sum=student_info.getChineseScore()+student_info.getMathScore()+student_info.getEnglishScore();
        viewHolder.student_score.setText(String.valueOf(sum));
        return view;

    }
    class ViewHolder{
        TextView student_order;
        TextView student_name;
        TextView student_id;
        TextView student_score;
    }


}
