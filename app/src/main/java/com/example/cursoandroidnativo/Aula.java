package com.example.cursoandroidnativo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import entities.AulaEntidade;
import entities.PlayerConfig;

public class Aula extends YouTubeBaseActivity {
    private TextView titulo;
    private TextView descricao;
    private TextView questao;
    private TextView solucao;
    private YouTubePlayerView youTubePlayerView;
    private Button buttonPlay;
    private Button buttonConcluir;
    private Button buttonSolucao;
    private YouTubePlayer.OnInitializedListener onInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula);

        final AulaEntidade aula = (AulaEntidade) getIntent().getSerializableExtra("aula");

        titulo = findViewById(R.id.tituloAula);
        descricao = findViewById(R.id.descricaoAula);
        questao = findViewById(R.id.questaoAula);
        solucao = findViewById(R.id.solucaoAula);
        youTubePlayerView = findViewById(R.id.viewVideoYoutube);
        buttonPlay = findViewById(R.id.buttonVideo);
        buttonConcluir = findViewById(R.id.buttonConcluirAula);
        buttonSolucao = findViewById(R.id.buttonSolucao);

        titulo.setText(aula.getNome());
        descricao.setText(aula.getDescricao());
        questao.setText(aula.getPergunta());

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(aula.getChave_url());
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubePlayerView.initialize(PlayerConfig.API_KEY, onInitializedListener);
            }
        });

        buttonSolucao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solucao.setText(aula.getResposta());
            }
        });

        final Activity a = this;
        buttonConcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aula.concluirAula(a, aula);
                Intent intent = new Intent(Aula.this, DeveloperGuidesActivity.class);
                startActivity(intent);
            }
        });
    }
}
