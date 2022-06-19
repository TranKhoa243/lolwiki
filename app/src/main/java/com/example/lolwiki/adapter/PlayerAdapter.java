package com.example.lolwiki.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lolwiki.PlayerProfileActivity;
import com.example.lolwiki.R;
import com.example.lolwiki.modle.PlayerKey;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>{

    private Context mContext;
    private List<PlayerKey> mListPlayerKey;

    public PlayerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<PlayerKey> list){
        this.mListPlayerKey =list;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_player,parent,false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        final PlayerKey playerKey = mListPlayerKey.get(position);
        //Log.d("sad",playerKey.toString());
        if(playerKey ==null){
            return;
        }
        holder.tv_name.setText(playerKey.getName());
        holder.tv_lever.setText("Level:"+playerKey.getSummonerLevel());
        Picasso.get().load("https://ddragon.leagueoflegends.com/cdn/12.10.1/img/profileicon/"+playerKey.getProfileIconId()+".png").into(holder.img_player);

        holder.layout_itemPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCLickPlayer(playerKey);
            }
        });
    }

    private void onCLickPlayer(PlayerKey playerKey) {
        Intent intent=new Intent(mContext, PlayerProfileActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("object_player",playerKey);
        intent.putExtras(bundle);
        mContext.startActivities(new Intent[]{intent});
    }

    public void release(){
        mContext=null;
    }

    @Override
    public int getItemCount() {
        if (mListPlayerKey !=null){
            //Log.d("tag ss",mListPlayerKey.toString());
            return mListPlayerKey.size();
        }
        return 0;
    }

    public class PlayerViewHolder extends RecyclerView.ViewHolder{

        private CardView layout_itemPlayer;
        private ImageView img_player;
        private TextView tv_name,tv_lever;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);

            layout_itemPlayer=itemView.findViewById(R.id.layout_itemPlayer);
            img_player=itemView.findViewById(R.id.img_player);
            tv_name=itemView.findViewById(R.id.tv_name);
            tv_lever=itemView.findViewById(R.id.tv_lever);
        }
    }

}
