package com.example.sulekhasurbhi.swasthya.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sulekhasurbhi.swasthya.Activity.CheckMeDetails;
import com.example.sulekhasurbhi.swasthya.R;

import java.util.ArrayList;

public class CheckMeFragment extends Fragment {


    private TextView t1,t2;
    Button b1;
    AutoCompleteTextView v1,v2,v3,v4,v5;
    ImageView i1,i2,i3,i4,i5;
    ListView listView;
    private static final String[] symptoms = new String[]{"Chest pain","Shortness of breath","Pain in your arms or legs",
            "Pain in the neck/jaw/throat","Pain in upper abdomen going down to lower right abdomen "
            ,"Dizziness","Weakness or fatigue","Dry or persistent cough","Skin rashes or unusual spots","Coughing up blood",
            "Unintentional weight loss","Night sweats","Fever","Chills","Night sweats","Loss of appetite","Runny or stuffy nose",
            "Sore throat","irregular periods/abnormal periods","weight gain","oily skin or acne","excessive hair growth (hirsutism)",
            "difficulty getting pregnant","Darkening of skin","Sleep apnea","Anxiety and depression","Infertility","Hair loss"};
    private ArrayList<Integer> itemName = new ArrayList<>();
    private ArrayList<Integer> itemSub = new ArrayList<>();
    private ArrayList<Integer> itemDessymp = new ArrayList<>();
    private ArrayList<Integer> itemDesreme = new ArrayList<>();


     @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getImages();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_check_me, container, false);

        t1 =rootView.findViewById(R.id.textView2);

        v1 = rootView.findViewById(R.id.autoCompleteTextView);
        v2 = rootView.findViewById(R.id.autoCompleteTextView2);
        v3 = rootView.findViewById(R.id.autoCompleteTextView3);
        v4 = rootView.findViewById(R.id.autoCompleteTextView4);
        v5 = rootView.findViewById(R.id.autoCompleteTextView5);

        v1.setThreshold(1);
        v2.setThreshold(1);
        v3.setThreshold(1);
        v4.setThreshold(1);
        v5.setThreshold(1);

        i1 =rootView.findViewById(R.id.icon1);
        i2 =rootView.findViewById(R.id.icon2);
        i3 =rootView.findViewById(R.id.icon3);
        i4 =rootView.findViewById(R.id.icon4);
        i5 =rootView.findViewById(R.id.icon5);

        b1 = rootView.findViewById(R.id.buttonDiagnose);

        t2 = rootView.findViewById(R.id.textView1);

        listView = rootView.findViewById(R.id.ListViewNB);

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

        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v1.showDropDown();
            }
        });

        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v2.showDropDown();
            }
        });

        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v3.showDropDown();
            }
        });

        i4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v4.showDropDown();
            }
        });

        i5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v5.showDropDown();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1,s2,s3,s4,s5;
                s1 = v1.getText().toString().trim();
                s2 = v2.getText().toString().trim();
                s3 = v3.getText().toString().trim();
                s4 = v4.getText().toString().trim();
                s5 = v5.getText().toString().trim();

                if((s1.equals("") && s2.equals("") && s3.equals("")) || (s4.equals("Symptom 4")
                        && s5.equals("Symptom 5"))){
                    Toast.makeText(getActivity(), "Select atleast three symptoms", Toast.LENGTH_LONG).show();
                }else {
                    //Toast.makeText(getActivity(), "inside else", Toast.LENGTH_SHORT).show();
                    CheckMeAdapter checkMeAdapter = new CheckMeAdapter();
                    listView.setAdapter(checkMeAdapter);
                    int resId = R.anim.layout_animation_fall_down;
                    LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getActivity(), resId);
                    listView.setLayoutAnimation(animation);
                    predictor();
                }
            }
        });

        return rootView;
    }

    private void predictor(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), CheckMeDetails.class);
                intent.putExtra("Title", itemName.get(position));
                intent.putExtra("Descsy",itemDessymp.get(position));
                intent.putExtra("Descre", itemDesreme.get(position));
                startActivity(intent);
            }
        });
    }

    private void getImages(){
        itemName.add(R.string.app); //1
        itemName.add(R.string.pcos);
        itemName.add(R.string.pms);

        itemSub.add(R.string.nb_s);
        itemSub.add(R.string.nb_m);
        itemSub.add(R.string.nb_fair);

        itemDessymp.add(R.string.app_symptoms);
        itemDessymp.add(R.string.pcos_symptoms);
        itemDessymp.add(R.string.pms_symptoms);

        itemDesreme.add(R.string.app_cure);
        itemDesreme.add(R.string.pcos_cure);
        itemDesreme.add(R.string.pms_cure);
    }

    class CheckMeAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return itemName.size();
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
            convertView = getLayoutInflater().inflate(R.layout.custom_layout_checkme,null);
            TextView textView = convertView.findViewById(R.id.item_name);
            TextView tv = convertView.findViewById(R.id.textView8);
            textView.setText(itemName.get(position));
            tv.setText(itemSub.get(position));
            return convertView;
        }
    }

}
