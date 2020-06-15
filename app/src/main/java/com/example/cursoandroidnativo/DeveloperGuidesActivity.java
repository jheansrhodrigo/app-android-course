package com.example.cursoandroidnativo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import entities.Aula;

public class DeveloperGuidesActivity extends AppCompatActivity {

    private LinearLayout getLinearLayoutAula(){
        String filter = "";
        List<Aula> lista = Aula.buscaTodos(this, filter);
        Log.v("DB", "Items: "+String.valueOf(lista.size()));
        LinearLayout layout = new LinearLayout(this);
        for (final Aula aula : lista) {
            layout.setPadding(0,0,0,60);
            layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            layout.setOrientation(LinearLayout.VERTICAL);//LinearLayout.HORIZONTAL

            TextView tv = new TextView(this); ;

            tv = new TextView(this);
            tv.setText(aula.getNome());
            tv.setTextSize(24);
            LinearLayout.LayoutParams p1 =  new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(p1);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("\nAcessar Aula\n");
                    Intent myIntent = new Intent(getBaseContext(), com.example.cursoandroidnativo.Aula.class);
                    startActivity(myIntent);
                }
            });
            layout.addView(tv);

            CheckBox cb = new CheckBox(this);
            cb.setChecked(aula.getConcluido());
            cb.setTextSize(24);
            cb.setEnabled(false);
            p1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            cb.setLayoutParams(p1);
            layout.addView(cb);

        }
        return layout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_guides);

        final LinearLayout ll = (LinearLayout) findViewById(R.id.linearLayoutAula);
        ll.addView(getLinearLayoutAula());
    }

}
