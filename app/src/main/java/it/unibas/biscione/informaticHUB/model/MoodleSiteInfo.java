package it.unibas.biscione.informaticHUB.model;

public class MoodleSiteInfo {


    private String sitename;
    private String username;
    private String firstname;
    private String lastname;
    private String fullname;
    private String lang;
    private int userid;
    private String siteurl;
    private String userpictureurl;
    private int downloadfiles;
    private int uploadfiles;
    private String release;
    private String version;
    private String message;

    public String getUserpictureurl() {
        return userpictureurl;
    }

    public String getSiteName() {
        return sitename;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return firstname;
    }

    public String getSurname() {
        return lastname;
    }

    public String getFullName() {
        return fullname;
    }

    public String getLang() {
        return lang;
    }

    public int getUserid() {
        return userid;
    }

    public String getSiteurl() {
        return siteurl;
    }

    //1 = si, 0 = no
    public int getPermessoDownload() {
        return downloadfiles;
    }

    //1 = si, 0 = no
    public int getPermessoUpload() {
        return uploadfiles;
    }

    public String getRelease() {
        return release;
    }

    public String getVersione() {
        return version;
    }

    public String getMessage() {
        return message;
    }

    public String  getStringSiteInfo(){
        return "Sito: " + sitename + "\n\nUtente: " + fullname;
    }
}
