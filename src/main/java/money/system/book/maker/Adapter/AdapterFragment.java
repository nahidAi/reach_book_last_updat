package money.system.book.maker.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import money.system.book.maker.fragment.PageFragment;


public class AdapterFragment extends FragmentPagerAdapter {
    private String tabTitle[] = new String[]{"علاقه مندی","فهرست مطالب"};
    public AdapterFragment(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position+1);
    }

    @Override
    public int getCount() {
        return 2;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle[position];
    }
}

