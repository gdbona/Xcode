package com.example.giovanni.xcodeiam.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Giovanni on 29/01/2018.
 */

//@SuppressWarnings("ALL")
public class EMFSESSION extends SQLiteOpenHelper {
    //ATENÇÃO ATENÇÃO ESSE NOME FAZ PARTE DE UMA FUNÇÃO PARA CRIAR A TABLE
    //DEIXAR ESSE NOME DE VARIAVEL PADRAO EM TODAS AS CLASSES
    //o conteudo vai mudar de acordo com o nome da claase que devera ser gravado no banco
    public static final String TABLE = "EMFSESSION";

    //Variaveis para nomes de colunas no SQLLITE
    public static long LOCAL_IDSESSION;
    public static final String _SGUSER = "";
    public static final String _PASSWORD = "";
    public static final String _SGENVIRONMENT = "";
    public static final String _SGLANGUAGE = "";
    public static final String _EQUIPMENT = "";
    public static final String _DHSESSION = "";

    //Necessario para salvar no banco;
    private Context context;

    //Variaveis da classe
    private long IDSESSION;
    private String SGUSER;
    private String PASSWORD;
    private String SGENVIRONMENT;
    private String SGLANGUAGE;
    private String EQUIPMENT;
    private String DHSESSION;
    private String MENSAGEM ;

    public long getIDSESSION() {
        return IDSESSION;
    }

    public void setIDSESSION(long IDSESSION) {
        this.IDSESSION = IDSESSION;
    }

    public String getSGUSER() {
        return SGUSER;
    }

    public void setSGUSER(String SGUSER) {
        this.SGUSER = SGUSER;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getSGENVIRONMENT() {
        return SGENVIRONMENT;
    }

    public void setSGENVIRONMENT(String SGENVIRONMENT) {
        this.SGENVIRONMENT = SGENVIRONMENT;
    }

    public String getSGLANGUAGE() {
        return SGLANGUAGE;
    }

    public void setSGLANGUAGE(String SGLANGUAGE) {
        this.SGLANGUAGE = SGLANGUAGE;
    }

    public String getEQUIPMENT() {
        return EQUIPMENT;
    }

    public void setEQUIPMENT(String EQUIPMENT) {
        this.EQUIPMENT = EQUIPMENT;
    }

    public String getDHSESSION() {
        return DHSESSION;
    }

    public void setDHSESSION(String DHSESSION) {
        this.DHSESSION = DHSESSION;
    }

    public String getMENSAGEM() {
        return MENSAGEM;
    }

    public void setMENSAGEM(String MENSAGEM) {
        this.MENSAGEM = MENSAGEM;
    }

    //Construtor da classe
    public EMFSESSION(Context context) {
        super(context, DATABASE.NOME_BANCO, null, DATABASE.VERSAO);
        this.context = context;
        //super(context,null,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String CREATE_TABLE = " CREATE TABLE " + TABLE + " ( "
                    + EMFSESSION.LOCAL_IDSESSION + " integer primary key autoincrement, "
                    + EMFSESSION._SGUSER + " text, "
                    + EMFSESSION._PASSWORD + " text, "
                    + EMFSESSION._SGENVIRONMENT + " text , "
                    + EMFSESSION._SGLANGUAGE + " text , "
                    + EMFSESSION._EQUIPMENT + " text "
                    + " ) ";
            db.execSQL(CREATE_TABLE);
        } catch (Exception e) {
            e.printStackTrace();
            //NotificationCompat.MessagingStyle.Message.message(context,""+e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}

