package com.example.sulekhasurbhi.swasthya.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sulekhasurbhi.swasthya.R;

public class CheckMeFragment extends Fragment implements View.OnClickListener {

    private TextView t1;
    private ListView listView;
    AutoCompleteTextView a1;
    Button b1;
    //ImageView i1,i2,i3,i4,i5;

    private static final String[] symptoms = new String[]{"Chest pain","Shortness of breath","Pain in your arms or legs",
            "Pain in the neck/jaw/throat","Pain in upper abdomen or back"
            ,"Dizziness","Weakness or fatigue","Dry or persistent cough","Skin rashes or unusual spots","Coughing up blood",
            "Unintentional weight loss","Night sweats","Fever","Chills","Night sweats","Loss of appetite","Runny or stuffy nose",
            "Sore throat","irregular periods","weight gain","oily skin or acne","excessive hair growth (hirsutism)",
            "difficulty getting pregnant","Darkening of skin","Sleep apnea","Anxiety and depression","Infertility","Hair loss"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  getImages();
        //i1.setOnClickListener(this);
        //i2.setOnClickListener(this);
        //i3.setOnClickListener(this);
        //i4.setOnClickListener(this);
        //i5.setOnClickListener(this);
        b1.setOnClickListener(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_check_me, container, false);

        final AutoCompleteTextView v1 = rootView.findViewById(R.id.autoCompleteTextView);
        final AutoCompleteTextView v2 = rootView.findViewById(R.id.autoCompleteTextView2);
        final AutoCompleteTextView v3 = rootView.findViewById(R.id.autoCompleteTextView3);
        final AutoCompleteTextView v4 = rootView.findViewById(R.id.autoCompleteTextView4);
        final AutoCompleteTextView v5 = rootView.findViewById(R.id.autoCompleteTextView5);
        b1 = rootView.findViewById(R.id.buttonDiagnose);
       /* i1 = rootView.findViewById(R.id.imageView1);
        i2 = rootView.findViewById(R.id.imageView2);
        i3 = rootView.findViewById(R.id.imageView3);
        i4 = rootView.findViewById(R.id.imageView4);
        i5 = rootView.findViewById(R.id.imageView5);
        t1 = rootView.findViewById(R.id.textView2);*/

        v1.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.right_to_left));
        v2.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.right_to_left));
        v3.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.right_to_left));
        v4.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.right_to_left));
        v5.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.right_to_left));
        b1.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.left_to_right));
        t1.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.left_to_right));

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,symptoms );
        v1.setAdapter(adapter1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,symptoms );
        v2.setAdapter(adapter2);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,symptoms );
        v3.setAdapter(adapter3);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,symptoms );
        v4.setAdapter(adapter4);
        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,symptoms );
        v5.setAdapter(adapter5);




        /*DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Location");
        GeoFire geoFire = new GeoFire(ref);
        // geoFire.setLocation("firebase-hq", new GeoLocation(37.7853889, -122.4056973));

        geoFire.setLocation("firebase-hq", new GeoLocation(28.6774052, 77.2066878), new GeoFire.CompletionListener() {

            @Override
            public void onComplete(String key, DatabaseError error) {
                if (error != null) {
                    System.err.println("There was an error saving the location to GeoFire: " + error);
                } else {
                    System.out.println("Location saved on server successfully!");
                }
            }
        });


        geoFire.getLocation("firebase-hq", new LocationCallback() {
            @Override
            public void onLocationResult(String key, GeoLocation location) {
                if (location != null) {
                    System.out.println(String.format("The location for key %s is [%f,%f]", key, location.latitude, location.longitude));


                    Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
                    List<Address> addresses = null;
                    try {
                        addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getActivity(),"Your Current location is - " + "\n" + addresses.get(0).getAddressLine(0) + ", " +
                            addresses.get(0).getAddressLine(1) + ", " + addresses.get(0).getAddressLine(2),Toast.LENGTH_LONG).show();


//                    Toast.makeText(getActivity(),  String.valueOf(location.latitude) +" "+ String.valueOf(location.longitude), Toast.LENGTH_LONG).show();
                } else {
                    System.out.println(String.format("There is no location for key %s in GeoFire", key));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("There was an error getting the GeoFire location: " + databaseError);
            }
        });


        GeoQuery geoQuery = geoFire.queryAtLocation(new GeoLocation(28.6774052, 77.2066878), 0.2);


        geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
            @Override
            public void onKeyEntered(String key, GeoLocation location) {
                System.out.println(String.format("Key %s entered the search area at [%f,%f]", key, location.latitude, location.longitude));
                //The location of a key now matches the query criteria.
                Toast.makeText(getActivity(),"Location key matches area.",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onKeyExited(String key) {
                System.out.println(String.format("Key %s is no longer in the search area", key));
                Toast.makeText(getActivity(),"Out of area",Toast.LENGTH_LONG).show();
                //The location of a key no longer matches the query criteria.
            }

            @Override
            public void onKeyMoved(String key, GeoLocation location) {
                System.out.println(String.format("Key %s moved within the search area to [%f,%f]", key, location.latitude, location.longitude));
                Toast.makeText(getActivity(),"In the  area",Toast.LENGTH_LONG).show();

               //The location of a key changed but the location still matches the query criteria.
            }

            @Override
            public void onGeoQueryReady() {
                System.out.println("All initial data has been loaded and events have been fired!");
                // All current data has been loaded from the server and all initial events have been fired.
            }

            @Override
            public void onGeoQueryError(DatabaseError error) {
                System.err.println("There was an error with this query: " + error);

                //There was an error while performing this query, e.g. a violation of security rules.
            }
        });
        // textResult = rootView.findViewById(R.id.text_view_json);

       /* listView = rootView.findViewById(R.id.listView);

        // adding the base url.
       /* Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.myjson.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

        Call<List<Post>> call = jsonPlaceHolderAPI.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                // Checking if response is null or not. server may cause 404 error.
                if (!response.isSuccessful()) {
                    textResult.setText("Code : " + response.code());
                    return;
                }

                List<Post> posts = response.body();


                // getting all the post with id, userid, title and text.
                /*for (Post post : posts) {

                    String content = "";

                    content += "ID:" + post.getId() + "\n";
                    content += "UserId:" + post.getUserId() + "\n";
                    content += "Title" + post.getTitle() + "\n";
                    content += "Text : " + post.getText() + "\n\n";

                    // using append  so that it does not override value.
                    textResult.append(content);
                }

                // getting only title and showing into listView
                String[] titile_text = new String[posts.size()];

                for (int i = 0; i < posts.size(); i++) {
                    titile_text[i] = posts.get(i).getTitle();
                }

                //displaying the string array into listview
                listView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, titile_text));

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textResult.setText(t.getMessage());

            }
        });*/


        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonDiagnose :

                break;
            default:
                System.out.println("Wont Able To Get you Please TRY AGAIN!");
        }
    }
}
