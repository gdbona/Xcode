package com.example.giovanni.xcodeiam.View.Activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.giovanni.xcodeiam.Controller.clsLogon;
import com.example.giovanni.xcodeiam.MainActivity;
import com.example.giovanni.xcodeiam.R;
import com.example.giovanni.xcodeiam.View.Fragment.fgm_webservice;

import static android.app.PendingIntent.getActivity;

public class ACT_Login extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
    }

    @SuppressWarnings("deprecation")
    public void OnClickEntrar(View v) {
        Toast.makeText(getApplicationContext(), "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show();

        clsLogon.clsLogonInt.BOLLOGON = true;

        finish();
    }

    public void onClick_WebService(View v) {
        FragmentManager fragmentManager = getFragmentManager ();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction ();

        fgm_webservice myfragment = new fgm_webservice();  //your fragment

        // work here to add, remove, etc
//        fragmentTransaction.add (R.id.CONST_Layout, myfragment);
        fragmentTransaction.commit ();
    }

}
