package com.example.sidicamara.projetppm_3673669;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserAcount extends AppCompatActivity {

    Button edit;
    Button jouer;
    EditText prenom;
    EditText nom;
    private String EDITER="Editer";
    private String SAVE="Enregistrer";
    private  boolean isEditable=false;
    MetierDAO db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_acount);

        edit=(Button)findViewById(R.id.edit);
        jouer=(Button)findViewById(R.id.jouer);
        prenom=(EditText)findViewById(R.id.prenomCount);
        nom=(EditText)findViewById(R.id.nomCount);

        prenom.setText(MainActivity.prenom.getText());
        nom.setText(MainActivity.nom.getText());
        prenom.setFocusable(false);
        nom.setFocusable(false);
        edit.setText(EDITER);
        db=new MetierDAO(this);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEdition();
            }
        });

        jouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onJouer();
            }
        });
    }
    public  void onJouer(){
        Intent in = new Intent(this,UserActivity.class);
        startActivity(in);
    }
    //pour modifier les informations d'un utilisateur 
    public  void onEdition(){
        if(isEditable){
            db.open();
           db.upDateUser(prenom.getText().toString(),nom.getText().toString(),MainActivity.user.getId());
           MainActivity.user=db.getUser(MainActivity.user.getId());
            db.close();
            prenom.setFocusable(false);
            nom.setFocusable(false);
            edit.setText(EDITER);
            isEditable=false;
        }else {
            prenom.setFocusable(true);
            nom.setFocusable(true);
            nom.setFocusableInTouchMode(true);
            prenom.setFocusableInTouchMode(true);
            edit.setText(SAVE);
            isEditable=true;
        }
    }
}
