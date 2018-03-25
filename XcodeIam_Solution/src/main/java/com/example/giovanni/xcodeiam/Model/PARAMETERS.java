package com.example.giovanni.xcodeiam.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Giovanni on 25/03/2018.
 */

public class PARAMETERS extends SQLiteOpenHelper {

    public static final String TABLE = "PARAMETERS";

    public static final String INSERT_WB = "/insert";
    public static final String READ_WB = "";
    public static final String UPDATE_WB = "/update";
    public static final String DELETE_WB = "/delete";

    //Necessario para salvar no banco;
    private Context context;

    //Construtor da classe
    public PARAMETERS(Context context) {
        super(context, DATABASE.NOME_BANCO, null, DATABASE.VERSAO);
        this.context = context;
        //super(context,null,null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String CREATE_TABLE = " CREATE TABLE " + TABLE + " ( "
                    + "IDPARAMETRO" + " integer primary key autoincrement, "
                    + "IDWEBAPI" + " text, "
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