package com.example.lolwiki.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lolwiki.MatchSummaryActivity;
import com.example.lolwiki.PlayerProfileActivity;
import com.example.lolwiki.R;
import com.example.lolwiki.modle.MatchHistory;
import com.example.lolwiki.modle.Participants;
import com.example.lolwiki.modle.PlayerKey;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SummaryFragmentAdapter extends RecyclerView.Adapter<SummaryFragmentAdapter.SummaryFragmentViewHolder>{

    private Context mContext;
    private ArrayList<MatchHistory> mListMatchHistories;
    private String puuid;

    public SummaryFragmentAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(ArrayList<MatchHistory> list,String puuid){
        this.puuid=puuid;
        this.mListMatchHistories=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SummaryFragmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_summary,parent,false);
        return new SummaryFragmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SummaryFragmentViewHolder holder, int position) {
        final MatchHistory matchHistory = mListMatchHistories.get(position);
        if(matchHistory ==null){
            return;
        }
        Participants participants=new Participants();
        for(Participants i:matchHistory.getListParticipants()){
            if(i.getPuuid().equals(this.puuid)){
                participants=i;
            }
        }
        holder.tv_name.setText(participants.getSummonerName());
        holder.tv_KillsSupportDeath.setText(participants.getKills()+"/"+participants.getAssists()+"/"+participants.getDeaths());
        if (participants.getWin()!=null){
        if(participants.getWin()){
            holder.tvWinLose.setText("Win");
        }
        else {
            holder.tvWinLose.setText("Lose");
        }}
        Picasso.get().load("https://ddragon.leagueoflegends.com/cdn/12.11.1/img/champion/"+participants.getChampionName()+".png").into(holder.image_Champion);
        holder.layout_itemSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCLickMatchSummary(matchHistory);
            }
        });
    }

    private void onCLickMatchSummary(MatchHistory matchHistory) {
        Intent intent=new Intent(mContext, MatchSummaryActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Bundle bundle=new Bundle();
        bundle.putSerializable("object_matchHistory",matchHistory);
        intent.putExtras(bundle);
        mContext.startActivities(new Intent[]{intent});
    }

    @Override
    public int getItemCount() {
        if (mListMatchHistories !=null){
            return mListMatchHistories.size();
        }
        return 0;
    }

    public class SummaryFragmentViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_name,tvWinLose,tv_KillsSupportDeath;
        private ImageView image_Champion;
        private LinearLayout layout_itemSummary;

        public SummaryFragmentViewHolder(@NonNull View itemView) {
            super(itemView);
            layout_itemSummary=itemView.findViewById(R.id.layout_itemSummary);
            tv_name=itemView.findViewById(R.id.tv_name);
            tvWinLose=itemView.findViewById(R.id.tvWinLose);
            tv_KillsSupportDeath=itemView.findViewById(R.id.tv_KillsSupportDeath);
            image_Champion=itemView.findViewById(R.id.image_Champion);
        }
    }
}
