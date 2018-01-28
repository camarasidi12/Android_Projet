package com.example.sidicamara.projetppm_3673669;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sidicamara on 25/01/2018.
 */

public class   DatabaseHandler extends SQLiteOpenHelper {
    public   String TABLE_SCORE;
    public   String TABLE_USER;
    public   String TABLE_USER_DROP;
    public   String TABLE_SCORE_DROP;

    public void setTABLE_SCORE(String TABLE_SCORE) {
        this.TABLE_SCORE = TABLE_SCORE;
    }

    public void setTABLE_USER(String TABLE_USER) {
        this.TABLE_USER = TABLE_USER;
    }

    public void setTABLE_USER_DROP(String TABLE_USER_DROP) {
        this.TABLE_USER_DROP = TABLE_USER_DROP;
    }

    public void setTABLE_SCORE_DROP(String TABLE_SCORE_DROP) {
        this.TABLE_SCORE_DROP = TABLE_SCORE_DROP;
    }

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_SCORE);
        db.execSQL(TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //On peut faire ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
        //comme ça lorsque je change la version les id repartent de 0
        db.execSQL(TABLE_SCORE_DROP);
        db.execSQL(TABLE_USER_DROP);
        onCreate(db);
    }
}

