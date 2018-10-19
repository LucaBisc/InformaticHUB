package it.unibas.biscione.informaticHUB.control;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import it.unibas.biscione.informaticHUB.activity.SectionActivity;
import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.Costants;
import it.unibas.biscione.informaticHUB.R;
import it.unibas.biscione.informaticHUB.model.UserMoodle;

public class AsyncTaskPage extends AsyncTask<String, String, Boolean>{

    private static final String TAG = AsyncTaskEnroll.class.getName();

    private SectionActivity sectionActivity = (SectionActivity) Application.getInstance().getCurrentActivity();
    private String error = "";
    private String result = "";

    @Override
    protected void onPreExecute() {
        sectionActivity.showLoadingDialog(sectionActivity.getString(R.string.download_file), sectionActivity.getString(R.string.wait));
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        UserMoodle userMoodle = (UserMoodle) Application.getInstance().getModel().getBean(Costants.USER);
        String url = strings[0];
        if (!downloadPage(url, userMoodle)){
            return false;
        }
        return true;
    }

    private boolean downloadPage(String url, UserMoodle userMoodle) {
        String param = "";
        HttpURLConnection connection;

        try{
            param = "&" + URLEncoder.encode("token", "UTF-8") + "="
                    + userMoodle.getToken().getToken() + "&" + URLEncoder.encode("offline", "UTF-8")
                    +  "=1";
            url += param;
        } catch (UnsupportedEncodingException e) {
            Log.d(TAG,"Codifica url non riuscita.");
            e.printStackTrace();
        }
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = reader.readLine();
            String html = "";
            if (line == null){
                error = sectionActivity.getString(R.string.unknown_error);
                return false;
            }
            while (line != null){
                html += line;
                line = reader.readLine();
            }
            result += html;
            SectionActivity sectionActivity = (SectionActivity) Application.getInstance().getCurrentActivity();
            sectionActivity.setHtmlPage(result);
            return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    protected void onPostExecute(Boolean stato) {
        super.onPostExecute(stato);
        sectionActivity.closeLoadingDialog();
        if (!stato) {
            sectionActivity.showErrorDialog(error);
        } else {
            sectionActivity.showPageActivity();
        }
    }
}
