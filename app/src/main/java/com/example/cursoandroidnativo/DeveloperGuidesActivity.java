package com.example.cursoandroidnativo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;
import entities.AulaEntidade;

public class DeveloperGuidesActivity extends AppCompatActivity {
    private Button buttonChooseTec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_guides);

        buttonChooseTec = findViewById(R.id.buttonTrocarTecnologia);
        buttonChooseTec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeveloperGuidesActivity.this, ChooseLanguageActivity.class);
                startActivity(intent);
            }
        });

        final LinearLayout ll = findViewById(R.id.linearLayoutAula);
        ll.addView(getLinearLayoutAula());
    }

    private LinearLayout getLinearLayoutAula(){
        String filter = "";
        List<AulaEntidade> lista = AulaEntidade.buscaTodos(this, filter);
        Log.v("DB", "Items: "+String.valueOf(lista.size()));
        LinearLayout layout = new LinearLayout(this);

        for (final AulaEntidade aula : lista) {
            layout.setPadding(0,0,0,60);
            layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            layout.setOrientation(LinearLayout.VERTICAL);

            TextView tv = new TextView(this);
            tv.setText(aula.getNome());
            tv.setTextSize(24);
            tv.setTextColor(Color.WHITE);
            LinearLayout.LayoutParams p1 =  new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(p1);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("\nAcessar Aula\n");
                    Intent myIntent = new Intent(getBaseContext(), com.example.cursoandroidnativo.Aula.class);
                    myIntent.putExtra("aula", aula);
                    startActivity(myIntent);
                }
            });
            layout.addView(tv);

            CheckBox cb = new CheckBox(this);
            cb.setChecked(aula.getConcluido());
            cb.setTextSize(20);

            if(cb.isChecked()){
                cb.setText("OK");
            }

            cb.setTextColor(Color.GREEN);
            cb.setEnabled(false);
            p1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            cb.setLayoutParams(p1);
            layout.addView(cb);
        }

        return layout;
    }
}
