package it.unibas.biscione.informaticHUB.model;

import java.util.ArrayList;

public class Section {

    private int id;
    private String name;
    private String summary;
    private int section;
    private ArrayList<Module> modules;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSummary() {
        return summary;
    }

    public int getSection() {
        return section;
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public Module getModuleByID (int id){
        for (Module m : modules){
            if (m.getId() == id){
                return m;
            }
        }
        return null;
    }
}
