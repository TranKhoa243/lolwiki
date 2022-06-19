package com.example.lolwiki.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lolwiki.MainActivity;
import com.example.lolwiki.PlayerProfileActivity;
import com.example.lolwiki.R;
import com.example.lolwiki.adapter.PlayerAdapter;
import com.example.lolwiki.adapter.SummaryFragmentAdapter;
import com.example.lolwiki.modle.MatchHistory;
import com.example.lolwiki.modle.Participants;
import com.example.lolwiki.modle.PlayerKey;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SummaryFragment extends Fragment {

    private View mView;
    private ImageView img_player,img_rank;
    private TextView tv_name,tv_lever,tv_rank,tv_wins_losses;
    private PlayerKey mPlayerKey;
    private PlayerProfileActivity mPlayerProfileActivity;
    private RecyclerView mRecyclerView;
    private SummaryFragmentAdapter summaryFragmentAdapter;
    private ArrayList<MatchHistory> matchHistoryList = new ArrayList<>();

    public SummaryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView=inflater.inflate(R.layout.fragment_summary, container, false);
        mPlayerProfileActivity= (PlayerProfileActivity) getActivity();
        addControls();

        return mView;
    }

    private ArrayList<Participants> secondServiceCall(String matchID) {
        String mRegion=mPlayerKey.getRegion();
        ArrayList<Participants> arrayListParticipants=new ArrayList<Participants>();
        String url=null ;
        if(mRegion.equals("BR1") || mRegion.equals("LA1") || mRegion.equals("LA2") || mRegion.equals("NA1") || mRegion.equals("OC1")){
            url="https://americas.api.riotgames.com/lol/match/v5/matches/"+matchID.toString()+"?api_key="+getString(R.string.api_key);
        }
        else if (mRegion.equals("EUN1") || mRegion.equals("EUW1") || mRegion.equals("RU") || mRegion.equals("TR1")){
            url="https://europe.api.riotgames.com/lol/match/v5/matches/"+matchID+"?api_key="+getString(R.string.api_key);
        }
        else if (mRegion.equals("JP1") || mRegion.equals("KR")){
            url="https://asia.api.riotgames.com/lol/match/v5/matches/"+matchID+"?api_key="+getString(R.string.api_key);
        }
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONObject jsonObjectInfo=jsonObject.getJSONObject("info");
                            JSONArray jsonArrayList=jsonObjectInfo.getJSONArray("participants");
                            for (int i=0;i<jsonArrayList.length();i++){
                                Participants participant=new Participants();
                                ArrayList<Integer> items = new ArrayList<Integer>();;
                                JSONObject jsonObjectList=jsonArrayList.getJSONObject(i);
                                participant.setAssists(jsonObjectList.getInt("assists"));
                                participant.setPuuid(jsonObjectList.getString("puuid"));
                                participant.setSummonerId(jsonObjectList.getString("summonerId"));

                                items.add(jsonObjectList.getInt("item0"));
                                items.add(jsonObjectList.getInt("item1"));
                                items.add(jsonObjectList.getInt("item2"));
                                items.add(jsonObjectList.getInt("item3"));
                                items.add(jsonObjectList.getInt("item4"));
                                items.add(jsonObjectList.getInt("item5"));
                                participant.setItems(items);
                                participant.setDeaths(jsonObjectList.getInt("deaths"));
                                participant.setKills(jsonObjectList.getInt("kills"));
                                participant.setChampLevel(jsonObjectList.getInt("champLevel"));
                                participant.setChampionName(jsonObjectList.getString("championName"));
                                participant.setPhysicalDamageDealt(jsonObjectList.getInt("physicalDamageDealt"));
                                participant.setMagicDamageDealt(jsonObjectList.getInt("magicDamageDealt"));
                                participant.setSummonerName(jsonObjectList.getString("summonerName"));
                                participant.setWin(jsonObjectList.getBoolean("win"));
                                participant.setGoldEarned(jsonObjectList.getInt("goldEarned"));
                                participant.setGoldSpent(jsonObjectList.getInt("goldSpent"));
                                arrayListParticipants.add(participant);

                            }
                        } catch (JSONException exception) {
                            exception.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(stringRequest);
        return arrayListParticipants;
    }

    private void GetListMatchHistory() {
        ArrayList<MatchHistory> mListMatchHistories=new ArrayList<>();
        String mRegion=mPlayerKey.getRegion();
        String url=null ;
        if(mRegion.equals("BR1") || mRegion.equals("LA1") || mRegion.equals("LA2") || mRegion.equals("NA1") || mRegion.equals("OC1")){
            url="https://americas.api.riotgames.com/lol/match/v5/matches/by-puuid/"+mPlayerKey.getPuuid()+"/ids?type=ranked&start=0&count=20&api_key="+getString(R.string.api_key);
        }
        else if (mRegion.equals("EUN1") || mRegion.equals("EUW1") || mRegion.equals("RU") || mRegion.equals("TR1")){
            url="https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/"+mPlayerKey.getPuuid()+"/ids?type=ranked&start=0&count=20&api_key="+getString(R.string.api_key);
        }
        else if (mRegion.equals("JP1") || mRegion.equals("KR")){
            url="https://asia.api.riotgames.com/lol/match/v5/matches/by-puuid/"+mPlayerKey.getPuuid()+"/ids?type=ranked&start=0&count=20&api_key="+getString(R.string.api_key);
        }
        Log.d("s",url);
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i=0;i<20;i++){
                            try {
                                String str=response.get(i)+"";
                                MatchHistory matchHistory=new MatchHistory(str,mPlayerKey.getPuuid(),secondServiceCall(str));
                                matchHistoryList.add(matchHistory);
                            } catch (JSONException exception) {
                                exception.printStackTrace();
                            }
                        }
                        mPlayerKey.getPlayerDataSolo().setMatchHistory(matchHistoryList);
                        if(mPlayerKey.getPlayerDataSolo().getMatchHistory()!=null) {
                            buildRecyclerView();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("VolleyError:",error.toString());
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    private void buildRecyclerView() {
        summaryFragmentAdapter=new SummaryFragmentAdapter(getActivity().getApplicationContext());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity().getApplicationContext(),RecyclerView.VERTICAL,false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        if (0 == mRecyclerView.getItemDecorationCount()) {
            RecyclerView.ItemDecoration decoration=new DividerItemDecoration(getActivity().getApplicationContext(),DividerItemDecoration.VERTICAL);
            mRecyclerView.addItemDecoration(decoration);
        }
        summaryFragmentAdapter.setData(matchHistoryList,mPlayerKey.getPuuid());
        mRecyclerView.setAdapter(summaryFragmentAdapter);
    }

    private void addControls() {
        img_player=mView.findViewById(R.id.img_player);
        img_rank=mView.findViewById(R.id.img_rank);
        tv_name=mView.findViewById(R.id.tv_name);
        tv_lever=mView.findViewById(R.id.tv_lever);
        tv_rank=mView.findViewById(R.id.tv_rank);
        tv_wins_losses=mView.findViewById(R.id.tv_wins_losses);

        mPlayerKey= mPlayerProfileActivity.getPlayerKey();

        mRecyclerView=mView.findViewById(R.id.rcv_MatchHistory);

        tv_name.setText(mPlayerKey.getName());
        tv_lever.setText("Lever:"+mPlayerKey.getSummonerLevel());
        tv_rank.setText("Division:"+mPlayerKey.getPlayerDataSolo().getRank());
        String text="Points:"+mPlayerKey.getPlayerDataSolo().getLeaguePoints()+"\nWins:"+mPlayerKey.getPlayerDataSolo().getWins()+"\nLosses"+mPlayerKey.getPlayerDataSolo().getLosses();
        tv_wins_losses.setText(text);
        Picasso.get().load("https://ddragon.leagueoflegends.com/cdn/12.10.1/img/profileicon/"+mPlayerKey.getProfileIconId()+".png").into(img_player);
        switch ( mPlayerKey.getPlayerDataSolo().getTier() ) {
            case  "IRON":
                img_rank.setImageResource(R.drawable.emblem_iron);
                break;
            case  "BRONZE":
                img_rank.setImageResource(R.drawable.emblem_bronze);
                break;
            case  "SILVER":
                img_rank.setImageResource(R.drawable.emblem_silver);
                break;
            case  "GOLD":
                img_rank.setImageResource(R.drawable.emblem_gold);
                break;
            case  "PLATINUM":
                img_rank.setImageResource(R.drawable.emblem_platinum);
                break;
            case  "DIAMOND":
                img_rank.setImageResource(R.drawable.emblem_diamond);
                break;
            case  "MASTER":
                img_rank.setImageResource(R.drawable.emblem_master);
                break;
            case  "GRANDMASTER":
                img_rank.setImageResource(R.drawable.emblem_grandmaster);
                break;
            case  "CHALLENGER":
                img_rank.setImageResource(R.drawable.emblem_challenger);
                break;
        }
        GetListMatchHistory();
    }
}