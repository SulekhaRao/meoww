package com.example.sulekhasurbhi.swasthya.Fragment;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sulekhasurbhi.swasthya.Activity.DetailsActivity;
import com.example.sulekhasurbhi.swasthya.Adapter.ItemAdapter;
import com.example.sulekhasurbhi.swasthya.Adapter.OffterAdapter;
import com.example.sulekhasurbhi.swasthya.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class DailyTipsFragment extends Fragment {
    /**
     * In this screen user can see the daily tips
     * Used firebase for realtime database
     * and firebase push notifications;
     */
    private ArrayList<Integer> OfferImages = new ArrayList<>();
    private ArrayList<Integer> itemImages = new ArrayList<>();

    private ArrayList<String> itemName = new ArrayList<>();

    private ArrayList<Integer> mDesc = new ArrayList<>();

    private ArrayList<Integer> mTitle = new ArrayList<>();
    private ArrayList<Integer> mHeading = new ArrayList<>();

    private ProgressDialog progressDialog;

    private TextView textViewone;
    private ImageView imageView;
    public String value=" ";

    private static final int NOTIFICATION_ID = 1;

    private NotificationManager mNotificationManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getImages();

    }

    private void getImages() {
        OfferImages.add(R.drawable.pple);
        OfferImages.add(R.drawable.ppleone);
        OfferImages.add(R.drawable.ppletwo);
        OfferImages.add(R.drawable.pletwo);
        OfferImages.add(R.drawable.pplethree);
        OfferImages.add(R.drawable.pplefour);
        OfferImages.add(R.drawable.pplefive);


        itemImages.add(R.drawable.ple); //1
        itemImages.add(R.drawable.pleone); //2
        itemImages.add(R.drawable.plethree); //3
        itemImages.add(R.drawable.plefour);
        itemImages.add(R.drawable.plefive);
        itemImages.add(R.drawable.plesix);
        itemImages.add(R.drawable.pleseven);  //7


        itemName.add("5 टिप्स सेहत के लिए"); //1
        itemName.add("स्वस्थ रहने की 10 अच्छी आदतें ");
        itemName.add("सुपर फूड्स");
        itemName.add("लम्\u200Dबा होना है? तो करिए ये योगासन");
        itemName.add("स्वस्थ रहने के नौ टिप्स");
        itemName.add("स्वस्थ रहने के 20 सूत्र");
        itemName.add("फिट रहना है तो अपनाएं ये छोटे-छोट टिप्\u200Dस");

        mDesc.add(R.string.desc_a);
        mDesc.add(R.string.desc_b);
        mDesc.add(R.string.desc_c);
        mDesc.add(R.string.desc_d);
        mDesc.add(R.string.desc_e);
        mDesc.add(R.string.desc_f);
        mDesc.add(R.string.desc_g);


        mTitle.add(R.string.title_a);
        mTitle.add(R.string.title_b);
        mTitle.add(R.string.title_c);
        mTitle.add(R.string.title_d);
        mTitle.add(R.string.title_e);
        mTitle.add(R.string.title_f);
        mTitle.add(R.string.title_g);


        mHeading.add(R.string.heading_a);
        mHeading.add(R.string.heading_a);
        mHeading.add(R.string.heading_c);
        mHeading.add(R.string.heading_d);
        mHeading.add(R.string.heading_a);
        mHeading.add(R.string.heading_f);
        mHeading.add(R.string.heading_a);


    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_daily_tips, container, false);


        textViewone = rootView.findViewById(R.id.textView5);

        progressDialog = new ProgressDialog(getActivity());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        RecyclerView offterRecyclerView = rootView.findViewById(R.id.recyclerView);
        imageView = rootView.findViewById(R.id.imageView2);

        offterRecyclerView.setLayoutManager(layoutManager);

        OffterAdapter adapter = new OffterAdapter(getActivity(), OfferImages);


        offterRecyclerView.setAdapter(adapter);




        /* FIREBASE NOTIFICATION */



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Daily Notification");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                final String value = dataSnapshot.getValue(String.class);
                showNotification("Team Swasthya",value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        LinearLayoutManager layoutManagerForItems = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        RecyclerView itemRecyclerView = rootView.findViewById(R.id.items_recyclerView);

        itemRecyclerView.setLayoutManager(layoutManagerForItems);
        ItemAdapter adapterforItem = new ItemAdapter(getActivity(), itemImages, itemName);

        itemRecyclerView.setAdapter(adapterforItem);

        offterRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                offterRecyclerView, new ClickListener() {

            @Override
            public void onClick(View view, final int position) {

                final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle("Swasthya");
                alertDialog.setIcon(R.drawable.ic_social_care_green);
                alertDialog.setCancelable(true);

                if (position == 0){
                    alertDialog.setMessage("सेब \n\n" +
                            "1. सेब अच्छा एंटी ओक्सिडेंट भी है जो मधुमेह, कैंसर, और दिमाग से सम्बंधित बीमारियों को दूर करने में भी मदद करता है। \n" +
                            "2. सेब शरीर में ग्लूकोज की मात्रा को सामान्य करता है। \n" +
                            "3. सेब में आयरन बहुत अच्छी मात्रा में पाया जाता है। ये एनेमिया जैसी बीमारी का इलाज भी करता है|\n" +
                            "4. सेब में बहुत अच्छी मात्रा में फाइबर पाया जाता है जो की पाचन में मदद करता है। \n" +
                            "5. लीवर को मजबूत रखने के लिए रोज़ एक सेब खाए क्योंकी इसमें एंटीओक्सिडेंट पाए जाते है।\n");
                }else if (position == 1){
                    alertDialog.setMessage("बादाम \n\n" +
                            "1. बादाम में कई पोषक तत्\u200Dव होते हैं। इसमें प्रोटीन, मैगनीज, कॉपर, राइबोफ्लाविन आदि मौजूद होते हैं। ये सब पोषक तत्\u200Dव शरीर को भरपूर शक्ति और ऊर्जा प्रदान करते हैं।\n" +
                            "2. सुबह के समय बादाम के साथ दूध का सेवन करने से शरीर को पूरी शक्ति मिलती है। \n" +
                            "3. बादाम में पौटेशियम की मात्रा काफी अधिक होती है और साथ ही सोडियम भी काफी कम मात्रा में होता है। इससे हमारे शरीर में रक्\u200Dत संचार सुचारू बना रहता है।\n" +
                            "4. बादाम में कैल्शियम भरपूर मात्रा में होता है। कैल्शियम हड्डियों के लिए जरूरी होता है।\n" +
                            "5. बादाम में फाइबर काफी अधिक मात्रा में होता है। फाइबर हमारी पाचन क्रिया को दुरुस्\u200Dत बनाये रखने का काम करता है।\n");
                }else if (position==2){
                    alertDialog.setMessage("काजू \n\n" +
                            "1. काजू में प्रोटीन अधिक मात्रा में होता है इसलिए इसे खाने से बाल और त्वचा स्वस्थ और सुंदर हो जाते हैं.\n" +
                            "2. काजू आयरन का अच्छा स्त्रोत माना जाता है इसलिए खून की कमी को दूर करने के लिए आप इसे खा सकते हैं.\n" +
                            "3. काजू खाने से त्वचा का ग्लो करने लगती है और रंगत भी निखर जाती है. सौंदर्य बढ़ाने के लिए अक्सर ही घरेलू नुस्खों में इसका उपयोग किया जाता रहा है.\n" +
                            "4. काजू विटामिन-बी का खजाना है. भूखे पेट काजू खाकर शहद खाने से स्मरण शक्ति बढ़ती है.\n" +
                            "5. काजू खाने से यूरिक एसिड बनना बंद हो जाता है और इसके सेवन से ब्लड प्रेशर भी कंट्रोल में रहता है.");
                }else if (position==3){
                    alertDialog.setMessage("पालक \n\n" +
                            "1. पालक को हिमोग्\u200Dलोबिन बढ़ाने वाली स\u200Dब्\u200Dजी माना जाता हैं। \n" +
                            "2. पालक में कैलोरी, प्रोटीन, कार्बोहाइड्रेट, फैट, फाइबर और खनिज लवण होता हैं। \n" +
                            "3. पालक स्\u200Dवास्\u200Dथ्\u200Dय के लिए ही नहीं बल्कि बालों के लिए भी बहत अच्छा होता है। जो लोग बाल गिरने की समस्\u200Dया से परेशान हैं उन्हें पालक को अपने नियमित आहार में शामिल करना चाहिए।\n" +
                            "4. पालक त्\u200Dवचा को रूखा होने से बचाता है। साथ ही चेहरे के कील मुहांसे मिटाने और त्\u200Dवचा को स्\u200Dवस्\u200Dथ रखने में मददगार होता है। पालक का पेस्ट बनाकर चेहरे पर लगाने से चेहरे से झाइयां दूर हो जाती है।\n" +
                            "5. पालक के रस में गाजर का रस मिलाकर पीने से चर्बी कम होने लगती है या फिर पालक के रस में नींबू का रस मिलाकर पीने से मोटापा दूर होता है।\n");
                }else if (position==4){
                    alertDialog.setMessage("खजूर \n\n" +
                            "1. खजूर में भरपूर मात्रा में मैग्\u200Dनीशियम पाया जाता है। जो हार्ट की बीमारी, गठिया और अल्\u200Dजाइमर जैसे रोगों को आपसे दूर रखता है।\n" +
                            "2. आयरन से भरपूर खजूर मां और होने वाले बच्\u200Dचे दोनों के लिए बेहद उपयोगी है। खजूर में मौजूद पोषक तत्\u200Dव यूट्रेस यानी कि गर्भाशय की मांसपेश\u200Dियों को मजबूती देने का काम भी करते हैं। \n" +
                            "3. विटामिन C से भरपूर खजूर त्\u200Dवचा के लचीलेपन को बरकरार रखकर उसे कोमल बनाता है।\n" +
                            "4. खजूर में मौजूद विटामिन B5 स्\u200Dट्रेच मार्क्\u200Dस हटाने में भी कारगर है। यही नहीं यह बालों को भी स्\u200Dवस्\u200Dथ बनाए रखता है।\n" +
                            "5. खजूर में भरपूर मात्रा में फाइबर होता है जो आपके पाचन तंत्र की सफाई करने के काम आता है। \n");
                }else if (position==5){
                    alertDialog.setMessage("शहद \n" +
                            "1. शहद में विटामिन ए, बी, सी, आयरन, कैल्शियम और आयोडीन पाया जाता हैं।\n" +
                            "2. शहद में एंटीसेप्टिक, एंटीबैक्टीरियल और एंटीमाइक्रोबियल गुण होते हैं। इसलिए यह घावों, कटे और जले हुए स्थानों पर तथा खरोंच पर लगाया जाता है। \n" +
                            "3. शहद में मौजूद एंटीबैक्टीरियल और नमी प्रदान करने वाले गुण दाग-धब्\u200Dबों को दूर कर त्\u200Dवचा में नई जान भर देते हैं। \n" +
                            "4. चेहरे की खुश्\u200Dकी दूर करने के लिए शहद, मलाई और बेसन का उबटन लगाना चाहिए। इससे चेहरे पर चमक आ जाती है।\n" +
                            "5. शहद हीमोग्लोबिन बढ़ाने में मदद करता है। इसके नियमित सेवन से रक्त शुद्ध होता है और एनीमिया भी दूर हो जाती है।\n");
                }else if (position==6){
                    alertDialog.setMessage("दूध \n" +
                            "1. दूध में भरपूर मात्रा में कैल्शियम होता है, जो हमारी हड्डियों की मजबूती के लिए बेहद आवश्\u200Dयक होता है। \n" +
                            "2. दूध दांतों के लिए भी फायदेमंद होता है। यह दांतों की सड़न और कैविटी से बचाये रखने में मदद करता है। \n" +
                            "3. दूध में प्रोटीन होता है, जो मांसपेशियों के पुनर्निर्माण में मदद करता है। व्\u200Dयायाम के बाद एक गिलास दूध पीजिये इससे आपके शरीर को रिकवर होने के लिए पर्याप्\u200Dत ऊर्जा मिलेगा।\n" +
                            "4. गर्म दूध आपके तनाव को दूर कर आपकी मांसपेशियों को राहत देता है।\n" +
                            "5. दूध में मौजूद विटामिन ए और बी आंखों की रोशनी के लिए भी फायदेमंद होता है।\n");
                }
                alertDialog.setButton(alertDialog.BUTTON_POSITIVE, "Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.cancel();
                    }
                });

                alertDialog.show();
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(), "Showing Position  (Long Press) : " + position,
                        Toast.LENGTH_SHORT).show();
            }
        }));


        itemRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                itemRecyclerView, new ClickListener() {

            @Override
            public void onClick(View view, final int position) {
                progressDialog.setIcon(R.drawable.ic_social_care_green);
                progressDialog.setTitle("Loading");
                progressDialog.setMessage("Please wait for a moment..");
                progressDialog.show();

                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("Title", mTitle.get(position));
                intent.putExtra("Desc", mDesc.get(position));
                intent.putExtra("Heading", mHeading.get(position));

                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(), "Showing Position  (Long Press) : " + position,
                        Toast.LENGTH_SHORT).show();
            }
        }));

        return rootView;
    }

    private String returndata(String value) {
        return String.valueOf(value);
    }


    // Create the NotificationChannel, but only on API 26+ because
    // the NotificationChannel class is new and not in the support library
    void showNotification(String title, String content) {
        NotificationManager mNotificationManager =
                (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("default",
                    "YOUR_CHANNEL_NAME",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("YOUR_NOTIFICATION_CHANNEL_DISCRIPTION");
            mNotificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getActivity(), "default")
                .setSmallIcon(R.mipmap.ic_launcher) // notification icon
                .setContentTitle(title) // title for notification
                .setContentText(content)// message for notification
                .setColor(getResources().getColor(R.color.colorPrimaryDark))
                // .setSound(alarmSound) // set alarm sound for notification
                .setStyle(new NotificationCompat.BigTextStyle()) // full text of notification
                .setAutoCancel(true); // clear notification after click

        mNotificationManager.notify(0, mBuilder.build());

    }

    private void addRecyclerTouchListner(RecyclerView recyclerView) {

    }


    //RECYCLER VIEW ONCLICK METHOND STARTS
    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private DailyTipsFragment.ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView,
                                     final DailyTipsFragment.ClickListener clicklistener) {

            this.clicklistener = clicklistener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recycleView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clicklistener != null) {
                        clicklistener.onLongClick(child, recycleView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clicklistener != null && gestureDetector.onTouchEvent(e)) {
                clicklistener.onClick(child, rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}


