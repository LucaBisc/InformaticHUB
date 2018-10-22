package it.unibas.biscione.informaticHUB.model;

import com.google.gson.annotations.SerializedName;

public class Exam {

    @SerializedName("adDes")
    private String name;
    @SerializedName("esito")
    private Data data;
    @SerializedName("peso")
    private int cfu;
    @SerializedName("stato")
    private Status status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public int getCfu() {
        return cfu;
    }

    public void setCfu(int cfu) {
        this.cfu = cfu;
    }

    public String getStringCFUDate(){
        return cfu + " CFU - " + data.getDate();
    }

    public String getVote(){
        if (data.isLode()){
            return "30+";
        }
        if (data.getVote() == 0){
            return "IDO";
        }
        return data.getVote() + "  ";
    }

    public Status getStatus() {
        return status;
    }

    public boolean isPassed(){
        return status.isPassed();
    }
}
