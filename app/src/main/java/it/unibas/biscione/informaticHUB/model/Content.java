package it.unibas.biscione.informaticHUB.model;

public class Content {

    private String type; //file, folder or external link
    private String filename;
    private String filepath;
    private String fileurl;
    private int filesize;
    private String content;
    private String mimetype;

    public String getMimetype() {
        return mimetype;
    }

    public String getType() {
        return type;
    }

    public String getFilename() {
        return filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public String getFileurl() {
        return fileurl;
    }

    public int getFilesize() {
        return filesize;
    }

    public String getContent() {
        return content;
    }
}
