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

import com.example.lolwiki.MatchSummaryActivity;
import com.example.lolwiki.R;
import com.example.lolwiki.adapter.CategoryAdapter;
import com.example.lolwiki.adapter.FavoritesFragmentAdapter;
import com.example.lolwiki.adapter.MatchDetailsAdapter;
import com.example.lolwiki.modle.MatchHistory;
import com.example.lolwiki.modle.Participants;

import java.util.ArrayList;


public class DireFragment extends Fragment {
    private View mView;
    private MatchSummaryActivity mMatchSummaryActivity;
    private RecyclerView recyclerView;
    private MatchDetailsAdapter matchDetailsAdapter;
    private MatchHistory mMatchHistory;
    private ArrayList<Participants> mParticipantsArrayList=new ArrayList<>();

    public DireFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_dire, container, false);
        mMatchSummaryActivity= (MatchSummaryActivity) getActivity();
        addControls();

        return mView;
    }

    private void addControls() {
        mMatchHistory=mMatchSummaryActivity.getMatchHistory();

        recyclerView=mView.findViewById(R.id.rcv_FragmentDire);
        Boolean win = true;
        for (Participants i: mMatchHistory.getListParticipants()){
            if(i.getPuuid().equals(mMatchHistory.getPuuid())){
                win=i.getWin();
            }
        }
        for (Participants i: mMatchHistory.getListParticipants()){
            if(Boolean.compare(i.getWin(),win)!=0) {
                mParticipantsArrayList.add(i);
            }

        }
        matchDetailsAdapter=new MatchDetailsAdapter(getActivity().getApplicationContext());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity().getApplicationContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        matchDetailsAdapter.setData(mParticipantsArrayList);
        recyclerView.setAdapter(matchDetailsAdapter);
    }
}