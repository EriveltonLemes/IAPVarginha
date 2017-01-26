package br.com.apptools.iapvarginha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.apptools.iapvarginha.database.DataBaseHelper;
import br.com.apptools.iapvarginha.dominio.RepositorioUsuario;

public class Login extends AppCompatActivity {

    //Declaração de variaveis para retorno de dados
    EditText mEdtLogin;
    EditText mEdtSenha;
    Button mBtnLogar;
    Button mBtnLimpar;
    TextView mTxtCadastro;

    DataBaseHelper dataBaseHelper;
    RepositorioUsuario repositorioUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        dataBaseHelper = new DataBaseHelper(this);
        mEdtLogin = (EditText) findViewById(R.id.edtLogin);
        mEdtSenha = (EditText) findViewById(R.id.edtSenha);
        mBtnLogar = (Button) findViewById(R.id.btnEntrar);
        mBtnLimpar = (Button) findViewById(R.id.btnLimparLogin);
        mTxtCadastro = (TextView) findViewById(R.id.txtCadastro);

        repositorioUsuario = new RepositorioUsuario(this);

        //Função do botão que limpa (apaga os caracteres) os campos de CPF e SENHA da tela de content_login
        mBtnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEdtLogin.getText().clear();
                mEdtSenha.getText().clear();
            }
        });

        //Função do TextView que abre a tela de cadastro_usuario de novo usuário
        mTxtCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, CadastrarUsuario.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //Função de login. Testa se o usuário existe e se os dados de login estão corretos.
    public void logar (View view) {
        String login = mEdtLogin.getText().toString();
        String senha = mEdtSenha.getText().toString();

        boolean validacao = true;

        if (login == null || login.equals("")) {
            validacao = false;
            Toast.makeText(getApplicationContext(),"Nenhum campo pode estar vazio",Toast.LENGTH_LONG).show();
        }

        if (senha == null || senha.equals("")) {
            validacao = false;
            Toast.makeText(getApplicationContext(),"Nenhum campo pode estar vazio",Toast.LENGTH_LONG).show();
        }

        if (validacao) {
            if (repositorioUsuario.logarUsuario(login, senha)){
                startActivity(new Intent(this, Home.class));
                finish();
            } else {
                Toast.makeText(getApplicationContext(),"Usuário e/ou senha incorretos", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    //Menu superior
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    //Lista de opções do menu superior
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //Ação de click para opção de INFORMAÇÕES SOBRE O APLICATIVO
        if (id == R.id.acaoSobre) {

            android.app.AlertDialog.Builder dlg = new android.app.AlertDialog.Builder(this);
            dlg.setMessage("        Desenvolvido por:\n" +
                    "       AppTools Tecnologia\n\n" +
                    "       Versão da aplicação: 1.0.0\n\n" +
                    "       Contato:\n" +
                    "       apptools@apptools.com\n" +
                    "       (35)99999-9999");
            dlg.setNeutralButton("OK", null);
            dlg.show();

            return true;
        }

        //Ação de click para opção de AJUDA
        if (id == R.id.acaoAjuda) {

            android.app.AlertDialog.Builder dlg = new android.app.AlertDialog.Builder(this);
            dlg.setMessage("Para realizar o Login no aplicativo, é necessário o cadastro_usuario de seus dados, como: CPF, nome, email e senha.\n\n" +
                    "Na tela de Login, clique na opção 'Primeiro Acesso? Cadastre-se' e preencha o formulário.");
            dlg.setNeutralButton("OK", null);
            dlg.show();

            return true;
        }

        //Ação de click para a opção de SAIR do App. Fecha o App
        if (id == R.id.acaoSair) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}