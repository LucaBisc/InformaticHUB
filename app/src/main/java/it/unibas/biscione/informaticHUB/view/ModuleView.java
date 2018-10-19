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
import it.unibas.biscione.informaticHUB.adapter.ModuleAdapter;
import it.unibas.biscione.informaticHUB.model.Course;
import it.unibas.biscione.informaticHUB.model.Module;
import it.unibas.biscione.informaticHUB.model.Section;
import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.Costants;
import it.unibas.biscione.informaticHUB.R;

public class ModuleView extends Fragment {

    private static final String TAG = ModuleView.class.getName();

    private TextView txtTitle;
    private RecyclerView rvModule;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, TAG + "created.");
        View view = inflater.inflate(R.layout.view_module,container,false);
        txtTitle = view.findViewById(R.id.txtTitle);
        rvModule = view.findViewById(R.id.rvModule);
        LinearLayoutManager llmCategory = new LinearLayoutManager(Application.getInstance().getCurrentActivity());
        rvModule.setLayoutManager(llmCategory);
        this.setView();
        return view;
    }

    private void setView() {
        SectionActivity sectionActivity = (SectionActivity) Application.getInstance().getCurrentActivity();
        Course course = (Course) Application.getInstance().getModel().getBean(Costants.COURSE);
        Section section = course.getSectionByID(sectionActivity.getId());
        if (section == null){
            sectionActivity.showErrorDialog(sectionActivity.getString(R.string.error));
            return;
        }
        txtTitle.setText(section.getName() + " - " + course.getFullname());
        ArrayList<Module> modules = section.getModules();
        rvModule.setAdapter(new ModuleAdapter(Application.getInstance().getCurrentActivity(), modules));
    }
}
