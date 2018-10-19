package it.unibas.biscione.informaticHUB.control;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import it.unibas.biscione.informaticHUB.activity.CategoryCourseActivity;
import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.Costants;
import it.unibas.biscione.informaticHUB.R;
import it.unibas.biscione.informaticHUB.model.Archive;
import it.unibas.biscione.informaticHUB.model.Course;
import it.unibas.biscione.informaticHUB.model.Section;
import it.unibas.biscione.informaticHUB.model.UserMoodle;

public class AsyncTaskFetchContent extends AsyncTask <Integer, String, Boolean>{

    private static final String TAG = AsyncTaskFetchContent.class.getName();

    private CategoryCourseActivity categoryCourseActivity = (CategoryCourseActivity) Application.getInstance().getCurrentActivity();
    private String error = "";
    private int id;

    @Override
    protected void onPreExecute() {
        categoryCourseActivity.showLoadingDialog(categoryCourseActivity.getString(R.string.fetch_course), categoryCourseActivity.getString(R.string.wait));
    }

    @Override
    protected Boolean doInBackground(Integer... integers) {
        int id = integers[0];
        this.id = id;
        Archive archive = (Archive) Application.getInstance().getModel().getBean(Costants.ARCHIVE);
        Course c = archive.getCourseById(id);
        if (c.getSections() == null){
            if(!getSection(id)) {
                return false;
            }
        }
        return true;
    }

    private boolean getSection(int id) {
        String param = "";
        String restUrl = "";
        UserMoodle userMoodle = (UserMoodle) Application.getInstance().getModel().getBean(Costants.USER);
        ArrayList<Section> sections = new ArrayList<>();
        HttpURLConnection connection;

        try {
            param = "&" + URLEncoder.encode("courseid", "UTF-8") + "="
                    + id + "&" + URLEncoder.encode("moodlewssettingfilter", "UTF-8") + "=" + URLEncoder.encode("true", "UTF-8");
            restUrl = Costants.URI + "/webservice/rest/server.php?wstoken=" + userMoodle.getToken().getToken()
                    + "&wsfunction=core_course_get_contents&moodlewsrestformat=json";
        } catch (UnsupportedEncodingException e) {
            Log.d(TAG,"Codifica url non riuscita.");
            e.printStackTrace();
        }

        try {
            connection = (HttpURLConnection) new URL(restUrl + param).openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write("");
            writer.flush();
            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            Gson gson = new GsonBuilder().create();
            Type tipo = new TypeToken<ArrayList<Section>>() {
            }.getType();
            sections = gson.fromJson(reader, tipo);
            if (sections == null) {
                return false;
            }
            Archive archive = (Archive) Application.getInstance().getModel().getBean(Costants.ARCHIVE);
            archive.getCourseById(id).setSections(sections);
            Application.getInstance().getModel().putBean(Costants.ARCHIVE, archive);
            return true;
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        error += categoryCourseActivity.getString(R.string.section_failed);
        return false;
    }

    @Override
    protected void onPostExecute(Boolean stato) {
        super.onPostExecute(stato);
        categoryCourseActivity.closeLoadingDialog();
        if (!stato) {
            categoryCourseActivity.showErrorDialog(error);
        } else {
           categoryCourseActivity.showSectionActivity(id);
        }
    }
}
