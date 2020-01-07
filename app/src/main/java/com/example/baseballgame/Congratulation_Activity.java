package com.example.baseballgame;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class Congratulation_Activity extends AppCompatActivity implements View.OnClickListener {

    TextView textView1;
    Button restartButton;
    LottieAnimationView animationView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulation);
        textView1 = findViewById(R.id.text_congratulation);
        restartButton =findViewById(R.id.restartButton);
        restartButton.setOnClickListener(this);

  /*        animationView = findViewById(R.id.animation_view);
        animationView.setAnimation("congratulation2.json");
        animationView.setRepeatCount(10);
        animationView.playAnimation();*/
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.restartButton)
        {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
    }
}
