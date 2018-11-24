package com.example.giovanni.xcodeiam.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.giovanni.xcodeiam.Model.CHECKLISTMESTRE;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class CHECKLISTMESTREController {
    private SQLiteDatabase db;
    private CHECKLISTMESTRE CHECKLISTMESTREController;

    public static Context contexts;

    public CHECKLISTMESTREController(Context context) {
        contexts = context;
        CHECKLISTMESTREController = new CHECKLISTMESTRE(contexts);
    }

    //CRUD WB
    public String FU_Insert_WB(CHECKLISTMESTRE CLS_CHECKLISTMESTRE) {
        CHECKLISTMESTRE LCLS_TPCOMPONENTE = null;
        ClasseUtil LCLS_UTIL = null;
        Object LOBJ_Retorno = null;
        try {
            if (CLS_CHECKLISTMESTRE != null) {
                LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA_CRUD(CLS_CHECKLISTMESTRE, CHECKLISTMESTRE.INSERT_WB, 0);
            } else {
                return new String("Não pode enviar classe Null");
            }
            return LOBJ_Retorno.toString();

        } catch (Exception ex) {
            return new String("Exception: " + ex.getMessage());
            //Log.e("TAG", Log.getStackTraceString(ex));
        } finally {
            LCLS_TPCOMPONENTE = null;
            LCLS_UTIL = null;
            LOBJ_Retorno = null;
        }
    }

    public List<CHECKLISTMESTRE> FU_Read_WB(CHECKLISTMESTRE CLS_CHECKLISTMESTRE, int INT_IDCHECKLISTMESTRE, String STR_STATUS) {
        Gson LGS_JSON = null;
        CHECKLISTMESTRE[] LCLS_CHECKLISTMESTRE = null;
        String LOBJ_Retorno = null;
        Cursor LCUR_Cursor = null;
        try {

            if (ConexaoWebAPI.FU_WB_TestaConexao() == "true") {
                LOBJ_Retorno = ConexaoWebAPI.FU_WB_ARROBJECT(
                        CLS_CHECKLISTMESTRE,
                        CHECKLISTMESTRE.READ_WB, INT_IDCHECKLISTMESTRE, STR_STATUS).toString();
                LGS_JSON = new Gson();
                LCLS_CHECKLISTMESTRE = LGS_JSON.fromJson(LOBJ_Retorno.toString()
                        , CHECKLISTMESTRE[].class);
            }

            if (LCLS_CHECKLISTMESTRE != null) {
                for (int LINT_COUNT = 0; LCLS_CHECKLISTMESTRE.length > LINT_COUNT; LINT_COUNT++) {
                    LCUR_Cursor = FU_Read_ID_BD(LCLS_CHECKLISTMESTRE[LINT_COUNT].getIDCHECKLIST());
                    if (LCUR_Cursor == null) {
                        LCUR_Cursor.getString(LCUR_Cursor.getColumnIndex("LOCALCHECKLIST")).trim();

                        if (FU_Insert_BD(LCLS_CHECKLISTMESTRE[LINT_COUNT]) == "1") {

                        }
                    }
                }
                for (CHECKLISTMESTRE item : LCLS_CHECKLISTMESTRE) {
                    item.setDESCRICAOTIPOCHECKLIST(FU_Busca_Descricao_Tipo_CheckList(item.getCDTIPOCHECKLIST()));
                   //item.setDESCRICAOEQUIPAMENTO(FU_Busca_Descricao_Equipamento(item.getIDEQUIPAMENTO()));
                }
            }
        } catch (Exception ex) {
            try {
                throw new Exception(ex.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            LGS_JSON = null;
            LOBJ_Retorno = null;
            LCUR_Cursor = null;
        }
        return Arrays.asList(LCLS_CHECKLISTMESTRE);
    }

    public String FU_Update_WB(CHECKLISTMESTRE CLS_CHECKLISTMESTRE, int INT_IDCHECKLISTMESTRE) {
        ClasseUtil LCLS_UTIL = null;
        Object LOBJ_Retorno = null;

        try {
            if (CLS_CHECKLISTMESTRE != null || INT_IDCHECKLISTMESTRE > 0) {
                if (ConexaoWebAPI.FU_WB_TestaConexao() == "true")
                    LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA_CRUD(CLS_CHECKLISTMESTRE, CHECKLISTMESTRE.UPDATE_WB, INT_IDCHECKLISTMESTRE);

                if (FU_Update_BD(INT_IDCHECKLISTMESTRE, CLS_CHECKLISTMESTRE) == "1") {
                    return new String("Salvo banco interno , não foi possível conectar-se a webapi");
                } else {
                    return new String("Erro salvar banco interno, não foi possível conectar-se a webapi");
                }

            } else {
                return new String("Não pode enviar classe Null");
            }
        } catch (Exception ex) {
            return new String("Exception: " + ex.getMessage());
            //Log.e("TAG", Log.getStackTraceString(ex));
        } finally {
            //LCLS_TPCOMPONENTE = null;
            LCLS_UTIL = null;
            LOBJ_Retorno = null;
        }
    }

    public String FU_Delete_WB(CHECKLISTMESTRE CLS_CHECKLISTMESTRE, int INT_IDCHECKLISTMESTRE) {
        CHECKLISTMESTRE LCLS_TPCOMPONENTE = null;
        ClasseUtil LCLS_UTIL = null;
        Object LOBJ_Retorno = null;

        try {
            if (CLS_CHECKLISTMESTRE != null || INT_IDCHECKLISTMESTRE > 0) {
                LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA_CRUD(CLS_CHECKLISTMESTRE, CHECKLISTMESTRE.DELETE_WB, INT_IDCHECKLISTMESTRE);
            } else {
                return new String("Não pode enviar classe Null");
            }
            return LOBJ_Retorno.toString();

        } catch (Exception ex) {
            return new String("Exception: " + ex.getMessage());
            //Log.e("TAG", Log.getStackTraceString(ex));
        } finally {
            LCLS_TPCOMPONENTE = null;
            LCLS_UTIL = null;
            LOBJ_Retorno = null;
        }
    }

    //CRUD BD
    public String FU_Insert_BD(CHECKLISTMESTRE CLS_CHECKLISTMESTRE) {
        ContentValues LCVA_VALUES;
        long LINT_RETURN = 0;
        try {

            db = CHECKLISTMESTREController.getWritableDatabase();
            CHECKLISTMESTREController.onUpgrade(db, 0, 1);
            LCVA_VALUES = new ContentValues();
            LCVA_VALUES.put("IDCHECKLIST", CLS_CHECKLISTMESTRE.getIDCHECKLIST().toString());
            //LCVA_VALUES.put("IDEQUIPAMENTO", CLS_CHECKLISTMESTRE.getIDEQUIPAMENTO().toString());
            LCVA_VALUES.put("CDTIPOCHECKLIST", CLS_CHECKLISTMESTRE.getCDTIPOCHECKLIST().toString());
            LCVA_VALUES.put("CDCADASTRORESP", CLS_CHECKLISTMESTRE.getCDCADASTRORESP().toString());
            LCVA_VALUES.put("CONTAGEMUSO", CLS_CHECKLISTMESTRE.getCONTAGEMUSO().toString());
            LCVA_VALUES.put("DTREALIZADO", CLS_CHECKLISTMESTRE.getDTREALIZADO().toString());
            LCVA_VALUES.put("LOCALREALIZADO", CLS_CHECKLISTMESTRE.getLOCALREALIZADO().toString());
            LCVA_VALUES.put("FGSITUACAO", CLS_CHECKLISTMESTRE.getFGSITUACAO().toString());
            LCVA_VALUES.put("OBSERVACAO", CLS_CHECKLISTMESTRE.getOBSERVACAO().toString());
            LCVA_VALUES.put("IDORDEMSERVICO", CLS_CHECKLISTMESTRE.getIDORDEMSERVICO().toString());
            LCVA_VALUES.put("CDCENTRORESULTADO", CLS_CHECKLISTMESTRE.getCDCENTRORESULTADO().toString());
            if (CLS_CHECKLISTMESTRE.getIDSINCRINIZA() != null)
                LCVA_VALUES.put("IDSINCRINIZA", CLS_CHECKLISTMESTRE.getIDSINCRINIZA().toString());
            if (CLS_CHECKLISTMESTRE.getIMAGEMCOMPONENTE() != null)
                LCVA_VALUES.put("IMAGEMCOMPONENTE", CLS_CHECKLISTMESTRE.getIMAGEMCOMPONENTE().toString());

            LINT_RETURN = db.insert(CHECKLISTMESTRE.TABLE, null, LCVA_VALUES);
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
            db = CHECKLISTMESTREController.getReadableDatabase();
            LCUR_CURSOR = db.query(CHECKLISTMESTRE.TABLE, campos, null, null, null, null, null, null);

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
            String[] campos = {" * "};
            String where = "IDCHECKLIST" + " = " + id;
            db = CHECKLISTMESTREController.getReadableDatabase();
            LCUR_CURSOR = db.query(CHECKLISTMESTRE.TABLE, campos, where, null, null, null, null, null);

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

    public String FU_Update_BD(int id, CHECKLISTMESTRE CLS_CHECKLISTMESTRE) {
        ContentValues LCVA_VALUES;
        String where = "";
        int LINT_RETURN = 0;
        try {
            db = CHECKLISTMESTREController.getWritableDatabase();

            where = "IDCHECKLIST" + " = " + id;

            LCVA_VALUES = new ContentValues();
            //LCVA_VALUES.put("IDEQUIPAMENTO", CLS_CHECKLISTMESTRE.getIDEQUIPAMENTO().toString());
            LCVA_VALUES.put("CDTIPOCHECKLIST", CLS_CHECKLISTMESTRE.getCDTIPOCHECKLIST().toString());
            LCVA_VALUES.put("CDCADASTRORESP", CLS_CHECKLISTMESTRE.getCDCADASTRORESP().toString());
            LCVA_VALUES.put("CONTAGEMUSO", CLS_CHECKLISTMESTRE.getCONTAGEMUSO().toString());
            LCVA_VALUES.put("DTREALIZADO", CLS_CHECKLISTMESTRE.getDTREALIZADO().toString());
            LCVA_VALUES.put("LOCALREALIZADO", CLS_CHECKLISTMESTRE.getLOCALREALIZADO().toString());
            LCVA_VALUES.put("FGSITUACAO", CLS_CHECKLISTMESTRE.getFGSITUACAO().toString());
            LCVA_VALUES.put("OBSERVACAO", CLS_CHECKLISTMESTRE.getOBSERVACAO().toString());
            LCVA_VALUES.put("IDORDEMSERVICO", CLS_CHECKLISTMESTRE.getIDORDEMSERVICO().toString());
            LCVA_VALUES.put("CDCENTRORESULTADO", CLS_CHECKLISTMESTRE.getCDCENTRORESULTADO().toString());
            if (CLS_CHECKLISTMESTRE.getIDSINCRINIZA() != null)
                LCVA_VALUES.put("IDSINCRINIZA", CLS_CHECKLISTMESTRE.getIDSINCRINIZA().toString());
            if (CLS_CHECKLISTMESTRE.getIMAGEMCOMPONENTE() != null)
                LCVA_VALUES.put("IMAGEMCOMPONENTE", CLS_CHECKLISTMESTRE.getIMAGEMCOMPONENTE().toString());

            LINT_RETURN = db.update(CHECKLISTMESTRE.TABLE, LCVA_VALUES, where, null);
            db.close();
            return Integer.toString(LINT_RETURN);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return Integer.toString(LINT_RETURN);
    }

    public void FU_Delete_BD(int id) {
        try {
            String where = "IDCHECKLIST " + " = " + id;
            db = CHECKLISTMESTREController.getReadableDatabase();
            db.delete(CHECKLISTMESTRE.TABLE, where, null);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    public String FU_Busca_Descricao_Tipo_CheckList(Object CDTIPOCHECKLIST){
        String descricao = "";
        try{
//            TIPOCHECKLIST tipochecklist = new TIPOCHECKLIST(null);
//            TIPOCHECKLISTController tipochecklistController = new TIPOCHECKLISTController(null);
//            List<TIPOCHECKLIST> tiposchecklist = tipochecklistController.FU_Read_WB(tipochecklist,
//                    Integer.parseInt((Double.toString((Double) CDTIPOCHECKLIST)).replace(".0", "")));
//            descricao = (String) tiposchecklist.get(0).getDSTIPOCHECKLIST();
        }catch (Exception e){
            e.printStackTrace();
        }
        return descricao;
    }

    public String FU_Busca_Descricao_Equipamento(Integer IDEQUIPAMENTO){
        String descricao = "";
        try{
//            EQUIPAMENTO equipamentoVazio = new EQUIPAMENTO(null);
//            EQUIPAMENTOController equipamentoController = new EQUIPAMENTOController(null);
//            List<EQUIPAMENTO> equipamento = equipamentoController.FU_Read_WB(equipamentoVazio,
////                    Integer.parseInt((Double.toString((Double) IDEQUIPAMENTO)).replace(".0", ""))
//                    IDEQUIPAMENTO
//            );
//            descricao = (String) ( equipamento.get(0).getDSEQUIPAMENTO());
        }catch (Exception e){
            e.printStackTrace();
        }
        return descricao;
    }

}
