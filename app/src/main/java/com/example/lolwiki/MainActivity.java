package com.example.lolwiki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lolwiki.adapter.PlayerAdapter;
import com.example.lolwiki.modle.PlayerDataSolo;
import com.example.lolwiki.modle.PlayerKey;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity{

    private String apiKey;

    private EditText edtSearch;
    private Spinner spinner_Tier,spinner_Region,spinner_Division;
    private Button btnSearch;
    private RecyclerView rcv_Player;
    private String[] str_Tier,str_Division,str_Region;
    private PlayerAdapter playerAdapter;
    private ArrayAdapter<String> ar_Tier,ar_Division,ar_Region;

    private List<PlayerKey> listPlayerKey = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();

    }

    private void buildRecyclerView() {

        playerAdapter=new PlayerAdapter(this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcv_Player.setLayoutManager(linearLayoutManager);
        if (0 == rcv_Player.getItemDecorationCount()) {
            RecyclerView.ItemDecoration decoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
            rcv_Player.addItemDecoration(decoration);
        }
        playerAdapter.setData(listPlayerKey);
        rcv_Player.setAdapter(playerAdapter);

    }

    private void addEvents() {
        spinner_Tier.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner_Region.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner_Division.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listPlayerKey.clear();
                String keySearch=edtSearch.getText().toString();
                if(keySearch.trim().equals("")){
                    try {
                        HttpsTrustManager.allowAllSSL();
                        GetListCurrentPlayerData();

                    }
                    catch (Exception e){
                        Toast.makeText(MainActivity.this,e.toString(),Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    HttpsTrustManager.allowAllSSL();
                    GetCurrentPlayerData(keySearch.trim());
                }
            }
        });
    }

    private void addControls() {
        apiKey=getString(R.string.api_key);

        edtSearch=findViewById(R.id.edtSearch);
        spinner_Tier=findViewById(R.id.spinner_Tier);
        spinner_Region=findViewById(R.id.spinner_Region);
        spinner_Division=findViewById(R.id.spinner_Division);
        btnSearch=findViewById(R.id.btnSearch);
        rcv_Player=findViewById(R.id.rcv_Player);

        str_Tier=getResources().getStringArray(R.array.tier);
        str_Division=getResources().getStringArray(R.array.division);
        str_Region=getResources().getStringArray(R.array.region);

        ar_Tier = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,str_Tier);
        ar_Division = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,str_Division);
        ar_Region = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,str_Region);

        spinner_Tier.setAdapter(ar_Tier);
        spinner_Division.setAdapter(ar_Division);
        spinner_Region.setAdapter(ar_Region);


    }

    private void GetListCurrentPlayerData(){

        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
        String url="https://"+spinner_Region.getSelectedItem().toString().toLowerCase(Locale.ROOT)+".api.riotgames.com/lol/league-exp/v4/entries/RANKED_SOLO_5x5/"+spinner_Tier.getSelectedItem().toString()+"/"+spinner_Division.getSelectedItem().toString()+"?page=1&api_key="+apiKey;
        //Log.d("MyurlGetListCurrentPlayerData:",url);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i=0;i<20;i++){
                            try {
                                JSONObject object=response.getJSONObject(i);
                                String leagueId=object.getString("leagueId");
                                String tier=object.getString("tier");
                                String rank=object.getString("rank");
                                String summonerId=object.getString("summonerId");
                                Integer leaguePoints=object.getInt("leaguePoints");
                                Integer wins=object.getInt("wins");
                                Integer losses=object.getInt("losses");

                                PlayerKey playerKey=secondServiceCall(new PlayerDataSolo(leagueId,summonerId,tier,rank,leaguePoints,wins,losses,null));
                                listPlayerKey.add(playerKey);
                                buildRecyclerView();

                            }
                            catch (JSONException exception) {
                                exception.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,"Load data from api fail",Toast.LENGTH_LONG);
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);

    }


    public PlayerKey secondServiceCall(PlayerDataSolo playerDataSolo){
        PlayerKey PlayerKey = new PlayerKey();
            RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);

            String url1="https://"+spinner_Region.getSelectedItem().toString().toLowerCase(Locale.ROOT)+".api.riotgames.com/lol/summoner/v4/summoners/"+playerDataSolo.getSummonerId()+"?api_key="+apiKey;
            //Log.d("MyurlsecondServiceCall",url1);
            JsonObjectRequest jsonObjectRequest1=new JsonObjectRequest(Request.Method.GET, url1, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                PlayerKey.setId(response.getString("id"));
                                PlayerKey.setAccountId(response.getString("accountId"));
                                PlayerKey.setPuuid(response.getString("puuid"));
                                PlayerKey.setName(response.getString("name"));
                                PlayerKey.setProfileIconId(response.getInt("profileIconId"));
                                PlayerKey.setRevisionDate(String.valueOf(response.getInt("revisionDate")));
                                PlayerKey.setSummonerLevel(String.valueOf(response.getInt("summonerLevel")));
                                PlayerKey.setRegion(spinner_Region.getSelectedItem().toString());
                                PlayerKey.setPlayerDataSolo(playerDataSolo);
                                //Log.d("PlayerKey secondServiceCall",PlayerKey.toString());
                            } catch (JSONException exception) {
                                exception.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("VolleyError",error.toString());
                        }
                    });
            requestQueue.add(jsonObjectRequest1);

        return PlayerKey;
    }

    private void GetCurrentPlayerData(String playerName){
        PlayerKey PlayerKey = new PlayerKey();
        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
        String url="https://"+spinner_Region.getSelectedItem().toString().toLowerCase(Locale.ROOT)+".api.riotgames.com/lol/summoner/v4/summoners/by-name/"+playerName+"?api_key="+apiKey;
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            PlayerKey.setId(response.getString("id"));
                            PlayerKey.setAccountId(response.getString("accountId"));
                            PlayerKey.setPuuid(response.getString("puuid"));
                            PlayerKey.setName(response.getString("name"));
                            PlayerKey.setProfileIconId(response.getInt("profileIconId"));
                            PlayerKey.setRevisionDate(String.valueOf(response.getInt("revisionDate")));
                            PlayerKey.setSummonerLevel(String.valueOf(response.getInt("summonerLevel")));
                            PlayerKey.setRegion(spinner_Region.getSelectedItem().toString());
                            PlayerKey.setPlayerDataSolo(getPlayerDataSolo(PlayerKey.getId()));
                            listPlayerKey.add(PlayerKey);
                            buildRecyclerView();
                        } catch (JSONException exception) {
                            exception.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error GetCurrentPlayer",error.toString());
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }

    public PlayerDataSolo getPlayerDataSolo(String summonerId){
        PlayerDataSolo playerDataSolo=new PlayerDataSolo();
        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
        String url="https://"+spinner_Region.getSelectedItem().toString().toLowerCase(Locale.ROOT)+".api.riotgames.com/lol/league/v4/entries/by-summoner/"+summonerId+"?api_key="+apiKey;
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject object=response.getJSONObject(0);
                            playerDataSolo.setLeagueId(object.getString("leagueId"));
                            playerDataSolo.setTier(object.getString("tier"));
                            playerDataSolo.setRank(object.getString("rank"));
                            playerDataSolo.setSummonerId(object.getString("summonerId"));
                            playerDataSolo.setLeaguePoints(object.getInt("leaguePoints"));
                            playerDataSolo.setWins(object.getInt("wins"));
                            playerDataSolo.setLosses(object.getInt("losses"));
                            /*Log.d("playerDataSolo getPlayerDataSolo",playerDataSolo.toString());*/
                        } catch (JSONException exception) {
                            exception.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error getPlayerDataSolo",error.toString());
                    }
                });
        requestQueue.add(jsonArrayRequest);
        return playerDataSolo;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(playerAdapter!=null){
            playerAdapter.release();
        }
    }
}