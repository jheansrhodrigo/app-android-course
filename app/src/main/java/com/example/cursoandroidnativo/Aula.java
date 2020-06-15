package com.example.cursoandroidnativo;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import entities.AulaEntidade;
import entities.PlayerConfig;

public class Aula extends YouTubeBaseActivity {
    private TextView titulo;
    private TextView descricao;
    private YouTubePlayerView youTubePlayerView;
    private Button button;
    private YouTubePlayer.OnInitializedListener onInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula);

        AulaEntidade aula = (AulaEntidade) getIntent().getSerializableExtra("aula");

        titulo = findViewById(R.id.tituloAula);
        descricao = findViewById(R.id.descricaoAula);
        youTubePlayerView = findViewById(R.id.viewVideoYoutube);
        button = findViewById(R.id.buttonVideo);

        //CheckBox checkBox = findViewById(R.id.);
        titulo.setText(aula.getNome());
        descricao.setText(aula.getDescricao());

        /*final VideoView videoView =  findViewById(R.id.videoAula);
        MediaController mediacontroller = new MediaController(this);
        mediacontroller.setAnchorView(videoView);
        videoView.setMediaController(mediacontroller);
        Uri uri = Uri.parse("http://vfx.mtime.cn/Video/2019/02/08/mp4/190208204943376259.mp4");
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
            }
        });*/

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("MnJEbS5p3kQ");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubePlayerView.initialize(PlayerConfig.API_KEY, onInitializedListener);
            }
        });
    }
}
