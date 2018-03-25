package com.example.giovanni.xcodeiam.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.giovanni.xcodeiam.Model.PARAMETERS;
import com.example.giovanni.xcodeiam.Model.USER;
import com.example.giovanni.xcodeiam.Model.WEBAPI;

/**
 * Created by Giovanni on 25/03/2018.
 */

public class PARAMETERSController {


    private SQLiteDatabase db;
    private PARAMETERS PARAMETERSModel;

    public static Context contexts;

    public PARAMETERSController(Context context) {
        PARAMETERSModel = new PARAMETERS(contexts);
    }

    //CRUD WB
    public String FU_Insert_WB(PARAMETERS CLS_PARAMETERS) {
        Util LCLS_UTIL = null;
        Object LOBJ_Retorno = null;
        try {
            if (CLS_PARAMETERS != null) {
                LOBJ_Retorno = ConnectWebAPI.FU_WB_EXECUTA_CRUD(CLS_PARAMETERS, PARAMETERS.INSERT_WB, 0);
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

    public String FU_Read_WB(PARAMETERS CLS_TIPOCOMPONENTE, int INT_IDTIPOCOMPNENTE) {
        Util LCLS_UTIL = null;
        Object LOBJ_Retorno = null;

        try {
            if (CLS_TIPOCOMPONENTE != null || INT_IDTIPOCOMPNENTE > 0) {
                LOBJ_Retorno = ConnectWebAPI.FU_WB_EXECUTA_CRUD(CLS_TIPOCOMPONENTE, PARAMETERS.READ_WB, INT_IDTIPOCOMPNENTE);
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

    public String FU_Update_WB(PARAMETERS CLS_TIPOCOMPONENTE, int INT_IDTIPOCOMPNENTE) {
        Util LCLS_UTIL = null;
        Object LOBJ_Retorno = null;

        try {
            if (CLS_TIPOCOMPONENTE != null || INT_IDTIPOCOMPNENTE > 0) {
                LOBJ_Retorno = ConnectWebAPI.FU_WB_EXECUTA_CRUD(CLS_TIPOCOMPONENTE, PARAMETERS.UPDATE_WB, INT_IDTIPOCOMPNENTE);
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

    public String FU_Delete_WB(PARAMETERS CLS_TIPOCOMPONENTE, int INT_IDTIPOCOMPNENTE) {
        Util LCLS_UTIL = null;
        Object LOBJ_Retorno = null;

        try {
            if (CLS_TIPOCOMPONENTE != null || INT_IDTIPOCOMPNENTE > 0) {
                LOBJ_Retorno = ConnectWebAPI.FU_WB_EXECUTA_CRUD(CLS_TIPOCOMPONENTE, PARAMETERS.DELETE_WB, INT_IDTIPOCOMPNENTE);
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
    public String FU_Insert_BD(PARAMETERS CLS_PARAMETERS) {
        ContentValues LCVA_VALUES;
        long LINT_RETURN = 0;
        Cursor LCUR_Cursor = null;
        WEBAPIController LCLS_WEBAPIController = null;

        try {


            LCLS_WEBAPIController = new WEBAPIController(contexts);
            if (LCLS_WEBAPIController.FU_Insert_BD().toUpperCase().contains("SUCESSO")) ;
            {
                LCUR_Cursor = LCLS_WEBAPIController.FU_Read_BD();
                if (LCUR_Cursor.getCount() > 0) {
                    LCUR_Cursor.moveToLast();
                    WEBAPI.setPintIdwebapi((Integer.parseInt(LCUR_Cursor.getString(LCUR_Cursor.getColumnIndex("IDWEBAPI")).trim())));
                }

                db = PARAMETERSModel.getWritableDatabase();
                //PARAMETERSModel.onUpgrade(db, 0, 1);
                LCVA_VALUES = new ContentValues();
                LCVA_VALUES.put("IDWEBAPI", WEBAPI.getPintIdwebapi());
                LCVA_VALUES.put("NMUSUARIO", USER.userNameLocal);
                LINT_RETURN = db.insert(PARAMETERS.TABLE, null, LCVA_VALUES);
                db.close();

                if (LINT_RETURN == -1)
                    return "Erro ao inserir registro";
                else
                    return "Registro Inserido com sucesso";
            }
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
        WEBAPIController LCLS_WEBAPIController = null;
        Cursor LCUR_WebApi = null;

        try {
            db = PARAMETERSModel.getReadableDatabase();
            //PARAMETERSModel.onUpgrade(db, 0, 1);
            LCUR_CURSOR = db.query(PARAMETERS.TABLE, campos, null, null, null, null, null, null);

            if (LCUR_CURSOR != null) {
                if (LCUR_CURSOR.getCount() > 0) {
                    LCUR_CURSOR.moveToFirst();
                    try {
                        LCLS_WEBAPIController = new WEBAPIController(contexts);
                        LCUR_WebApi = LCLS_WEBAPIController.FU_Read_ID_BD((Integer.parseInt(LCUR_CURSOR.getString(LCUR_CURSOR.getColumnIndex("IDWEBAPI")))));

                        if (LCUR_WebApi != null) {
                            LCUR_WebApi.moveToFirst();
                            WEBAPI.setPintIdwebapi((Integer.parseInt(LCUR_WebApi.getString(LCUR_WebApi.getColumnIndex("IDWEBAPI")))));
                            //WEBAPI.setPstrEnderecowebapi(LCUR_WebApi.getString(LCUR_WebApi.getColumnIndex("ENDERECOWEBAPI")));
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            db.close();
        } catch (
                Exception e)

        {
            e.printStackTrace();
        } finally

        {

        }
        return LCUR_CURSOR;
    }

    public Cursor FU_Read_ID_BD(int id) {
        Cursor LCUR_CURSOR = null;
        WEBAPIController LCLS_WEBAPIController = null;
        Cursor LCUR_WebApi = null;
        try {
            String[] campos = {"*"};
            String where = "IDPARAMETRO" + "=" + id;
            db = PARAMETERSModel.getReadableDatabase();
            LCUR_CURSOR = db.query(PARAMETERS.TABLE, campos, where, null, null, null, null, null);

            if (LCUR_CURSOR != null) {
                LCUR_CURSOR.moveToFirst();

                try {

                    LCLS_WEBAPIController = new WEBAPIController(contexts);
                    LCUR_WebApi = LCLS_WEBAPIController.FU_Read_ID_BD((Integer.parseInt(LCUR_CURSOR.getString(LCUR_CURSOR.getColumnIndex("IDWEBAPI")))));

                    if (LCUR_WebApi != null) {
                        LCUR_WebApi.moveToFirst();
                        WEBAPI.setPintIdwebapi((Integer.parseInt(LCUR_WebApi.getString(LCUR_WebApi.getColumnIndex("IDWEBAPI")))));
                        WEBAPI.setPstrEnderecowebapi(LCUR_WebApi.getString(LCUR_WebApi.getColumnIndex("ENDERECOWEBAPI")));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
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
            db = PARAMETERSModel.getWritableDatabase();

            if (id > 0)
                where = "IDPARAMETRO" + "=" + id;

            LCVA_VALUES = new ContentValues();
            LCVA_VALUES.put("IDWEBAPI", WEBAPI.getPintIdwebapi());
            LCVA_VALUES.put("NMUSUARIO", USER.userNameLocal.toUpperCase());
            LINT_RETURN = db.update(PARAMETERS.TABLE, LCVA_VALUES, where, null);
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
                where = "IDPARAMETRO" + "=" + id;

            db = PARAMETERSModel.getReadableDatabase();
            LINT_RETURN = db.delete(PARAMETERS.TABLE, where, null);
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

}
