package com.example.giovanni.xcodeiam.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Giovanni on 25/03/2018.
 */

public class WEBAPI extends SQLiteOpenHelper {

    public static final String TABLE = "WEBAPI";

    public static final String INSERT_WB = TABLE + "/insert";
    public static final String READ_WB = TABLE;
    public static final String UPDATE_WB = TABLE + "/update";
    public static final String DELETE_WB = TABLE + "/delete";

    //public static String PSTR_ENDERECOWEBAPI = "http://192.168.10.124:8021/GiclPLibWebAPI/api";
    private static int PINT_IDWEBAPI;
    public static String PSTR_ENDERECOWEBAPI = "http://192.168.0.3:8021/GiclPLibWebAPI/api";
    public static Boolean PBOL_Conectado = false;

    //Necessario para salvar no banco;
    private Context context;

    //Construtor da classe
    public WEBAPI(Context context) {
        super(context, DATABASE.NOME_BANCO, null, DATABASE.VERSAO);
        this.context = context;
        //super(context,null,null,1);
    }

    public static String getPstrEnderecowebapi() {
        return PSTR_ENDERECOWEBAPI;
    }

    public static void setPstrEnderecowebapi(String pstrEnderecowebapi) {
        PSTR_ENDERECOWEBAPI = pstrEnderecowebapi;
    }

    public static int getPintIdwebapi() {
        return PINT_IDWEBAPI;
    }

    public static void setPintIdwebapi(int pintIdwebapi) {
        PINT_IDWEBAPI = pintIdwebapi;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String CREATE_TABLE = " CREATE TABLE " + TABLE + " ( "
                    + "IDWEBAPI" + " integer primary key autoincrement, "
                    + "ENDERECOWEBAPI" + " text, "
                    + "NMUSUARIO" + " text "
                    + " ) ";
            db.execSQL(CREATE_TABLE);
        } catch (Exception e) {
            e.printStackTrace();
            //NotificationCompat.MessagingStyle.Message.message(context,""+e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE);
            onCreate(db);
        } catch (Exception e) {
            e.printStackTrace();
            //NotificationCompat.MessagingStyle.Message.message(context,""+e);
        }
    }
}
