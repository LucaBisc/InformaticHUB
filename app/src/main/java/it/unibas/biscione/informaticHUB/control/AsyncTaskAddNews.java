package it.unibas.biscione.informaticHUB.control;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import it.unibas.biscione.informaticHUB.activity.AddNewsActivity;
import it.unibas.biscione.informaticHUB.model.UserMoodle;
import it.unibas.biscione.informaticHUB.persistence.DAOGenericoJson;
import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.Costants;
import it.unibas.biscione.informaticHUB.R;
import it.unibas.biscione.informaticHUB.model.Discussion;
import it.unibas.biscione.informaticHUB.model.Forum;

public class AsyncTaskAddNews extends AsyncTask<String, String, Boolean> {

    public static final String TAG = AsyncTaskAddNews.class.getSimpleName();

    private AddNewsActivity addNewsActivity = (AddNewsActivity) Application.getInstance().getCurrentActivity();
    private UserMoodle userMoodle = (UserMoodle) Application.getInstance().getModel().getBean(Costants.USER);
    private String error;

    @Override
    protected void onPreExecute() {
        addNewsActivity.showLoadingDialog(addNewsActivity.getString(R.string.post_news), addNewsActivity.getString(R.string.wait));
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        String title = strings[0];
        String message = strings[1];
        if (!postNews(title,message)){
            error = addNewsActivity.getString(R.string.unknown_error);
            return false;
        }
        return true;

    }

    private boolean postNews(String title, String message){
        String param = "";
        String restUrl = "";
        Forum f = (Forum) Application.getInstance().getModel().getBean(Costants.FORUM);
        HttpURLConnection connection;

        try {
            param = "&" + URLEncoder.encode("forumid", "UTF-8") + "=" + f.getId() + "&"
                    + URLEncoder.encode("subject", "UTF-8") + "=" + title + "&"
                    + URLEncoder.encode("message", "UTF-8") + "=" + message;
            restUrl = Costants.URI + "/webservice/rest/server.php?wstoken=" + userMoodle.getToken().getToken()
                    + "&wsfunction=mod_forum_add_discussion&moodlewsrestformat=json";
        } catch (UnsupportedEncodingException e) {
            Log.d(TAG,"Codifica url non riuscita.");
            e.printStackTrace();
        }

        try{
            connection = (HttpURLConnection) new URL(restUrl + param).openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write("");
            writer.flush();
            InputStream input = connection.getInputStream();
            DAOGenericoJson daoGenericoJson = new DAOGenericoJson();
            Discussion discussion = (Discussion) daoGenericoJson.load(input, Discussion.class);
            if (discussion.getDiscussionid() == 999){
                return false;
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean stato) {
        super.onPostExecute(stato);
        addNewsActivity.closeLoadingDialog();
        if (!stato) {
            addNewsActivity.showErrorDialog(error);
        } else {
            int forumID = ((Forum)Application.getInstance().getModel().getBean(Costants.FORUM)).getId();
            AsyncTaskForum asyncTaskForum = new AsyncTaskForum(false);
            asyncTaskForum.execute(forumID);
        }
    }
}
