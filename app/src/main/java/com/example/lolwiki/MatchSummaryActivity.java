package com.example.lolwiki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import com.example.lolwiki.adapter.ViewPagerFragmentAdapter;
import com.example.lolwiki.adapter.ViewPagerSummaryAdapter;
import com.example.lolwiki.modle.MatchHistory;
import com.example.lolwiki.modle.PlayerKey;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MatchSummaryActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager2 mviewPager2;
    private ViewPagerSummaryAdapter viewPagerSummaryAdapter;
    private String[] titles= new String[]{"Summary","Radiant","Dire"};
    private MatchHistory matchHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_summary);

        addControls();
        addEvents();

    }

    private void addEvents() {

    }

    private void addControls() {
        mTabLayout=findViewById(R.id.tab_layout);
        mviewPager2=findViewById(R.id.view_pager);
        viewPagerSummaryAdapter=new ViewPagerSummaryAdapter(this);
        mviewPager2.setAdapter(viewPagerSummaryAdapter);
        new TabLayoutMediator(mTabLayout,mviewPager2,((tab, position) -> tab.setText(titles[position]))).attach();

        Bundle bundle=getIntent().getExtras();
        if(bundle==null){
            return;
        }

        matchHistory = (MatchHistory) bundle.get("object_matchHistory");
        /*Log.d("Check matchHistory",matchHistory.toString());*/
    }

    public MatchHistory getMatchHistory() {
        return matchHistory;
    }
}