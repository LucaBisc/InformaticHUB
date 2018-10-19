package it.unibas.biscione.informaticHUB.view;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import it.unibas.biscione.informaticHUB.adapter.CategoryAdapter;
import it.unibas.biscione.informaticHUB.adapter.CourseAdapter;
import it.unibas.biscione.informaticHUB.model.Archive;
import it.unibas.biscione.informaticHUB.model.Category;
import it.unibas.biscione.informaticHUB.model.Course;
import it.unibas.biscione.informaticHUB.model.UserMoodle;
import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.Costants;
import it.unibas.biscione.informaticHUB.R;

public class CategoryCourseView extends Fragment {

    public static final String TAG = CategoryCourseView.class.getName();

    private TextView textTitle;
    private RecyclerView rvCategory;
    private RecyclerView rvCourse;


    public RecyclerView getRvCategory() {
        return rvCategory;
    }


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, TAG + "created.");
        View view = inflater.inflate(R.layout.view_cat_and_course,container,false);
        textTitle = view.findViewById(R.id.txtTitle);
        rvCategory = view.findViewById(R.id.rvCategory);
        rvCourse = view.findViewById(R.id.rvCourse);
        LinearLayoutManager llmCategory = new LinearLayoutManager(Application.getInstance().getCurrentActivity());
        rvCategory.setLayoutManager(llmCategory);
        LinearLayoutManager llmCourse = new LinearLayoutManager(Application.getInstance().getCurrentActivity());
        rvCourse.setLayoutManager(llmCourse);
        this.setView();
        return view;
    }

    public void refreshView(int id) {
        Archive archive = (Archive) Application.getInstance().getModel().getBean(Costants.ARCHIVE);
        if (id == 9999){ //id My Courses
            ArrayList<Category> list = new ArrayList<>();
            rvCategory.setAdapter(new CategoryAdapter(Application.getInstance().getCurrentActivity(), list));
            textTitle.setText(getString(R.string.my_courses));
            textTitle.setVisibility(View.VISIBLE);
            UserMoodle userMoodle = (UserMoodle) Application.getInstance().getModel().getBean(Costants.USER);
            rvCourse.setAdapter(new CourseAdapter(Application.getInstance().getCurrentActivity(), userMoodle.getUserCoursesList()));
        } else {
            Category c = archive.getCategoryById(id);
            textTitle.setText(c.getName());
            ArrayList<Category> catList = archive.getChildCategories(id);
            ArrayList<Course> courseList = archive.getCourseByCategoryID(id);
            rvCategory.setAdapter(new CategoryAdapter(Application.getInstance().getCurrentActivity(), catList));
            rvCourse.setAdapter(new CourseAdapter(Application.getInstance().getCurrentActivity(), courseList));
            if (catList.size() == 0){
                rvCategory.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void setView() {
        Archive archive = (Archive) Application.getInstance().getModel().getBean(Costants.ARCHIVE);
        UserMoodle userMoodle = (UserMoodle) Application.getInstance().getModel().getBean(Costants.USER);
        textTitle.setText(userMoodle.getSiteInfo().getSiteName());
        ArrayList<Category> catList = archive.getCategoriesByDepth(1);
        Category c = new Category();
        c.setName(getString(R.string.my_courses));
        c.setId(9999);
        catList.add(c);
        ArrayList<Course> courseList = archive.getCourseByCategoryID(1);
        Log.d(TAG, " " + courseList.size());
        rvCategory.setAdapter(new CategoryAdapter(Application.getInstance().getCurrentActivity(), catList));
        rvCourse.setAdapter(new CourseAdapter(Application.getInstance().getCurrentActivity(), courseList));
    }



}
