package it.unibas.biscione.informaticHUB.model;

public class Status {

    private String value;

    public boolean isPassed(){
        if (value.trim().equalsIgnoreCase("S")){
            return true;
        }
        return false;
    }

    public String getValue() {
        return value;
    }
}
