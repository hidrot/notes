package com.note.dx.note.helper;

import android.widget.EditText;

import com.note.dx.note.FormActivity;
import com.note.dx.note.R;
import com.note.dx.note.model.Nota;

//Classe helper para auxiliar no cadastro e alteraçao de notas
public class FormHelper {

    private final EditText campoTitulo;
    private final EditText campoConteudo;

    private Nota nota;

    public FormHelper(FormActivity activity){
        campoTitulo = (EditText) activity.findViewById(R.id.form_titulo);
        campoConteudo = (EditText) activity.findViewById(R.id.form_conteudo);
        nota = new Nota();
    }

    public Nota getNota() {
        nota.setTitulo(campoTitulo.getText().toString());
        nota.setConteudo(campoConteudo.getText().toString());

        return nota;
    }

    public void preencheFormulario(Nota nota) {
        campoTitulo.setText(nota.getTitulo());
        campoConteudo.setText(nota.getConteudo());

        this.nota = nota;
    }
}
