package id.ac.umn.made_basiliusbias_submission4.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import id.ac.umn.made_basiliusbias_submission4.fragments.ProfileFragment;

public class TabAdapter extends FragmentStatePagerAdapter {

    private int noTabItem;

    public TabAdapter(FragmentManager fm, int noTabItem) {
        super(fm);
        this.noTabItem = noTabItem;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {

            case 0:
                return ProfileFragment.newInstance("NoData", null);

            case 1:
                return ProfileFragment.newInstance("FavoriteMovies", "GridLayout");

            case 2:
                return ProfileFragment.newInstance("FavoriteTV", "LinearLayout");

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return noTabItem;
    }
}