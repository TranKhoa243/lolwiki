package com.example.lolwiki.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.lolwiki.fragment.FavoritesFragment;
import com.example.lolwiki.fragment.SummaryFragment;

public class ViewPagerFragmentAdapter extends FragmentStateAdapter {

    private String[] titles= new String[]{"Summary","Favorites"};

    public ViewPagerFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0:
                return new SummaryFragment();
            case 1:
                return new FavoritesFragment();
        }
        return new SummaryFragment();
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}
