package it.unibas.biscione.informaticHUB.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserEsse3 {

    private String password;
    private String username;

    @SerializedName("user")
    private Detail detail;

    private ArrayList<Exam> exams;

    public String getPassword() {
        return password;
    }

    public void setPassword(String pw) {
        password = pw;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String user) {
        username = user;
    }


    public Detail getDetails() {
        return detail;
    }

    public void setDetails(Detail detail) {
        this.detail = detail;
    }

    public ArrayList<Exam> getExams() {
        return exams;
    }

    public void setExams(ArrayList<Exam> exams) {
        this.exams = exams;
    }

    public ArrayList<Exam> getPassedExam() {
        ArrayList<Exam> list = new ArrayList<>();
        for (Exam e : exams){
            if (e.isPassed() && !e.getName().equalsIgnoreCase("Inglese")){
                list.add(e);
            }
        }
        return list;
    }

    public String  getNameString (){
        return detail.getFirstName() + " " +  detail.getLastName();
    }

    public String getName(){
        return detail.getFirstName();
    }

    public String getIDNumber(){
        return detail.getCareers().get(0).getMatricola();
    }

    public String getSurname(){ return detail.getLastName();}

    public int getTotalCFU(){
        ArrayList<Exam> list = getPassedExam();
        int sum = 0;
        for (Exam e : list){
            sum += e.getCfu();
        }
        return sum;
    }

    public double getAverage() {
        if (getPassedExam().isEmpty()) {
            return 0;
        }
        int sumGrades = 0;
        int sumCFU = getTotalCFU();
        for (Exam e : getPassedExam()) {
            sumGrades += e.getData().getVote() * e.getCfu();
        }
        return ((double) sumGrades) / sumCFU;
    }

    public double getGrade() {
        if (getAverage() == 0){
            return 0;
        }
        return getAverage() / 30 * 110;
    }
}
