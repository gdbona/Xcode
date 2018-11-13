package com.example.giovanni.xcodeiam.Controller;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by Giovanni on 19/10/2017.
 */
public class ClasseUtil {

    public static String caminhoApp;

    public boolean FU_dataAnteriorAHoje(String data) {
        Boolean validou;
        try {
            Calendar now = Calendar.getInstance();
            now.set(Calendar.HOUR, 0);
            now.set(Calendar.HOUR_OF_DAY, 0);
            now.set(Calendar.MINUTE, 0);
            now.set(Calendar.SECOND, 0);
            now.set(Calendar.MILLISECOND, 0);

            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
            Date dataFormatada = formatador.parse(data);

            Date hoje = now.getTime();
            validou = dataFormatada.before(hoje);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            validou = false;
        }
        return validou;
    }

    public boolean FU_validaPlaca(String placa) {
        return Pattern.matches("[A-Z]{3}-[0-9]{4}", placa);
    }

    public String FU_converteArquivoParaArrayBytes(String caminho) {
        byte[] imagem = null;
        String base64 = "";
        Bitmap image;
        BitmapFactory.Options options;
        ByteArrayOutputStream baos;
        try {
//            FileInputStream fis = new FileInputStream(caminho);
//            imagem = new byte[fis.available()];
//            fis.read(imagem);
//            fis.close();

            baos = new ByteArrayOutputStream();
            options = new BitmapFactory.Options();
            image = BitmapFactory.decodeFile(caminho, options);
            image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] byteArray = baos.toByteArray();
            base64 = Base64.encodeToString(byteArray, Base64.DEFAULT);

//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            FileInputStream fis = new FileInputStream(new File(caminho));
//            GZIPOutputStream gzos = null;
//            byte[] buf = new byte[1024];
//            int n;
//            gzos = new GZIPOutputStream(baos);
//
//            while (-1 != (n = fis.read(buf)))
//                gzos.write(buf, 0, n);
//
//            byte[] videoBytes = baos.toByteArray();

            imagem = null;
            base64 = "";
            image = null;
            options = null;
            baos = null;

            //base64 = Base64.encodeToString(videoBytes, Base64.DEFAULT);
        } catch (OutOfMemoryError oomer) {
            Log.e("ERRO", "Não foi possível converter o arquivo de vídeo para a transmissão. OutOfMemoryError. ", oomer);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        //return imagem;
        return base64;
    }

    public String FU_converteFotoParaArrayBytes(String caminho) {
        byte[] imagem = null;
        String base64 = "";
        Bitmap image;
        BitmapFactory.Options options;
        ByteArrayOutputStream baos;
        try {
//            FileInputStream fis = new FileInputStream(caminho);
//            imagem = new byte[fis.available()];
//            fis.read(imagem);
//            fis.close();

            baos = new ByteArrayOutputStream();
            options = new BitmapFactory.Options();
            image = BitmapFactory.decodeFile(caminho, options);
            image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] byteArray = baos.toByteArray();
            base64 = Base64.encodeToString(byteArray, Base64.DEFAULT);

//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            FileInputStream fis = new FileInputStream(new File(caminho));
//            GZIPOutputStream gzos = null;
//            byte[] buf = new byte[1024];
//            int n;
//            gzos = new GZIPOutputStream(baos);
//
//            while (-1 != (n = fis.read(buf)))
//                gzos.write(buf, 0, n);
//
//            byte[] videoBytes = baos.toByteArray();

            imagem = null;
            base64 = "";
            image = null;
            options = null;
            baos = null;

            //base64 = Base64.encodeToString(videoBytes, Base64.DEFAULT);
        } catch (OutOfMemoryError oomer) {
            Log.e("ERRO", "Não foi possível converter o arquivo de vídeo para a transmissão. OutOfMemoryError. ", oomer);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        //return imagem;
        return base64;
    }

