package com.example.sulekhasurbhi.swasthya.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sulekhasurbhi.swasthya.Add;
import com.example.sulekhasurbhi.swasthya.Fragment.CheckMeFragment;
import com.example.sulekhasurbhi.swasthya.Fragment.DailyTipsFragment;
import com.example.sulekhasurbhi.swasthya.Fragment.FinDiseaseFragment;
import com.example.sulekhasurbhi.swasthya.Fragment.GhareluNuksheFragment;
import com.example.sulekhasurbhi.swasthya.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hsalf.smilerating.SmileRating;

public class HomeScreenActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Toolbar myToolbar;
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    private FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        /**
         **
         * Connecting all XML views to java file using findViewById
         */
        AddXMLToJava();

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Ratings");
        /**
         **
         * Load Fragments on home screen
         */
        LoadFragmentToHomeScreen();

    }

    private void LoadFragmentToHomeScreen() {

        // Loading DailyTips Fragment to home screen
        // At first when user will come to this screen he/she will see DailyTips Screen

        loadFragment(new DailyTipsFragment());

        //Bottom navigation option that allows user to switch between fragments.
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;

                switch (menuItem.getItemId()) {

                         // Showing DailyTips Fragment when clicked.
                    case R.id.near_me:
                        fragment = new DailyTipsFragment();
                        loadFragment(fragment);
                        return true;

                         // Showing GhareluNuskhe Fragment when clicked.
                    case R.id.explore:
                        fragment = new GhareluNuksheFragment();
                        loadFragment(fragment);
                        return true;

                    // Showing CheckMe Fragment when clicked.
                    case R.id.cart:
                        fragment = new CheckMeFragment();
                        loadFragment(fragment);
                        return true;

                    // Showing FinDisease Fragment when clicked.
                    case R.id.account:
                        fragment = new FinDiseaseFragment();
                        loadFragment(fragment);
                        return true;
                }

                return false;
            }
        });
    }

    private void AddXMLToJava() {

        bottomNavigationView = findViewById(R.id.navigation);
        myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
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
                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.custom_ratebox);

                TextView title = dialog.findViewById(R.id.titlerate);
                SmileRating smileRating = dialog.findViewById(R.id.smile_rating);
                Button button = dialog.findViewById(R.id.buttonlater);
                Button b1 = dialog.findViewById(R.id.buttonow);

                smileRating.setOnRatingSelectedListener(new SmileRating.OnRatingSelectedListener() {
                    @Override
                    public void onRatingSelected(int level, boolean reselected) {
                        String id = mUser.getUid();
                        Add add = new Add(level);
                        databaseReference.child(id).setValue(add);
                    }
                });

                dialog.show();
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(HomeScreenActivity.this, "Rate added", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                return true;

                // when user clicks on about section dialog will appear and it will show details about Swasthya.
            case R.id.action_about:
                // User chose the "Settings" item, show the app settings UI...

                final AlertDialog alertDialog = new AlertDialog.Builder(this).create();

                alertDialog.setTitle("Swasthya");
                alertDialog.setIcon(R.drawable.ic_social_care_green);
                alertDialog.setCancelable(true);
                alertDialog.setMessage("\nToday, Ayurveda is dominated by all over the world, advises the practitioners, doctors, scientists, Ayurveda and yoga of the entire world and follow their miraculous effects.\n" +
                        "Home remedies can be used in various everyday diseases such as fever, colds, headache, physical impairment, obesity, abdominal pain, back pain, joints and knee pain etc and it is instant relief. Think of your health, now you sit at home.\n" +
                        "Through this app, we are trying to reach the domestic Ayurvedic prescriptions to the general public so that all people can take Ayurveda in their life and benefit from it.\n" +
                        "\n\u2022 Prevention is better than cure. Get personal and useful health tips. Simple tips to improve your daily health or lifestyle.\n" +
                        "\n\u2022 Here you can check of any disease after entering the symptoms in user interface and provide details of the corresponding disease to you.\n" +
                        "\n\u2022 What if you already know that this disease is spreading rapidly in your area. Is not it good? Of course it is good so that you can save yourself from suffering or take action accordingly.\n" +
                        "This feature of the application provides the name of the disease that most people suffer and to prevent that illness, an expert will be sent to resolve that common disease.");
                alertDialog.setButton(alertDialog.BUTTON_POSITIVE, "Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.cancel();
                    }
                });

                alertDialog.show();

                return true;

            case R.id.action_logout:
                // User chose the "Settings" item, show the app settings UI...
                mAuth.signOut();
                Intent intent=new Intent(HomeScreenActivity.this,LoginActivity.class);
                startActivity(intent);

                return true;

            case R.id.action_help:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...

                sendEmail();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    protected void sendEmail() {
        Intent mailIntent = new Intent(Intent.ACTION_VIEW);
        Uri data = Uri.parse("mailto:?subject=" + "Swasthya App feedback" + "&body=" + " " + "&to=" + "feedback.swasthya@gmail.com");
        mailIntent.setData(data);
        startActivity(Intent.createChooser(mailIntent, "Send mail..."));
    }
}