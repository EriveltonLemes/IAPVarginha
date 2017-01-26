package br.com.apptools.iapvarginha.dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;


import br.com.apptools.iapvarginha.database.DataBaseHelper;
import br.com.apptools.iapvarginha.dominio.entidades.Numero;

public class RepositorioNumero {

    private SQLiteDatabase database;
    private DataBaseHelper dataBaseHelper;


    public RepositorioNumero(SQLiteDatabase database) {

        this.database = database;
    }

    private SQLiteDatabase getDatabase () {
        if (database == null) {
            database = dataBaseHelper.getWritableDatabase();
        }
        return database;
    }

    public void inserirNumeros (Numero numero) {

        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.Numero.COLUNA_QTDBIBLIAS, numero.getQtdBiblia());
        values.put(DataBaseHelper.Numero.COLUNA_QTDESTUDODIARIO, numero.getQtdEstudoDiario());
        values.put(DataBaseHelper.Numero.COLUNA_DATACULTO, numero.getDataCulto());

        database.insertOrThrow(DataBaseHelper.Numero.TABELA_NUMEROS, null, values);
    }

    public ArrayAdapter <Numero> buscaNumeros(Context context) {

        ArrayAdapter<Numero> adpNumero = new ArrayAdapter <Numero>(context, android.R.layout.simple_list_item_1);

        Cursor cursor = database.query(DataBaseHelper.Numero.TABELA_NUMEROS, null, null, null, null, null, null);

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();

            do {

                Numero numero = new Numero();

                //imovel.getTipoLogradouro();
                numero.setQtdBiblia(cursor.getInt(1));
                numero.setQtdEstudoDiario(cursor.getInt(2));
                numero.setDataCulto(cursor.getString(3));

                adpNumero.add(numero);

            } while (cursor.moveToNext());
        }

        return adpNumero;
    }

}
