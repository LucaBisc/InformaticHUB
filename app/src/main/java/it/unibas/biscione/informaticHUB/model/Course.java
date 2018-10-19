package it.unibas.biscione.informaticHUB.model;

import java.util.ArrayList;

public class Course {
    private int id;
    private String shortname;
    private String fullname;
    private String displayname;
    private int categoryid;
    private String categoryname;
    private int sortorder; //order index in category
    private String idnumber;
    private int newsitems;
    private int startdate;
    private int enddate;
    private int visible;
    private String summary;
    private int summaryformat;
    private String lang;
    private double progress;
    private ArrayList<Option> courseformatoptions;
    private ArrayList<Section> sections;

    public ArrayList<Section> getSections() {
        return sections;
    }

    public void setSections(ArrayList<Section> sections) {
        this.sections = sections;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public int getSortorder() {
        return sortorder;
    }

    public void setSortorder(int sortorder) {
        this.sortorder = sortorder;
    }


    public int getNewsitems() {
        return newsitems;
    }

    public void setNewsitems(int newsitems) {
        this.newsitems = newsitems;
    }

    public double getProgress() {
        return progress;
    }

    public int getEnddate() {
        return enddate;
    }

    public int getId() {
        return id;
    }

    public String getShortname() {
        return shortname;
    }

    public String getFullname() {
        return fullname;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public String getSummary() {
        return summary;
    }

    public int getSummaryformat() {
        return summaryformat;
    }

    public int getStartdate() {
        return startdate;
    }


    public int getVisible() {
        return visible;
    }

    public String getLang() {
        return lang;
    }

    public Section getSectionByID(int id){
        for (Section s : sections){
            if (s.getId() == id){
                return s;
            }
        }
        return null;
    }

    public String optionToString(){
        String result = "";
        for (Option o : courseformatoptions){
            result += "\n" + o.toString();
        }
        return result;
    }


    public Module getModuleByID(int id){
        for (Section s : sections){
            Module m = s.getModuleByID(id);
            if (m != null){
                return m;
            }
        }
        return null;
    }

    public ArrayList<Option> getCourseformatoptions() {
        return courseformatoptions;
    }
}
