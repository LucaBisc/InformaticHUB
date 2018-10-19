package it.unibas.biscione.informaticHUB.model;

public class Category {

    private int id;
    private String name;
    private String idnumber;
    private String description;
    private String descriptionformat;
    private int parent;
    private int sortorder;
    private int coursecount;
    private int visible;
    private int visibleold;
    private int timemodified;
    private int depth;
    private String path;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getParent() {
        return parent;
    }

    public int getVisible() {
        return visible;
    }

    public int getDepth() {
        return depth;
    }

    public String getPath() {
        return path;
    }
}
