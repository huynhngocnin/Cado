package nin.app.cado.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.baoyz.widget.PullRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import nin.app.cado.R;
import nin.app.cado.Util.ConnectionUntil;
import nin.app.cado.Util.SnackbarUtil;
import nin.app.cado.Util.ToastUntil;
import nin.app.cado.adapter.MatchAdapter;
import nin.app.cado.listener.OnLoadMoreListener;
import nin.app.cado.listener.OnMatchItemClickListener;
import nin.app.cado.listener.TaskListener;
import nin.app.cado.model.MatchModel;
import nin.app.cado.model.MatchResponseModel;
import nin.app.cado.model.MatchResultModel;
import nin.app.cado.service.MatchLiveService;

import static nin.app.cado.constant.ServiceConstant.FLAG_LOAD_MATCH_NEW;
import static nin.app.cado.constant.ServiceConstant.FLAG_LOAD_MATCH_REFRESH;

/**
 * Created by NinHN on 9/19/2016.
 */
public class TabItemLiveFragment extends Fragment implements TaskListener, OnMatchItemClickListener {

    private List<MatchResultModel> matchResultModels;
    private RecyclerView mRecyclerView;
    private MatchAdapter matchAdapter;
    private PullRefreshLayout pullRefreshLayout;
    private ProgressBar progressBarRefresh;
//    private MatchResultModel admobModel;
//    private int admobCount = ADMOB_INIT_POSITION;

    //private List<MatchModel> matchModelListTemp;
    private MatchResponseModel matchResponseModel;

//    private int page = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_item_livescore, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        initAdmobModel();
        initRecyclerView();
        getMatchNew();
        initPullRefresh();
    }

//    private void initAdmobModel() {
//        admobModel = new MatchResultModel();
//        admobModel.setId(getString(R.string.banner_ad_unit_id));
//    }
//
//    private void addAdmobToList(List<MatchResultModel> matchResultModels) {
//        if (matchResultModels.size() >= 100) {
//            addAdmobItem(10);
//        } else if (matchResultModels.size() >= 90) {
//            addAdmobItem(9);
//        } else if (matchResultModels.size() >= 80) {
//            addAdmobItem(8);
//        } else if (matchResultModels.size() >= 70) {
//            addAdmobItem(7);
//        } else if (matchResultModels.size() >= 60) {
//            addAdmobItem(6);
//        } else if (matchResultModels.size() >= 50) {
//            addAdmobItem(5);
//        } else if (matchResultModels.size() >= 40) {
//            addAdmobItem(4);
//        } else if (matchResultModels.size() >= 30) {
//            addAdmobItem(3);
//        } else if (matchResultModels.size() >= 20) {
//            addAdmobItem(2);
//        } else if (matchResultModels.size() >= 10) {
//            addAdmobItem(1);
//        }
//    }
//
//    private void addAdmobItem(int number) {
//        for (int i = 0; i < number; i++) {
//            admobCount += ADMOB_CYCLE_SHOW;
//            matchResultModels.add(admobCount, admobModel);
//        }
//    }

    private void initRecyclerView() {
        matchResultModels = new ArrayList<>();
        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.recycle_live);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        matchAdapter = new MatchAdapter(getActivity(), mRecyclerView, matchResultModels, this);
        mRecyclerView.setAdapter(matchAdapter);

        progressBarRefresh = (ProgressBar) getActivity().findViewById(R.id.refresh_live);

        matchAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
//                matchModelList.add(null);
//                matchAdapter.notifyItemInserted(matchModelList.size() - 1);
//                //Get more photo when scroll down to bottom
//                getMatchMore();
            }
        });

        //mRecyclerView.addOnItemTouchListener(new );

    }

    private void initPullRefresh() {
        //Map component to control
        pullRefreshLayout = (PullRefreshLayout) getActivity().findViewById(R.id.pullRefresh_live);

        // listen refresh event
        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!ConnectionUntil.isConnection(getActivity())) {
                    ToastUntil.showShort(getActivity(), R.string.network_connect_fail);
                    pullRefreshLayout.setRefreshing(false);
                } else {
                    // start refresh
                    getMatchRefresh();
                }
            }
        });
    }

    private void getMatchNew() {
        MatchLiveService matchLiveService = new MatchLiveService(FLAG_LOAD_MATCH_NEW);
        matchLiveService.addListener(this);
        matchLiveService.execute();
    }

//    private void getMatchMore() {
//        MatchLiveService matchLiveService = new MatchLiveService(FLAG_LOAD_MATCH_SCROLL);
//        matchLiveService.addListener(this);
//        matchLiveService.execute();
//    }

    private void getMatchRefresh() {
        MatchLiveService matchLiveService = new MatchLiveService(FLAG_LOAD_MATCH_REFRESH);
        matchLiveService.addListener(this);
        matchLiveService.execute();
    }

    private void addMatchToList(boolean isClearList) {
        if (isClearList) {
            //Clear current photo list
            matchResultModels.clear();
            //Clear admob are added before
//            admobCount = ADMOB_INIT_POSITION;
        }

        //Add new photo list just loaded
        matchResultModels.addAll(matchResponseModel.getResult());
        //Add admob to show list
//        addAdmobToList(matchResponseModel.getResult());
    }


    @Override
    public void onResultAvailable(Object... objects) {
        matchResponseModel = (MatchResponseModel) objects[1];
//        if (FLAG_LOAD_MATCH_SCROLL == (int) objects[0]) {
//            if (matchModelList.size() > 0) {
//                //Remove loading item
//                matchModelList.remove(matchModelList.size() - 1);
//                matchAdapter.notifyItemRemoved(matchModelList.size());
//                //Add list photo had just loaded and admob to list
//                addPhotoToList(false);
//                //count page
//                page++;
//                //Hide load more progress
//                matchAdapter.setLoaded();
//            }
//        } else
        if (FLAG_LOAD_MATCH_NEW == (int) objects[0]) {
            //Add list photo had just loaded and admob to list
            addMatchToList(false);
        } else {
            //Add list photo had just loaded and admob to list
            addMatchToList(true);
            //Disable refresh control
            pullRefreshLayout.setRefreshing(false);
            //Reset page to start
//            page = 1;
        }
        progressBarRefresh.setVisibility(View.GONE);
        //Update list after change
        matchAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(MatchModel matchModel, View type) {
        switch (type.getId()) {
            case R.id.lives_img_match_follow:
                //SnackbarUtil.showShort(progressBarRefresh, "Follow clicked: " + matchModel.getlName());
                int positionDeo = matchResultModels.indexOf(matchModel);
                //SnackbarUtil.showShort(progressBarRefresh, "Follow position: " + positionDeo);
                ToastUntil.showShort(getActivity(), "Follow position: " + positionDeo);
                break;
            default:
                SnackbarUtil.showShort(progressBarRefresh, "Item clicked: " + matchModel.getlName());
                break;

        }
    }

}
