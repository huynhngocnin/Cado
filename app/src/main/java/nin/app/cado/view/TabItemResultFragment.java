package nin.app.cado.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baoyz.widget.PullRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import nin.app.cado.R;
import nin.app.cado.Util.ConnectionUntil;
import nin.app.cado.Util.ToastUntil;
import nin.app.cado.adapter.MatchAdapter;
import nin.app.cado.listener.OnLoadMoreListener;
import nin.app.cado.listener.OnMatchItemClickListener;
import nin.app.cado.listener.TaskListener;
import nin.app.cado.model.MatchModel;
import nin.app.cado.model.MatchResponseModel;
import nin.app.cado.model.MatchResultModel;
import nin.app.cado.service.MatchResultService;

import static nin.app.cado.constant.CommonConstant.ADMOB_CYCLE_SHOW;
import static nin.app.cado.constant.CommonConstant.ADMOB_INIT_POSITION;
import static nin.app.cado.constant.ServiceConstant.FLAG_LOAD_MATCH_NEW;
import static nin.app.cado.constant.ServiceConstant.FLAG_LOAD_MATCH_SCROLL;

/**
 * Created by NinHN on 9/19/2016.
 */
public class TabItemResultFragment extends Fragment implements TaskListener, OnMatchItemClickListener {

    private List<MatchResultModel> matchResultModels;
    private RecyclerView mRecyclerView;
    private MatchAdapter matchAdapter;
    private PullRefreshLayout pullRefreshLayout;
    private MatchResultModel admobModel;
    private int admobCount = ADMOB_INIT_POSITION;

    //private List<MatchModel> matchModelListTemp;
    private MatchResponseModel matchResponseModel;

    private int page = 1;
    private String date;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_item_livescore, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initAdmobModel();
        initRecyclerView();
        getMatchPage();
        initPullRefresh();
    }

    private void initAdmobModel() {
        admobModel = new MatchResultModel();
        admobModel.setId(getString(R.string.banner_ad_unit_id));
    }

    private void addAdmobToList(List<MatchResultModel> matchResultModels) {
        if (matchResultModels.size() >= 100) {
            addAdmobItem(10);
        } else if (matchResultModels.size() >= 90) {
            addAdmobItem(9);
        } else if (matchResultModels.size() >= 80) {
            addAdmobItem(8);
        } else if (matchResultModels.size() >= 70) {
            addAdmobItem(7);
        } else if (matchResultModels.size() >= 60) {
            addAdmobItem(6);
        } else if (matchResultModels.size() >= 50) {
            addAdmobItem(5);
        } else if (matchResultModels.size() >= 40) {
            addAdmobItem(4);
        } else if (matchResultModels.size() >= 30) {
            addAdmobItem(3);
        } else if (matchResultModels.size() >= 20) {
            addAdmobItem(2);
        } else if (matchResultModels.size() >= 10) {
            addAdmobItem(1);
        }
    }

    private void addAdmobItem(int number) {
        for (int i = 0; i < number; i++) {
            admobCount += ADMOB_CYCLE_SHOW;
            matchResultModels.add(admobCount, admobModel);
        }
    }

    public void handleUploadSuccess() {
        getMatchRefresh();
    }

    private void initRecyclerView() {
        matchResultModels = new ArrayList<>();
        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.recycle_live);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        matchAdapter = new MatchAdapter(getActivity(), mRecyclerView, matchResultModels, this);
        mRecyclerView.setAdapter(matchAdapter);

        matchAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
//                matchModelList.add(null);
//                matchAdapter.notifyItemInserted(matchModelList.size() - 1);
//                //Get more photo when scroll down to bottom
//                getMatchMore();
            }
        });

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

//    private void getMatchMore() {
//        MatchResultService matchResultService = new MatchResultService(FLAG_LOAD_MATCH_SCROLL);
//        matchResultService.addListener(this);
//        matchResultService.execute(date);
//    }

    private void getMatchPage() {
        MatchResultService matchResultService = new MatchResultService(FLAG_LOAD_MATCH_SCROLL);
        matchResultService.addListener(this);
        matchResultService.execute(date);
    }

    private void getMatchRefresh() {
        MatchResultService matchResultService = new MatchResultService(FLAG_LOAD_MATCH_SCROLL);
        matchResultService.addListener(this);
        matchResultService.execute(date);
    }

    private void addPhotoToList(boolean isClearList) {
        if (isClearList) {
            //Clear current photo list
            matchResultModels.clear();
            //Clear admob are added before
            admobCount = ADMOB_INIT_POSITION;
        }
        //Add new photo list just loaded
        matchResultModels.addAll(matchResponseModel.getResult());
        //Add admob to show list
        addAdmobToList(matchResponseModel.getResult());
    }


    @Override
    public void onResultAvailable(Object... objects) {
        matchResponseModel = (MatchResponseModel) objects[1];
//        if (FLAG_LOAD_MATCH_SCROLL == (int) objects[0]) {
//            if (matchResultModels.size() > 0) {
//                //Remove loading item
//                matchResultModels.remove(matchResultModels.size() - 1);
//                matchAdapter.notifyItemRemoved(matchResultModels.size());
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
            addPhotoToList(false);
        } else {
            //Add list photo had just loaded and admob to list
            addPhotoToList(true);
            //Disable refresh control
            pullRefreshLayout.setRefreshing(false);
            //Reset page to start
            page = 1;
        }

        //Update list after change
        matchAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(MatchModel matchModel, View type) {
        switch (type.getId()) {
            case 1:
                break;
            default:
                break;

        }
    }
}