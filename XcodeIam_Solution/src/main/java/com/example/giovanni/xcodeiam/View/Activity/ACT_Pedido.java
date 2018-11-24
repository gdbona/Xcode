package com.example.giovanni.xcodeiam.View.Activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.giovanni.xcodeiam.R;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class ACT_Pedido extends Activity implements TextToSpeech.OnInitListener {

    //Variaveis para converter texto para voz
    private Button   BTN_FalarTexto;
    private EditText EDT_TextoEntrada;
    private TextToSpeech TTS_ConversorVoz;
    //Variaveis para converter voz para texto
    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private TextView TextoCapturado;
    private Button BTN_Speak;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pedido);

        //Logica para pegar texto para voz
        TTS_ConversorVoz = new TextToSpeech(this, this);

        BTN_FalarTexto = (Button) findViewById(R.id.BTN_FalarTexto);

        EDT_TextoEntrada = (EditText) findViewById(R.id.EDT_TextoEntrada);


        BTN_FalarTexto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ConvertendoTextoParaVoz(EDT_TextoEntrada.getText().toString());

            }
        });

        TextoCapturado = (TextView) findViewById(R.id.TextoCapturado);
        BTN_Speak = (Button) findViewById(R.id.BTN_Speak);
        BTN_Speak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startVoiceInput();
            }
        });
    }

    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Olá, Como posso ajudar você?");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    TextoCapturado.setText(result.get(0));
                }
                break;
            }

        }
    }

    @Override
    public void onDestroy() {

        if (TTS_ConversorVoz != null) {
            TTS_ConversorVoz.stop();
            TTS_ConversorVoz.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            int result = TTS_ConversorVoz.setLanguage(Locale.getDefault());
            // definindo o idioma padrão do celular poderia ser um idioma específico(ex:Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                //verifica se o aparelho possui suporte ao pacote de voz
                Log.e("TTS", "Não tem suporte para este idioma");
                BTN_FalarTexto.setEnabled(false);
            } else {
                BTN_FalarTexto.setEnabled(true);

                ConvertendoTextoParaVoz("Seja Bem Vindo ao Picking by voice");
            }

        } else {
            Log.e("TTS", "Falha na inicialização!");
        }

    }

    private void ConvertendoTextoParaVoz( String Texto) {

        TTS_ConversorVoz.setPitch(0); // Afinação da Voz
        TTS_ConversorVoz.setSpeechRate(0);//Velocidade da Voz
        TTS_ConversorVoz.speak(Texto, TextToSpeech.QUEUE_FLUSH, null);
    }
}
