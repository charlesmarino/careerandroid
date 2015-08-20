package com.charlesmarino.tustle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.charlesmarino.tustle.Fragments.ArticlesFragment;
import com.charlesmarino.tustle.Fragments.SnapshotFragment;

/**
 * Pager adapter for the sliding tabs in the home activity
 * Created by kirstiebooras on 8/17/15.
 */
public class HomeActivityPagerAdapter extends FragmentStatePagerAdapter {

    public HomeActivityPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new SnapshotFragment();
            case 1:
                return new Fragment();
            case 2:
                return new ArticlesFragment();
            default:
                return new Fragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Snapshots";
            case 1:
                return "Products";
            case 2:
                return "Articles";
            default:
                return "Tab";
        }
    }

}
