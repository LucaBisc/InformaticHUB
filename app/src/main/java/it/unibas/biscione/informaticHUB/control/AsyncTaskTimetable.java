package it.unibas.biscione.informaticHUB.control;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Calendar;

import it.unibas.biscione.informaticHUB.persistence.DAOGenericoJson;
import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.Costants;
import it.unibas.biscione.informaticHUB.R;
import it.unibas.biscione.informaticHUB.model.Archive;

public class AsyncTaskTimetable extends AsyncTask<Void, String, Boolean> {

    private static final String TAG = AsyncTaskTimetable.class.getName();


    private String error = "";

    @Override
    protected Boolean doInBackground(Void... voids) {
        String url = generateUrl();
        if(!downloadTimetable(url)) {
            return false;
        }
        return true;
    }

    private Boolean downloadTimetable(String url) {
        HttpURLConnection connection;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            InputStream input = connection.getInputStream();
            DAOGenericoJson daoGenericoJson = new DAOGenericoJson();
            Archive cohorts = (Archive) daoGenericoJson.load(input, Archive.class);
            if (cohorts == null){
                return false;
            }
            Archive archive = (Archive) Application.getInstance().getModel().getBean(Costants.ARCHIVE);
            if (archive == null) {
                Application.getInstance().getModel().putBean(Costants.ARCHIVE, cohorts);
            } else {
                archive.setCohorts(cohorts.getCohorts());
                Application.getInstance().getModel().putBean(Costants.ARCHIVE, archive);
            }
            Log.d(TAG, "" + ((Archive) Application.getInstance().getModel().getBean(Costants.ARCHIVE)).getCohorts().size());
            return true;
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            error = Application.getInstance().getCurrentActivity().getString(R.string.file_not_available);
        }
        return false;
    }

    private String generateUrl(){
        String url = "http://www.informatica.unibas.it/informatica/documenti/orario/";
        Calendar date = Calendar.getInstance();
        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH);
        String semester = "";
        String yearUrl = "";
        if (month > 7 ){
            yearUrl += "" + year + "-" + (year+1);
        } else {
            yearUrl += "" + (year-1) + "-" + year;
        }
        if (month < 2 || month > 7){
            semester += "/ISemestre";
        } else {
            semester += "/IISemestre";
        }
        return url + yearUrl + semester + "/orario.json";
    }

    @Override
    protected void onPostExecute(Boolean state) {
        super.onPostExecute(state);
        if (! state){
            Toast.makeText(Application.getInstance().getCurrentActivity(), error, Toast.LENGTH_LONG).show();
        }
    }
}
