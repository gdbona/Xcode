package com.example.giovanni.xcodeiam;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import static android.app.PendingIntent.getActivity;

public class ACT_Login extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act__login);
    }

    @SuppressWarnings("deprecation")
    public void OnClickCancelar(View view) {

//        onDestroy();

//        Intent IMain = new Intent(ACT_Login.this, MainActivity.class);
//        IMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        IMain.putExtra("EXIT", true);
//        startActivity(IMain);

        System.exit(0);
    }

    @SuppressWarnings("deprecation")
    public void OnClickEntrar(View v) {
        Toast.makeText(getApplicationContext(), "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show();

        clsLogon.clsLogonInt.BOLLOGON = true;

        finish();
    }

}
