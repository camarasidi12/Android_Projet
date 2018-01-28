package com.example.sidicamara.projetppm_3673669;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class MainActivity extends AppCompatActivity {


    public static EditText prenom;
    public static EditText nom;
    private Button logIn;
    MetierDAO db;
    public static  MetierUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        // Set up the login form.
        db=new MetierDAO(this);
        prenom = (EditText) findViewById(R.id.prenom);
        nom = (EditText) findViewById(R.id.nom);
        logIn = (Button) findViewById(R.id.sign_in);
        logIn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onLogin();
            }
        });

    }


public void onLogin(){
        db.open();
        user=db.getUser(prenom.getText().toString(),nom.getText().toString());
        if(user.prenom.equals("UNKNOW")){
            user.setPrenom(prenom.getText().toString());
            user.setNom(nom.getText().toString());
            db.ajouter(user);
            user=db.getUser(prenom.getText().toString(),nom.getText().toString());
        }
        db.close();

        Intent in = new Intent(this,UserAcount.class);
        startActivity(in);

}









}





