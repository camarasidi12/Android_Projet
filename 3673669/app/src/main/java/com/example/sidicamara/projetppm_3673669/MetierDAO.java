package com.example.sidicamara.projetppm_3673669;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by sidicamara on 25/01/2018.
 */



public class MetierDAO {
    private static final int VERSION_BDD = 2;
    private static final String NOM_BDD = "mabase.db";


    public static final String TABLE_SCORE = "tableScore";
    public static final String KEYSR = "id";
    public static final String SCORE = "score";
    public static final String LEVEL = "level";
    public static final String DATE = "date";
    public static final String KEYUSFOREIG="userId";

    public static final String TABLE_USER = "tableUser";
    public static final String KEYUS = "id";
    public static final String PRENOM = "prenom";
    public static final String NOM = "nom";


    public static final String TABLE_USER_CREATE = "CREATE TABLE " + TABLE_USER +
            " (" + KEYUS + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PRENOM + " TEXT, " + NOM + " TEXT);";

    public static final String TABLE_USER_DROP =  "DROP TABLE IF EXISTS " + TABLE_USER + ";";


    public static final String TABLE_SCORE_CREATE = "CREATE TABLE " + TABLE_SCORE +
            " (" + KEYSR + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SCORE + " TEXT, "+ KEYUSFOREIG + " INTEGER, "
            +DATE+" TEXT, "+ LEVEL + " TEXT, FOREIGN KEY ("+KEYUSFOREIG+") REFERENCES "+TABLE_USER+"("+KEYUS+"));";

    public static final String TABLE_SCORE_DROP =  "DROP TABLE IF EXISTS " + TABLE_SCORE + ";";

    public  static  String SELECT_USER= "SELECT* FROM "+TABLE_USER;
    public  static  String SELECT_SCORE= "SELECT* FROM "+TABLE_SCORE;
    public  static  String DELETE_SCORE= "DELETE FROM "+TABLE_SCORE;


    DatabaseHandler  maBaseSQLite;
    private SQLiteDatabase bdd;

    public MetierDAO(Context context) {
        maBaseSQLite = new DatabaseHandler(context, NOM_BDD, null, VERSION_BDD);
        maBaseSQLite.setTABLE_SCORE(TABLE_SCORE_CREATE);
        maBaseSQLite.setTABLE_USER(TABLE_USER_CREATE);
        maBaseSQLite.setTABLE_USER_DROP(TABLE_USER_DROP);
        maBaseSQLite.setTABLE_SCORE_DROP(TABLE_SCORE_DROP);
    }



    public void open(){
        //on ouvre la BDD en écriture
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    /**
     * @param m le métier à ajouter à la base
     */
    public long ajouter(MetierUser m) {

        ContentValues values= new ContentValues();
        //values.put(KEYUS,m.getId());
        values.put(PRENOM,m.getPrenom());
        values.put(NOM,m.getNom());

        return  bdd.insert(TABLE_USER,null,values);


    }
    public long ajouter(MetierScore m) {
        Log.i("d","ADDUSER+"+m.getUserId());
        ContentValues values= new ContentValues();
       // values.put(KEYSR,m.getId());
        values.put(LEVEL,m.getLevel());
        values.put(SCORE,m.getScore());
        values.put(DATE,m.getDate());
        values.put(KEYUSFOREIG,m.getUserId());

        return  bdd.insert(TABLE_SCORE,null,values);
    }


    public ArrayList<MetierScore> selectionnerScore() {
        Cursor cursor = bdd.rawQuery(SELECT_SCORE,null);
        ArrayList<MetierScore>  scores= new  ArrayList<MetierScore>();


        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String scorec= cursor.getString(cursor.getColumnIndex(SCORE));
                String levelc= cursor.getString(cursor.getColumnIndex(LEVEL));
                String datec= cursor.getString(cursor.getColumnIndex(DATE));

                scores.add(new MetierScore(scorec,levelc,datec));

                cursor.moveToNext();
            }
        }

        return  scores;
    }

    public ArrayList<MetierScore> selectionnerUserScore(int userId) {
        Cursor cursor = bdd.rawQuery("SELECT* FROM "+TABLE_SCORE+" WHERE "+KEYUSFOREIG+" = "
                        +userId
                ,null);
        ArrayList<MetierScore>  scores= new  ArrayList<MetierScore>();


        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                String scorec= cursor.getString(cursor.getColumnIndex(SCORE));
                String levelc= cursor.getString(cursor.getColumnIndex(LEVEL));
                String datec= cursor.getString(cursor.getColumnIndex(DATE));

                scores.add(new MetierScore(scorec,levelc,datec));

                cursor.moveToNext();
            }
        }

        return  scores;
    }

    public ArrayList<MetierUser> selectionnerUser() {
        Cursor cursor = bdd.rawQuery(SELECT_USER,null);
        ArrayList<MetierUser>  users= new  ArrayList<MetierUser>();


        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                String prenomc= cursor.getString(cursor.getColumnIndex(PRENOM));
                String nomc= cursor.getString(cursor.getColumnIndex(NOM));

                MetierUser us =new MetierUser(nomc,prenomc);
                us.setId(cursor.getInt(cursor.getColumnIndex(KEYUS)));

                users.add(us);

                cursor.moveToNext();
            }
        }

        return  users;
    }

    public  void  deleteAllScore(){
     //  bdd.rawQuery(DELETE_SCORE,null);
        bdd.execSQL(DELETE_SCORE);
    }



    public  MetierUser getUser(int id){
        Cursor cursor = bdd.rawQuery("SELECT* FROM "+TABLE_USER+" WHERE "+KEYUS+" = "
                        +id
                ,null);

        if (cursor.moveToFirst()){
            String prenomc= cursor.getString(cursor.getColumnIndex(PRENOM));
            String nomc= cursor.getString(cursor.getColumnIndex(NOM));
            MetierUser us =new MetierUser(nomc,prenomc);
            us.setId(cursor.getInt(cursor.getColumnIndex(KEYUS)));
            return us;
        }else  return  new MetierUser("UNKNOW","UNKNOW");

    }


    public  MetierUser getUser(String prenom,String nom){
        Cursor cursor = bdd.rawQuery("SELECT* FROM "+TABLE_USER+" WHERE "+PRENOM+" = '"
                        +prenom+"' AND "+NOM+" = '"+nom+"'"
                ,null);

        if (cursor.moveToFirst()){
            String prenomc= cursor.getString(cursor.getColumnIndex(PRENOM));
            String nomc= cursor.getString(cursor.getColumnIndex(NOM));
            MetierUser us =new MetierUser(nomc,prenomc);
            us.setId(cursor.getInt(cursor.getColumnIndex(KEYUS)));
            return us;
        }

        else  return  new MetierUser("UNKNOW","UNKNOW");

    }


    public  void upDateUser(String prenom,String nom,int id){

        String quer="UPDATE "+TABLE_USER+" SET "+PRENOM+" = '"+prenom+"' , "+NOM+" = '"+nom+"' " +
                " WHERE "+KEYUS+" = '"+id+"'";

        Cursor cursor = bdd.rawQuery(quer,null);


    }
}

