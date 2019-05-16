package com.example.sulekhasurbhi.swasthya.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sulekhasurbhi.swasthya.R;

public class TinytipsDetialsActivity extends AppCompatActivity{

    private TextView titleText, sheetalText;
    private Toolbar myToolbar;
    private Button b1, b2;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinytips_detials);

        /**
         **
         * Connecting all XML views to java file using findViewById
         */
        AddXMLToJava();


        /**
         * check value of bundle
         */
        CheckBundle();

        /**
         * Add TabLayout
         * */
       // AddTabLayoutToHomeScreen();

    }

    private void CheckBundle() {

        try {
            Bundle intent = getIntent().getExtras();
            Integer Title = intent.getInt("Title");
            final Integer descsy = intent.getInt("Descsy");
            final Integer descre = intent.getInt("Descre");
            final Integer images = intent.getInt("Images");

            titleText.setText(Title);
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sheetalText.setText(descsy);
                }
            });
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sheetalText.setText(descre);
                }
            });
            imageView.setImageResource(images);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void AddXMLToJava() {

        myToolbar = findViewById(R.id.my_toolbar);
        titleText = findViewById(R.id.title_text);
        sheetalText = findViewById(R.id.sheetal_text);

        b1 = findViewById(R.id.buttonsymptom);
        b2 = findViewById(R.id.buttonremedy);
        imageView = findViewById(R.id.imageView10);

        // Adding toolbar to Home screen
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        /*myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/



    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.appbar, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...

                return true;

            case R.id.action_favorite:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

}
