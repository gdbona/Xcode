package com.example.giovanni.xcodeiam;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.widget.TextView;

import com.example.giovanni.xcodeiam.View.Activity.ACT_Pedido;

import java.util.ArrayList;
import java.util.Locale;

public class SpeechToText implements RecognitionListener {

    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private ACT_Pedido act_Pedido;
    private String text = "";
    private int IDText = 0;
    private boolean isListening = false;
    private int MINIMUM_LENGTH_FOR_EXTRA_SPEECH_IN_MILLIS = 3000;
    public final BackgroundVoiceListener backgroundVoiceListener;
    public TextView subtitle;

    public boolean isListening() {
        return isListening;
    }

    public void setListening(boolean listening) {
        isListening = listening;
    }

    public SpeechToText(){
        act_Pedido = new ACT_Pedido();
        subtitle = act_Pedido.getSubtitle();
        backgroundVoiceListener = new BackgroundVoiceListener();
        speech = SpeechRecognizer.createSpeechRecognizer(act_Pedido.getContext());
        speech.setRecognitionListener(this);
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, Locale.getDefault());
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, act_Pedido.getPackageName());
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS, MINIMUM_LENGTH_FOR_EXTRA_SPEECH_IN_MILLIS);

    }

    @Override
    public void onReadyForSpeech(Bundle bundle) {
        setListening(false);
    }

    @Override
    public void onBeginningOfSpeech() {
        setListening(true);
    }

    @Override
    public void onRmsChanged(float v) {
        Log.i("Text", "onRmsChanged: " + v);

    }

    @Override
    public void onBufferReceived(byte[] bytes) {

    }

    @Override
    public void onEndOfSpeech() {
        setListening(false);
    }

    @Override
    public void onError(int i) {
        act_Pedido.setsubtitle(text);
        Log.i("Text","text: " + text);

    }

    @Override
    public void onResults(Bundle bundle) {

    }

    @Override
    public void onPartialResults(Bundle partialResults) {
        ArrayList matches = partialResults
                .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        text = "";
        Log.i("Text","text: " + text);
        if(matches!=null)
            for (Object result : matches)
                text += result + "\n";
        Log.i("Text","text: " + text);
        act_Pedido.setsubtitle(text);
        setListening(false);

    }

    @Override
    public void onEvent(int i, Bundle bundle) {

    }

    public class BackgroundVoiceListener extends Thread{
        public void run(){
            try {
                this.sleep(2000);
                Log.i("Text","islistening: " + isListening());
                if(!isListening()){
                    setListening(true);
                    speech.startListening(recognizerIntent);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}