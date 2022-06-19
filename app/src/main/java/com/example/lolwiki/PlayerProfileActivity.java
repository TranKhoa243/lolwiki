package com.example.lolwiki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.lolwiki.adapter.ViewPagerFragmentAdapter;
import com.example.lolwiki.fragment.SummaryFragment;
import com.example.lolwiki.modle.PlayerKey;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class PlayerProfileActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager2 mviewPager2;
    private ViewPagerFragmentAdapter viewPagerFragmentAdapter;
    private String[] titles= new String[]{"Summary","Favorites"};
    private PlayerKey playerKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_profile);

        addControls();
        addEvents();
    }

    public PlayerKey getPlayerKey() {
        return playerKey;
    }

    private void addEvents() {

    }

    private void addControls() {

        mTabLayout=findViewById(R.id.tab_layout);
        mviewPager2=findViewById(R.id.view_pager);
        viewPagerFragmentAdapter=new ViewPagerFragmentAdapter(this);
        mviewPager2.setAdapter(viewPagerFragmentAdapter);
        new TabLayoutMediator(mTabLayout,mviewPager2,((tab, position) -> tab.setText(titles[position]))).attach();

        Bundle bundle=getIntent().getExtras();
        if(bundle==null){
            return;
        }

        playerKey = (PlayerKey) bundle.get("object_player");
    }
}