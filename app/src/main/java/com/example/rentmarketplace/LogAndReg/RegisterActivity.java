package com.example.rentmarketplace.LogAndReg;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.rentmarketplace.MainActivity;
import com.example.rentmarketplace.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout emailLayout, passwordLayout, nameLayout, passwordRetryLayout;
    private Button registerButton;
    private TextView goToLogin;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        emailLayout = findViewById(R.id.textInputEmail);
        passwordLayout = findViewById(R.id.textInputPassword);
        passwordRetryLayout = findViewById(R.id.textInputPasswordReset);
        nameLayout = findViewById(R.id.textInputName);

        goToLogin = findViewById(R.id.TextButtonGoToLoginActivity);
        registerButton = findViewById(R.id.registerButton);

        ImageView logo = findViewById(R.id.Logo);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        Animation slide = AnimationUtils.loadAnimation(this, R.anim.down_to_app);
        registerButton.setAnimation(slide);
        goToLogin.setAnimation(slide);

        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                Pair<View, String> pair = new Pair<>(logo, "logo");
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(RegisterActivity.this, pair).toBundle());            }
        });

        if (user != null && user.isEmailVerified()) {
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            finish();
            return;
        }

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        final String name = nameLayout.getEditText().getText().toString().trim();
        final String email = emailLayout.getEditText().getText().toString().trim();
        String password = passwordLayout.getEditText().getText().toString().trim();
        String password2 = passwordRetryLayout.getEditText().getText().toString().trim();

        if (!password2.equals(password)) {
            passwordRetryLayout.setError("not equal passwords");
            return;
        }

        if (TextUtils.isEmpty(name)) {
            nameLayout.setError("Write Name");
            return;
        }

        if (TextUtils.isEmpty(email)) {
            emailLayout.setError("Write Email");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            passwordLayout.setError("Write Password");
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(name)
                                        .build();

                                user.updateProfile(profileUpdates)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    sendVerificationEmail(user);
                                                }
                                            }
                                        });
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void sendVerificationEmail(FirebaseUser user) {
        if (user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Check your email", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                finish();
                            } else {
                                Toast.makeText(RegisterActivity.this, "Error of sending verification email", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
