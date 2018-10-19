package it.unibas.biscione.informaticHUB.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.Costants;
import it.unibas.biscione.informaticHUB.R;
import it.unibas.biscione.informaticHUB.adapter.SectionAdapter;
import it.unibas.biscione.informaticHUB.model.Course;
import it.unibas.biscione.informaticHUB.model.Section;
import it.unibas.biscione.informaticHUB.model.UserMoodle;

public class SectionViewLand extends Fragment {

    private static final String TAG = SectionView.class.getName();

    private TextView txtTitle;
    private RecyclerView rvSection;
    private TextView txtSite;
    private TextView txtSummary;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, TAG + "created.");
        View view = inflater.inflate(R.layout.view_section_land,container,false);
        txtTitle = view.findViewById(R.id.txtTitle);
        rvSection = view.findViewById(R.id.rvSection);
        txtSummary = view.findViewById(R.id.txtSummary);
        txtSite = view.findViewById(R.id.txtSiteInfo);
        LinearLayoutManager llmCategory = new LinearLayoutManager(Application.getInstance().getCurrentActivity());
        rvSection.setLayoutManager(llmCategory);
        this.setView();
        return view;
    }

    private void setView() {
        UserMoodle userMoodle = (UserMoodle) Application.getInstance().getModel().getBean(Costants.USER);
        Course c = (Course) Application.getInstance().getModel().getBean(Costants.COURSE);
        txtTitle.setText(c.getFullname());
        ArrayList<Section> sections = c.getSections();
        rvSection.setAdapter(new SectionAdapter(Application.getInstance().getCurrentActivity(), sections));
        txtSummary.setText(Html.fromHtml(c.getSummary()));
        txtSummary.setMovementMethod(LinkMovementMethod.getInstance());
        txtSite.setText(userMoodle.getSiteInfo().getStringSiteInfo());
    }
}
