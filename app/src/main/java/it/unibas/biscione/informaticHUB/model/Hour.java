package it.unibas.biscione.informaticHUB.model;

import it.unibas.biscione.informaticHUB.Costants;

public class Hour {

    private String ora;
    private String giorno;
    private String aula = " ";
    private String insegnamento;

    public String getGiorno() {
        return giorno;
    }

    public String getAula() {
        return aula;
    }

    public String getInsegnamento() {
        return insegnamento;
    }

    public String getOra() {
        return ora;
    }

    public void setInsegnamento(String insegnamento) {
        this.insegnamento = insegnamento;
    }

    public Hour(String insegnamento) {
        this.insegnamento = insegnamento;
    }

    public Hour() {
    }

    public String getStringHour(int hour){
        if (hour == 0){
            return "\n8:30-9:30";
        }
        if (hour == 1){
            return "\n9:30-10:30";
        }
        if (hour == 2){
            return "\n10:30-11:30";
        }
        if (hour == 3){
            return "\n11:30-12:30";
        }
        if (hour == 4){
            return "\n12:30-13:30";
        }
        if (hour == 5){
            return "\n13:30-14:00";
        }
        if (hour == 6){
            return "\n14:00-15:00";
        }
        if (hour == 7){
            return "\n15:00-16:00";
        }
        if (hour == 8){
            return "\n16:00-17:00";
        }
        if (hour == 9){
            return "\n17:00-18:00";
        }
        if (hour == 10){
            return "\n18:00-19:00";
        }
        return "\n19:00-20:00";
    }

    public int getIntHour(){
        if (ora.equals("8:30-9:30")){
            return Costants.FIRST;
        }
        if (ora.equals("9:30-10:30")){
            return Costants.SECOND;
        }
        if (ora.equals("10:30-11:30")){
            return Costants.THIRD;
        }
        if (ora.equals("11:30-12:30")){
            return Costants.FOURTH;
        }
        if (ora.equals("12:30-13:30")){
            return Costants.FIFTH;
        }
        if (ora.equals("13:30-14:00")){
            return Costants.SIXTH;
        }
        if (ora.equals("14:00-15:00")){
            return Costants.SEVENTH;
        }
        if (ora.equals("15:00-16:00")){
            return Costants.EIGHTH;
        }
        if (ora.equals("16:00-17:00")){
            return Costants.NINTH;
        }
        if (ora.equals("17:00-18:00")){
            return Costants.TENTH;
        }
        if (ora.equals("18:00-19:00")){
            return Costants.ELEVENTH;
        }
        return Costants.TWELFTH;
    }

    public String toStringForTimetable(){
        String result = insegnamento + "\n";
        result += aula;
        return  result;
    }

    public String toString(){
        String result = insegnamento;
        result += "\naula";
        return result;
    }
}
