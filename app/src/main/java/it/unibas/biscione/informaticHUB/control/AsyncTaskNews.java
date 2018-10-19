package it.unibas.biscione.informaticHUB.control;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import it.unibas.biscione.informaticHUB.activity.MainActivity;
import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.Costants;

public class AsyncTaskNews extends AsyncTask<Void, String, String> {

    private static final String TAG = AsyncTaskNews.class.getName();

    MainActivity mainActivity = (MainActivity) Application.getInstance().getCurrentActivity();
    private String error = "";

    public AsyncResponse delegate = null;

    public interface AsyncResponse {
        void processFinish(String output);
    }

    public AsyncTaskNews(AsyncResponse delegate){
        this.delegate = delegate;
    }


    @Override
    protected String doInBackground(Void... voids) {
        String urlNews = Costants.URI_NEWS;
        HttpURLConnection connection;
        try {
            connection = (HttpURLConnection) new URL(urlNews).openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            String result = "";
            InputStream in = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            int data = reader.read();
            while(data != -1){
                char cur = (char)data;
                result += cur;
                data = reader.read();
            }
            return result;
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String  news) {
        delegate.processFinish(news);
    }


    private String stampaJson(HttpURLConnection connessione) throws IOException {
        //Test json
        String result = "";
        InputStream in = connessione.getInputStream();
        InputStreamReader reader = new InputStreamReader(in);


        int data = reader.read();

        while(data != -1){
            char cur = (char)data;
            result += cur;
            data = reader.read();
        }
        return result;
    }
}
