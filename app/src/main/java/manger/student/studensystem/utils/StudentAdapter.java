package manger.student.studensystem.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import manger.student.studensystem.R;


public class StudentAdapter extends ArrayAdapter {
    private int resourseid;
    public StudentAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.resourseid=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Student_info student_info= (Student_info) getItem(position);
        View view;
        ViewHoldwe viewHoldwe;
        if (convertView==null){
            view=LayoutInflater.from(getContext()).inflate(resourseid,null);
            viewHoldwe=new ViewHoldwe();
            viewHoldwe.student_id=view.findViewById(R.id.student_id);
            viewHoldwe.student_name=view.findViewById(R.id.student_name);
            viewHoldwe.student_image=view.findViewById(R.id.student_image);
            viewHoldwe.student_image.setLayoutParams(new LinearLayout.LayoutParams(100,100));
            view.setTag(viewHoldwe);
        }else {
            view=convertView;
            viewHoldwe= (ViewHoldwe) view.getTag();
        }
        viewHoldwe.student_name.setText(student_info.getName());
        viewHoldwe.student_id.setText(student_info.getId());
        String sex=student_info.getSex();
        if (sex.equals("男")){
            viewHoldwe.student_image.setImageResource(R.drawable.man);

        }else if (sex.equals("女")){
            viewHoldwe.student_image.setImageResource(R.drawable.woman);

        }

        return view;
    }
    class ViewHoldwe{
        ImageView student_image;
        TextView student_name;
        TextView student_id;
    }
}

