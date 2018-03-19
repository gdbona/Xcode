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
        try
        {
        Intent intent = new Intent(this, ACT_Configuracao.class);
        startActivity(intent);
        }
        catch (Exception ex)
        {
            Toast.makeText(this, "Erro ao abrir ACT_Configuracao", Toast.LENGTH_SHORT).show();
        }
    }

}
