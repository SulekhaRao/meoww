package com.example.sulekhasurbhi.swasthya.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sulekhasurbhi.swasthya.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class CurrLocation extends AppCompatActivity {

    //private Button button;
    private ArrayList<Integer> itemImages = new ArrayList<>();
    private ArrayList<Integer> itemName = new ArrayList<>();
    private ProgressDialog progressDialog;
    private Toolbar myToolbar;
    private ArrayList<Integer> itemDessymp = new ArrayList<>();
    private ArrayList<Integer> itemDesreme = new ArrayList<>();
    private ArrayList<Integer> itemDesprev = new ArrayList<>();
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curr_location);

        mAuth = FirebaseAuth.getInstance();

        myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getImages();

        //progressDialog = new ProgressDialog(this);

        final ListView listView = findViewById(R.id.ListView);

        CurLocAdapter curLocAdapter = new CurLocAdapter();
        listView.setAdapter(curLocAdapter);

        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, resId);
        listView.setLayoutAnimation(animation);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                progressDialog = new ProgressDialog(CurrLocation.this);
                progressDialog.setIcon(R.drawable.ic_social_care_green);
                progressDialog.setTitle("Loading");
                progressDialog.setMessage("Please wait for a moment..");
                progressDialog.show();

                //sending data from an activity to another activity
                //Bundle bundle = new Bundle();
                //bundle.putInt("Title",itemName.get(position));


                Intent intent = new Intent(CurrLocation.this, CurrLocDetailsActivity.class);
                intent.putExtra("Title", itemName.get(position));
                //intent.putExtra("Title",listView.getItemAtPosition(position).toString());
                intent.putExtra("Descsy",itemDessymp.get(position));
                intent.putExtra("Descre", itemDesreme.get(position));
                intent.putExtra("Descpr", itemDesprev.get(position));
                startActivity(intent);
            }
           /* public void onLongClick(View view, int position) {
                Toast.makeText(CurrLocation.this, "Showing Position  (Long Press) : " + position,
                        Toast.LENGTH_SHORT).show();
            }*/
        });

    }

    private void getImages(){

        itemImages.add(R.drawable.tuberculosis); //1
        itemImages.add(R.drawable.heartdisease); //2
        itemImages.add(R.drawable.skinrash);

        itemName.add(R.string.tb); //1
        itemName.add(R.string.bs);
        itemName.add(R.string.si);

        itemDessymp.add(R.string.tb_symptoms);
        itemDessymp.add(R.string.bs_symptoms);
        itemDessymp.add(R.string.si_symptoms);

        itemDesreme.add(R.string.tb_cure);
        itemDesreme.add(R.string.bs_cure);
        itemDesreme.add(R.string.si_cure);

        itemDesprev.add(R.string.tb_prevention);
        itemDesprev.add(R.string.bs_prevention);
        itemDesprev.add(R.string.si_prevention);
    }

    class CurLocAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return itemImages.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = getLayoutInflater().inflate(R.layout.custom_layout_currloc, null);
            ImageView imageView = convertView.findViewById(R.id.imageView4);
            TextView textView = convertView.findViewById(R.id.item_name);
            imageView.setImageResource(itemImages.get(position));
            textView.setText(itemName.get(position));

            return convertView;
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
                        "\n\u2022 Here you can check of any disease after uploading an image of that part of your body which is suffered from the disease and provide details of the corresponding disease to you.\n" +
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
                startActivity(new Intent(CurrLocation.this,LoginActivity.class));

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

    protected void sendEmail() {
        Intent mailIntent = new Intent(Intent.ACTION_VIEW);
        Uri data = Uri.parse("mailto:?subject=" + "Swasthya App feedback" + "&body=" + " " + "&to=" + "feedback.swasthya@gmail.com");
        mailIntent.setData(data);
        startActivity(Intent.createChooser(mailIntent, "Send mail..."));
    }
}
