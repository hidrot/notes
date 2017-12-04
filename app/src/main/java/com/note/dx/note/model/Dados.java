package com.note.dx.note.model;

import android.app.Application;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.ArrayList;

//Classe para modelagem de informações com os dados da aplicação
public class Dados extends Application{
    GoogleSignInAccount account;
    ArrayList<Nota> notas;

    public GoogleSignInAccount getAccount(){
        return account;
    }

    public void setAccount(GoogleSignInAccount account){
        this.account=account;
    }

    public void addNota(Nota nota){
        notas.add(nota);
    }

    public void removeNota(Nota nota){
        notas.remove(nota);
    }
}
