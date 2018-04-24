package com.example.sidicamara.projetppm_3673669;

/**
 * Created by sidicamara on 25/01/2018.
 */

//Class correspondant a la table score au niveau de la base de donnée
public class MetierScore {

    String score;
    String level;
    String date;
    long id=0;
    long userId=0;

    public MetierScore(String score, String level, String date) {
        this.score = score;
        this.level = level;
        this.date = date;
       // this.id=id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getScore() {
        return score;
    }

    public String getLevel() {
        return level;
    }

    public String getDate() {
        return date;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }
}
