package it.unibas.biscione.informaticHUB.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Archive {

    @SerializedName("courses")
    private ArrayList<Course> courseList = new ArrayList<>();
    private ArrayList<Category> categoryList = new ArrayList<>();
    private ArrayList<Cohort> coorti = new ArrayList<>();

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }

    public ArrayList<Category> getCategoryList() {
        return categoryList;
    }

    public ArrayList<Cohort> getCohorts() {
        return coorti;
    }

    public void setCohorts(ArrayList<Cohort> cohorts) {
        this.coorti = cohorts;
    }

    public void setCategoryList(ArrayList<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public ArrayList<Category> getChildCategories(int id){
        ArrayList<Category> list = new ArrayList<>();
        for (Category c : categoryList){
            if (c.getParent() == id){
                list.add(c);
            }
        }
        return list;
    }



    public ArrayList<Category> getCategoriesByDepth(int depth){
        ArrayList<Category> list = new ArrayList<>();
        for (Category c : categoryList){
            if (c.getDepth() == depth) {
                list.add(c);
            }
        }
        return list;
    }

    public ArrayList<Course> getCourseByCategoryID(int id){
        ArrayList<Course> list = new ArrayList<>();
        for (Course c : courseList){
            if (c.getCategoryid() == id){
                list.add(c);
            }
        }
        return list;
    }

    public Category getCategoryById(int id){
        for (Category c : categoryList){
            if (c.getId() == id){
                return c;
            }
        }
        return null;
    }

    public Course getCourseById(int id){
        for (Course c : courseList){
            if (c.getId() == id){
                return  c;
            }
        }
        return null;
    }

    public Cohort getCohortByName (String name){
        for (Cohort c : coorti){
            if (c.getNome().trim().equalsIgnoreCase(name.trim())){
                return c;
            }
        }
        return null;
    }
}
