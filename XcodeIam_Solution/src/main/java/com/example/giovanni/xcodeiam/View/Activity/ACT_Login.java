package com.example.giovanni.xcodeiam.View.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.giovanni.xcodeiam.R;

import static android.app.PendingIntent.getActivity;

public class ACT_Login extends Activity {

    Button btn_login=null;
    TextView tve_webservice = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);

        btn_login = (Button) findViewById(R.id.BTN_Entrar);
        tve_webservice = (TextView) findViewById(R.id.TV_WebService);


        btn_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // aqui voce faz alguma coisa ou pode chamar uma funcao
//                Intent intent = new Intent(ACT_Login.this, ACT_ListaEquipamento.class);
//                startActivity(intent);


            }
        });

        tve_webservice.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                // aqui voce faz alguma coisa ou pode chamar uma funcao
                Intent intent = new Intent(ACT_Login.this, ACT_Configuracao.class);
                startActivity(intent);


            }
        });
    }

    @SuppressWarnings("deprecation")
    public void OnClickEntrar(View v) {
        Toast.makeText(getApplicationContext(), "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show();

        //clsLogon.clsLogonInt.BOLLOGON = true;

        finish();
    }

    public void onClick_WebService(View v) {
        try
        {
        //Intent intent = new Intent(this, ACT_Configuracao.class);
        //startActivity(intent);

        }
        catch (Exception ex)
        {
            Toast.makeText(this, "Erro ao abrir ACT_Configuracao", Toast.LENGTH_SHORT).show();
        }
    }

}
