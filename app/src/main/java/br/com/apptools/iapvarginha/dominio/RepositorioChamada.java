package br.com.apptools.iapvarginha.dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import java.util.Date;

import br.com.apptools.iapvarginha.database.DataBaseHelper;
import br.com.apptools.iapvarginha.dominio.entidades.Chamada;

public class RepositorioChamada {

    SQLiteDatabase database;
    private DataBaseHelper dataBaseHelper;

    private RepositorioChamada (Context context) {
        dataBaseHelper = new DataBaseHelper(context);
    }

    private SQLiteDatabase getDatabase () {
        if (database == null) {
            database = dataBaseHelper.getWritableDatabase();
        }
        return database;
    }

    public void inserirChamada (Chamada chamada) {

        ContentValues values = new ContentValues();

        //values.put(DataBaseHelper.Chamada.COLUNA_IDMEMBROCHADA, chamada.get_idMembro());
        values.put(DataBaseHelper.Chamada.COLUNA_STATUSCHAMADA, chamada.getStatusChamada());
        values.put(DataBaseHelper.Chamada.COLUNA_DATACHAMADA, chamada.getDataChamada().getTime());

        database.insertOrThrow(DataBaseHelper.Chamada.TABELA_CHAMADA, null, values);
    }

    public ArrayAdapter<Chamada> buscaChamada(Context context) {

        ArrayAdapter<Chamada> adpChamada = new ArrayAdapter <Chamada>(context, android.R.layout.simple_list_item_1);

        Cursor cursor = database.query(DataBaseHelper.Chamada.TABELA_CHAMADA, null, null, null, null, null, null);

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();

            do {

                Chamada chamada = new Chamada();


                //chamada.set_idMembro(cursor.getInt(1));
                chamada.setStatusChamada(cursor.getString(1));
                chamada.setDataChamada(new Date(cursor.getLong(2)));

                adpChamada.add(chamada);

            } while (cursor.moveToNext());
        }

        return adpChamada;
    }
}
