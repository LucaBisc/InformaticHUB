package it.unibas.biscione.informaticHUB.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Post {

    private int id;
    private String name;
    private String subject;
    private String message;
    private String userfullname;
    private int modified;
    private int messageformat;

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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserfullname() {
        return userfullname;
    }

    public void setUserfullname(String userfullname) {
        this.userfullname = userfullname;
    }

    public int getMessageformat() {
        return messageformat;
    }

    public String getCreationDate(){
        Date creationDate = new Date(modified);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        return (simpleDateFormat.format(creationDate.getTime()));
        /*Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.setTimeInMillis(modified);
        return (cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH) + 1) + "/"
                + cal.get(Calendar.DAY_OF_MONTH) + " " + cal.get(Calendar.HOUR_OF_DAY) + ":"
                + cal.get(Calendar.MINUTE));*/

    }
}
