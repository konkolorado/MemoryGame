package hu.ait.android.uriel.memorygame;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by urielmandujano on 4/14/15.
 */

public class MyFragmentAdapter extends FragmentPagerAdapter {

    FragmentManager self;
    public MyFragmentAdapter(FragmentManager fm) {
        super(fm);
        self = fm;
    }

    @Override
    public Fragment getItem(int i) {

        switch (i) {
            // Looks like every swipe creates a new Fragment - which is not efficient
            // BUT this implementation is CORRECT
            // Android only calls this function ONCE so this implementation is correct
            case 0:
                return new FirstTutorialFragment();
            case 1:
                return new SecondTutorialFragment();
            case 2:
                return new ThirdTutorialFragment();
            default:
                return new FirstTutorialFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + Integer.toString(position + 1);
    }

    @Override
    public int getCount() {
        // Should return the number of pages
        return 3;
    }


    public String getFragmentTag(int viewPagerId, int fragmentPosition)
    {
        return "android:switcher:" + viewPagerId + ":" + fragmentPosition;
    }
}

