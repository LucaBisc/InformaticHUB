package it.unibas.biscione.informaticHUB.model;

import com.google.gson.annotations.SerializedName;

public class Forum {

    private int id;
    private String name;
    private int cmid;
    private Discussion discussion;
    private String intro;
    private int introFormat;
    @SerializedName("status")
    private boolean addNews;


    public int getCmid() {
        return cmid;
    }

    public void setCmid(int cmid) {
        this.cmid = cmid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Discussion getDiscussion() {
        return discussion;
    }

    public void setDiscussion(Discussion discussion) {
        this.discussion = discussion;
    }

    public String getIntro() {
        return intro;
    }

    public int getIntroFormat() {
        return introFormat;
    }

    public void setAddNews(boolean addNews) {
        this.addNews = addNews;
    }

    public boolean canUserAddNews(){
        return addNews;
    }
}
