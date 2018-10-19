package it.unibas.biscione.informaticHUB.model;

import java.util.ArrayList;

public class Module {

    private int id;
    private String url;
    private String name;
    private int instance;
    private String description;
    private ArrayList<Content> contents;
    private String modicon; //url icon
    private String modname; //type
    private ArrayList<Forum> forums;


    public ArrayList<Forum> getForums() {
        return forums;
    }

    public void setForums(ArrayList<Forum> forums) {
        this.forums = forums;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public int getInstance() {
        return instance;
    }

    public String getDescription() {
        return description;
    }


    public ArrayList<Content> getContents() {
        return contents;
    }

    public String getModicon() {
        return modicon;
    }

    public String getModname() {
        return modname;
    }

    public String toString(){
        String s = "";
        s += modname;
        /*for (Content c : contents){
            s += c.getType() + c.getFileurl() + c.getFilename();
        }*/
        return s;
    }

    public Forum getForumByID(int id){
        for (Forum f : forums){
            if(f.getCmid() == id){
                return f;
            }
        }
        return null;
    }

    public String contentToString(){
        String s = "";
        for (Content c : contents){
            s += "\n" + c.getFileurl() + "\n" + c.getFilepath();
        }
        return s;
    }

}
