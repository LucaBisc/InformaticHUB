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

import it.unibas.biscione.informaticHUB.activity.SectionActivity;
import it.unibas.biscione.informaticHUB.adapter.ContentAdapter;
import it.unibas.biscione.informaticHUB.model.Content;
import it.unibas.biscione.informaticHUB.model.Course;
import it.unibas.biscione.informaticHUB.model.Module;
import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.Costants;
import it.unibas.biscione.informaticHUB.R;


public class ContentView extends Fragment {

    private static final String TAG = ContentView.class.getName();

    private TextView txtTitle;
    private RecyclerView rvContent;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, TAG + "created.");
        View view = inflater.inflate(R.layout.view_content,container,false);
        txtTitle = view.findViewById(R.id.txtTitle);
        rvContent = view.findViewById(R.id.rvContent);
        LinearLayoutManager llmCategory = new LinearLayoutManager(Application.getInstance().getCurrentActivity());
        rvContent.setLayoutManager(llmCategory);
        this.setView();
        return view;
    }

    private void setView() {
        SectionActivity sectionActivity = (SectionActivity) Application.getInstance().getCurrentActivity();
        Course course = (Course) Application.getInstance().getModel().getBean(Costants.COURSE);
        Module module = (Module) Application.getInstance().getModel().getBean(Costants.MODULE);
        if (module == null){
            sectionActivity.showErrorDialog(sectionActivity.getString(R.string.error));
            return;
        }
        txtTitle.setText(module.getName() + " - " + course.getFullname());
        ArrayList<Content> contents = module.getContents();
        System.out.println(contents.size());
        rvContent.setAdapter(new ContentAdapter(Application.getInstance().getCurrentActivity(), contents));
    }
}
