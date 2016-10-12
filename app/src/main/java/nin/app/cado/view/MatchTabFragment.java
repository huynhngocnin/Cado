package nin.app.cado.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nin.app.cado.R;

/**
 * Created by NinHN on 8/27/2016.
 */
public class MatchTabFragment extends Fragment implements View.OnClickListener {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
        View x = inflater.inflate(R.layout.match_detail_fragment, null);
        tabLayout = (TabLayout) x.findViewById(R.id.detail_tabs);
        viewPager = (ViewPager) x.findViewById(R.id.detai_viewpager);

        /**
         *Set an Apater for the View Pager
         */
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        /**
         * Now , this is a workaround ,
         * The setupWithViewPager dose't works without the runnable .
         * Maybe a Support Library Bug .
         */

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
        return x;
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new MatchTabItemGeneralFragment();
                case 1:
                    return new MatchTabItemLiveFragment();
                case 2:
                    return new MatchTabItemAnalysisFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return int_items;
        }

        /**
         * This method returns the title of the tab according to the position.
         */

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.match_tab_child_general);
                case 1:
                    return getString(R.string.match_tab_child_live);
                case 2:
                    return getString(R.string.match_tab_child_analysis);
            }
            return null;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.btn_list_refresh:
//                break;
            default:
                break;
        }
    }

}
