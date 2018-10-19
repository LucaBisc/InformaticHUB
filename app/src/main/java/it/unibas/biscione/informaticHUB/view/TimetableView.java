package it.unibas.biscione.informaticHUB.view;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

import it.unibas.biscione.informaticHUB.adapter.GridAdapter;
import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.Costants;
import it.unibas.biscione.informaticHUB.R;
import it.unibas.biscione.informaticHUB.model.Archive;
import it.unibas.biscione.informaticHUB.model.Cohort;

public class TimetableView extends Fragment {

    private GridView gridTimetable;
    private TextView txtTitle;
    private View view;


    public static final String TAG = MainView.class.getName();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, TAG + "created.");
        view = inflater.inflate(R.layout.view_timetable,container,false);
        gridTimetable = view.findViewById(R.id.gridTimetable);
        txtTitle = view.findViewById(R.id.txtTitle);
        this.setView();
        return view;
    }

    private void setView() {
        if (((Archive) Application.getInstance().getModel().getBean(Costants.ARCHIVE)) == null){
            return;
        }
        Cohort c = ((Cohort) Application.getInstance().getModel().getBean(Costants.COHORT));
        ArrayList<String> strings = c.getHoursString();
        txtTitle.setText(c.getNome());
        gridTimetable.setAdapter(new GridAdapter(Application.getInstance().getApplicationContext(), strings));
    }
}
