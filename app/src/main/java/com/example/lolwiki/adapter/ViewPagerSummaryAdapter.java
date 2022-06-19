package com.example.lolwiki.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.lolwiki.fragment.DireFragment;
import com.example.lolwiki.fragment.FavoritesFragment;
import com.example.lolwiki.fragment.MatchSummaryFragment;
import com.example.lolwiki.fragment.RadiantFragment;
import com.example.lolwiki.fragment.SummaryFragment;

public class ViewPagerSummaryAdapter extends FragmentStateAdapter {

    private String[] titles= new String[]{"Summary","Radiant","Dire"};

    public ViewPagerSummaryAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0:
                return new MatchSummaryFragment();
            case 1:
                return new RadiantFragment();
            case 2:
                return new DireFragment();
        }
        return new MatchSummaryFragment();
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}
