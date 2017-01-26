package br.com.apptools.iapvarginha.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper{

    private static final String NOME_BANCO= "iapVGA";
    private static final int VERSAO_BANCO = 1;

    public DataBaseHelper(Context context) {

        super(context,NOME_BANCO,null,VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Tabela Usuário
        db.execSQL("CREATE TABLE Usuario(_idUsuario INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT , login TEXT, senha TEXT, email TEXT);");

        //Tabela Membro
        db.execSQL("CREATE TABLE Membro (_idMembro INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nomeMembro TEXT (50), sobrenomeMembro TEXT (50), tipoMembro TEXT, statusMembro TEXT)");

        //Tabela dados culto
        db.execSQL("CREATE TABLE Numero (_idNumeros INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "qtdBiblia int (2), qtdEstudoDiario int (2), dataCulto TEXT)");

        //Tabela Chamada
        db.execSQL("CREATE TABLE Chamada (_idChamada INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "statusChamada TEXT (1), dataChamada TEXT)");
        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Tabela Membro
    public static class Usuario{

        public static final String TABELA_USUARIO = "Usuario";
        public static final String COLUNA_IDUSUARIO = "_idUsuario";
        public static final String COLUNA_NOMEUSUARIO = "nome";
        public static final String COLUNA_LOGIN = "login";
        public static final String COLUNA_SENHA = "senha";
        public static final String COLUNA_EMAIL = "email";

        public static final String[] COLUNAS_USUARIO = new String[]{
                COLUNA_IDUSUARIO, COLUNA_NOMEUSUARIO, COLUNA_LOGIN, COLUNA_SENHA, COLUNA_EMAIL
        };
    }

    public static class Membro {
        public static final String TABELA_MEMBRO = "Membro";
        public static final String COLUNA_IDMEMBRO = "_idMembro";
        public static final String COLUNA_NOMEMEMBRO = "nomeMembro";
        public static final String COLUNA_SOBRENOMEMEMBRO = "sobrenomeMembro";
        public static final String COLUNA_TIPOMEMBRO = "tipoMembro";
        public static final String COLUNA_STATUSMEMBRO = "statusMembro";

        public static final String[] COLUNAS_MEMBRO = new String[] {
                COLUNA_IDMEMBRO, COLUNA_NOMEMEMBRO, COLUNA_SOBRENOMEMEMBRO, COLUNA_TIPOMEMBRO, COLUNA_STATUSMEMBRO
        };
    }

    public static class Numero {
        public static final String TABELA_NUMEROS = "Numero";
        public static final String COLUNA_IDNUMEROS = "_idNumero";
        public static final String COLUNA_QTDBIBLIAS = "qtdBiblia";
        public static final String COLUNA_QTDESTUDODIARIO = "qtdEstudoDiario";
        public static final String COLUNA_DATACULTO = "dataCulto";

        public static final String [] COLUNAS_NUMEROS = new String[] {
                COLUNA_IDNUMEROS, COLUNA_QTDBIBLIAS, COLUNA_QTDESTUDODIARIO, COLUNA_DATACULTO
        };
    }

    public static class Chamada {
        public static final String TABELA_CHAMADA = "Chamada";
        public static final String COLUNA_IDCHAMADA = "_idChamada";
        //public static final String COLUNA_IDMEMBROCHAMADA = "_idMembro";
        public static final String COLUNA_STATUSCHAMADA = "statusChamada";
        public static final String COLUNA_DATACHAMADA = "dataChamada";

        public static final String[] COLUNAS_CHAMADA = new String[] {
          COLUNA_IDCHAMADA, /*COLUNA_IDMEMBROCHAMADA,*/ COLUNA_STATUSCHAMADA, COLUNA_DATACHAMADA
        };
    }
}

