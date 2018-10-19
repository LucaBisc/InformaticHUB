package it.unibas.biscione.informaticHUB.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Cohort {

    private String nome;
    private ArrayList<Hour> ore;

    public String getNome() {
        return nome;
    }

    public ArrayList<Hour> getOre() {
        return ore;
    }

    private ArrayList<Hour> getHoursByDay(String day){
        ArrayList<Hour> hours = new ArrayList<>();
        for (Hour h : ore){
            if (h.getGiorno().equals(day)){
                hours.add(h);
            }
        }
        return hours;
    }

    private ArrayList<Hour> getHoursByTime(int i){
        ArrayList<Hour> hours = new ArrayList<>();
        for (Hour h : ore){
            if (h.getIntHour() == i) {
                hours.add(h);
            }
        }
        return hours;
    }

    public HashMap<Integer, ArrayList<Hour>> getHoursForTimetable(){
       HashMap<Integer, ArrayList<Hour>> map = new HashMap<Integer, ArrayList<Hour>>();
       for (int i = 0; i < 12; i ++){
           ArrayList<Hour> hours = map.get(i);
           if (hours == null){
               hours = getHoursByTime(i);
               map.put(i, hours);
           }
       }
        return map;
    }

    public ArrayList<String> getHoursString (){
        HashMap<Integer, ArrayList<Hour>> map = getHoursForTimetable();
        ArrayList<Hour> hours = new ArrayList<>();
        Hour hour = new Hour("\nOra");
        Hour mon = new Hour("\nLunedì");
        Hour tue = new Hour("\nMartedì");
        Hour wed = new Hour("\nMercoledì");
        Hour thur = new Hour("\nGiovedì");
        Hour fri = new Hour("\nVenerdì");
        hours.add(hour);
        hours.add(mon);
        hours.add(tue);
        hours.add(wed);
        hours.add(thur);
        hours.add(fri);
        for (int i = 0; i < 12; i ++){
            ArrayList<Hour> list = map.get(i);
            Hour hourString = new Hour();
            hourString.setInsegnamento(hourString.getStringHour(i));
            hours.add(hourString);
            Hour h = new Hour(" ");
            if (getHourByDay("Lunedì", list) != null) {
                hours.add(getHourByDay("Lunedì", list));
            } else {
                hours.add(h);
            }
            if (getHourByDay("Martedì", list) != null) {
                hours.add(getHourByDay("Martedì", list));
            } else {
                hours.add(h);
            }
            if (getHourByDay("Mercoledì", list) != null) {
                hours.add(getHourByDay("Mercoledì", list));
            } else {
                hours.add(h);
            }
            if (getHourByDay("Giovedì", list) != null) {
                hours.add(getHourByDay("Giovedì", list));
            } else {
                hours.add(h);
            }
            if (getHourByDay("Venerdì", list) != null) {
                hours.add(getHourByDay("Venerdì", list));
            } else {
                hours.add(h);
            }
        }
        ArrayList<String> strings = new ArrayList<>();
        for (Hour h : hours){
            strings.add(h.toStringForTimetable());
        }
        return strings;
    }

    private Hour getHourByDay(String day, ArrayList<Hour> hours){
        for (Hour hour : hours){
            if (hour.getGiorno().trim().equalsIgnoreCase(day)){
                return hour;
            }
        }
        return null;
    }
}
