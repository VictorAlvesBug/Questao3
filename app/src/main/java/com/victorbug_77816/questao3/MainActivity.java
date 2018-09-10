package com.victorbug_77816.questao3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText edtTitulo;
    private EditText edtConteudo;
    private final String DICIONARIO = "NOTAS";
    private final String NOT_FOUND = " ~~~ NOTA NÃO ENCONTRADA ~~~ ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void doAdicionarNota(View view)
    {
        //ATRELA EDITTEXTS DO CODIGO AOS DA ACTIVITY
        edtTitulo = (EditText) findViewById(R.id.edtTitulo);
        edtConteudo = (EditText) findViewById(R.id.edtConteudo);

        //RECUPERA TITULO E CONTEUDO DIGITADOS
        String titulo = edtTitulo.getText().toString();
        String conteudo = edtConteudo.getText().toString();

        //INSTANCIA SharedPreferences E SETA O DICIONARIO A SER UTILIZADO
        SharedPreferences sh = getSharedPreferences(DICIONARIO, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sh.edit();

        //INSERE A CHAVE E O VALOR A SER GRAVADO
        ed.putString(titulo, conteudo);

        /// SÃO SIMILARES, POREM:
        //SINCRONO
        //ed.commit();
        //ASSINCRONO
        //ed.apply();

        //SALVA PAR (CHAVE, VALOR)
        ed.apply();

        edtTitulo.setText("");
        edtConteudo.setText("");

        Toast.makeText(this, "Nota (" + titulo + ") foi adicionada", Toast.LENGTH_LONG).show();
    }

    public void doBuscarNota(View view)
    {
        //ATRELA EDITTEXTS DO CODIGO AOS DA ACTIVITY
        edtTitulo = (EditText) findViewById(R.id.edtTitulo);
        edtConteudo = (EditText) findViewById(R.id.edtConteudo);

        //RECUPERA TITULO DIGITADO
        String titulo = edtTitulo.getText().toString();

        //INSTANCIA SharedPreferences E SETA O DICIONARIO A SER UTILIZADO
        SharedPreferences sh = getSharedPreferences(DICIONARIO, Context.MODE_PRIVATE);

        //TENTA RECUPERAR O VALOR ATRELADO ÀQUELA CHAVE
        String conteudo = sh.getString(titulo, NOT_FOUND);

        if(conteudo == NOT_FOUND)
        {
            //PAR (CHAVE, VALOR) NÃO ENCONTRADO
            Toast.makeText(this, "Desculpe, não encontramos nenhuma nota com título " + titulo, Toast.LENGTH_LONG).show();
        }
        else
        {
            //ARMAZENA O VALOR ATRELADO A CHAVE NO EDITTEXT DE CONTEUDO
            edtConteudo.setText(conteudo);
        }
    }
}
