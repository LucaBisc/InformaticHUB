package it.unibas.biscione.informaticHUB.control;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import it.unibas.biscione.informaticHUB.activity.LoginMoodleActivity;
import it.unibas.biscione.informaticHUB.persistence.DAOGenericoJson;
import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.Costants;
import it.unibas.biscione.informaticHUB.R;
import it.unibas.biscione.informaticHUB.model.Archive;
import it.unibas.biscione.informaticHUB.model.Category;
import it.unibas.biscione.informaticHUB.model.Course;
import it.unibas.biscione.informaticHUB.model.MoodleSiteInfo;
import it.unibas.biscione.informaticHUB.model.Token;
import it.unibas.biscione.informaticHUB.model.UserMoodle;

public class AsyncTaskLoginMoodle extends AsyncTask<Void, String, Boolean> {

    public static final String TAG = AsyncTaskLoginMoodle.class.getSimpleName();

    private Token token = new Token();
    MoodleSiteInfo siteInfo = new MoodleSiteInfo();
    private UserMoodle userMoodle = new UserMoodle();
    private Archive archive;
    private String error = Application.getInstance().getCurrentActivity().getString(R.string.server_problem);
    private LoginMoodleActivity loginMoodleActivity = (LoginMoodleActivity) Application.getInstance().getCurrentActivity();

    public AsyncTaskLoginMoodle(UserMoodle userMoodle) {
        this.userMoodle = userMoodle;
    }

    @Override
    protected void onPreExecute() {
        loginMoodleActivity.showLoadingDialog(loginMoodleActivity.getString(R.string.login_moodle), loginMoodleActivity.getString(R.string.wait));
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        if (userMoodle.getToken() == null) {
            if(!this.getToken()){
                return false;
            }
        }
        Log.d(TAG, "" + userMoodle.getToken().getToken());
        if (userMoodle.getSiteInfo() == null){
            if(!this.getSiteInfo()) {
                return false;
            }
        }
        Log.d(TAG, "" + userMoodle.getSiteInfo().getSiteName());
        if (archive == null){
            if(!this.getCourses()) {
                return false;
            }
            if(!this.getCategories()){
                return false;
            }
        }
        Log.d(TAG, "" + archive.getCourseList().size() + archive.getCategoryList().size() + archive.toString());
        if (userMoodle.getUserCoursesList() == null){
            if (!this.getUserCourse()){
                return false;
            }
        }
        Log.d(TAG,"" + userMoodle.getUserCoursesList().size());
        if(userMoodle == null || siteInfo == null || archive == null || userMoodle.getUserCoursesList() == null) {
            error = loginMoodleActivity.getString(R.string.unknown_error);
            return false;
        }
        Application.getInstance().getModel().putBean(Costants.USER, userMoodle);
        Archive ar = (Archive) Application.getInstance().getModel().getBean(Costants.ARCHIVE);
        if (ar != null) {
            archive.setCohorts(ar.getCohorts());
        }
        Application.getInstance().getModel().putBean(Costants.ARCHIVE, archive);
        return true;
    }

    private boolean getCategories() {
        String param = "";
        String restUrl = "";
        if (siteInfo == null){
            return false;
        }
        MoodleSiteInfo siteInfo = userMoodle.getSiteInfo();
        ArrayList<Category> categoryList = new ArrayList<>();
        HttpURLConnection connection;

        try {
            param = "&" + URLEncoder.encode("moodlewssettingfilter", "UTF-8") + "=" + URLEncoder.encode("true", "UTF-8");
            restUrl = Costants.URI + "/webservice/rest/server.php?wstoken=" + userMoodle.getToken().getToken()
                    + "&wsfunction=core_course_get_categories&moodlewsrestformat=json";
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
            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            Gson gson = new GsonBuilder().create();
            Type tipo = new TypeToken<ArrayList<Category>>() {
            }.getType();
            categoryList = gson.fromJson(reader, tipo);
            if (categoryList == null){
                return false;
            }
            archive.setCategoryList(categoryList);
            return true;
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException ex){
            ex.printStackTrace();
            error = loginMoodleActivity.getString(R.string.category_failed);
        }
        return false;

    }

