package com.note.dx.note.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.note.dx.note.model.Nota;

import java.util.ArrayList;
import java.util.List;

public class NotaDAO extends SQLiteOpenHelper {

    //Instanciaçao da classe do DAO, que verifica se o banco existe e o cria se necessario
    public NotaDAO(Context context) {
        super(context, "Notes", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Notas (id INTEGER PRIMARY KEY, titulo TEXT NOT NULL, conteudo TEXT)";
        db.execSQL(sql);
    }

    @Override
    //Metodo para atualizar o banco se necessasrio
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Notas";
        db.execSQL(sql);
        onCreate(db);
    }

    //Metodo para inserir notas no banco
    public void insere(Nota nota) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDados(nota);

        db.insert("Notas", null, dados);
    }

    //Método para capturar nota especifica do banco
    private ContentValues pegaDados(Nota nota) {
        ContentValues dados = new ContentValues();
        dados.put("titulo", nota.getTitulo());
        dados.put("conteudo", nota.getConteudo());

        return dados;
    }

   //Método para capturar todas as notas do banco
    public List<Nota> buscaNotas() {
        String sql = "SELECT * from Notas;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Nota> notas = new ArrayList<>();
        while(c.moveToNext()){
            Nota nota = new Nota();
            nota.setId(c.getLong(c.getColumnIndex("id")));
            nota.setTitulo(c.getString(c.getColumnIndex("titulo")));
            nota.setConteudo(c.getString(c.getColumnIndex("conteudo")));
            notas.add(nota);
        }
        c.close();
        return notas;
    }


    //Metodo para deletar nota do banco
    public void deleta(Nota nota) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {nota.getId().toString()};
        db.delete("Notas", "id = ?", params);
    }

    //Metodo para alterar nota do banco
    public void altera(Nota nota) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = pegaDados(nota);
        String[] params = {nota.getId().toString()};
        db.update("Notas", dados, "id = ?", params);
    }
}
