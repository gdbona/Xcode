package com.example.giovanni.xcodeiam.Controller;

import android.os.Environment;

import com.example.giovanni.xcodeiam.Model.EMFSESSION;


/**
 * Created by Giovanni on 29/01/2018.
 */
public class ClsAutenticacao {

    public static String FU_AutenticaUsuario() {

        EMFSESSION LCLS_SESSION = null;
        Object LOBJ_Retorno = null;
        String LSTR_RETURN = "false";

        try {

            LCLS_SESSION = new EMFSESSION(null);

            if (EMFSESSION.LOCAL_IDSESSION == 0) {
                LCLS_SESSION.setDHSESSION("0");
                LCLS_SESSION.setEQUIPMENT("2");
                LCLS_SESSION.setPASSWORD("MTM");
                LCLS_SESSION.setSGENVIRONMENT("GESTOR");
                LCLS_SESSION.setSGLANGUAGE("PT-BR");
                LCLS_SESSION.setSGUSER("MASTER");
            } else {
                LCLS_SESSION.setIDSESSION(EMFSESSION.LOCAL_IDSESSION);
            }
            LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA(LCLS_SESSION, "gestoricl/autentica", "POST");
            if (LOBJ_Retorno != null) {
                if (LOBJ_Retorno.toString().toUpperCase().contains("EXCEPTION:"))
                {return LOBJ_Retorno.toString();}

                LCLS_SESSION = null;
                LCLS_SESSION = (EMFSESSION) LOBJ_Retorno;

                //if(LCLS_SESSION.getMENSAGEM().length() > 6){
                // return LCLS_SESSION.getMENSAGEM().toString();}

                EMFSESSION.LOCAL_IDSESSION = LCLS_SESSION.getIDSESSION();

                //EMFSESSIONConroller controle = new EMFSESSIONConroller(EMFSESSIONConroller.contexts);
                //controle.Insert(LCLS_SESSION);
                //controle.ReturnData();//controle.ReturnDataById(11254);
                //controle.Update(11254, LCLS_SESSION);
                //controle.Delete(11254);
//                TIPOCOMPONENTE LCLS_TPCOMPONENTE=new TIPOCOMPONENTE(null);
//                ClsUtil LCLS_UTIL = new ClsUtil();
//
//                LCLS_TPCOMPONENTE.setCDGRUPOCOMPONENTE("1");
//                LCLS_TPCOMPONENTE.setCDTIPOCOMPONENTE("2");
//                LCLS_TPCOMPONENTE.setDSTIPOCOMPONENTE("3");
//                LCLS_TPCOMPONENTE.setFGACTIVE("a");
//                LCLS_TPCOMPONENTE.setFGEXIGEID("b");
//                LCLS_TPCOMPONENTE.setFGEXIGEMATERIAL("s");
////                LCLS_TPCOMPONENTE.setIMAGEMCOMPONENTE((byte[])
////                        Base64.encodeToString(
////                                LCLS_UTIL.FU_converteArquivoParaArrayBytes(
////                                        Environment.getExternalStorageDirectory()+"/foto.jpg")
////                                , Base64.DEFAULT).getBytes());
//                LCLS_TPCOMPONENTE.setIMAGEMCOMPONENTE(
//                        LCLS_UTIL.FU_converteArquivoParaArrayBytes(
//                                Environment.getExternalStorageDirectory()+"/MOV_0002.mp4")
//                );
//                LCLS_UTIL = null;
//                LOBJ_Retorno = ConexaoWebAPI.FU_WB_EXECUTA(LCLS_TPCOMPONENTE, "tipocomponente/insere", "POST");

                LSTR_RETURN = "true";
            }
        } catch (Exception ex) {
            return new String("Exception: " + ex.getMessage());
//            Log.e("TAG", Log.getStackTraceString(ex));
        } finally {
        }
        return LSTR_RETURN;
    }


    //----------------------------------codigo pode ser util para teste---------------------------------------
//        if (BuildInfo.DEBUG) {
//        return debugBackground;
//    } else {
//        return getBaseContext().getWallpaper();
//    }
}
