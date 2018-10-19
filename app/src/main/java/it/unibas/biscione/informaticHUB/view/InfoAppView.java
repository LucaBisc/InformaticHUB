package it.unibas.biscione.informaticHUB.view;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.unibas.biscione.informaticHUB.R;

public class InfoAppView extends Fragment {

    private static final String TAG = InfoAppView.class.getName();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, TAG + "created.");
        View view = inflater.inflate(R.layout.view_app_info, container, false);
        return view;
    }

}
