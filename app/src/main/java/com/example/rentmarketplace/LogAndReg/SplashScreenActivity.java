package com.example.rentmarketplace.LogAndReg;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.rentmarketplace.R;

public class SplashScreenActivity extends AppCompatActivity {

    ImageView logo;
    TextView appName;
    private static final int SPLASH_DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        logo = findViewById(R.id.Logo);
        appName = findViewById(R.id.projectName);

        Animation blinkAnimation = AnimationUtils.loadAnimation(this, R.anim.logo_animation);
        logo.startAnimation(blinkAnimation);
        appName.startAnimation(blinkAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                Pair<View, String> pair1 = new Pair<>(logo, "logo");
                Pair<View, String> pair2 = new Pair<>(appName, "nameOfProject");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashScreenActivity.this, pair1, pair2);
                startActivity(intent, options.toBundle());
            }
        }, SPLASH_DELAY);

    }
}