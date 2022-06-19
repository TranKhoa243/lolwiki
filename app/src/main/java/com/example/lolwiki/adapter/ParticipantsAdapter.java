package com.example.lolwiki.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lolwiki.R;
import com.example.lolwiki.modle.Participants;
import com.example.lolwiki.modle.PlayerKey;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ParticipantsAdapter extends RecyclerView.Adapter<ParticipantsAdapter.ParticipantsViewHolder>{

    private ArrayList<Participants> listParticipants;

    public void setData(ArrayList<Participants> listParticipants){
        this.listParticipants=listParticipants;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ParticipantsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_match_summary,parent,false);
        return new ParticipantsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParticipantsViewHolder holder, int position) {
        final Participants participants = listParticipants.get(position);
        if(participants ==null){
            return;
        }
        holder.tv_name.setText(participants.getSummonerName());
        holder.tv_Level.setText(participants.getChampLevel()+"");
        holder.tv_KillsSupportDeath.setText(participants.getKills()+"/"+participants.getAssists()+"/"+participants.getDeaths());
        ArrayList<Integer> items=participants.getItems();
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
        Picasso.get().load("https://ddragon.leagueoflegends.com/cdn/12.11.1/img/champion/"+participants.getChampionName()+".png").into(holder.image_Champion);
    }

    @Override
    public int getItemCount() {
        if (listParticipants !=null){
            return listParticipants.size();
        }
        return 0;
    }

    public class ParticipantsViewHolder extends RecyclerView.ViewHolder{

        private ImageView img_Iteam1,img_Iteam2,img_Iteam3,img_Iteam4,img_Iteam5,img_Iteam6,image_Champion;
        private TextView tv_Level,tv_KillsSupportDeath,tv_name;

        public ParticipantsViewHolder(@NonNull View itemView) {
            super(itemView);
            img_Iteam1=itemView.findViewById(R.id.img_Iteam1);
            img_Iteam2=itemView.findViewById(R.id.img_Iteam2);
            img_Iteam3=itemView.findViewById(R.id.img_Iteam3);
            img_Iteam4=itemView.findViewById(R.id.img_Iteam4);
            img_Iteam5=itemView.findViewById(R.id.img_Iteam5);
            img_Iteam6=itemView.findViewById(R.id.img_Iteam6);
            image_Champion=itemView.findViewById(R.id.image_Champion);
            tv_Level=itemView.findViewById(R.id.tv_Level);
            tv_KillsSupportDeath=itemView.findViewById(R.id.tv_KillsSupportDeath);
            tv_name=itemView.findViewById(R.id.tv_name);
        }
    }
}
