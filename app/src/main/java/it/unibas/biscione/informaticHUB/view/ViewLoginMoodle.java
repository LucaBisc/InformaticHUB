package it.unibas.biscione.informaticHUB.view;

import android.graphics.Paint;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.R;

public class ViewLoginMoodle extends Fragment {

    public static final String TAG = ViewLoginMoodle.class.getSimpleName();

    private EditText textUser;
    private EditText textPw;
    private Button btnLogin;
    private TextView txtRedirect;



    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG,TAG + "created.");
        View view = inflater.inflate(R.layout.view_login_moodle, container,false);
        textUser = (EditText) view.findViewById(R.id.eTUser);
        textPw = (EditText) view.findViewById(R.id.eTPw);
        btnLogin = (Button) view.findViewById(R.id.buttonLogin);
        txtRedirect = (TextView) view.findViewById(R.id.txtRedirect);
        txtRedirect.setPaintFlags(txtRedirect.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        inizializeAction();
        return view;
    }

    private void inizializeAction() {
        btnLogin.setOnClickListener(Application.getInstance().getControlLoginMoodle().getActionLogin());
        txtRedirect.setOnClickListener(Application.getInstance().getControlLoginMoodle().getActionPw());
    }

    public String getUsername(){
        return textUser.getText().toString();
    }

    public String getPw(){
        return textPw.getText().toString();
    }

    public void showUserError(String s) {
        textUser.setError(s);
    }

    public void showPWError(String s) {
        textPw.setError(s);
    }

    public void setTextUser(String user){
        textUser.setText(user);
    }

    public void setTextPW(String pw){
        textPw.setText(pw);
    }
}
