package com.example.drawabletest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.drawable.LevelListDrawable;


public class MainActivity extends AppCompatActivity {

    // Member variables for holding the score
    private int mScore1 = 0;
    private int mScore2;

    // Member variables for the two score TextView elements
    private ImageView mScoreText1;
    private Drawable mScoreText2;

    static final String STATE_SCORE_1 = "Team 1 Score";
    static final String STATE_SCORE_2 = "Team 2 Score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScoreText1 = findViewById(R.id.battery);
        //mScoreText2 = findViewById(R.id.score_2);

        if (savedInstanceState != null) {
            mScore1 = savedInstanceState.getInt(STATE_SCORE_1);
            //mScore2 = savedInstanceState.getInt(STATE_SCORE_2);
            //mScoreText1.setText(String.valueOf(mScore1));
            //mScoreText2.setText(String.valueOf(mScore2));
        }
    }


    public void decreaseScore(View view) {
        int viewID = view.getId();
        switch (viewID) {
            case R.id.decreaseTeam1:
                mScore1--;
                if(mScore1 >= 0){
                    mScoreText1.setImageLevel(mScore1);
                }
                else mScore1 = 0;
                break;
            /*case R.id.decreaseTeam2:
                mScore2--;
                mScoreText2.setText(String.valueOf(mScore2));*/
        }
    }

    public void increaseScore(View view) {
        // Get the ID of the button that was clicked.
        int viewID = view.getId();
        switch (viewID) {
            // If it was on Team 1:
            case R.id.increaseTeam1:
                // Increment the score and update the TextView.
                mScore1++;
                if(mScore1 <= 6){
                mScoreText1.setImageLevel(mScore1);
                }
                else mScore1 = 6;
                break;
            // If it was Team 2:
          /*  case R.id.increaseTeam2:
                mScore2++;
                mScoreText2.setText(String.valueOf(mScore2));*/
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if(nightMode == AppCompatDelegate.MODE_NIGHT_YES){
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else{
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.night_mode) {
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode
                        (AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode
                        (AppCompatDelegate.MODE_NIGHT_YES);
            }
            recreate();
        }
        return true;
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Save the scores.
        outState.putInt(STATE_SCORE_1, mScore1);
        outState.putInt(STATE_SCORE_2, mScore2);
        super.onSaveInstanceState(outState);
    }
}
