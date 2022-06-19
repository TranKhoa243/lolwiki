package com.example.lolwiki.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lolwiki.R;
import com.example.lolwiki.modle.Category;
import com.example.lolwiki.modle.ChampionMastery;
import com.example.lolwiki.modle.PlayerKey;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragmentAdapter extends RecyclerView.Adapter<FavoritesFragmentAdapter.FavoritesViewHolder>{

    private Context mContext;
    private ArrayList<ChampionMastery> mChampionMasteries;

    public FavoritesFragmentAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void setData(ArrayList<ChampionMastery> championMasteries){
        this.mChampionMasteries=championMasteries;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_favorites,parent,false);
        return new FavoritesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesViewHolder holder, int position) {
        ChampionMastery championMastery = mChampionMasteries.get(position);
        if(championMastery ==null){
            return;
        }
        holder.tv_championPoints.setText("Point:"+championMastery.getChampionPoints());
        holder.tv_nameChamp.setText("Champion :"+championMastery.getChampionName());
        holder.tv_champLever.setText("Mastery Lever:"+championMastery.getChampionLevel());
        holder.tv_championPointsUntilNextLevel.setText("Next Level Mastery:"+championMastery.getChampionPointsUntilNextLevel());
        Picasso.get().load("https://ddragon.leagueoflegends.com/cdn/12.11.1/img/champion/"+championMastery.getChampionName()+".png").into(holder.image_Champion);
    }

    @Override
    public int getItemCount() {
        if (mChampionMasteries!=null){
            return mChampionMasteries.size();
        }
        return 0;
    }

    public class FavoritesViewHolder extends RecyclerView.ViewHolder{

        private ImageView image_Champion;
        private TextView tv_nameChamp,tv_champLever,tv_championPoints,tv_championPointsUntilNextLevel;

        public FavoritesViewHolder(@NonNull View itemView) {
            super(itemView);
            image_Champion=itemView.findViewById(R.id.image_Champion);
            tv_nameChamp=itemView.findViewById(R.id.tv_nameChamp);
            tv_champLever=itemView.findViewById(R.id.tv_champLever);
            tv_championPoints=itemView.findViewById(R.id.tv_championPoints);
            tv_championPointsUntilNextLevel=itemView.findViewById(R.id.tv_championPointsUntilNextLevel);
        }
    }
}
