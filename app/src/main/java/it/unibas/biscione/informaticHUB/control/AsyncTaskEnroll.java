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

import it.unibas.biscione.informaticHUB.activity.CategoryCourseActivity;
import it.unibas.biscione.informaticHUB.persistence.DAOGenericoJson;
import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.Costants;
import it.unibas.biscione.informaticHUB.R;
import it.unibas.biscione.informaticHUB.model.Archive;
import it.unibas.biscione.informaticHUB.model.Course;
import it.unibas.biscione.informaticHUB.model.MoodleSiteInfo;
import it.unibas.biscione.informaticHUB.model.Outcome;
import it.unibas.biscione.informaticHUB.model.UserMoodle;

public class AsyncTaskEnroll extends AsyncTask<Integer, String, Boolean> {

    private static final String TAG = AsyncTaskEnroll.class.getName();
    CategoryCourseActivity categoryCourseActivity = (CategoryCourseActivity) Application.getInstance().getCurrentActivity();
    String error = "";

    @Override
    protected void onPreExecute() {
        categoryCourseActivity.showLoadingDialog(categoryCourseActivity.getString(R.string.enrollment), categoryCourseActivity.getString(R.string.wait));
    }

    @Override
    protected Boolean doInBackground(Integer... integers) {
        UserMoodle userMoodle = (UserMoodle) Application.getInstance().getModel().getBean(Costants.USER);
        Archive archive = (Archive) Application.getInstance().getModel().getBean(Costants.ARCHIVE);
        int id = integers[0];
        if (!enrollUser(id, userMoodle)){
            return false;
        }
        Course c = archive.getCourseById(id);
        userMoodle.addCourse(c);
        Application.getInstance().getModel().putBean(Costants.USER, userMoodle);
        return true;
    }

    private boolean enrollUser(int id, UserMoodle userMoodle) {
        String parametri = "";
        String restUrl = "";
        MoodleSiteInfo siteInfo = userMoodle.getSiteInfo();
        HttpURLConnection connection;

        try {
            parametri = "&" + URLEncoder.encode("courseid", "UTF-8") + "="
                    + id + "&" + URLEncoder.encode("password", "UTF-8") + "=&"
                    + URLEncoder.encode("instanceid", "UTF-8") + "=0&"
                    + URLEncoder.encode("moodlewssettingfilter", "UTF-8") + "=" + URLEncoder.encode("true", "UTF-8");
            restUrl = Costants.URI + "/webservice/rest/server.php?wstoken=" + userMoodle.getToken().getToken()
                    + "&wsfunction=enrol_self_enrol_user&moodlewsrestformat=json";
        } catch (UnsupportedEncodingException e) {
            Log.d(TAG,"Codifica url non riuscita.");
            e.printStackTrace();
        }

        try{
            connection = (HttpURLConnection) new URL(restUrl + parametri).openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write("");
            writer.flush();
            InputStream input =  connection.getInputStream();
            DAOGenericoJson daoGenericoJson = new DAOGenericoJson();
            Outcome outcome = (Outcome) daoGenericoJson.load(input, Outcome.class);
            if(!outcome.isStatus()){
                error += outcome.getMessage();
                return false;
            }
            return true;
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        error += categoryCourseActivity.getString(R.string.unknown_error);
        return false;
    }

    @Override
    protected void onPostExecute(Boolean stato) {
        super.onPostExecute(stato);
        categoryCourseActivity.closeLoadingDialog();
        if (!stato) {
            categoryCourseActivity.showErrorDialog(error);
        } else {
            categoryCourseActivity.showMessageDialog(categoryCourseActivity.getString(R.string.enrolled_successfull));
        }
    }
}
