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

import java.util.ArrayList;

import it.unibas.biscione.informaticHUB.control.AsyncTaskTimetable;
import it.unibas.biscione.informaticHUB.model.Archive;
import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.Costants;
import it.unibas.biscione.informaticHUB.R;
import it.unibas.biscione.informaticHUB.adapter.CohortAdapter;
import it.unibas.biscione.informaticHUB.model.Cohort;

public class CohortView extends Fragment {

    private static final String TAG = CohortView.class.getName();

    private TextView txtTitle;
    private RecyclerView rvCohort;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, TAG + "created.");
        View view = inflater.inflate(R.layout.view_cohort, container, false);
        txtTitle = view.findViewById(R.id.txtTitle);
        rvCohort = view.findViewById(R.id.rvCohort);
        LinearLayoutManager llmCategory = new LinearLayoutManager(Application.getInstance().getCurrentActivity());
        rvCohort.setLayoutManager(llmCategory);
        this.setView();
        return view;
    }

    private void setView() {
        AsyncTaskTimetable asyncTaskTimetable = new AsyncTaskTimetable();
        asyncTaskTimetable.execute();
        ArrayList<Cohort> cohorts = ((Archive)Application.getInstance().getModel().getBean(Costants.ARCHIVE)).getCohorts();
        rvCohort.setAdapter(new CohortAdapter(Application.getInstance().getCurrentActivity(), cohorts));
    }
}
