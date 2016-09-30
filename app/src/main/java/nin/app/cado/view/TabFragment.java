package nin.app.cado.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

import nin.app.cado.R;

/**
 * Created by NinHN on 8/27/2016.
 */
public class TabFragment extends Fragment implements View.OnClickListener {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static FloatingActionButton btnAdd;
    public static int int_items = 3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
        View x = inflater.inflate(R.layout.tab_layout, null);
        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);
        btnAdd = (FloatingActionButton) x.findViewById(R.id.btn_global_add);
        btnAdd.setOnClickListener(this);

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
                    return new TabItemLiveFragment();
                case 1:
                    return new TabItemResultFragment();
                case 2:
                    return new TabItemFixturesFragment();
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
            case R.id.btn_global_add:
//                Intent intent = new Intent(getActivity(), RegisterMatchActivity.class);
//                startActivity(intent);
                break;
            default:
                break;
        }
    }

    public void showFloatButton() {
        btnAdd.animate().translationX(0).setInterpolator(new DecelerateInterpolator(2)).start();
    }

    public void hideFloatButton() {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) btnAdd.getLayoutParams();
        int fabBottomMargin = lp.bottomMargin;
        btnAdd.animate().translationX(btnAdd.getWidth() + fabBottomMargin).setInterpolator(new AccelerateInterpolator(2)).start();
    }
}