    public Bitmap FU_converteArrayBytesParaImagem(byte[] byteArray) {
        Bitmap imagem = null;
        try {
            imagem = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return imagem;
    }

    public String FU_salvaByteArrayComoArquivoERetornaCaminho(byte[] arquivo, String nomeArquivo) {
        //exemplo do nome do arquivo com a pasta "teste\\logo.png"
        String caminho = null;
        try {
            FileOutputStream out;
            File file;

            try {
                out = new FileOutputStream(caminhoApp + "/" + nomeArquivo);
                caminho = caminhoApp + "/" + nomeArquivo;
                out.write(arquivo);
                out.close();
            } catch (IOException e) {
                if (FU_podeLerEEscreverMidiaExterna()) {
                    file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), nomeArquivo);
                    out = new FileOutputStream(file.getPath());
                    caminho = file.getPath();
                } else {
                    out = new FileOutputStream(nomeArquivo);
                    caminho = null;
                }
                out.write(arquivo);
                out.close();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return caminho;
    }

    public boolean FU_podeLerEEscreverMidiaExterna() {
        String state = Environment.getExternalStorageState();
        return (Environment.MEDIA_MOUNTED.equals(state) && Environment.MEDIA_MOUNTED_READ_ONLY.equals(state) == false);
    }

    //Para a função abaixo funcionar é preciso extender a activity para a classe
//    public String FU_retornaCaminhoImagem(Resources objeto, Integer idImagem)
//    {
//        Bitmap bitMap = BitmapFactory.decodeResource(objeto, idImagem);
//
//        String fileName ="img1.jpg";
//        String mPath = "";
//        try {
//
//            FileOutputStream out1 = openFileOutput(fileName, Context.MODE_PRIVATE);
//
//            bitMap.compress(Bitmap.CompressFormat.JPEG, 100, out1);
//
//            out1.flush();
//
//            out1.close();
//
//            File f = getFileStreamPath(fileName);
//
//            mPath = f.getAbsolutePath();
//
//        } catch (FileNotFoundException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return mPath;
//    }

    //Função para enviar aquivo de fotos ou video para p webservice, nao testado! - giovanni 09/01/2018
//    public void Enviar()
//    {
//        string fileName = Server.MapPath("~") + "/Files/arq.txt";
//
//        using (HttpClient client = new HttpClient())
//        using (MultipartFormDataContent content = new MultipartFormDataContent())
//        using (FileStream fileStream = System.IO.File.OpenRead(fileName))
//        using (StreamContent fileContent = new StreamContent(fileStream))
//        {
//
//            fileContent.Headers.ContentType = MediaTypeHeaderValue.Parse("text/plain");
//            fileContent.Headers.ContentDisposition = new ContentDispositionHeaderValue("form-data")
//            {
//                FileName = "arq.txt",
//            };
//            fileContent.Headers.Add("name", "arq.txt");
//            content.Add(fileContent);
//            var result = client.PostAsync("http://localhost:62951/api/arquivo", content).Result;
//            result.EnsureSuccessStatusCode();
//        }
//    }

    int LINT_PERMISSION = 21;
    ProgressBar progressBar;
    private ProgressDialog load;
    String LSTR_STATUS;
    Button BTN_TestarConexao;

    EditText ET_IDEQUIP;
    EditText ET_DSEQUIP;

//    private class GetJson extends AsyncTask<Void, Void, String> {
//
//        @Override
//        protected void onPreExecute() {
//            load = ProgressDialog.show(ACT_ConfigWebService.class, "Por favor Aguarde ...", "Recuperando Informações do Servidor...");
//        }
//
//        @Override
//        protected String doInBackground(Void... params) {
//            //EquipamentoRestClient util = new EquipamentoRestClient();
//
//            //return util.getInformacao("https://randomuser.me/api/0.7");
//            //return util.getInformacao("http://localhost:8021/GiclPLibWebAPI/api/equipamento");
//
//            String resposta = NetworkUtils.get("http://192.168.10.124:8021/GiclPLibWebAPI/api/gestoricl/status/8021");
//
//            LSTR_STATUS = resposta;
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return LSTR_STATUS;
//        }
//
//        @Override
//        protected void onPostExecute(String STR_STATUS) {
////            ET_IDEQUIP.setText(pessoa.get_IDEQUIPAMENTO().substring(0,1).toUpperCase()+pessoa.getNome().substring(1));
////            ET_DSEQUIP.setText(pessoa.get_DSEQUIPAMENTO().substring(0,1).toUpperCase()+pessoa.getSobrenome().substring(1));
//
//            ET_IDEQUIP.setText(STR_STATUS);
//
//
////            email.setT
//// ext(pessoa.getEmail());
////            endereco.setText(pessoa.getEndereco());
////            cidade.setText(pessoa.getCidade().substring(0,1).toUpperCase()+pessoa.getCidade().substring(1));
////            estado.setText(pessoa.getEstado());
////            username.setText(pessoa.getUsername());
////            senha.setText(pessoa.getSenha());
////            nascimento.setText(pessoa.getNascimento());
////            telefone.setText(pessoa.getTelefone());
////            foto.setImageBitmap(pessoa.getFoto());
//            load.dismiss();
//            if (LSTR_STATUS.equals("true"))
//                Toast.makeText(ACT_ConfigWebService.this, "Conexão Satisfatória",
//                        Toast.LENGTH_LONG).show();
//        }
//    }
//
//    private class Task extends AsyncTask<Void, Void, String> {
//
//        private ProgressDialog dialog;
//        private Context context;
//
//        public Task(Context context) {
//            this.context = context;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            dialog = new ProgressDialog(context);
//            dialog.setTitle("Realizando o carregamento dos dados");
//            dialog.setMessage("Aguarde o fim da requisição...");
//            dialog.show();
//        }
//
//        @Override
//        protected String doInBackground(Void... params) {
//
//            String urlServico = "http://echo.jsontest.com/key/value/one/two";
//
//            try {
//                URL url = new URL(urlServico);
//                HttpURLConnection http = (HttpURLConnection) url.openConnection();
//                InputStream stream = http.getInputStream();
//
//                Reader reader = new InputStreamReader(stream, "UTF-8");
//                char[] buffer = new char[1024];
//                reader.read(buffer);
//                return new String(buffer);
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            return "";
//        }
//
//        @Override
//        protected void onPostExecute(String retorno) {
//            Activity activity = (Activity) context;
//            TextView dados = (TextView) activity.findViewById(R.id.dados);
//            dados.setText("Dados: " + retorno);
//            dialog.dismiss();
//        }
//    }

}
