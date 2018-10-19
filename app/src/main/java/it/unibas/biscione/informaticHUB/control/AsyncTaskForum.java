package it.unibas.biscione.informaticHUB.control;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

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

import it.unibas.biscione.informaticHUB.activity.AddNewsActivity;
import it.unibas.biscione.informaticHUB.activity.SectionActivity;
import it.unibas.biscione.informaticHUB.persistence.DAOGenericoJson;
import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.Costants;
import it.unibas.biscione.informaticHUB.R;
import it.unibas.biscione.informaticHUB.model.Course;
import it.unibas.biscione.informaticHUB.model.Discussion;
import it.unibas.biscione.informaticHUB.model.Forum;
import it.unibas.biscione.informaticHUB.model.Module;
import it.unibas.biscione.informaticHUB.model.UserMoodle;

public class AsyncTaskForum extends AsyncTask <Integer,String, Boolean> {

    private static final String TAG = AsyncTaskForum.class.getName();

    private String error = "";
    private int id;
    private boolean isSectionActivity;

    public AsyncTaskForum (boolean isSectionActivity){
        this.isSectionActivity = isSectionActivity;
    }

    @Override
    protected void onPreExecute() {
        if (isSectionActivity) {
            SectionActivity sectionActivity = (SectionActivity)Application.getInstance().getCurrentActivity();
            sectionActivity.showLoadingDialog(sectionActivity.getString(R.string.fetch_forum), sectionActivity.getString(R.string.wait));
        } else {
            AddNewsActivity addNewsActivity = (AddNewsActivity)Application.getInstance().getCurrentActivity();
            addNewsActivity.showLoadingDialog(addNewsActivity.getString(R.string.fetch_forum), addNewsActivity.getString(R.string.wait));
        }
    }

    @Override
    protected Boolean doInBackground(Integer... integers) {
        int id = integers[0];
        this.id = id;
        if(!getForum(id)) {
            if (isSectionActivity) {
                SectionActivity sectionActivity = (SectionActivity) Application.getInstance().getCurrentActivity();
                error = sectionActivity.getString(R.string.forum_failed);
            } else {
                AddNewsActivity addNewsActivity = (AddNewsActivity) Application.getInstance().getCurrentActivity();
                error = addNewsActivity.getString(R.string.forum_failed);
            }
            return false;
        }
        int idF;
        if (isSectionActivity) {
            Module m = (Module) Application.getInstance().getModel().getBean(Costants.MODULE);
            idF = m.getForumByID(id).getId();
        } else {
            idF = id;
        }
        if (!getDiscussion(idF)){
            if (isSectionActivity) {
                SectionActivity sectionActivity = (SectionActivity) Application.getInstance().getCurrentActivity();
                error = sectionActivity.getString(R.string.discussion_failed);
            } else {
                AddNewsActivity addNewsActivity = (AddNewsActivity) Application.getInstance().getCurrentActivity();
                error = addNewsActivity.getString(R.string.discussion_failed);
            }
            return false;
        }
        getPermission(idF);
        return true;
    }

