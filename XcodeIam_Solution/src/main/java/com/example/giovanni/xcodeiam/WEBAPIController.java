package com.example.giovanni.xcodeiam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.giovanni.xcodeiam.Controller.ConnectWebAPI;
import com.example.giovanni.xcodeiam.Controller.Util;
import com.example.giovanni.xcodeiam.Model.USER;
import com.example.giovanni.xcodeiam.Model.WEBAPI;

/**
 * Created by Giovanni on 25/03/2018.
 */

public class WEBAPIController  {
    private SQLiteDatabase db;
    private WEBAPI WEBAPIModel;

    public static Context contexts;

    public WEBAPIController(Context context) {
        WEBAPIModel = new WEBAPI(context);

    }

    //CRUD WB
    public String FU_Insert_WB(WEBAPI CLS_PARAMETROS) {
        Util LCLS_UTIL = null;
        Object LOBJ_Retorno = null;
        try {
            if (CLS_PARAMETROS != null) {
                LOBJ_Retorno = ConnectWebAPI.FU_WB_EXECUTA_CRUD(CLS_PARAMETROS, WEBAPI.INSERT_WB, 0);
            } else {
                return new String("Não pode enviar classe Null");
            }
            return LOBJ_Retorno.toString();

        } catch (Exception ex) {
            return new String("Exception: " + ex.getMessage());
            //Log.e("TAG", Log.getStackTraceString(ex));
        } finally {
            LCLS_UTIL = null;
            LOBJ_Retorno = null;
        }
    }

    public String FU_Read_WB(WEBAPI CLS_TIPOCOMPONENTE, int INT_IDTIPOCOMPNENTE) {
        Util LCLS_UTIL = null;
        Object LOBJ_Retorno = null;

        try {
            if (CLS_TIPOCOMPONENTE != null || INT_IDTIPOCOMPNENTE > 0) {
                LOBJ_Retorno = ConnectWebAPI.FU_WB_EXECUTA_CRUD(CLS_TIPOCOMPONENTE, WEBAPI.READ_WB, INT_IDTIPOCOMPNENTE);
            } else {
                return new String("Não pode enviar classe Null");
            }
            return LOBJ_Retorno.toString();

        } catch (Exception ex) {
            return new String("Exception: " + ex.getMessage());
            //Log.e("TAG", Log.getStackTraceString(ex));
        } finally {
            LCLS_UTIL = null;
            LOBJ_Retorno = null;
        }
    }

    public String FU_Update_WB(WEBAPI CLS_TIPOCOMPONENTE, int INT_IDTIPOCOMPNENTE) {
        Util LCLS_UTIL = null;
        Object LOBJ_Retorno = null;

        try {
            if (CLS_TIPOCOMPONENTE != null || INT_IDTIPOCOMPNENTE > 0) {
                LOBJ_Retorno = ConnectWebAPI.FU_WB_EXECUTA_CRUD(CLS_TIPOCOMPONENTE, WEBAPI.UPDATE_WB, INT_IDTIPOCOMPNENTE);
            } else {
                return new String("Não pode enviar classe Null");
            }
            return LOBJ_Retorno.toString();

        } catch (Exception ex) {
            return new String("Exception: " + ex.getMessage());
            //Log.e("TAG", Log.getStackTraceString(ex));
        } finally {
            LCLS_UTIL = null;
            LOBJ_Retorno = null;
        }
    }

    public String FU_Delete_WB(WEBAPI CLS_TIPOCOMPONENTE, int INT_IDTIPOCOMPNENTE) {
        Util LCLS_UTIL = null;
        Object LOBJ_Retorno = null;

        try {
            if (CLS_TIPOCOMPONENTE != null || INT_IDTIPOCOMPNENTE > 0) {
                LOBJ_Retorno = ConnectWebAPI.FU_WB_EXECUTA_CRUD(CLS_TIPOCOMPONENTE, WEBAPI.DELETE_WB, INT_IDTIPOCOMPNENTE);
            } else {
                return new String("Não pode enviar classe Null");
            }
            return LOBJ_Retorno.toString();

        } catch (Exception ex) {
            return new String("Exception: " + ex.getMessage());
            //Log.e("TAG", Log.getStackTraceString(ex));
        } finally {
            LCLS_UTIL = null;
            LOBJ_Retorno = null;
        }
    }

    //CRUD BD
    public String FU_Insert_BD() {
        ContentValues LCVA_VALUES;
        long LINT_RETURN = 0;
        try {

            db = WEBAPIModel.getWritableDatabase();
            //WEBAPIModel.onUpgrade(db,0,1);
            LCVA_VALUES = new ContentValues();
            LCVA_VALUES.put("ENDERECOWEBAPI", WEBAPI.getPstrEnderecowebapi());
            LCVA_VALUES.put("NMUSUARIO", USER.userNameLocal);
            LINT_RETURN = db.insert(WEBAPI.TABLE, null, LCVA_VALUES);
            db.close();

            if (LINT_RETURN == -1)
                return "Erro ao inserir registro";
            else
                return "Registro Inserido com sucesso";

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return "";
    }

    public Cursor FU_Read_BD() {
        Cursor LCUR_CURSOR = null;
//        String[] campos = {String.valueOf(EMFSESSION.LOCAL_IDSESSION)};
        String[] campos = {"*"};
        try {
            db = WEBAPIModel.getReadableDatabase();
            LCUR_CURSOR = db.query(WEBAPI.TABLE, campos, null, null, null, null, null, null);

            if (LCUR_CURSOR != null) {
                LCUR_CURSOR.moveToFirst();
            }
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return LCUR_CURSOR;
    }

    public Cursor FU_Read_ID_BD(int id) {
        Cursor LCUR_CURSOR = null;
        try {
            String[] campos = {"*"};
            String where = "IDWEBAPI" + "=" + id;
            db = WEBAPIModel.getReadableDatabase();
            LCUR_CURSOR = db.query(WEBAPI.TABLE, campos, where, null, null, null, null, null);

            if (LCUR_CURSOR != null) {
                LCUR_CURSOR.moveToFirst();
            }
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return LCUR_CURSOR;
    }

    public void FU_Update_BD(int id) {
        ContentValues LCVA_VALUES;
        String where = "";
        long LINT_RETURN = 0;
        try {
            db = WEBAPIModel.getWritableDatabase();

            if (id > 0)
                where = "IDWEBAPI" + "=" + id;

            LCVA_VALUES = new ContentValues();
            LCVA_VALUES.put("ENDERECOWEBAPI", WEBAPI.getPstrEnderecowebapi());
            LCVA_VALUES.put("NMUSUARIO", USER.userNameLocal.toUpperCase());
            LINT_RETURN = db.update(WEBAPI.TABLE, LCVA_VALUES, where, null);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    public void FU_Delete_BD(int id) {
        String where = "";
        int LINT_RETURN;
        try {
            if (id > 0)
                where = "IDWEBAPI" + "=" + id;

            db = WEBAPIModel.getReadableDatabase();
            LINT_RETURN = db.delete(WEBAPI.TABLE, where, null);
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}
