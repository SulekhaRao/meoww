package com.example.sulekhasurbhi.swasthya.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.example.sulekhasurbhi.swasthya.Activity.TinytipsDetialsActivity;
import com.example.sulekhasurbhi.swasthya.Adapter.GhareluAdapter;
import com.example.sulekhasurbhi.swasthya.R;

import java.util.ArrayList;

public class GhareluNuksheFragment extends Fragment {
  private ArrayList<Integer> itemImages = new ArrayList<>();
    private ArrayList<Integer> itemName = new ArrayList<>();

    private ArrayList<Integer> itemDessymp = new ArrayList<>();
    private ArrayList<Integer> itemDesreme = new ArrayList<>();

    private ProgressDialog progressDialog;

    public String sheetal ="Hello world";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


         // getting image database
         getImages();
    }


    // this function is adding data to the array list

    private void getImages() {


        itemImages.add(R.drawable.hairfall); //1
        itemImages.add(R.drawable.chickenpox); //2
        itemImages.add(R.drawable.nb); //3
        itemImages.add(R.drawable.gas);
        itemImages.add(R.drawable.cough);
        itemImages.add(R.drawable.constipation);
        itemImages.add(R.drawable.pimpleacne);  //7
        itemImages.add(R.drawable.dental);  //7
        itemImages.add(R.drawable.vomiting);  //7
        itemImages.add(R.drawable.pneumonia);  //7

        itemName.add(R.string.gn_a); //1
        itemName.add(R.string.gn_b);
        itemName.add(R.string.gn_c);
        itemName.add(R.string.gn_d);
        itemName.add(R.string.gn_e);
        itemName.add(R.string.gn_f);
        itemName.add(R.string.gn_g);
        itemName.add(R.string.gn_h); //1
        itemName.add(R.string.gn_i);
        itemName.add(R.string.gn_j);

        itemDessymp.add(R.string.gn_la);
        itemDessymp.add(R.string.gn_lb);
        itemDessymp.add(R.string.gn_lc);
        itemDessymp.add(R.string.gn_ld);
        itemDessymp.add(R.string.gn_le);
        itemDessymp.add(R.string.gn_lf);
        itemDessymp.add(R.string.gn_lg);
        itemDessymp.add(R.string.gn_lh);
        itemDessymp.add(R.string.gn_li);
        itemDessymp.add(R.string.gn_lj);


        itemDesreme.add(R.string.gn_upa);
        itemDesreme.add(R.string.gn_upb);
        itemDesreme.add(R.string.gn_upc);
        itemDesreme.add(R.string.gn_upd);
        itemDesreme.add(R.string.gn_upe);
        itemDesreme.add(R.string.gn_upf);
        itemDesreme.add(R.string.gn_upg);
        itemDesreme.add(R.string.gn_uph);
        itemDesreme.add(R.string.gn_upi);
        itemDesreme.add(R.string.gn_upj);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gharelu_nukshe, container, false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);


        Animation animation1 = AnimationUtils.loadAnimation(getActivity(),R.anim.left_to_right);

        RecyclerView offterRecyclerView = rootView.findViewById(R.id.recyclerView);
        offterRecyclerView.setLayoutManager(layoutManager);

        // defining the progress bar
        progressDialog = new ProgressDialog(getActivity());




        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
        offterRecyclerView.setLayoutAnimation(animation);



        GhareluAdapter adapter = new GhareluAdapter(getContext(), itemName);
        offterRecyclerView.setAdapter(adapter);
       // offterRecyclerView.startAnimation(animation1);


        // adding the on click listener to the recycler view
        offterRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                offterRecyclerView, new ClickListener() {

            @Override
            public void onClick(View view, final int position) {
               // Toast.makeText(getActivity(), "Showing Position  (Single Press) : " + position,
                 //       Toast.LENGTH_SHORT).show();

                progressDialog.setIcon(R.drawable.ic_social_care_green);
                progressDialog.setTitle("Loading");
                progressDialog.setMessage("Please wait for a moment..");
                progressDialog.show();



                Intent intent = new Intent(getActivity(), TinytipsDetialsActivity.class);

                //Sending Data from a fragment to an activity (DailyTipsFragment -> DetailsActivity)

                 intent.putExtra("Title", itemName.get(position));
                 intent.putExtra("Descsy",itemDessymp.get(position));
                 intent.putExtra("Descre", itemDesreme.get(position));
                 intent.putExtra("Images", itemImages.get(position));
                //intent.putExtra("Heading", mHeading.get(position));
                startActivity(intent);
                progressDialog.dismiss();
            }

                // startActivity(intent);


            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(), "Showing Position  (Long Press) : " + position,
                        Toast.LENGTH_SHORT).show();
            }
        }));

        return rootView;
    }
    private void addRecyclerTouchListner(RecyclerView recyclerView) {

    }


    //RECYCLER VIEW ONCLICK METHOND STARTS
    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GhareluNuksheFragment.ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView,
                                     final GhareluNuksheFragment.ClickListener clicklistener) {

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

