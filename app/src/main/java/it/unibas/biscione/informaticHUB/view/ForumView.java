package it.unibas.biscione.informaticHUB.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import it.unibas.biscione.informaticHUB.adapter.ForumAdapter;
import it.unibas.biscione.informaticHUB.model.Course;
import it.unibas.biscione.informaticHUB.model.Discussion;
import it.unibas.biscione.informaticHUB.model.Forum;
import it.unibas.biscione.informaticHUB.model.Module;
import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.Costants;
import it.unibas.biscione.informaticHUB.R;

public class ForumView extends Fragment {

    private static final String TAG = ModuleView.class.getName();

    private TextView txtTitle;
    private TextView txtIntro;
    private RecyclerView rvForum;
    private FloatingActionButton btnAddNews;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, TAG + "created.");
        View view = inflater.inflate(R.layout.view_forum,container,false);
        txtTitle = view.findViewById(R.id.txtTitle);
        txtIntro = view.findViewById(R.id.txtIntro);
        rvForum = view.findViewById(R.id.rvForum);
        btnAddNews = view.findViewById(R.id.btnAddNews);
        if (!((Forum)Application.getInstance().getModel().getBean(Costants.FORUM)).canUserAddNews()){
            btnAddNews.setVisibility(View.INVISIBLE);
        }
        LinearLayoutManager llmCategory = new LinearLayoutManager(Application.getInstance().getCurrentActivity());
        rvForum.setLayoutManager(llmCategory);
        this.setView();
        this.inizializeAction();
        return view;
    }

    private void inizializeAction() {
        btnAddNews.setOnClickListener(Application.getInstance().getControlForum().getAddNews());
    }

    private void setView() {
        Module module = (Module) Application.getInstance().getModel().getBean(Costants.MODULE);
        Course course = (Course) Application.getInstance().getModel().getBean(Costants.COURSE);
        Forum forum = (Forum) Application.getInstance().getModel().getBean(Costants.FORUM);
        txtTitle.setText(forum.getName() + " - " + course.getFullname());
        if (forum.getIntroFormat() == 1 || forum.getIntroFormat() == 0){
            txtIntro.setText(Html.fromHtml(forum.getIntro()));
        } else {
            txtIntro.setText(forum.getIntro());
        }
        Discussion discussion = forum.getDiscussion();
        rvForum.setAdapter(new ForumAdapter(Application.getInstance().getCurrentActivity(), discussion.getDiscussions()));
    }
}
