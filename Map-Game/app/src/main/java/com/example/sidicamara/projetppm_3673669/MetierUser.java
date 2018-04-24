package com.example.sidicamara.projetppm_3673669;

import android.util.Log;

/**
 * Created by sidicamara on 25/01/2018.
 */

//Class correspondant a la table user au niveau de la base de donnée

public class MetierUser {
    // Notez que l'identifiant est un long

    String nom;
    String prenom;
    int id=0;



    public MetierUser( String nom, String  prenom) {
        this.nom = nom;
        this.prenom=prenom;

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void  printUser(){
        Log.i("s","NOM: "+nom+" Prenom "+ prenom);

    }

}

