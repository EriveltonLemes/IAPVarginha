package br.com.apptools.iapvarginha.dominio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.apptools.iapvarginha.database.DataBaseHelper;
import br.com.apptools.iapvarginha.dominio.entidades.Usuario;

public class RepositorioUsuario {

    SQLiteDatabase database;
    private DataBaseHelper dataBaseHelper;

    public RepositorioUsuario(Context context) {
        dataBaseHelper = new DataBaseHelper(context);
    }

    private SQLiteDatabase getDatabase () {
        if (database == null) {
            database = dataBaseHelper.getWritableDatabase();
        }
        return database;
    }

    private Usuario criarUsuario (Cursor cursor) {
        Usuario user = new Usuario(
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Usuario.COLUNA_IDUSUARIO)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Usuario.COLUNA_NOMEUSUARIO)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Usuario.COLUNA_LOGIN)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Usuario.COLUNA_SENHA)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Usuario.COLUNA_EMAIL))
        );

        return user;
    }

    public List<Usuario> listarUsuario () {
        Cursor cursor = getDatabase().query(DataBaseHelper.Usuario.TABELA_USUARIO,
                DataBaseHelper.Usuario.COLUNAS_USUARIO, null, null, null, null, null);

        List<Usuario> usuario = new ArrayList<Usuario>();
        while (cursor.moveToNext()) {
            Usuario user = criarUsuario(cursor);
            usuario.add(user);
        }
        cursor.close();
        return usuario;
    }

    public long salvarUsuario(Usuario usuario) {
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.Usuario.COLUNA_LOGIN, usuario.getLogin());
        values.put(DataBaseHelper.Usuario.COLUNA_NOMEUSUARIO, usuario.getNomeUsuario());
        values.put(DataBaseHelper.Usuario.COLUNA_SENHA, usuario.getSenha());
        values.put(DataBaseHelper.Usuario.COLUNA_EMAIL, usuario.getEmail());

        if (usuario.get_idUsuario() != null) {
            return getDatabase().update(DataBaseHelper.Usuario.TABELA_USUARIO, values,
                    "_idUsuario = ?", new String[]{usuario.get_idUsuario().toString()});
        }

        return getDatabase().insert(DataBaseHelper.Usuario.TABELA_USUARIO, null, values);
    }

    public boolean removerUsuario (int id) {
        return getDatabase().delete(DataBaseHelper.Usuario.TABELA_USUARIO,
                "_idUsuario = ?", new String[]{Integer.toString(id)}) > 0;

    }

    public Usuario buscaUsuarioID (int id) {
        Cursor cursor = getDatabase().query(DataBaseHelper.Usuario.TABELA_USUARIO,
                DataBaseHelper.Usuario.COLUNAS_USUARIO, "_idUsuario = ?", new String[] {Integer.toString(id)}, null, null, null);

        if (cursor.moveToNext()) {
            Usuario user = criarUsuario(cursor);
            cursor.close();
            return user;
        }
        return null;
    }

    public boolean logarUsuario (String login, String senha) {
        Cursor cursor = getDatabase().query(DataBaseHelper.Usuario.TABELA_USUARIO,
                null, "login = ? AND senha = ?", new String[] {login, senha}, null, null, null);

        if (cursor.moveToFirst()) {
            return  true;
        }
        return false;
    }

    public boolean checkIFExistis(String login){
        database = dataBaseHelper.getReadableDatabase();

        Cursor cursor = database.rawQuery("select * from " + DataBaseHelper.Usuario.TABELA_USUARIO + " where " +
                DataBaseHelper.Usuario.COLUNA_LOGIN + " = ? ", new String[]{login});

        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        else{
            return true;
        }
    }

    public void fechar () {
        dataBaseHelper.close();
        database = null;
    }
}
