package com.example.giovanni.xcodeiam.View.Activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.giovanni.xcodeiam.R;
import com.example.giovanni.xcodeiam.View.Fragment.fgm_webservice;

import java.util.ArrayList;

public class ACT_Configuracao extends Activity {

    private ArrayList<String> series = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.FRL_Fragment, new fgm_webservice());
        fragmentTransaction.commit();
    }
}
