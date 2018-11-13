package com.example.giovanni.xcodeiam.Controller;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
public class NetworkUtils {

    //Responsavel por carregar o Objeto JSON
    public static String getJSONFromAPI(String STR_url){
        String LSTR_RETORNO = "";
        URL LURL_ENDERECOAPI = null;
        int INT_codigoResposta;
        HttpURLConnection LHUC_CONEXAO;
        InputStream LISM_STREAM;

        try {
            LURL_ENDERECOAPI = new URL(STR_url);
            LHUC_CONEXAO = (HttpURLConnection) LURL_ENDERECOAPI.openConnection();
            LHUC_CONEXAO.setRequestMethod("GET");
            LHUC_CONEXAO.setReadTimeout(115000);
            LHUC_CONEXAO.setConnectTimeout(115000);
            LHUC_CONEXAO.connect();

            INT_codigoResposta = LHUC_CONEXAO.getResponseCode();
            if(INT_codigoResposta < HttpURLConnection.HTTP_BAD_REQUEST){
                LISM_STREAM = LHUC_CONEXAO.getInputStream();
            }else{
                LISM_STREAM = LHUC_CONEXAO.getErrorStream();
            }
            LSTR_RETORNO = converterInputStreamToString(LISM_STREAM);
            LISM_STREAM.close();
            LHUC_CONEXAO.disconnect();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        return LSTR_RETORNO;
    }

    private static String converterInputStreamToString(InputStream is){
        StringBuffer LSTB_BUFFER = null;
        BufferedReader LBUF_BUFFERREADER;
        String STR_linha;
        try{

            LSTB_BUFFER = new StringBuffer();

            LBUF_BUFFERREADER = new BufferedReader(new InputStreamReader(is));
            while((STR_linha = LBUF_BUFFERREADER.readLine())!=null){
                LSTB_BUFFER.append(STR_linha);
            }

            LBUF_BUFFERREADER.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        return LSTB_BUFFER.toString();
    }
    public static String get(String urlString){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resposta = null;
        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            StringBuffer buffer = new StringBuffer();
            while ((line = reader.readLine()) != null){
                buffer.append(line);
            }
            resposta = buffer.toString();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
            try {
                reader.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return resposta;
    }
}

