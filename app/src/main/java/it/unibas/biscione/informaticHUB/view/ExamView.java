package it.unibas.biscione.informaticHUB.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import it.unibas.biscione.informaticHUB.model.Exam;
import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.Costants;
import it.unibas.biscione.informaticHUB.R;
import it.unibas.biscione.informaticHUB.adapter.ExamAdapter;
import it.unibas.biscione.informaticHUB.model.UserEsse3;

public class ExamView extends Fragment {

    private static final String TAG = CohortView.class.getName();

    private TextView txtTitle;
    private TextView txtCFU;
    private TextView txtGrade;
    private TextView txtAverage;
    private RecyclerView rvExam;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, TAG + "created.");
        View view = inflater.inflate(R.layout.view_exam, container, false);
        txtTitle = view.findViewById(R.id.txtTitle);
        rvExam = view.findViewById(R.id.rvExam);
        txtAverage = view.findViewById(R.id.txtAverage);
        txtCFU = view.findViewById(R.id.txtCFU);
        txtGrade = view.findViewById(R.id.txtGrade);
        LinearLayoutManager llmCategory = new LinearLayoutManager(Application.getInstance().getCurrentActivity());
        rvExam.setLayoutManager(llmCategory);
        this.setView();
        return view;
    }

    private void setView() {
        UserEsse3 userEsse3 = (UserEsse3) Application.getInstance().getModel().getBean(Costants.USER_ESSE3);
        ArrayList<Exam> exams = userEsse3.getPassedExam();
        rvExam.setAdapter(new ExamAdapter(Application.getInstance().getCurrentActivity(), exams));
        DecimalFormat format = new DecimalFormat("###.##");
        txtAverage.setText(format.format(userEsse3.getAverage()));
        txtCFU.setText(userEsse3.getTotalCFU() + "");
        txtGrade.setText(format.format(userEsse3.getGrade()));
    }
}
