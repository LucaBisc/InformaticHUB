package it.unibas.biscione.informaticHUB.model;

import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("dataEsa")
    private String date;
    @SerializedName("lodeFlg")
    private int lode;
    @SerializedName("voto")
    private int vote;

    public String getDate() {
        return date.substring(0,10);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isLode() {
        if (lode == 0){
            return false;
        }
        return true;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }
}
