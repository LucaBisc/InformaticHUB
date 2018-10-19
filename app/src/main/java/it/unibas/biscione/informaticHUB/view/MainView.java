package it.unibas.biscione.informaticHUB.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import it.unibas.biscione.informaticHUB.control.AsyncTaskNews;
import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.Costants;
import it.unibas.biscione.informaticHUB.R;


public class MainView extends Fragment {

    private TextView txtNews;
    private View loadingPanel;

    public static final String TAG = MainView.class.getName();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, TAG + "created.");
        View view = inflater.inflate(R.layout.view_main,container,false);
        txtNews = view.findViewById(R.id.txtNews);
        this.setView();
        loadingPanel = view.findViewById(R.id.loadingPanel);
        if (Application.getInstance().getModel().getBean(Costants.NEWS) != null){
            shutLoadingPanel();
        }
        return view;
    }

    private void setView() {
        if (Application.getInstance().getModel().getBean(Costants.NEWS) == null) {
            AsyncTaskNews asyncTaskNews = (AsyncTaskNews) new AsyncTaskNews(new AsyncTaskNews.AsyncResponse() {
                @Override
                public void processFinish(String output) {
                    String news = output;
                    if (news == null){
                        txtNews.setText(getString(R.string.server_problem));
                    } else {
                    String firstSearch = "Novità";
                    String lastSearch = "<span class=\"article_separator\">&nbsp;</span>";
                    String result = news.substring(news.indexOf(firstSearch) + 6, news.indexOf(lastSearch));
                    Application.getInstance().getModel().putBean(Costants.NEWS, result);
                    txtNews.setText(Html.fromHtml(result));
                    txtNews.setMovementMethod(LinkMovementMethod.getInstance());
                    }
                    shutLoadingPanel();
                }
            }).execute();
        } else {
            txtNews.setText(Html.fromHtml((String) Application.getInstance().getModel().getBean(Costants.NEWS)));
            txtNews.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    public void shutLoadingPanel(){
        loadingPanel.setVisibility(View.INVISIBLE);
    }
}
