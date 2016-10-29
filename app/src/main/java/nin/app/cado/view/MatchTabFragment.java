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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import nin.app.cado.R;
import nin.app.cado.Util.DateTimeUtil;
import nin.app.cado.constant.CommonConstant;
import nin.app.cado.listener.TaskListener;
import nin.app.cado.model.MatchDetailResponseModel;
import nin.app.cado.service.MatchDetailService;

/**
 * Created by NinHN on 8/27/2016.
 */
public class MatchTabFragment extends Fragment implements View.OnClickListener, TaskListener {

    public static final String MATCH_ID = "matchId";
    public static final String MATCH_DATE = "matchDate";
    public static final String MATCH_TIME = "matchTime";
    public static final String MATCH_LEAGUE = "matchLeague";
    public static final String MATCH_HOME_LOGO = "homeLogo";
    public static final String MATCH_HOME_NAME = "homeName";
    public static final String MATCH_HOME_SCORE = "homeScore";
    public static final String MATCH_GUEST_LOGO = "guestLogo";
    public static final String MATCH_GUEST_NAME = "guestName";
    public static final String MATCH_GUEST_SCORE = "guestScore";

    private static TabLayout tabLayout;
    private static ViewPager viewPager;
    public static int int_items = 2;
    private FloatingActionButton fbtnBack;
    private FloatingActionButton fbtnRefresh;
    private ProgressBar progressBarRefresh;
    private MatchTabItemLiveFragment matchTabItemLiveFragment;
    private MatchTabItemAnalysisFragment matchTabItemAnalysisFragment;

    private TextView matchDate;
    private TextView matchTime;
    private TextView matchLeague;

    private ImageView homeLogo;
    private TextView homeName;
    private TextView homeScore;

    private ImageView guestLogo;
    private TextView guestName;
    private TextView guestScore;

    private String paramId;
    private String paramDate;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
        View x = inflater.inflate(R.layout.match_detail_fragment, null);
        initControls(x);

        getBundleAndSetValue();

        return x;
    }

    private void initControls(View x) {
        tabLayout = (TabLayout) x.findViewById(R.id.detail_tabs);
        viewPager = (ViewPager) x.findViewById(R.id.detai_viewpager);

        fbtnBack = (FloatingActionButton) x.findViewById(R.id.btn_detail_back);
        fbtnBack.setOnClickListener(this);

        fbtnRefresh = (FloatingActionButton) x.findViewById(R.id.btn_detail_refresh);
        fbtnRefresh.setOnClickListener(this);

        progressBarRefresh = (ProgressBar) x.findViewById(R.id.match_detail_refresh);

        matchDate = (TextView) x.findViewById(R.id.match_general_date);
        matchTime = (TextView) x.findViewById(R.id.match_general_time);
        matchLeague = (TextView) x.findViewById(R.id.match_general_league);

        homeLogo = (ImageView) x.findViewById(R.id.match_general_home_flag);
        homeName = (TextView) x.findViewById(R.id.match_general_home_name);
        homeScore = (TextView) x.findViewById(R.id.match_general_home_score);

        guestLogo = (ImageView) x.findViewById(R.id.match_general_guest_flag);
        guestName = (TextView) x.findViewById(R.id.match_general_guest_name);
        guestScore = (TextView) x.findViewById(R.id.match_general_guest_score);
    }

    private void getBundleAndSetValue(){
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            paramId = bundle.getString(MATCH_ID, CommonConstant.BLANK);
            paramDate = bundle.getString(MATCH_DATE, CommonConstant.BLANK);
            if (CommonConstant.BLANK.equals(paramId) || CommonConstant.BLANK.equals(paramDate)) {
                getActivity().onBackPressed();
            } else {
                getMatchDetail();
                //Set temp value
                matchDate.setText(paramDate);
                matchTime.setText(bundle.getString(MATCH_TIME, CommonConstant.BLANK));
                matchLeague.setText(bundle.getString(MATCH_LEAGUE, CommonConstant.BLANK));

                homeName.setText(bundle.getString(MATCH_HOME_NAME, CommonConstant.BLANK));
                homeScore.setText(bundle.getString(MATCH_HOME_SCORE, CommonConstant.BLANK));
                Picasso.with(getActivity())
                        .load(bundle.getString(MATCH_HOME_LOGO, CommonConstant.BLANK))
                        .error(R.mipmap.ic_flag_default)
                        .placeholder(R.mipmap.ic_flag_default)
                        .into(homeLogo);

                guestName.setText(bundle.getString(MATCH_GUEST_NAME, CommonConstant.BLANK));
                guestScore.setText(bundle.getString(MATCH_GUEST_SCORE, CommonConstant.BLANK));
                Picasso.with(getActivity())
                        .load(bundle.getString(MATCH_GUEST_LOGO, CommonConstant.BLANK))
                        .error(R.mipmap.ic_flag_default)
                        .placeholder(R.mipmap.ic_flag_default)
                        .into(guestLogo);
            }
        } else {
            getActivity().onBackPressed();
        }

    }


    private void getMatchDetail() {
        progressBarRefresh.setVisibility(View.VISIBLE);
        MatchDetailService matchDetailService = new MatchDetailService();
        matchDetailService.addListener(this);
        matchDetailService.execute(paramId, DateTimeUtil.convertDateFromReceiveToRequest(paramDate));
    }

    @Override
    public void onResultAvailable(Object... objects) {
        MatchDetailResponseModel matchDetailResponseModel = (MatchDetailResponseModel) objects[0];
        if(matchDetailResponseModel!=null & CommonConstant.BLANK.equals(matchDetailResponseModel.getMessage())){
            progressBarRefresh.setVisibility(View.GONE);

            Bundle bundleLive = new Bundle();
            bundleLive.putParcelable(MatchTabItemLiveFragment.DATA_PARAM, Parcels.wrap(matchDetailResponseModel.getResult().getDetailList()));

            matchTabItemLiveFragment = new MatchTabItemLiveFragment();
            matchTabItemLiveFragment.setArguments(bundleLive);

            Bundle bundleAnalysis = new Bundle();
            bundleAnalysis.putParcelable(MatchTabItemAnalysisFragment.DATA_PARAM, Parcels.wrap(matchDetailResponseModel.getResult().getTechStatistic()));

            matchTabItemAnalysisFragment = new MatchTabItemAnalysisFragment();
            matchTabItemAnalysisFragment.setArguments(bundleAnalysis);
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
        }else{
            getActivity().onBackPressed();
        }
        //ToastUntil.showShort(getActivity(), matchDetailResponseModel.getResult().getMatch().getDate());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_detail_back:
                getActivity().onBackPressed();
                break;
            case R.id.btn_detail_refresh:
                break;
            default:
                break;
        }
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
                    return matchTabItemLiveFragment;
                case 1:
                    return matchTabItemAnalysisFragment;
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
                    return getString(R.string.match_tab_child_live);
                case 1:
                    return getString(R.string.match_tab_child_analysis);
            }
            return null;
        }
    }

}
