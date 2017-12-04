package com.note.dx.note.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import com.note.dx.note.model.Nota;

public class NotaDAO extends SQLiteOpenHelper {

    public NotaDAO(Context context) {
        super(context, "Notes", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Notas (id INTEGER PRIMARY KEY, titulo TEXT NOT NULL, conteudo TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Notas";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insere(Nota nota) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDados(nota);

        db.insert("Notas", null, dados);
    }

    private ContentValues pegaDados(Nota nota) {
        ContentValues dados = new ContentValues();
        dados.put("titulo", nota.getTitulo());
        dados.put("conteudo", nota.getConteudo());

        return dados;
    }

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

    public void deleta(Nota nota) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {nota.getId().toString()};
        db.delete("Notas", "id = ?", params);
    }

    public void altera(Nota nota) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = pegaDados(nota);
        String[] params = {nota.getId().toString()};
        db.update("Notas", dados, "id = ?", params);
    }
}
