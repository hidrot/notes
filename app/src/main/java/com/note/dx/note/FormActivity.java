package com.note.dx.note;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.widget.Toast;

import com.note.dx.note.helper.FormHelper;
import com.note.dx.note.model.Nota;
import com.note.dx.note.DAO.NotaDAO;

public class FormActivity extends Activity {

    private FormHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        helper = new FormHelper(this);

        Intent intent = getIntent();
        Nota nota = (Nota) intent.getSerializableExtra("nota");

        if(nota != null){
            helper.preencheFormulario(nota);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:
                Nota nota = helper.getNota();
                NotaDAO dao = new NotaDAO(this);

                if(nota.getId() != null){
                    dao.altera(nota);
                }else{
                    dao.insere(nota);
                }
                dao.close();
                Toast.makeText(FormActivity.this, "Nota " +nota.getTitulo()+ " salvo!", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
