package it.unibas.biscione.informaticHUB.control;

import android.view.View;

import it.unibas.biscione.informaticHUB.activity.AddNewsActivity;
import it.unibas.biscione.informaticHUB.view.ViewAddNews;
import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.R;

public class ControlAddNews {

    private View.OnClickListener actionAddNews = new ActionAddNews();

    public View.OnClickListener getActionAddNews() {
        return actionAddNews;
    }


    private static final class ActionAddNews implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            AddNewsActivity addNewsActivity = (AddNewsActivity) Application.getInstance().getCurrentActivity();
            ViewAddNews viewAddNews = addNewsActivity.getAddNewsView();
            String title = viewAddNews.getTitle();
            String message = viewAddNews.getMessage();
            boolean error = validate (title,message, viewAddNews);
            if (error){
                return;
            }
            AsyncTaskAddNews asyncTaskAddNews = new AsyncTaskAddNews();
            asyncTaskAddNews.execute(title, message);
        }


        private boolean validate(String title, String message, ViewAddNews viewAddNews) {
            AddNewsActivity addNewsActivity = (AddNewsActivity) Application.getInstance().getCurrentActivity();
            if (title.trim().isEmpty()){
                viewAddNews.showTitleError(addNewsActivity.getString(R.string.insert_title));
                return true;
            }
            if (message.trim().isEmpty()){
                viewAddNews.showMessageError(addNewsActivity.getString(R.string.insert_message));
                return true;
            }
            return false;
        }
    }
}