    private boolean getUserCourse() {
        String param = "";
        String restUrl = "";
        if (siteInfo == null) {
            return false;
        }
        MoodleSiteInfo siteInfo = userMoodle.getSiteInfo();
        ArrayList<Course> courseList = new ArrayList<>();
        HttpURLConnection connection;

        try {
            param = "&" + URLEncoder.encode("userid", "UTF-8") + "=" + siteInfo.getUserid();
            restUrl = Costants.URI + "/webservice/rest/server.php?wstoken=" + userMoodle.getToken().getToken()
                    + "&wsfunction=core_enrol_get_users_courses&moodlewsrestformat=json";
        } catch (UnsupportedEncodingException e) {
            Log.d(TAG, "Codifica url non riuscita.");
            e.printStackTrace();
        }

        try {
            connection = (HttpURLConnection) new URL(restUrl + param).openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/xml");
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write("");
            writer.flush();
            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            Gson gson = new GsonBuilder().create();
            Type tipo = new TypeToken<ArrayList<Course>>() {
            }.getType();
            courseList = gson.fromJson(reader, tipo);
            if (courseList == null){
                return false;
            }
            userMoodle.setUserCoursesList(courseList);
            return true;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException ex){
            ex.printStackTrace();
            error = loginMoodleActivity.getString(R.string.course_failed);
        }
        return false;

    }

    private boolean getCourses() {
        String param = "";
        String restUrl = "";
        if (siteInfo == null) {
            return false;
        }
        HttpURLConnection connection;

        try {
            param = "&" + URLEncoder.encode("field", "UTF-8") + "=&" + URLEncoder.encode("moodlewssettingfilter", "UTF-8") + "="
                    + URLEncoder.encode("true", "UTF-8") + "&" + URLEncoder.encode("moodlewssettingfileurl", "UTF-8") + "=";
            restUrl = Costants.URI + "/webservice/rest/server.php" + "?wstoken="
                    + userMoodle.getToken().getToken() + "&wsfunction=core_course_get_courses_by_field&moodlewsrestformat=json";
        } catch (UnsupportedEncodingException e) {
            Log.d(TAG, "Codifica url non riuscita.");
            e.printStackTrace();
        }

        try {
            connection = (HttpURLConnection) new URL(restUrl + param).openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/xml");
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write("");
            writer.flush();
            InputStream input = connection.getInputStream();
            DAOGenericoJson daoGenericoJson = new DAOGenericoJson();
            archive = (Archive) daoGenericoJson.load(input, Archive.class);
            if (archive == null){
                error = loginMoodleActivity.getString(R.string.course_failed);
                return false;
            }
            return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean getSiteInfo() {
        String param = "";
        String restUrl = "";
        try {
            param = "" + URLEncoder.encode("", "UTF-8");
            restUrl = restUrl + (Costants.URI + "/webservice/rest/server.php" + "?wstoken="
                    + userMoodle.getToken().getToken() + "&wsfunction=core_webservice_get_site_info&moodlewsrestformat=json");
        } catch (UnsupportedEncodingException ex) {
            Log.d(TAG, "Codifica url non riuscita.");
            ex.printStackTrace();
        }
        HttpURLConnection connection;
        try {
            connection = (HttpURLConnection) new URL(restUrl + param).openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/xml");
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write("");
            writer.flush();
            connection.connect();
            InputStream input = connection.getInputStream();
            DAOGenericoJson daoGenericoJson = new DAOGenericoJson();
            MoodleSiteInfo siteInfo = (MoodleSiteInfo) daoGenericoJson.load(input, MoodleSiteInfo.class);
            if (siteInfo.getSiteName() == null){
                error = loginMoodleActivity.getString(R.string.site_failed);
                return false;
            }
            userMoodle.setSiteInfo(siteInfo);
            return true;

        } catch (MalformedURLException ex) {
            Log.d(TAG, "Problemi generazione url." + ex.getLocalizedMessage());
            ex.printStackTrace();
        } catch (IOException e) {
            Log.d(TAG, "Problemi generazione connessione." + e.getLocalizedMessage());
            e.printStackTrace();
        }

        return false;
    }

    private boolean getToken() {
        String param = "";
        try {
            param = "username=" + URLEncoder.encode(userMoodle.getUsername(), "UTF-8")
                    + "&password=" + URLEncoder.encode(userMoodle.getPw(), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Log.d(TAG, "Codifica credenziali non riuscita.");
            ex.printStackTrace();
        }

        HttpURLConnection connection;
        try {
            connection = (HttpURLConnection) new URL(Costants.URI + "/login/token.php?" + param
                    + "&service=moodle_mobile_app").openConnection();
            connection.setRequestProperty("Accept", "application/xml");
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write("");
            writer.flush();
            connection.connect();
            InputStream input = connection.getInputStream();
            DAOGenericoJson daoGenericoJson = new DAOGenericoJson();
            Token token = (Token) daoGenericoJson.load(input, Token.class);
            if (token.getToken() == null){
                error = token.getError();
                return false;
            }
            userMoodle.setToken(token);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "Errore durante il recupero del token. " + e.getLocalizedMessage());
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean stato) {
        super.onPostExecute(stato);
        loginMoodleActivity.closeLoadingDialog();
        if (!stato) {
            loginMoodleActivity.showErrorDialog(error);
        } else {
            loginMoodleActivity.showCategoryCourseActivity();
        }
    }
}
