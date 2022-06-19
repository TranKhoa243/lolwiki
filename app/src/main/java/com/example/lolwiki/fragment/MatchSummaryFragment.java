package com.example.lolwiki.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lolwiki.MatchSummaryActivity;
import com.example.lolwiki.PlayerProfileActivity;
import com.example.lolwiki.R;
import com.example.lolwiki.adapter.CategoryAdapter;
import com.example.lolwiki.modle.Category;
import com.example.lolwiki.modle.MatchHistory;
import com.example.lolwiki.modle.Participants;

import java.util.ArrayList;
import java.util.List;


public class MatchSummaryFragment extends Fragment {

    private View mView;
    private MatchSummaryActivity mMatchSummaryActivity;
    private TextView textView_WhoWin;
    private MatchHistory mMatchHistory;
    private RecyclerView rcv_Match_Summary;
    private CategoryAdapter categoryAdapter;
    private Boolean win;

    public MatchSummaryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView=inflater.inflate(R.layout.fragment_match_summary, container, false);
        mMatchSummaryActivity= (MatchSummaryActivity) getActivity();
        addControls();
        //Log.d("S",mMatchHistory.toString());
        return mView;
    }


    private void addControls() {
        textView_WhoWin=mView.findViewById(R.id.textView_WhoWin);
        mMatchHistory=mMatchSummaryActivity.getMatchHistory();

        for (Participants i: mMatchHistory.getListParticipants()){
            if(i.getPuuid().equals(mMatchHistory.getPuuid())&&i.getWin()){
                this.win=i.getWin();
                textView_WhoWin.setText("Radiant Victory");
                textView_WhoWin.setTextColor(Color.parseColor("#8BC34A"));
            }
            else if(i.getPuuid().equals(mMatchHistory.getPuuid())&&i.getWin()==false){
                this.win=i.getWin();
                textView_WhoWin.setText("Dire Victory");
                textView_WhoWin.setTextColor(Color.parseColor("#F44336"));
            }
        }

        rcv_Match_Summary=mView.findViewById(R.id.rcv_Match_Summary);
        categoryAdapter=new CategoryAdapter(getActivity().getApplicationContext());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity().getApplicationContext(),RecyclerView.VERTICAL,false);
        rcv_Match_Summary.setLayoutManager(linearLayoutManager);

        categoryAdapter.setData(getListParticipants());
        rcv_Match_Summary.setAdapter(categoryAdapter);
    }

    private ArrayList<Category> getListParticipants() {
        ArrayList<Category> categories=new ArrayList<>();

        ArrayList<Participants> listRadiant=new ArrayList<>();
        ArrayList<Participants> listDire=new ArrayList<>();

        for (Participants i:mMatchHistory.getListParticipants()){
            if(i.getWin()==this.win){
                listRadiant.add(i);
            }
            else {
                listDire.add(i);
            }
        }
        categories.add(new Category("The Radiant",listRadiant));
        categories.add(new Category("The Dire",listDire));

        return categories;
    }
}