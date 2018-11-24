package com.example.giovanni.xcodeiam.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.giovanni.xcodeiam.Model.EMFSESSION;
import com.example.giovanni.xcodeiam.Model.PARAMETROS;
import com.example.giovanni.xcodeiam.Model.WEBAPI;

public class PARAMETROSController{

        private SQLiteDatabase db;
        private PARAMETROS PARAMETROSModel;

        public static Context contexts;

        public PARAMETROSController(Context context) {
            PARAMETROSModel = new PARAMETROS(contexts);
        }

        //CRUD WB
        public String FU_Insert_WB(PARAMETROS CLS_PARAMETROS) {
            ClasseUtil LCLS_UTIL = null;
            Object LOBJ_Retorno = null;
            try {
                if (CLS_PARAMETROS != null) {
                    LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA_CRUD(CLS_PARAMETROS, PARAMETROS.INSERT_WB, 0);
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

        public String FU_Read_WB(PARAMETROS CLS_TIPOCOMPONENTE, int INT_IDTIPOCOMPNENTE) {
            ClasseUtil LCLS_UTIL = null;
            Object LOBJ_Retorno = null;

            try {
                if (CLS_TIPOCOMPONENTE != null || INT_IDTIPOCOMPNENTE > 0) {
                    LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA_CRUD(CLS_TIPOCOMPONENTE, PARAMETROS.READ_WB, INT_IDTIPOCOMPNENTE);
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

        public String FU_Update_WB(PARAMETROS CLS_TIPOCOMPONENTE, int INT_IDTIPOCOMPNENTE) {
            ClasseUtil LCLS_UTIL = null;
            Object LOBJ_Retorno = null;

            try {
                if (CLS_TIPOCOMPONENTE != null || INT_IDTIPOCOMPNENTE > 0) {
                    LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA_CRUD(CLS_TIPOCOMPONENTE, PARAMETROS.UPDATE_WB, INT_IDTIPOCOMPNENTE);
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

        public String FU_Delete_WB(PARAMETROS CLS_TIPOCOMPONENTE, int INT_IDTIPOCOMPNENTE) {
            ClasseUtil LCLS_UTIL = null;
            Object LOBJ_Retorno = null;

            try {
                if (CLS_TIPOCOMPONENTE != null || INT_IDTIPOCOMPNENTE > 0) {
                    LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA_CRUD(CLS_TIPOCOMPONENTE, PARAMETROS.DELETE_WB, INT_IDTIPOCOMPNENTE);
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
        public String FU_Insert_BD(PARAMETROS CLS_PARAMETROS) {
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

                    db = PARAMETROSModel.getWritableDatabase();
                    //PARAMETROSModel.onUpgrade(db, 0, 1);
                    LCVA_VALUES = new ContentValues();
                    LCVA_VALUES.put("IDWEBAPI", WEBAPI.getPintIdwebapi());
                    LCVA_VALUES.put("NMUSUARIO", EMFSESSION.LOCAL_NMUSUARIO);
                    LINT_RETURN = db.insert(PARAMETROS.TABLE, null, LCVA_VALUES);
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
                db = PARAMETROSModel.getReadableDatabase();
                //PARAMETROSModel.onUpgrade(db, 0, 1);
                LCUR_CURSOR = db.query(PARAMETROS.TABLE, campos, null, null, null, null, null, null);

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
                db = PARAMETROSModel.getReadableDatabase();
                LCUR_CURSOR = db.query(PARAMETROS.TABLE, campos, where, null, null, null, null, null);

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
                db = PARAMETROSModel.getWritableDatabase();

                if (id > 0)
                    where = "IDPARAMETRO" + "=" + id;

                LCVA_VALUES = new ContentValues();
                LCVA_VALUES.put("IDWEBAPI", WEBAPI.getPintIdwebapi());
                LCVA_VALUES.put("NMUSUARIO", EMFSESSION.LOCAL_NMUSUARIO.toUpperCase());
                LINT_RETURN = db.update(PARAMETROS.TABLE, LCVA_VALUES, where, null);
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

                db = PARAMETROSModel.getReadableDatabase();
                LINT_RETURN = db.delete(PARAMETROS.TABLE, where, null);
                db.close();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }
        }

}