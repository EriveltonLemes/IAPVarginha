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
import android.widget.Spinner;
import android.widget.Toast;

import br.com.apptools.iapvarginha.database.DataBaseHelper;
import br.com.apptools.iapvarginha.dominio.RepositorioMembro;
import br.com.apptools.iapvarginha.dominio.entidades.Membro;

public class CadastrarMembro extends Activity{

    EditText mEdtNomeMembro;
    EditText mEdtSobrenomeMembro;
    Spinner mSpnTipoMembro;
    Spinner mSpnStatusMembro;
    Button mBtnSalvarCad;
    Button mBtnCancCad;
    ArrayAdapter<String> mAdpTipoMembro;
    ArrayAdapter<String> mAdpStatusMembro;

    DataBaseHelper dataBaseHelper;
    SQLiteDatabase database;
    RepositorioMembro repositorioMembro;
    Membro membro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastrar_membro);

        mEdtNomeMembro = (EditText) findViewById(R.id.edtNomeMembro);
        mEdtSobrenomeMembro = (EditText) findViewById(R.id.edtsobrenomeMembro);
        mSpnTipoMembro = (Spinner) findViewById(R.id.spnTipoMembro);
        mSpnStatusMembro = (Spinner) findViewById(R.id.spnStatusMembro);
        mBtnSalvarCad = (Button) findViewById(R.id.btnSalvarCad);
        mBtnCancCad = (Button) findViewById(R.id.btnCancelarCad);

        //Array tipo membro
        mAdpTipoMembro = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        mAdpTipoMembro.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpnTipoMembro.setAdapter(mAdpTipoMembro);

        mAdpTipoMembro.add("Escola Bíblica");
        mAdpTipoMembro.add("Classe Jovens");
        mAdpTipoMembro.add("Classe Crianças");


        //Array status membro
        mAdpStatusMembro = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        mAdpStatusMembro.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpnStatusMembro.setAdapter(mAdpStatusMembro);

        mAdpStatusMembro.add("Ativo");
        mAdpStatusMembro.add("Inativo");

        //Ação de cancelar o cadastro_usuario
        mBtnCancCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastrarMembro.this, Home.class);
                startActivity(intent);
                finish();
            }
        });

        //Ação de cadastrar
        mBtnSalvarCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inserirMembro();
                mEdtNomeMembro.getText().clear();
                mEdtSobrenomeMembro.getText().clear();
                Toast.makeText(getApplicationContext(),"Membro cadastrado com Sucesso", Toast.LENGTH_LONG).show();
            }
        });

        //Conexão com o banco de dados
        try{
            dataBaseHelper = new DataBaseHelper(this);
            database = dataBaseHelper.getWritableDatabase();
            repositorioMembro = new RepositorioMembro(database);

        }catch (SQLException ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao persistir o banco: " + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
    }

    //Método de inserir dados de um membro
    private void inserirMembro () {

        try{
        membro = new Membro();

        membro.setNomeMembro(mEdtNomeMembro.getText().toString());
        membro.setSobrenomeMembro(mEdtSobrenomeMembro.getText().toString());
        membro.setTipoMembro(String.valueOf(mSpnTipoMembro.getSelectedItem()));
        membro.setStatusMembro(String.valueOf(mSpnStatusMembro.getSelectedItem()));

        repositorioMembro.inserirMembro(membro);

        }catch (Exception ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao inserir os dados: " + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
    }
}
