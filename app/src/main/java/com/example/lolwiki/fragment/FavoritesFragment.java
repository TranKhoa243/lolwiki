package com.example.lolwiki.fragment;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.lolwiki.PlayerProfileActivity;
import com.example.lolwiki.R;
import com.example.lolwiki.adapter.FavoritesFragmentAdapter;
import com.example.lolwiki.adapter.SummaryFragmentAdapter;
import com.example.lolwiki.modle.ChampionMastery;
import com.example.lolwiki.modle.MatchHistory;
import com.example.lolwiki.modle.PlayerKey;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;


public class FavoritesFragment extends Fragment {

    private View mView;
    private SparseArray<String> myStringArray;
    private PlayerProfileActivity mPlayerProfileActivity;
    private PlayerKey mPlayerKey;
    private ArrayList<ChampionMastery> championMasteries=new ArrayList<>();
    private RecyclerView rcv_FragmentFavorites;
    private FavoritesFragmentAdapter favoritesFragmentAdapter;


    public FavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView=inflater.inflate(R.layout.fragment_favorites, container, false);
        mPlayerProfileActivity= (PlayerProfileActivity) getActivity();
        addControls();
        return mView;
    }


    private void addControls() {
        this.myStringArray = parseStringArray(R.array.my_string_array);
        mPlayerKey= mPlayerProfileActivity.getPlayerKey();

        rcv_FragmentFavorites=mView.findViewById(R.id.rcv_FragmentFavorites);
        GetListChampionMastery();
    }

    private void GetListChampionMastery() {
        String mRegion=mPlayerKey.getRegion().toLowerCase(Locale.ROOT);
        String url="https://"+mRegion+".api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-summoner/"+mPlayerKey.getPlayerDataSolo().getSummonerId()+"?api_key="+getString(R.string.api_key);
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i=0;i<20;i++){
                            try {
                                JSONObject jsonObject=response.getJSONObject(i);
                                Integer championId=jsonObject.getInt("championId");
                                Integer championPoints=jsonObject.getInt("championPoints");
                                Integer championPointsUntilNextLevel=jsonObject.getInt("championPointsUntilNextLevel");
                                Integer championLevel=jsonObject.getInt("championLevel");
                                String championName=myStringArray.get(championId);
                                championMasteries.add(new ChampionMastery(championName,championPoints,championPointsUntilNextLevel,championLevel));
                                Log.d("Sas",championMasteries.toString());
                                Log.d("Sasd",response.getJSONObject(4).toString());
                            } catch (JSONException exception) {
                                exception.printStackTrace();
                            }
                        }
                        buildRecyclerView();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("VolleyError",error.toString());
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    private void buildRecyclerView() {
        favoritesFragmentAdapter=new FavoritesFragmentAdapter(getActivity().getApplicationContext());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity().getApplicationContext(),RecyclerView.VERTICAL,false);
        rcv_FragmentFavorites.setLayoutManager(linearLayoutManager);
        favoritesFragmentAdapter.setData(championMasteries);
        rcv_FragmentFavorites.setAdapter(favoritesFragmentAdapter);
    }

    public SparseArray<String> parseStringArray(int stringArrayResourceId) {
        String[] stringArray = getResources().getStringArray(stringArrayResourceId);
        SparseArray<String> outputArray = new SparseArray<String>(stringArray.length);
        for (String entry : stringArray) {
            String[] splitResult = entry.split("\\|");
            outputArray.put(Integer.valueOf(splitResult[0]), splitResult[1]);
        }
        return outputArray;
    }
}