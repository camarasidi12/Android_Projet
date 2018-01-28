
package com.example.sidicamara.projetppm_3673669;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.design.widget.FloatingActionButton;
        import android.support.design.widget.Snackbar;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.view.View;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.widget.Button;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {

    Button novice;
    Button medium;
    Button expert;
    Button statistic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        novice = (Button)findViewById(R.id.novice);
        medium = (Button)findViewById(R.id.medium);
        expert = (Button)findViewById(R.id.expert);
        statistic = (Button)findViewById(R.id.statistic);

        statistic.setOnClickListener(this);
        novice.setOnClickListener(this);
        medium.setOnClickListener(this);
        expert.setOnClickListener(this);
    }




    @Override
    public void onClick(View v) {
        Intent intent=null ;
        if(v.getId()==statistic.getId()) {
            intent = new Intent(this, Statistics.class);
        }else if(v.getId()==novice.getId()){
            intent = new Intent(this, Game.class);
            intent.putExtra("level",MapStreatV.NOVICE);
        }else if(v.getId()==medium.getId()){
            intent = new Intent(this, Game.class);
            intent.putExtra("level",MapStreatV.MEDIUM);
        }else if(v.getId()==expert.getId()){
            intent = new Intent(this, Game.class);
            intent.putExtra("level",MapStreatV.EXPERT);
        }
        startActivity(intent);
    }
}