    private void getPermission(int idF){
        String param = "";
        String restUrl = "";
        UserMoodle userMoodle = (UserMoodle) Application.getInstance().getModel().getBean(Costants.USER);
        HttpURLConnection connection;

        try{
            param = "&" + URLEncoder.encode("forumid", "UTF-8") + "="
                    + idF + "&" + URLEncoder.encode("moodlewssettingfilter", "UTF-8") + "=" + URLEncoder.encode("true", "UTF-8");
            restUrl = Costants.URI + "/webservice/rest/server.php?wstoken=" + userMoodle.getToken().getToken()
                    + "&wsfunction=mod_forum_can_add_discussion&moodlewsrestformat=json";
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
            Forum f = (Forum) daoGenericoJson.load(input, Forum.class);
            Forum forum = (Forum) Application.getInstance().getModel().getBean(Costants.FORUM);
            forum.setAddNews(f.canUserAddNews());
            Application.getInstance().getModel().putBean(Costants.FORUM, forum);
            System.out.println(forum.canUserAddNews());
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean getDiscussion(int idF) {
        String param = "";
        String restUrl = "";
        UserMoodle userMoodle = (UserMoodle) Application.getInstance().getModel().getBean(Costants.USER);
        HttpURLConnection connection;

        try {
            param = "&" + URLEncoder.encode("forumid", "UTF-8") + "="
                    + idF + "&" + URLEncoder.encode("moodlewssettingfilter", "UTF-8") + "=" + URLEncoder.encode("true", "UTF-8");
            restUrl = Costants.URI + "/webservice/rest/server.php?wstoken=" + userMoodle.getToken().getToken()
                    + "&wsfunction=mod_forum_get_forum_discussions_paginated&moodlewsrestformat=json";
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
            if (discussion == null){
                return false;
            }
            Module m = (Module) Application.getInstance().getModel().getBean(Costants.MODULE);
            Forum forum = m.getForumByID(m.getId());
            forum.setDiscussion(discussion);
            Application.getInstance().getModel().putBean(Costants.FORUM, forum);
            forum = (Forum) Application.getInstance().getModel().getBean(Costants.FORUM);
            return true;
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (isSectionActivity) {
            SectionActivity sectionActivity = (SectionActivity) Application.getInstance().getCurrentActivity();
            error = sectionActivity.getString(R.string.unknown_error);
        } else {
            AddNewsActivity addNewsActivity = (AddNewsActivity) Application.getInstance().getCurrentActivity();
            error = addNewsActivity.getString(R.string.unknown_error);
        }
        return false;

    }


    private boolean getForum(int id) {
        String param = "";
        String restUrl = "";
        UserMoodle userMoodle = (UserMoodle) Application.getInstance().getModel().getBean(Costants.USER);
        Course course = (Course) Application.getInstance().getModel().getBean(Costants.COURSE);
        HttpURLConnection connection;

        try {
            param = "&" + URLEncoder.encode("courseids[0]", "UTF-8") + "="
                    + course.getId() + "&" + URLEncoder.encode("moodlewssettingfilter", "UTF-8") + "=" + URLEncoder.encode("true", "UTF-8");
            restUrl = Costants.URI + "/webservice/rest/server.php?wstoken=" + userMoodle.getToken().getToken()
                    + "&wsfunction=mod_forum_get_forums_by_courses&moodlewsrestformat=json";
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
            Type tipo = new TypeToken<ArrayList<Forum>>() {
            }.getType();
            ArrayList<Forum> forums = gson.fromJson(reader, tipo);
            if (forums == null){
                return false;
            }
            Module module = (Module) Application.getInstance().getModel().getBean(Costants.MODULE);
            module.setForums(forums);
            Application.getInstance().getModel().putBean(Costants.MODULE, module);
            return true;
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (isSectionActivity) {
            SectionActivity sectionActivity = (SectionActivity) Application.getInstance().getCurrentActivity();
            error = sectionActivity.getString(R.string.unknown_error);
        } else {
            AddNewsActivity addNewsActivity = (AddNewsActivity) Application.getInstance().getCurrentActivity();
            error = addNewsActivity.getString(R.string.unknown_error);
        }
        return false;

    }

    @Override
    protected void onPostExecute(Boolean stato) {
        super.onPostExecute(stato);
        if (isSectionActivity) {
            SectionActivity sectionActivity = (SectionActivity) Application.getInstance().getCurrentActivity();
            sectionActivity.closeLoadingDialog();
        } else {
            AddNewsActivity addNewsActivity = (AddNewsActivity) Application.getInstance().getCurrentActivity();
            addNewsActivity.closeLoadingDialog();
        }
        if (!stato) {
            if (isSectionActivity) {
                SectionActivity sectionActivity = (SectionActivity) Application.getInstance().getCurrentActivity();
                sectionActivity.showErrorDialog(error);
            } else {
                AddNewsActivity addNewsActivity = (AddNewsActivity) Application.getInstance().getCurrentActivity();
                addNewsActivity.showErrorDialog(error);
            }
        } else {
            if (isSectionActivity) {
                SectionActivity sectionActivity = (SectionActivity) Application.getInstance().getCurrentActivity();
                sectionActivity.showForumActivity(id);
            } else {
                AddNewsActivity addNewsActivity = (AddNewsActivity) Application.getInstance().getCurrentActivity();
                addNewsActivity.showForumActivity(id);
            }
        }
    }
}
