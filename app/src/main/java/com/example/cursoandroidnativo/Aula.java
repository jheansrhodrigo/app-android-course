package com.example.cursoandroidnativo;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.CheckBox;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import entities.AulaEntidade;

public class Aula extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula);
        AulaEntidade aula = (AulaEntidade) getIntent().getSerializableExtra("aula");

        TextView titulo = findViewById(R.id.tituloAula);
        TextView descricao = findViewById(R.id.descricaoAula);
        //CheckBox checkBox = findViewById(R.id.);
        titulo.setText(aula.getNome());
        descricao.setText(aula.getDescricao());

        final VideoView videoView =  findViewById(R.id.videoAula);
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
        });
    }
}
