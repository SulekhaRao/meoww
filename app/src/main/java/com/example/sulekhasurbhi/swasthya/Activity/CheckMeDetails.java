package com.example.sulekhasurbhi.swasthya.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sulekhasurbhi.swasthya.R;

public class CheckMeDetails extends AppCompatActivity {

    private TextView titleText, sheetalText;
    private Button b1, b2;
    private Toolbar myToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_me_details);

        AddXMLToJava();
        CheckBundle();
    }

    private void AddXMLToJava() {
        myToolbar = findViewById(R.id.my_toolbar);
        titleText = findViewById(R.id.title_text);
        sheetalText = findViewById(R.id.sheetal_text);
        b1 = findViewById(R.id.buttonsymptom);
        b2 = findViewById(R.id.buttonremedy);

        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void CheckBundle() {

        try {
            Bundle intent = getIntent().getExtras();
            Integer Title = intent.getInt("Title");
            final Integer descsy = intent.getInt("Descsy");
            final Integer descre = intent.getInt("Descre");

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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
