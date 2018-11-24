package com.example.giovanni.xcodeiam.View.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.giovanni.xcodeiam.Controller.NetworkUtils;
import com.example.giovanni.xcodeiam.R;

public class ACT_ConfigWebService  extends Activity {
    int LINT_PERMISSION = 21;
    ProgressBar progressBar;
    private ProgressDialog load;
    String LSTR_STATUS;
    Button BTN_TestarConexao;

    EditText ET_IDEQUIP;
    EditText ET_DSEQUIP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act__config_web_service);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        ET_IDEQUIP = (EditText) findViewById(R.id.ET_WebService);
        BTN_TestarConexao = (Button) findViewById(R.id.BT_Login);

        GetJson download = new GetJson();
        //Chama Async Task
        download.execute();
    }

    private class GetJson extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            load = ProgressDialog.show(ACT_ConfigWebService.this, "Por favor Aguarde ...", "Recuperando Informações do Servidor...");
        }

        @Override
        protected String doInBackground(Void... params) {
            //EquipamentoRestClient util = new EquipamentoRestClient();

            //return util.getInformacao("https://randomuser.me/api/0.7");
            //return util.getInformacao("http://localhost:8021/GiclPLibWebAPI/api/equipamento");

            String resposta = NetworkUtils.get("http://192.168.10.124:8021/GiclPLibWebAPI/api/gestoricl/status/8021");

            LSTR_STATUS = resposta;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return LSTR_STATUS;
        }

        @Override
        protected void onPostExecute(String STR_STATUS) {
//            ET_IDEQUIP.setText(pessoa.get_IDEQUIPAMENTO().substring(0,1).toUpperCase()+pessoa.getNome().substring(1));
//            ET_DSEQUIP.setText(pessoa.get_DSEQUIPAMENTO().substring(0,1).toUpperCase()+pessoa.getSobrenome().substring(1));

            ET_IDEQUIP.setText(STR_STATUS);


//            email.setT
// ext(pessoa.getEmail());
//            endereco.setText(pessoa.getEndereco());
//            cidade.setText(pessoa.getCidade().substring(0,1).toUpperCase()+pessoa.getCidade().substring(1));
//            estado.setText(pessoa.getEstado());
//            username.setText(pessoa.getUsername());
//            senha.setText(pessoa.getSenha());
//            nascimento.setText(pessoa.getNascimento());
//            telefone.setText(pessoa.getTelefone());
//            foto.setImageBitmap(pessoa.getFoto());
            load.dismiss();
            if (LSTR_STATUS.equals("true"))
                Toast.makeText(ACT_ConfigWebService.this, "Conexão Satisfatória",
                        Toast.LENGTH_LONG).show();
        }
    }

}