package nin.app.cado.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

import nin.app.cado.R;
import nin.app.cado.Util.SnackbarUtil;

/**
 * Created by NinHN on 8/27/2016.
 */
public class TabFragment extends Fragment implements View.OnClickListener {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static FloatingActionButton btnAdd;
    private static int int_items = 3;
    private TabItemLiveFragment tabItemLiveFragment;
    private TabItemResultFragment tabItemResultFragment;
    private TabItemFixturesFragment tabItemFixturesFragment;
    private int pageSelected = 0;
    private MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
        View x = inflater.inflate(R.layout.tab_layout, null);
        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);
        //Set number fragment no reload
        viewPager.setOffscreenPageLimit(3);
        btnAdd = (FloatingActionButton) x.findViewById(R.id.btn_list_refresh);
        btnAdd.setOnClickListener(this);

        mainActivity = (MainActivity) getActivity();

        tabItemLiveFragment = new TabItemLiveFragment();
        tabItemResultFragment = new TabItemResultFragment();
        tabItemFixturesFragment = new TabItemFixturesFragment();
        /**
         *Set an Apater for the View Pager
         */
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                //SnackbarUtil.showShort(tabLayout, "onPageSelected: " + position);
                pageSelected = position;
                mainActivity.setDatePicker(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

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
                    Log.d(getClass().toString(), "TabItemLiveFragment is selected");
                    return tabItemLiveFragment;
                case 1:
                    Log.d(getClass().toString(), "TabItemResultFragment is selected");
                    return tabItemResultFragment;
                case 2:
                    Log.d(getClass().toString(), "TabItemFixturesFragment is selected");
                    return tabItemFixturesFragment;
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
                    return getString(R.string.tab_child_live);
                case 1:
                    return getString(R.string.tab_child_result);
                case 2:
                    return getString(R.string.tab_child_fixtures);
            }
            return null;
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_list_refresh:
//                Intent intent = new Intent(getActivity(), RegisterMatchActivity.class);
//                startActivity(intent);
                break;
            default:
                break;
        }
    }

}
