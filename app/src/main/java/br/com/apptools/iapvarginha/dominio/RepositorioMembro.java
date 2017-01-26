package br.com.apptools.iapvarginha.dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import br.com.apptools.iapvarginha.database.DataBaseHelper;
import br.com.apptools.iapvarginha.dominio.entidades.Membro;


public class RepositorioMembro {

    private SQLiteDatabase database;
    private DataBaseHelper dataBaseHelper;

    public RepositorioMembro(SQLiteDatabase database) {

        this.database = database;
    }

    public void inserirMembro (Membro membro) {

        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.Membro.COLUNA_NOMEMEMBRO, membro.getNomeMembro());
        values.put(DataBaseHelper.Membro.COLUNA_SOBRENOMEMEMBRO, membro.getSobrenomeMembro());
        values.put(DataBaseHelper.Membro.COLUNA_TIPOMEMBRO, membro.getTipoMembro());
        values.put(DataBaseHelper.Membro.COLUNA_STATUSMEMBRO, membro.getStatusMembro());

        database.insertOrThrow(DataBaseHelper.Membro.TABELA_MEMBRO, null, values);
    }

    public ArrayAdapter<Membro> buscaMembro(Context context) {

        ArrayAdapter<Membro> adpMembro = new ArrayAdapter <Membro>(context, android.R.layout.simple_list_item_1);

        Cursor cursor = database.query(DataBaseHelper.Membro.TABELA_MEMBRO, null, null, null, null, null, null);

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();

            do {

                Membro membro = new Membro();

                //membro.getTipoLogradouro();
                membro.setNomeMembro(cursor.getString(1));
                membro.setSobrenomeMembro(cursor.getString(2));
                membro.setTipoMembro(cursor.getString(3));
                membro.setStatusMembro(cursor.getString(4));

                adpMembro.add(membro);

            } while (cursor.moveToNext());
        }

        return adpMembro;
    }

}