package com.example.giovanni.xcodeiam.View.Activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.giovanni.xcodeiam.BuildConfig;
import com.example.giovanni.xcodeiam.Controller.CHECKLISTMESTREController;
import com.example.giovanni.xcodeiam.Controller.ClasseUtil;
import com.example.giovanni.xcodeiam.Controller.ClsAutenticacao;
import com.example.giovanni.xcodeiam.Controller.ConexaoWebAPI;
import com.example.giovanni.xcodeiam.Controller.PARAMETROSController;
import com.example.giovanni.xcodeiam.Controller.clsLogon;
import com.example.giovanni.xcodeiam.MainActivity;
import com.example.giovanni.xcodeiam.Model.EMFSESSION;
import com.example.giovanni.xcodeiam.R;
import com.example.giovanni.xcodeiam.View.Fragment.fgm_webservice;

import java.io.IOException;

import static android.app.PendingIntent.getActivity;

public class ACT_Login extends Activity {
    Button BT_Login;
    EditText ET_Usuario;
    EditText ET_Senha;
    TextView TV_Webservice;
    Switch SW_Salvar;
    Cursor LCU_Cursor;
    String LSTR_MENSAGEM;
    ProgressBar progressBar;
    private ProgressDialog load;
    String LSTR_STATUS;
    PARAMETROSController LCLS_PARAMETROSController;
    EMFSESSION LCLS_EMFSESSION = null;
    ClasseUtil LCLS_UTIL = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);

        BT_Login = (Button) findViewById(R.id.BT_Login);
        ET_Usuario = (EditText) findViewById(R.id.ET_Usuario);
        ET_Senha = (EditText) findViewById(R.id.ET_Senha);
        TV_Webservice = (TextView) findViewById(R.id.TV_WebService);
        SW_Salvar = (Switch) findViewById(R.id.SW_Salvar);


        LCLS_EMFSESSION = new EMFSESSION(null);
        LCLS_UTIL = new ClasseUtil();
        PARAMETROSController.contexts = getBaseContext();
        LCLS_PARAMETROSController = new PARAMETROSController(getBaseContext());

        //Buscar parametros já registrados
        LCU_Cursor = LCLS_PARAMETROSController.FU_Read_BD();
        SW_Salvar.setChecked(false);

        if (LCU_Cursor.getCount() > 0) {
            //PARAMETROS.setPstrEnderecowebapi(LCU_Cursor.getString(LCU_Cursor.getColumnIndex("ENDERECOWEBAPI")).trim());
            EMFSESSION.LOCAL_NMUSUARIO = LCU_Cursor.getString(LCU_Cursor.getColumnIndex("NMUSUARIO")).trim();
            ET_Usuario.setText(EMFSESSION.LOCAL_NMUSUARIO.toUpperCase());
            if (!EMFSESSION.LOCAL_NMUSUARIO.equals(""))
                SW_Salvar.setChecked(true);
        } else {
            Intent intent = new Intent(getApplicationContext(), ACT_Configuracao.class);
            startActivity(intent);

        }

        if (BuildConfig.DEBUG) {
            //ET_Usuario.setText("MASTER");
            ET_Senha.setText("MTM");

            LCLS_EMFSESSION.setSGUSER("MASTER");
            LCLS_EMFSESSION.setPASSWORD("MTM");
            LCLS_EMFSESSION.setSGENVIRONMENT("GESTOR");
            LCLS_EMFSESSION.setSGLANGUAGE("PT-BR");
            try {
                LCLS_EMFSESSION.setDHSESSION(LCLS_UTIL.FU_DataAtual());
            } catch (Exception e) {
                e.printStackTrace();
            }
            LCLS_EMFSESSION.setEQUIPMENT(Build.MODEL);
//            DATABASE f = new DATABASE(getBaseContext());
//            DATABASE.FU_Monta_Create_Objeto(LCLS_EMFSESSION);

            CHECKLISTMESTREController.contexts = getBaseContext();
            // FU_chamarWebservice();

        }

        BT_Login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (ET_Usuario.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Falta preencher Login", Toast.LENGTH_LONG).show();
                    return;
                }
                if (ET_Senha.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Falta preencher Senha", Toast.LENGTH_LONG).show();
                    return;
                }

                LCLS_EMFSESSION.setSGUSER(ET_Usuario.getText().toString());
                LCLS_EMFSESSION.setPASSWORD(ET_Senha.getText().toString());
                LCLS_EMFSESSION.setSGENVIRONMENT("GESTOR");
                LCLS_EMFSESSION.setSGLANGUAGE("PT-BR");
                LCLS_EMFSESSION.setDHSESSION("0");
                LCLS_EMFSESSION.setEQUIPMENT(Build.MODEL);

                EMFSESSION.LOCAL_NMUSUARIO = ET_Usuario.getText().toString();
                FU_chamarWebservice();
            }
        });

        TV_Webservice.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // aqui voce faz alguma coisa ou pode chamar uma funcao
                Intent intent = new Intent(getApplicationContext(), ACT_Configuracao.class);
                startActivity(intent);
            }
        });

        SW_Salvar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                if (isChecked) {
                    EMFSESSION.LOCAL_NMUSUARIO = ET_Usuario.getText().toString();
                    LCLS_PARAMETROSController.FU_Update_BD(0);
                } else {
                    //do something when unchecked
                    EMFSESSION.LOCAL_NMUSUARIO = "";
                    LCLS_PARAMETROSController.FU_Update_BD(0);
                }
            }
        });
    }

    public String FU_chamarWebservice() {
        Autenticacao autentica = new Autenticacao();
        //Chama Async Task
        autentica.execute();
        return LSTR_STATUS;
    }


    public void onClick_Sair(View view) {
        System.exit(0);
    }

    public void onClick_WebService(View view) {
        Intent intent = new Intent(ACT_Login.this, ACT_ConfigWebService.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    private class Autenticacao extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            load = ProgressDialog.show(ACT_Login.this, "Por favor Aguarde ...", LSTR_MENSAGEM);
        }

        @Override
        protected String doInBackground(Void... params) {
            LSTR_MENSAGEM = "Verificando Conexão a internet";
            runOnUiThread(changeText);

            ClasseUtil util = new ClasseUtil();
            if (util.verificaConexao(ACT_Login.this)) {
                LSTR_MENSAGEM = "Conectado!";
            } else {
                LSTR_MENSAGEM = "Não conectado, Trabalhando Offiline";
                runOnUiThread(changeText);
                //return LSTR_STATUS = "Não conectado";
            }
            runOnUiThread(changeText);
            LSTR_MENSAGEM = "Verificando tipo Conexão";
            runOnUiThread(changeText);

            LSTR_MENSAGEM = util.FU_verificaTipoConexao(ACT_Login.this);
            if (LSTR_MENSAGEM == "?") {
                LSTR_MENSAGEM = "WIFI";
            }
            runOnUiThread(changeText);

            try {
                //DESMARCADO PARA TESTE, DEPOIS REMARCAR NOVAMENTE
                LSTR_MENSAGEM = ConexaoWebAPI.FU_WB_TestaConexao();
                if (LSTR_MENSAGEM != "true") {
                    runOnUiThread(changeText);
                    LSTR_STATUS = LSTR_MENSAGEM;
                    return LSTR_STATUS;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            LSTR_MENSAGEM = "Autenticando";
            runOnUiThread(changeText);
            LSTR_STATUS = ClsAutenticacao.FU_AutenticaUsuario(LCLS_EMFSESSION);
            return LSTR_STATUS;
        }

        @Override
        protected void onPostExecute(String STR_STATUS) {
            load.dismiss();

            if (LSTR_STATUS.equals("true")) {
                Toast.makeText(getApplicationContext(), "Autenticado", Toast.LENGTH_LONG).show();
                finish();
            } else if (LSTR_STATUS.toUpperCase().contains("EXCEPTION"))
                Toast.makeText(getApplicationContext(), LSTR_STATUS, Toast.LENGTH_LONG).show();
            else {
                Toast.makeText(getApplicationContext(), LSTR_STATUS, Toast.LENGTH_LONG).show();
            }
        }

        private Runnable changeText = new Runnable() {
            @Override
            public void run() {
                load.setMessage(LSTR_MENSAGEM);
            }
        };

    }

}
