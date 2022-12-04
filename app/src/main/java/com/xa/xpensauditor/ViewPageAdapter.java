package com.xa.xpensauditor;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages two tabs in main page for Categorized and un-Categorized
 */
public class ViewPageAdapter extends FragmentPagerAdapter {


    private String title[] = {"All Transactions", "Uncategorised Transaction"};

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    /**
     * Constructor
     * @param manager
     */
    public ViewPageAdapter(FragmentManager manager) {
        super(manager);
    }

    /**
     * Get item with position
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    /**
     * Get item count
     * @return
     */
    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title){
        mFragmentTitleList.add(title);
        mFragmentList.add(fragment);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}
