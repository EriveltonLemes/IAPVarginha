package br.com.apptools.iapvarginha;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

import br.com.apptools.iapvarginha.dominio.RepositorioUsuario;
import br.com.apptools.iapvarginha.dominio.entidades.Usuario;

public class CadastrarUsuario extends AppCompatActivity {

    //Declaração de variaveis para recuperação de dados

    EditText mEdtCadLogin;
    EditText mEdtCadNomeUsuario;
    EditText mEdtCadSenha;
    EditText mEdtCadEmail;
    EditText mEdtSenhaAdmin;
    Button mBtnSalvarCad;
    Button mBtnCancelarCad;

    RepositorioUsuario repositorioUsuario;
    Usuario usuario;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_usuario);

        mEdtCadLogin = (EditText) findViewById(R.id.edtCadLogin);
        mEdtCadNomeUsuario = (EditText) findViewById(R.id.edtCadNomeUsuario);
        mEdtCadSenha = (EditText) findViewById(R.id.edtCadSenha);
        mEdtCadEmail = (EditText) findViewById(R.id.edtCadEmail);
        mEdtSenhaAdmin = (EditText) findViewById(R.id.edtSenhaAdmin);
        mBtnSalvarCad = (Button) findViewById(R.id.btnSalvarCad);
        mBtnCancelarCad = (Button) findViewById(R.id.btnCancelarCad);

        mBtnSalvarCad.setOnClickListener(new View.OnClickListener() { //Função do botão de gravar o cadastro do usuário
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastrarUsuario.this, Login.class);

                //Teste de login
                usuario = new Usuario();
                repositorioUsuario = new RepositorioUsuario(getApplicationContext());
                usuario.setNomeUsuario(mEdtCadNomeUsuario.getText().toString());
                usuario.setEmail(mEdtCadEmail.getText().toString());
                usuario.setLogin(mEdtCadLogin.getText().toString());
                usuario.setSenha(mEdtCadSenha.getText().toString());

                String senhaAdmin = mEdtSenhaAdmin.getText().toString();

                if (senhaAdmin.equals("admin")) {

                    if (!repositorioUsuario.checkIFExistis(mEdtCadLogin.getText().toString())) {
                        Long code = repositorioUsuario.salvarUsuario(usuario);


                        if (code == -1) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Não foi possível inserir o seu usuário", Toast.LENGTH_LONG);
                            toast.show();
                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(), "Usuário adicionado com sucesso!", Toast.LENGTH_LONG);
                            toast.show();
                            startActivity(intent);
                            finish();
                        }
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Telefone já esta cadastrado", Toast.LENGTH_LONG);
                        toast.show();
                    }
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Senha do administrador inválida!", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        //Função do botão de cancelar o cadastro do usuário
        mBtnCancelarCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastrarUsuario.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
