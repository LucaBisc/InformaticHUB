package it.unibas.biscione.informaticHUB.control;

import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import it.unibas.biscione.informaticHUB.activity.LoginEsse3Activity;
import it.unibas.biscione.informaticHUB.model.Exam;
import it.unibas.biscione.informaticHUB.persistence.DAOGenericoJson;
import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.Costants;
import it.unibas.biscione.informaticHUB.R;
import it.unibas.biscione.informaticHUB.model.UserEsse3;

public class AsyncTaskLoginEsse3 extends AsyncTask<Void, String, Boolean> {

    public static final String TAG = AsyncTaskLoginEsse3.class.getSimpleName();

    private UserEsse3 userEsse3 = new UserEsse3();
    private LoginEsse3Activity loginEsse3Activity = (LoginEsse3Activity) Application.getInstance().getCurrentActivity();
    private String error = loginEsse3Activity.getString(R.string.unknown_error);


    public AsyncTaskLoginEsse3(UserEsse3 userEsse3) {
        this.userEsse3 = userEsse3;
    }

    @Override
    protected void onPreExecute() {
        loginEsse3Activity.showLoadingDialog(Application.getInstance().getCurrentActivity().getString(R.string.login_esse3), Application.getInstance().getCurrentActivity().getString(R.string.wait));
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        if (!login()){
            return false;
        }
        if (!getExams()){
            return false;
        }
        Application.getInstance().getModel().putBean(Costants.USER_ESSE3, userEsse3);
        return true;
    }

    private boolean getExams() {
        int param = userEsse3.getDetails().getCareers().get(0).getMatId();
        String parameter;
        HttpURLConnection connection;
        try{
            connection = (HttpURLConnection) new URL(Costants.URIESSE3 + "libretto-service-v1/libretti/" + param +"/righe/").openConnection();
            parameter = userEsse3.getUsername() + ":" + userEsse3.getPassword();
            String basic = "Basic " + new String(Base64.encode(parameter.getBytes(StandardCharsets.UTF_8), Base64.DEFAULT));
            connection.setRequestProperty("User-Agent", "MyAgent");
            connection.setConnectTimeout(30000);
            connection.setReadTimeout(30000);
            connection.addRequestProperty ("Authorization", basic);
            connection.setRequestMethod("GET");
            connection.addRequestProperty("Content-Type", "application/json");
            connection.setUseCaches(false);
            connection.connect();
            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            Gson gson = new GsonBuilder().create();
            Type tipo = new TypeToken<ArrayList<Exam>>() {
            }.getType();
            ArrayList<Exam> exams = gson.fromJson(reader, tipo);
            if (exams == null){
                error = loginEsse3Activity.getString(R.string.exam_error);
                return false;
            }
            userEsse3.setExams(exams);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean login() {
        String param;
        HttpURLConnection connection;
        try {
            connection = (HttpURLConnection) new URL(Costants.URIESSE3 + "login").openConnection();
            param = userEsse3.getUsername() + ":" + userEsse3.getPassword();
            String basic = "Basic " + new String(Base64.encode(param.getBytes(StandardCharsets.UTF_8), Base64.DEFAULT));
            connection.setRequestProperty("User-Agent", "MyAgent");
            connection.setConnectTimeout(30000);
            connection.setReadTimeout(30000);
            connection.addRequestProperty ("Authorization", basic);
            connection.setRequestMethod("GET");
            connection.addRequestProperty("Content-Type", "application/json");
            connection.setUseCaches(false);
            connection.connect();
            InputStream input = connection.getInputStream();
            DAOGenericoJson daoGenericoJson = new DAOGenericoJson();
            UserEsse3 user = (UserEsse3) daoGenericoJson.load(input, UserEsse3.class);
            if (user == null){
                error = loginEsse3Activity.getString(R.string.login_failed);
                return false;
            }
            user.setPassword(userEsse3.getPassword());
            user.setUsername(userEsse3.getUsername());
            userEsse3 = user;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "" + e.getLocalizedMessage());
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean stato) {
        super.onPostExecute(stato);
        loginEsse3Activity.closeLoadingDialog();
        if (!stato) {
            loginEsse3Activity.showErrorDialog(error);
        } else {
            loginEsse3Activity.showExamActivity();
        }
    }


}
