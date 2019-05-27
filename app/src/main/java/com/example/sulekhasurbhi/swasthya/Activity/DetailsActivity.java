package com.example.sulekhasurbhi.swasthya.Activity;


import android.media.Image;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sulekhasurbhi.swasthya.R;

import java.util.Locale;

public class DetailsActivity extends AppCompatActivity {

    private TextView descTextView, titleTextView, headingTextView,t4,t5;
    private Toolbar myToolbar;
    private RatingBar ratingBar;
    //private Switch aSwitch;
    ImageView i1,i2;
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        /**
         **
         * Connecting all XML views to java file using findViewById
         */
        AddXMLToJava();


        /**
         **
         * Connecting all XML views to java file using findViewById
         */
        ReceiveDataFromDailyTipsFragmentInBundle();


    }

    private void ReceiveDataFromDailyTipsFragmentInBundle() {

        // Receiving data from fragment to an activity (DailyTipsFragment -> DetailsActivity)
        try {

            Bundle intent = getIntent().getExtras();
            Integer Title = intent.getInt("Title");
            Integer Desc = intent.getInt("Desc");
            Integer Heading = intent.getInt("Heading");

            descTextView.setText(Desc);
            titleTextView.setText(Title);
            headingTextView.setText(Heading);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void AddXMLToJava() {

        myToolbar = findViewById(R.id.my_toolbar);
        descTextView = findViewById(R.id.textView21);
        titleTextView = findViewById(R.id.title_text);
        headingTextView = findViewById(R.id.textView18);
        ratingBar=findViewById(R.id.ratingBar);
        t4=findViewById(R.id.textView4);
        i1=findViewById(R.id.imagemicon);
        i2=findViewById(R.id.imagemicoff);
        //t5=findViewById(R.id.textread);
        //aSwitch=findViewById(R.id.switch1);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS){
                    int ttslang = textToSpeech.setLanguage(Locale.forLanguageTag("hin"));
                    if(ttslang==TextToSpeech.LANG_MISSING_DATA || ttslang==TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("TTS","The language is not supported" );
                    }else{
                        Log.e("TTS","Lang Supported");
                    }
                    Log.i("TTS","Initialization succeed ");
                }else{
                    Toast.makeText(getApplicationContext(), "TTS Initialization failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i2.setVisibility(View.VISIBLE);
                i1.setVisibility(View.INVISIBLE);
                onStop();
            }
        });

        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i2.setVisibility(View.INVISIBLE);
                i1.setVisibility(View.VISIBLE);
                String data = descTextView.getText().toString();
                Log.i("TTS", "button clicked: " + data);
                int speechStatus = textToSpeech.speak(data, TextToSpeech.QUEUE_FLUSH, null);
                if (speechStatus == TextToSpeech.ERROR) {
                    Log.e("TTS", "Error in converting Text to Speech!");
                }
            }
        });

        /*aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(aSwitch.isChecked()){
                    String data = descTextView.getText().toString();
                    Log.i("TTS", "button clicked: " + data);
                    int speechStatus = textToSpeech.speak(data, TextToSpeech.QUEUE_FLUSH, null);
                    if (speechStatus == TextToSpeech.ERROR) {
                        Log.e("TTS", "Error in converting Text to Speech!");
                    }
                }else{
                    onStop();
                }
            }
        });*/

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                t4.setText("Your rate :" + rating);
            }
        });
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(textToSpeech!=null){
            textToSpeech.stop();
        }
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
