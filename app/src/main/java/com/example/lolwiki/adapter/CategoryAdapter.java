package com.example.lolwiki.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lolwiki.R;
import com.example.lolwiki.modle.Category;
import com.example.lolwiki.modle.Participants;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{

    private Context mContext;
    private ArrayList<Category> mCategoryList;

    public CategoryAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(ArrayList<Category> mCategoryList){
        this.mCategoryList=mCategoryList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_match_summary,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        final Category category = mCategoryList.get(position);

        if(category ==null){
            return;
        }
        holder.tvNameCategory.setText(category.getNameCategory());
        if(category.getNameCategory().equals("The Dire")){
            holder.tvNameCategory.setBackgroundColor(Color.parseColor("#F44336"));
        }
        else {
            holder.tvNameCategory.setBackgroundColor(Color.parseColor("#8BC34A"));
        }

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(mContext,RecyclerView.VERTICAL,false);
        holder.rcvParticipants.setLayoutManager(linearLayoutManager);

        ParticipantsAdapter participantsAdapter=new ParticipantsAdapter();
        participantsAdapter.setData(category.getListParticipants());
        holder.rcvParticipants.setAdapter(participantsAdapter);
    }

    @Override
    public int getItemCount() {
        if (mCategoryList !=null){
            return mCategoryList.size();
        }
        return 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{

        private TextView tvNameCategory;
        private RecyclerView rcvParticipants;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameCategory=itemView.findViewById(R.id.tv_category);
            rcvParticipants=itemView.findViewById(R.id.rcv_ItemCategory);
        }
    }
}
