package com.example.noticeable.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.noticeable.model.OnBoardModel;
import com.example.noticeable.onBoard.FirstFragment;

import java.util.ArrayList;
import java.util.List;

public class OnBoardAdapter extends FragmentPagerAdapter {

    List<OnBoardModel> list = new ArrayList<>();

    public OnBoardAdapter(List<OnBoardModel> list, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new FirstFragment();
        switch (position) {
            case 0:
            case 1:
            case 2:
                return FirstFragment.newInstance(list.get(position).getTitle(), list.get(position).getImage());
        }
        return null;
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
