package br.com.apptools.iapvarginha;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import br.com.apptools.iapvarginha.database.DataBaseHelper;
import br.com.apptools.iapvarginha.dominio.RepositorioNumero;
import br.com.apptools.iapvarginha.dominio.RepositorioMembro;
import br.com.apptools.iapvarginha.dominio.entidades.Membro;
import br.com.apptools.iapvarginha.dominio.entidades.Numero;

public class ChamadaEB extends Activity{

    EditText mEdtQtdBibliasEB;
    EditText mEdtQtdEstudoDiarioEB;
    ListView mLstvMembrosEB;
    Button mBtnSalvChamadaEB;
    Button mBtnCancChamadaEB;
    ArrayAdapter<Membro> adpMembros;

    DataBaseHelper dataBaseHelper;
    SQLiteDatabase database;

    RepositorioMembro repositorioMembro;
    Membro membro;

    RepositorioNumero repositorioNumero;
    Numero numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.escola_biblica);

        mLstvMembrosEB = (ListView) findViewById(R.id.lstvMembrosEB);
        mBtnSalvChamadaEB = (Button) findViewById(R.id.btnSalvChamadaEB);
        mBtnCancChamadaEB = (Button) findViewById(R.id.btnCancChamadaEB);
        mEdtQtdBibliasEB = (EditText) findViewById(R.id.edtQtdBibliasEB);
        mEdtQtdEstudoDiarioEB = (EditText) findViewById(R.id.edtQtdEstudoDiarioEB);

        membro = new Membro();
        numero = new Numero();

        mBtnSalvChamadaEB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChamadaEB.this,Home.class);
                inserirNumeros();
                Toast.makeText(getApplicationContext(),"Dados armazenados com sucesso!", Toast.LENGTH_LONG).show();
                startActivity(intent);
                finish();
            }
        });

        mBtnCancChamadaEB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChamadaEB.this,Home.class);
                startActivity(intent);
                finish();
            }
        });

        //Conexão com o banco de dados
        try{
            dataBaseHelper = new DataBaseHelper(this);
            database = dataBaseHelper.getWritableDatabase();

            repositorioMembro = new RepositorioMembro(database);
            adpMembros = repositorioMembro.buscaMembro(this);
            mLstvMembrosEB.setAdapter(adpMembros);

            repositorioNumero = new RepositorioNumero(database);


        }catch (SQLException ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao persistir o banco: " + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
    }

    private void inserirNumeros () {

        try{
            numero = new Numero();


            numero.setDataCulto("");
            numero.setQtdBiblia(mEdtQtdBibliasEB.getInputType());
            numero.setQtdEstudoDiario(mEdtQtdEstudoDiarioEB.getInputType());

            repositorioNumero.inserirNumeros(numero);

        }catch (Exception ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao inserir os dados: " + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
    }

    /*private void inserirChamada () {

        try{
            membro = new Membro();

            membro.setStatusMembro("");

        }catch (Exception ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao inserir os dados: " + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
    }*/


}
