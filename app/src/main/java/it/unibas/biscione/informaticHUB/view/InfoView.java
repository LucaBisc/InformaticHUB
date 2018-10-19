package it.unibas.biscione.informaticHUB.view;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import it.unibas.biscione.informaticHUB.model.UserEsse3;
import it.unibas.biscione.informaticHUB.model.UserMoodle;
import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.Costants;
import it.unibas.biscione.informaticHUB.R;

public class InfoView extends Fragment {

    private static final String TAG = InfoView.class.getName();

    private TextView txtTitle;
    private TextView txtName;
    private TextView txtSurname;
    private TextView txtNumber;
    private TextView labelNumber;
    private Button btnLogout;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, TAG + "created.");
        View view = inflater.inflate(R.layout.view_info,container,false);
        txtTitle = view.findViewById(R.id.txtTitle);
        txtName = view.findViewById(R.id.txtName);
        txtSurname = view.findViewById(R.id.txtSurname);
        txtNumber = view.findViewById(R.id.txtIDNumber);
        btnLogout = view.findViewById(R.id.btnLogout);
        labelNumber = view.findViewById(R.id.labelNumber);
        this.setView();
        inizializeAction();
        return view;
    }

    private void inizializeAction() {
        btnLogout.setOnClickListener(Application.getInstance().getControlInfo().getActionLogout());
    }

    private void setView() {
        UserMoodle userMoodle = (UserMoodle) Application.getInstance().getModel().getBean(Costants.USER);
        UserEsse3 userEsse3 = (UserEsse3) Application.getInstance().getModel().getBean(Costants.USER_ESSE3);
        if (userEsse3 == null) {
            txtNumber.setVisibility(View.INVISIBLE);
            labelNumber.setVisibility(View.INVISIBLE);
            txtName.setText(userMoodle.getSiteInfo().getName());
            txtSurname.setText(userMoodle.getSiteInfo().getSurname());
        } else {
            txtNumber.setText(userEsse3.getIDNumber());
            txtName.setText(userEsse3.getName());
            txtSurname.setText(userEsse3.getSurname());
        }
    }
}
