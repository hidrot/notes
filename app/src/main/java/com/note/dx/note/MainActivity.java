package com.note.dx.note;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.note.dx.note.DAO.NotaDAO;
import com.note.dx.note.model.Dados;
import com.note.dx.note.model.Nota;

import java.util.List;

public class MainActivity extends Activity {

    private ListView listaNotas;
    private GoogleSignInAccount conta;

    @Override
    //Criaçao da Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        conta = ((Dados) this.getApplication()).getAccount();

       

        listaNotas = findViewById(R.id.lista_notas);

        listaNotas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //Click do item na lista
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Nota nota = (Nota) listaNotas.getItemAtPosition(position);
                Intent form = new Intent(MainActivity.this, FormActivity.class);
                form.putExtra("nota", nota);
                startActivity(form);
            }
        });

        Button novoAluno = findViewById(R.id.nova_nota);
        novoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToForm = new Intent(MainActivity.this, FormActivity.class);
                startActivity(goToForm);
            }
        });

        registerForContextMenu(listaNotas);
    }

    @Override
    //Retorno à activity
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    //Carregar a lista de notas
    private void carregaLista() {
        NotaDAO dao = new NotaDAO(this);
        List<Nota> notas = dao.buscaNotas();
        dao.close();
        ArrayAdapter<Nota> adapter = new ArrayAdapter<Nota>(this, android.R.layout.simple_list_item_1, notas);
        listaNotas.setAdapter(adapter);
    }

    @Override
    //Critar menu de contexto
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Nota nota = (Nota) listaNotas.getItemAtPosition(info.position);

                NotaDAO dao = new NotaDAO(MainActivity.this);
                dao.deleta(nota);
                dao.close();

                carregaLista();
                return false;
            }
        });
    }
}
