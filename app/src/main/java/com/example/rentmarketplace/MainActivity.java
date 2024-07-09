package com.example.rentmarketplace;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.rentmarketplace.Main_Fragments.Basket.BasketFragment;
import com.example.rentmarketplace.Main_Fragments.Profile.ProfileFragment;
import com.example.rentmarketplace.Main_Fragments.Rents.RentsFragment;
import com.example.rentmarketplace.Main_Fragments.Store.StoreFragment;

public class MainActivity extends AppCompatActivity {

    private int selectedTab = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        final LinearLayout homeLayout = findViewById(R.id.homeLayout);
        final LinearLayout rentLayout = findViewById(R.id.rentLayout);
        final LinearLayout basketLayout = findViewById(R.id.basketLayout);
        final LinearLayout profileLayout = findViewById(R.id.profileLayout);

        final ImageView homeImage = findViewById(R.id.homeImage);
        final ImageView rentImage = findViewById(R.id.rentImage);
        final ImageView basketImage = findViewById(R.id.basketImage);
        final ImageView profileImage = findViewById(R.id.profileImage);

        final TextView homeText = findViewById(R.id.homeText);
        final TextView rentText = findViewById(R.id.rentText);
        final TextView basketText = findViewById(R.id.basketText);
        final TextView profileText = findViewById(R.id.profileText);

        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.container, new StoreFragment()).commit();

        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTab != 1) {
                    basketText.setVisibility(View.GONE);
                    rentText.setVisibility(View.GONE);
                    profileText.setVisibility(View.GONE);

                    rentImage.setImageResource(R.drawable.baseline_handshake_24_black);
                    basketImage.setImageResource(R.drawable.baseline_local_grocery_store_24_black);
                    profileImage.setImageResource(R.drawable.baseline_person_24_black);

                    rentLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    profileLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    basketLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    homeText.setVisibility(View.VISIBLE);
                    homeImage.setImageResource(R.drawable.baseline_store_24);
                    homeLayout.setBackgroundResource(R.drawable.round_back);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    homeLayout.startAnimation(scaleAnimation);

                    getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.container, new StoreFragment()).commit();

                    selectedTab = 1;
                }
            }
        });

        rentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTab != 2) {
                    basketText.setVisibility(View.GONE);
                    homeText.setVisibility(View.GONE);
                    profileText.setVisibility(View.GONE);

                    homeImage.setImageResource(R.drawable.baseline_store_24_black);
                    basketImage.setImageResource(R.drawable.baseline_local_grocery_store_24_black);
                    profileImage.setImageResource(R.drawable.baseline_person_24_black);

                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    profileLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    basketLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    rentText.setVisibility(View.VISIBLE);
                    rentImage.setImageResource(R.drawable.baseline_handshake_24);
                    rentLayout.setBackgroundResource(R.drawable.round_back);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    rentLayout.startAnimation(scaleAnimation);

                    getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.container, new RentsFragment()).commit();

                    selectedTab = 2;
                }
            }
        });

        basketLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTab != 3) {
                    rentText.setVisibility(View.GONE);
                    homeText.setVisibility(View.GONE);
                    profileText.setVisibility(View.GONE);

                    homeImage.setImageResource(R.drawable.baseline_store_24_black);
                    rentImage.setImageResource(R.drawable.baseline_handshake_24_black);
                    profileImage.setImageResource(R.drawable.baseline_person_24_black);

                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    profileLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    rentLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    basketText.setVisibility(View.VISIBLE);
                    basketImage.setImageResource(R.drawable.baseline_local_grocery_store_24);
                    basketLayout.setBackgroundResource(R.drawable.round_back);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    basketLayout.startAnimation(scaleAnimation);

                    getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.container, new BasketFragment()).commit();

                    selectedTab = 3;
                }
            }
        });

        profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTab != 4) {
                    rentText.setVisibility(View.GONE);
                    homeText.setVisibility(View.GONE);
                    basketText.setVisibility(View.GONE);

                    homeImage.setImageResource(R.drawable.baseline_store_24_black);
                    rentImage.setImageResource(R.drawable.baseline_handshake_24_black);
                    basketImage.setImageResource(R.drawable.baseline_local_grocery_store_24_black);

                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    basketLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    rentLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    profileText.setVisibility(View.VISIBLE);
                    profileImage.setImageResource(R.drawable.baseline_person_24);
                    profileLayout.setBackgroundResource(R.drawable.round_back);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.3f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    profileLayout.startAnimation(scaleAnimation);

                    getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.container, new ProfileFragment()).commit();

                    selectedTab = 4;
                }
            }
        });
    }
}
