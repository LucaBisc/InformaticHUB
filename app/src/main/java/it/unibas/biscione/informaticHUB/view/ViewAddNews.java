package it.unibas.biscione.informaticHUB.view;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.R;

public class ViewAddNews extends Fragment {

    public static final String TAG = ViewAddNews.class.getSimpleName();

    private EditText txtTitle;
    private EditText txtMessage;
    private FloatingActionButton btnSend;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG,TAG + "created.");
        View view = inflater.inflate(R.layout.view_add_news, container,false);
        txtTitle = view.findViewById(R.id.txtTitle);
        txtMessage = view.findViewById(R.id.txtMessage);
        btnSend = view.findViewById(R.id.btnSend);
        inizializeAction();
        return view;
    }

    private void inizializeAction() {
        btnSend.setOnClickListener(Application.getInstance().getControlAddNews().getActionAddNews());
    }

    public String getTitle(){
        return txtTitle.getText().toString();
    }

    public String getMessage(){
        return txtMessage.getText().toString();
    }

    public void showTitleError(String s) {
        txtTitle.setError(s);
    }

    public void showMessageError(String s) {
        txtMessage.setError(s);
    }
}
