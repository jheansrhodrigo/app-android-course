package com.example.cursoandroidnativo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ChooseLanguageActivity extends AppCompatActivity {
    private ImageView kotlin;
    private ImageView java;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_language);

        kotlin = findViewById(R.id.imageViewKotlin);
        java = findViewById(R.id.imageViewJava);

        java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseLanguageActivity.this, DeveloperGuidesActivity.class);
                startActivity(intent);
            }
        });

        kotlin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseLanguageActivity.this, DeveloperGuidesKotlinActivity.class);
                startActivity(intent);
            }
        });
    }
}
