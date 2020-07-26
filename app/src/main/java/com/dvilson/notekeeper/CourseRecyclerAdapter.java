package com.dvilson.notekeeper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class CourseRecyclerAdapter extends  RecyclerView.Adapter<CourseRecyclerAdapter.ViewHolder>{

    private  final Context mContext;
    private final List<CourseInfo> mCourses;
    private final LayoutInflater mLayoutInflater;

    public CourseRecyclerAdapter(Context context, List<CourseInfo> courses) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mCourses = courses;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_course_list,parent,false);
        return new ViewHolder(itemView);
     }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CourseInfo course = mCourses.get(position);
        holder.tvCourse.setText(course.getTitle());
        holder.currentPosition = position;

    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvCourse;
        int currentPosition;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            tvCourse = (TextView) itemView.findViewById(R.id.tvCourse);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v,mCourses.get(currentPosition).getTitle(),Snackbar.LENGTH_LONG).show();
                }
            });

        }
    }
}
