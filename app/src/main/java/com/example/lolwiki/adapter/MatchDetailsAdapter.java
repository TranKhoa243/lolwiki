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
import com.example.lolwiki.modle.ChampionMastery;
import com.example.lolwiki.modle.Participants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MatchDetailsAdapter extends RecyclerView.Adapter<MatchDetailsAdapter.MatchDetailsViewHolder>{

    private Context mContext;
    private ArrayList<Participants> mParticipantsArrayList;

    public MatchDetailsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(ArrayList<Participants> participants){
        this.mParticipantsArrayList=participants;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MatchDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_match_details,parent,false);
        return new MatchDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchDetailsViewHolder holder, int position) {
        Participants participant = mParticipantsArrayList.get(position);
        if(participant ==null){
            return;
        }
        holder.tv_name.setText(participant.getSummonerName());
        holder.tv_KillsSupportDeath.setText(participant.getKills()+"/"+participant.getAssists()+"/"+participant.getDeaths());
        holder.tv_Level.setText(participant.getChampLevel().toString());
        holder.tv_PhysicalDamage.setText("Physical Damage:"+participant.getPhysicalDamageDealt());
        holder.tv_MagicDamage.setText("Magic Damage:"+participant.getMagicDamageDealt());
        holder.tv_GoldEarned.setText("Gold Earned:"+participant.getGoldEarned());
        holder.tv_GoldSpent.setText("Gold Spent:"+participant.getGoldSpent());

        ArrayList<Integer> items=participant.getItems();
        if(items.get(0)!=0){
            Picasso.get().load("https://ddragon.leagueoflegends.com/cdn/12.11.1/img/item/"+items.get(0)+".png").into(holder.img_Iteam1);
        }
        if(items.get(1)!=0){
            Picasso.get().load("https://ddragon.leagueoflegends.com/cdn/12.11.1/img/item/"+items.get(1)+".png").into(holder.img_Iteam2);
        }
        if(items.get(2)!=0){
            Picasso.get().load("https://ddragon.leagueoflegends.com/cdn/12.11.1/img/item/"+items.get(2)+".png").into(holder.img_Iteam3);
        }
        if(items.get(3)!=0){
            Picasso.get().load("https://ddragon.leagueoflegends.com/cdn/12.11.1/img/item/"+items.get(3)+".png").into(holder.img_Iteam4);
        }
        if(items.get(4)!=0){
            Picasso.get().load("https://ddragon.leagueoflegends.com/cdn/12.11.1/img/item/"+items.get(4)+".png").into(holder.img_Iteam5);
        }
        if(items.get(5)!=0){
            Picasso.get().load("https://ddragon.leagueoflegends.com/cdn/12.11.1/img/item/"+items.get(5)+".png").into(holder.img_Iteam6);
        }
        Picasso.get().load("https://ddragon.leagueoflegends.com/cdn/12.11.1/img/champion/"+participant.getChampionName()+".png").into(holder.image_Champion);

    }

    @Override
    public int getItemCount() {
        if (mParticipantsArrayList !=null){
            return mParticipantsArrayList.size();
        }
        return 0;
    }

    public class MatchDetailsViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name,tv_KillsSupportDeath,tv_Level,tv_PhysicalDamage,tv_MagicDamage,tv_GoldEarned,tv_GoldSpent;
        private ImageView image_Champion,img_Iteam1,img_Iteam2,img_Iteam3,img_Iteam4,img_Iteam5,img_Iteam6;
        public MatchDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name=itemView.findViewById(R.id.tv_name);
            tv_KillsSupportDeath=itemView.findViewById(R.id.tv_KillsSupportDeath);
            tv_PhysicalDamage=itemView.findViewById(R.id.tv_PhysicalDamage);
            tv_Level=itemView.findViewById(R.id.tv_Level);
            tv_MagicDamage=itemView.findViewById(R.id.tv_MagicDamage);
            tv_GoldEarned=itemView.findViewById(R.id.tv_GoldEarned);
            tv_GoldSpent=itemView.findViewById(R.id.tv_GoldSpent);
            img_Iteam1=itemView.findViewById(R.id.img_Iteam1);
            img_Iteam2=itemView.findViewById(R.id.img_Iteam2);
            img_Iteam3=itemView.findViewById(R.id.img_Iteam3);
            img_Iteam4=itemView.findViewById(R.id.img_Iteam4);
            img_Iteam5=itemView.findViewById(R.id.img_Iteam5);
            img_Iteam6=itemView.findViewById(R.id.img_Iteam6);
            image_Champion=itemView.findViewById(R.id.image_Champion);
        }
    }
}
