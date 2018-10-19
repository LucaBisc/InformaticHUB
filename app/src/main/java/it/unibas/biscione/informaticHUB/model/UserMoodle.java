package it.unibas.biscione.informaticHUB.model;

import java.util.ArrayList;

public class UserMoodle {

    private String username;
    private String pw;
    private Token token;
    private MoodleSiteInfo siteInfo;
    private ArrayList<Course> userCourses;

    public void setToken(Token token) {
        this.token = token;
    }

    public Token getToken() {

        return token;
    }

    public void setUserCoursesList(ArrayList<Course> listaCorsiUtente) {
        this.userCourses = listaCorsiUtente;
    }

    public void addCourse(Course c){
        userCourses.add(c);
    }

    public ArrayList<Course> getUserCoursesList() {
        return userCourses;
    }

    public void setSiteInfo(MoodleSiteInfo siteInfo) {
        this.siteInfo = siteInfo;
    }

    public MoodleSiteInfo getSiteInfo() {
        return siteInfo;
    }

    public String getUsername() {
        return username;
    }

    public String getPw() {
        return pw;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public boolean isUserEnrolledInCourse(int id){
        for (Course c : userCourses){
            if (c.getId() == id){
                return true;
            }
        }
        return false;
    }
}
