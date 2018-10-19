package it.unibas.biscione.informaticHUB.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import it.unibas.biscione.informaticHUB.activity.CategoryCourseActivity;
import it.unibas.biscione.informaticHUB.control.AsyncTaskFetchContent;
import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.Costants;
import it.unibas.biscione.informaticHUB.R;
import it.unibas.biscione.informaticHUB.model.Course;
import it.unibas.biscione.informaticHUB.model.UserMoodle;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<Course> courseList;

    public CourseAdapter (Context context, ArrayList<Course> courses){
        this.courseList = courses;
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_course, parent, false);
        CourseViewHolder courseViewHolder = new CourseViewHolder(view);
        return courseViewHolder;
    }

    @Override
    public void onBindViewHolder(CourseAdapter.CourseViewHolder holder, final int position) {
        holder.name.setText(courseList.get(position).getFullname());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Course c = courseList.get(position);
                int id = c.getId();
                UserMoodle userMoodle = (UserMoodle) Application.getInstance().getModel().getBean(Costants.USER);
                if (userMoodle.isUserEnrolledInCourse(c.getId()) || c.getCourseformatoptions() != null) {
                    AsyncTaskFetchContent asyncTaskFetchContent = new AsyncTaskFetchContent();
                    asyncTaskFetchContent.execute(id);
                } else {
                    CategoryCourseActivity categoryCourseActivity = (CategoryCourseActivity) Application.getInstance().getCurrentActivity();
                    categoryCourseActivity.showDialog(categoryCourseActivity.getString(R.string.enrollment_required), categoryCourseActivity.getString(R.string.enrollment_question), id);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder{

        TextView name;


        public CourseViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txtCourse);
        }
    }
}
