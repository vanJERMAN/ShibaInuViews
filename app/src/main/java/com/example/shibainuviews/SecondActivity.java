package com.example.shibainuviews;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    ImageView backArrow, secondUpArrow;
    TextView secondTextViewHeader, secondActivityDescription, moreDetails;
    LinearLayout linearLayout;

    Animation fromLeft, fromRight, fromBottom;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        backArrow = findViewById(R.id.backArrow);
        secondUpArrow = findViewById(R.id.secondUpArrow);
        secondTextViewHeader = findViewById(R.id.secondTextViewHeader);
        secondActivityDescription = findViewById(R.id.secondActivityDescription);
        moreDetails = findViewById(R.id.moreDetails);
        linearLayout = findViewById(R.id.linearLayout);


        backArrow.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
//                finish(); // samo zaključimo odprt Intent
            }
        });

        // Hide status bar and navigation bar at the bottom
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );

        // Load Animations
        fromRight = AnimationUtils.loadAnimation(this, R.anim.anim_from_right);
        fromLeft = AnimationUtils.loadAnimation(this, R.anim.anim_from_left);
        fromBottom = AnimationUtils.loadAnimation(this, R.anim.anim_from_bottom);

        // Set Animations
        backArrow.setAnimation(fromLeft);
        secondTextViewHeader.setAnimation(fromRight);
        secondActivityDescription.setAnimation(fromRight);
        linearLayout.setAnimation(fromLeft);
        secondUpArrow.setAnimation(fromBottom);
        moreDetails.setAnimation(fromBottom);

        secondUpArrow.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent thirdActivity = new Intent(SecondActivity.this, ThirdActivity.class);
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(secondUpArrow, "backgroundImageTransition");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SecondActivity.this, pairs);
                startActivity(thirdActivity, options.toBundle());
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}