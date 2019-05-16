package com.example.sulekhasurbhi.swasthya.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.sulekhasurbhi.swasthya.R;

import java.util.ArrayList;

public class OffterAdapter extends RecyclerView.Adapter<OffterAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<Integer> OfferImages = new ArrayList<>();
    private Context mContext;

    public OffterAdapter(Context context, ArrayList<Integer> imageUrls) {
        OfferImages = imageUrls;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout_for_tophomescreenitems, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.image.setImageResource(OfferImages.get(position));
        holder.image.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_scale_animation));

    }

    @Override
    public int getItemCount() {
        return OfferImages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.custom_offterImage);
        }
    }
}
