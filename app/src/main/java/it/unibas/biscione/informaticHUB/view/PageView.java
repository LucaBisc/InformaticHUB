package it.unibas.biscione.informaticHUB.view;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import it.unibas.biscione.informaticHUB.activity.SectionActivity;
import it.unibas.biscione.informaticHUB.model.Course;
import it.unibas.biscione.informaticHUB.model.Module;
import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.Costants;
import it.unibas.biscione.informaticHUB.R;

public class PageView extends Fragment {

    private static final String TAG = PageView.class.getName();

    private TextView txtTitle;
    private TextView txtPage;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, TAG + "created.");
        View view = inflater.inflate(R.layout.view_page,container,false);
        txtTitle = view.findViewById(R.id.txtTitle);
        txtPage = view.findViewById(R.id.txtPage);
        this.setView();
        return view;
    }

    private void setView() {
        SectionActivity sectionActivity = (SectionActivity) Application.getInstance().getCurrentActivity();
        Module m = (Module) Application.getInstance().getModel().getBean(Costants.MODULE);
        Course c = (Course) Application.getInstance().getModel().getBean(Costants.COURSE);
        if (sectionActivity.getHtmlPage().trim().isEmpty()){
            sectionActivity.showErrorDialog(sectionActivity.getString(R.string.error));
            return;
        }
        txtTitle.setText(m.getName() + " - " + c.getFullname());
        txtPage.setText(Html.fromHtml(sectionActivity.getHtmlPage()));
        txtPage.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
