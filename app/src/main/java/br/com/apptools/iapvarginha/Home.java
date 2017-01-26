package br.com.apptools.iapvarginha;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import br.com.apptools.iapvarginha.database.DataBaseHelper;
import br.com.apptools.iapvarginha.dominio.RepositorioMembro;
import br.com.apptools.iapvarginha.dominio.entidades.Membro;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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
                Intent intent = new Intent(Home.this, Home.class);
                startActivity(intent);
                finish();
            }
        });

        //Array status membro
        mAdpStatusMembro = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        mAdpStatusMembro.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpnStatusMembro.setAdapter(mAdpStatusMembro);

        mAdpTipoMembro.add("Ativo");
        mAdpTipoMembro.add("Inativo");

        //Ação de cancelar o cadastro_usuario
        mBtnCancCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Home.class);
                startActivity(intent);
                finish();
            }
        });

        //Ação de cadastrar
        mBtnSalvarCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inserirMembro();
                Toast.makeText(getApplicationContext(),"Membro cadastrado com Sucesso", Toast.LENGTH_LONG).show();
                mEdtNomeMembro.getText().clear();
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();




        if (id == R.id.acaoSobre) { //Abre a tela de informações sobre o App

            android.app.AlertDialog.Builder dlg = new android.app.AlertDialog.Builder(this);
            dlg.setMessage("        Desenvolvido por:\n" +
                    "       AppTools Tecnologia\n\n" +
                    "       Versão da aplicação: 1.0.0\n\n" +
                    "       Contato:\n" +
                    "       apptoolstecnologia@gmail.com\n" +
                    "       (35)99999-9999");
            dlg.setNeutralButton("OK", null);
            dlg.show();

            return true;
        }

        if (id == R.id.acaoAjuda) { //Abrea tela com tópicos de ajuda sobre o App

            android.app.AlertDialog.Builder dlg = new android.app.AlertDialog.Builder(this);
            dlg.setMessage("Para realizar o Login no Aplicativo, é necessário o cadastro de seus dados, como: telefone, nome, email e senha.\n\n" +
                    "Na tela de Login, clique na opção 'Primeiro Acesso? Cadastre-se' e preencha o formulário.");
            dlg.setNeutralButton("OK", null);
            dlg.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.cadastroMembro) {
            Intent intent = new Intent(Home.this, Home.class);
            startActivity(intent);
            finish();

        }  else if (id == R.id.chamadaEB) {
            Intent intent = new Intent(Home.this, ChamadaEB.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.chamadaCJ) {
            Intent intent = new Intent(Home.this, ChamadaCJ.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.chamadaCC) {
            Intent intent = new Intent(Home.this, ChamadaCC.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.configuracoes) {

        } else if (id == R.id.importar) {

        } else if (id == R.id.exportar) {

        } else if (id == R.id.sair) {
            Intent intent = new Intent(Home.this, Login.class);
            startActivity(intent);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
