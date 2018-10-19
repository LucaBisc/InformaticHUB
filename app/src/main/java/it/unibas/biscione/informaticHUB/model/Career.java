package it.unibas.biscione.informaticHUB.model;

import com.google.gson.annotations.SerializedName;

public class Career {

    private int matId;

    @SerializedName("matricola")
    private String mat;

    public int getMatId() {
        return matId;
    }

    public void setMatId(int matId) {
        this.matId = matId;
    }

    public String getMatricola() {
        return mat;
    }

    public void setMatricola(String matricola) {
        this.mat = matricola;
    }
}
