package it.unibas.biscione.informaticHUB.control;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.webkit.MimeTypeMap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import it.unibas.biscione.informaticHUB.activity.ContentActivity;
import it.unibas.biscione.informaticHUB.activity.SectionActivity;
import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.Costants;
import it.unibas.biscione.informaticHUB.R;
import it.unibas.biscione.informaticHUB.model.Content;
import it.unibas.biscione.informaticHUB.model.UserMoodle;

public class AsyncTaskDownload extends AsyncTask<Content, String, Boolean> {

    private static final String TAG = AsyncTaskDownload.class.getName();

    private Content c;

    private boolean isContentActivity;
    private String error = "";

    public AsyncTaskDownload(boolean bool) {
        this.isContentActivity = bool;
    }

    @Override
    protected void onPreExecute() {
        if (isContentActivity) {
            ContentActivity contentActivity = (ContentActivity) Application.getInstance().getCurrentActivity();
            contentActivity.showLoadingDialog(contentActivity.getString(R.string.wait), contentActivity.getString(R.string.download_file));
        } else {
            SectionActivity sectionActivity = (SectionActivity) Application.getInstance().getCurrentActivity();
            sectionActivity.showLoadingDialog(sectionActivity.getString(R.string.download_file), sectionActivity.getString(R.string.wait));
        }
    }


    @Override
    protected Boolean doInBackground(Content... contents) {
        c = contents[0];
        String url = c.getFileurl();
        String filename = c.getFilename();
        UserMoodle userMoodle = (UserMoodle) Application.getInstance().getModel().getBean(Costants.USER);
        File file;
        if (isContentActivity) {
            ContentActivity contentActivity = (ContentActivity) Application.getInstance().getCurrentActivity();
            file = new File(contentActivity.getFilesDir() + "/" + c.getFilename());
        } else {
            SectionActivity sectionActivity = (SectionActivity) Application.getInstance().getCurrentActivity();
            file = new File(sectionActivity.getFilesDir() + "/" + c.getFilename());
        }
        if (file.exists()){
            return true;
        }
        if (!downloadFile(url, userMoodle, filename)) {
            SectionActivity sectionActivity = (SectionActivity) Application.getInstance().getCurrentActivity();
            error = sectionActivity.getString(R.string.problem_download);
            return false;
        }
        return true;
    }

    private boolean downloadFile(String url, UserMoodle userMoodle, String filename) {
        String param = "";
        HttpURLConnection connection;
        File file;

        try {
            param = "&" + URLEncoder.encode("token", "UTF-8") + "="
                    + userMoodle.getToken().getToken() + "&" + URLEncoder.encode("offline", "UTF-8")
                    +  "=1";
            url += param;
        } catch (UnsupportedEncodingException e) {
            Log.d(TAG,"Codifica url non riuscita.");
            e.printStackTrace();
        }
        try {
            URL adress = new URL(url);
            connection = (HttpURLConnection) adress.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            if (isContentActivity) {
                ContentActivity contentActivity = (ContentActivity) Application.getInstance().getCurrentActivity();
                file = new File(contentActivity.getFilesDir(), filename);
            } else {
                SectionActivity sectionActivity = (SectionActivity) Application.getInstance().getCurrentActivity();
                file = new File(sectionActivity.getFilesDir(), filename);
            }
            if (!file.exists()) {
                file.createNewFile();
                Log.e(TAG, "File Created");
            }
            FileOutputStream fos = new FileOutputStream(file);
            InputStream is = connection.getInputStream();
            byte[] buffer = new byte[1024];
            int len1 = 0;
            while ((len1 = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len1);
            }
            fos.close();
            is.close();
            fos.close();
            is.close();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
             return false;
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean state) {
        super.onPostExecute(state);
        if (isContentActivity){
            ContentActivity contentActivity = (ContentActivity) Application.getInstance().getCurrentActivity();
            contentActivity.closeLoadingDialog();
            if (!state) {
                contentActivity.showErrorDialog(error);
            } else {
                File file = new File(contentActivity.getFilesDir() + "/" + c.getFilename());
                MimeTypeMap map = MimeTypeMap.getSingleton();
                String ext = MimeTypeMap.getFileExtensionFromUrl(file.getName());
                String type = map.getMimeTypeFromExtension(ext);
                if (type == null) {
                    type = "*/*";
                }
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri data = FileProvider.getUriForFile(contentActivity, contentActivity.getApplicationContext().getPackageName() + ".persistenza", file);
                intent.setDataAndType(data, type);
                intent.addFlags(intent.FLAG_GRANT_READ_URI_PERMISSION);
                try {
                    contentActivity.startActivity(intent);
                } catch (ActivityNotFoundException ex) {
                    contentActivity.showErrorDialog(contentActivity.getString(R.string.no_find_app));
                    Log.e("Error: ", ex.getMessage());
                }
            }
        } else {
            SectionActivity sectionActivity = (SectionActivity) Application.getInstance().getCurrentActivity();
            sectionActivity.closeLoadingDialog();
            if (!state) {
                sectionActivity.showErrorDialog(error);
            } else {
                File file = new File(sectionActivity.getFilesDir() + "/" + c.getFilename());
                MimeTypeMap map = MimeTypeMap.getSingleton();
                String ext = MimeTypeMap.getFileExtensionFromUrl(file.getName());
                String type = map.getMimeTypeFromExtension(ext);
                if (type == null) {
                    type = "*/*";
                }
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri data = FileProvider.getUriForFile(sectionActivity, sectionActivity.getApplicationContext().getPackageName() + ".persistenza", file);
                intent.setDataAndType(data, type);
                intent.addFlags(intent.FLAG_GRANT_READ_URI_PERMISSION);
                try {
                    sectionActivity.startActivity(intent);
                } catch (ActivityNotFoundException ex) {
                    sectionActivity.showErrorDialog(sectionActivity.getString(R.string.no_find_app));
                    Log.e("Error: ", ex.getMessage());
                }
            }
        }
    }
}
