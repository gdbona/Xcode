package com.example.giovanni.xcodeiam.Model;

import android.app.Notification;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.NotificationCompat;

import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Set;
import java.util.regex.Pattern;

public class DATABASE  extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "GESTORICL.db";
    public static final String TABELA = "livros";
    public static final int VERSAO = 1;

    private static final String DATABASE_NAME = "gestoricl";    // Database Name
    private static final String TABLE_NAME = "EMFSESSION";   // Table Name
    private static final int DATABASE_Version = 1;    // Database Version
    private static final long IDSESSION = 0;
    private static final String SGUSER = "MASTER";
    private static final String PASSWORD = "MTM";
    private static final String SGENVIRONMENT = "GESTOR";
    private static final String SGLANGUAGE = "PT-BR";
    private static final String EQUIPMENT = "TABLETE";
    private static final String DHSESSION = "";
    private static final String MENSAGEM = "TESTE";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    private Context context;

    public DATABASE(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_Version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "";
        int LINT_COUNT = 0;
        Object[] LARR_Model = null;
        try {
//            String CREATE_TABLE = " CREATE TABLE " + TABLE_NAME + " ( "
//                    + "IDSESSION" + " integer primary key autoincrement, "
//                    + "SGUSER" + " text, "
//                    + "PASSWORD" + " text, "
//                    + "SGENVIRONMENT" + " text "
//                    + "SGLANGUAGE" + " text "
//                    + "EQUIPMENT" + " text "
//                    + ")";

            //Deixar fixo por enquanto ,nao achei uma função para percorrar todos os objetos da model
//            LARR_Model[LINT_COUNT] = EMFSESSION.class;
//            LARR_Model[LINT_COUNT++] = EQUIPAMENTO.class;
//            LARR_Model[LINT_COUNT++] = EQUIPAMENTOCOMPONENTE.class;
//            LARR_Model[LINT_COUNT++] = EQUIPAMENTOCONTADOR.class;
//            LARR_Model[LINT_COUNT++] = EMFSESSION.class;
//            LARR_Model[LINT_COUNT++] = EMFSESSION.class;
//
//            for(LINT_COUNT=0;LARR_Model.length>LINT_COUNT;LINT_COUNT++)
//            {CREATE_TABLE += FU_Monta_Create_Objeto(EMFSESSION.class);}

            db.execSQL(CREATE_TABLE);
        } catch (Exception e) {
            e.printStackTrace();
//            NotificationCompat.MessagingStyle.Message.message(context,""+e);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS" + TABELA);
//        onCreate(db);
        try {
//            NotificationCompat.MessagingStyle.Message.message(context,"OnUpgrade");
            db.execSQL(DROP_TABLE);
            onCreate(db);
        } catch (Exception e) {
//            Notification.MessagingStyle.Message.message(context,""+e);
        }
    }

    //Abortado, nao consegui fazer achar a chave do processo para criação autmatica banco de dados.
    public static String FU_Monta_Create_Objeto(Object object) {
        Class<?> classe = object.getClass();
        Field[] campos = classe.getDeclaredFields();
        String LSTR_NMVARIAVEL = "";
        Object LOBJ_TPVARIAVEL = null;
        StringBuilder LSTB_SQL = null;
        String[] LSTP_SPLIT;
        Object tipoAtributo = object;
        //Object valorAtributo = null;
        try {

            //Usado para Separar os ., precisa usar o Pattern.quote
            LSTR_NMVARIAVEL = object.getClass().getName();
            LSTP_SPLIT = LSTR_NMVARIAVEL.split(Pattern.quote("."));

            LSTR_NMVARIAVEL = LSTP_SPLIT[LSTP_SPLIT.length - 1];

            LSTB_SQL = new StringBuilder();
            LSTB_SQL.append("CREATE TABLE " + LSTR_NMVARIAVEL + " (");

            LSTB_SQL.append(" ID" + LSTR_NMVARIAVEL + " integer primary key autoincrement, ");

            for (Field campo : campos) {
                try {

                    LSTR_NMVARIAVEL = campo.getName();
                    LOBJ_TPVARIAVEL = FU_ConverteCampoSQL(campo);
                    LSTB_SQL.append(" " + LSTR_NMVARIAVEL + "  ");
                    LSTB_SQL.append(" " + LOBJ_TPVARIAVEL + "  ,");

                    //campo.setAccessible(true); //Necessário por conta do encapsulamento das variáveis (private)

//                    Class[] classess = getClasses("mtmsistemas.gestorm");
//                    for (int i = 0; i < classes.length; i++) {
//                        //System.out.println(classes[i].getName());
//                    }

                    //Código adicionado
//                    if (tipoAtributo.toString().contains("mtmsistemas.gestorm.Model")) {
//                        Class<?> classeFilha = (Class<?>) tipoAtributo;
//                        Field[] camposFilha = classeFilha.getDeclaredFields();
//                        String nomeAtributoFilha = "";
//                        Object valorAtributoFilha = null;
//                        for (Field campoFilha : camposFilha) {
//                            try {
//                                nomeAtributoFilha = campoFilha.getName();
//                                campoFilha.setAccessible(true);
//                                valorAtributoFilha = campoFilha.get(LSTR_NMVARIAVEL); //Nesse momento é passado o valor do objeto endereço
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            System.out.println("(Filha) " + nomeAtributoFilha + ": " + valorAtributoFilha);
//                        }
//                    }
                    //valorAtributo = campo.get(object);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                //System.out.println(nomeAtributo + ": " + valorAtributo);
            }

            LSTB_SQL.append(" DHMANUTENANCE text  ");
            LSTB_SQL.append(" )");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return LSTB_SQL.toString();
    }

    public static String FU_ConverteCampoSQL(Field FLD_Dado) {

//NULL - como em qualquer outro banco de dados.
//INTEGER - inteiro com sinal, armazenado em 1, 2, 3, 4, 6 ou 8 bytes dependendo da grandeza do valor.
//REAL - valor de ponto flutuante armazenado em 8 bytes.
//TEXT - uma string armazenada usando UTF-8, UTF-16BE ou UTF-16LE.
// BLOB - armazena um blob, como indica o nome.

//Tipo Boolean
//Não existe uma classe Boolean. Pode-se considerar os inteiros 0 (false) e 1 (true).
//Tipos Data e Hora
//Não existem classes de armazenamento para armazenar data e/ou hora. Existem funções capazes de tratar TEXT, REAL ou INTEGER como data e hora:
//TEXT - como string ISO8601 ("YYYY-MM-DD HH:MM:SS.SSS").
//REAL - como dias Julianos. A origem (dia 0) é o meio dia de 24 de novembro de 4714 A.C., pelo calendário Gregoriano.
//INTEGER - como a hora no Unix. A origem é o dia 1º de janeiro de 1970.

//Funções de Data e Hora/
//São cinco as funções:
//date(timestring, modifier, modifier, ...) - retorna a data no formato YYYY-MM-DD.
//time(timestring, modifier, modifier, ...) - retorna a hora no formato HH:MM:SS.
//datetime(timestring, modifier, modifier, ...) - retorna data e hora no formato "YYYY-MM-DD HH:MM:SS".
//julianday(timestring, modifier, modifier, ...) - retorna do dia Juliano.
//strftime(format, timestring, modifier, modifier, ...) - retorna a data formatada de acordo com o formato especificado no primeiro argumento.

        String LSTR_Return = "";

        try {
            if (FLD_Dado.toString().toUpperCase().contains("INT")
                    || FLD_Dado.getType().toString().toUpperCase().contains("INTEGER")
                    || FLD_Dado.getType().toString().toUpperCase().contains("DECIMAL")
                    || FLD_Dado.getType().toString().toUpperCase().contains("LONG")) {
                LSTR_Return = "integer";
            } else {
                LSTR_Return = "text";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        }

        return LSTR_Return;
    }
}