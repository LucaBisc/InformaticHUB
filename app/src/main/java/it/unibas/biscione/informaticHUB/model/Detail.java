package it.unibas.biscione.informaticHUB.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Detail {

    private String firstName;
    private String lastName;

    @SerializedName("trattiCarriera")
    private ArrayList<Career> careers;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ArrayList<Career> getCareers() {
        return careers;
    }

    public void setCareers(ArrayList<Career> careers) {
        this.careers = careers;
    }
}
