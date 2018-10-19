package it.unibas.biscione.informaticHUB.persistence;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DAOGenericoJson {

    private static String TAG = DAOGenericoJson.class.getSimpleName();


    public String toJson(Object object){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        return gson.toJson(object);
    }

    public Object load(InputStream inputStream, Class t) throws DAOException {
        Object object = null;
        Reader flow = null;
        try {
            flow = new InputStreamReader(inputStream);
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            object = gson.fromJson(flow, t);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException(e);
        } finally {
            try {
                if (flow != null) {
                    flow.close();
                }
            } catch (java.io.IOException ioe) {
            }
        }
        return object;
    }

    public void salva(Object oggetto, OutputStream out) throws DAOException {
        PrintWriter flow = null;
        try {
            flow = new java.io.PrintWriter(out);
            String stringaJson = toJson(oggetto);
            flow.print(stringaJson);
        } catch (Exception ioe) {
            throw new DAOException(ioe);
        } finally {
            if (flow != null) {
                flow.close();
            }
        }

    }
}
