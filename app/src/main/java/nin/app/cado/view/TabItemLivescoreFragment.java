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
import nin.app.cado.service.MatchLiveService;

import static android.R.attr.type;
import static nin.app.cado.constant.CommonConstant.ADMOB_CYCLE_SHOW;
import static nin.app.cado.constant.CommonConstant.ADMOB_INIT_POSITION;

/**
 * Created by NinHN on 9/19/2016.
 */
public class TabItemLivescoreFragment extends Fragment implements TaskListener, OnMatchItemClickListener {

    private List<MatchModel> matchModelList;
    private RecyclerView mRecyclerView;
    private MatchAdapter matchAdapter;
    private PullRefreshLayout pullRefreshLayout;
    private MatchModel admobModel;
    private int admobCount = ADMOB_INIT_POSITION;

    private List<MatchModel> matchModelListTemp;

    private int page = 1;

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
        admobModel = new MatchModel();
        admobModel.setId(getString(R.string.banner_ad_unit_id));
    }

    private void addAdmobToList(List<MatchModel> photoModels) {
        if (photoModels.size() == 30) {
            addAdmobItem(3);
        } else if (photoModels.size() >= 20) {
            addAdmobItem(2);
        } else if (photoModels.size() >= 10) {
            addAdmobItem(1);
        }
    }

    private void addAdmobItem(int number) {
        for (int i = 0; i < number; i++) {
            admobCount += ADMOB_CYCLE_SHOW;
            matchModelList.add(admobCount, admobModel);
        }
    }

    public void handleUploadSuccess() {
        getMatchRefresh();
    }

    private void initRecyclerView() {
        matchModelList = new ArrayList<>();
        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.recycle_live);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        matchAdapter = new MatchAdapter(getActivity(), mRecyclerView, matchModelList, this);
        mRecyclerView.setAdapter(matchAdapter);

        matchAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                matchModelList.add(null);
                matchAdapter.notifyItemInserted(matchModelList.size() - 1);
                //Get more photo when scroll down to bottom
                getMatchMore();
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

    private void getMatchMore() {
        MatchLiveService photoReviewUserService = new MatchLiveService(FLAG_PAGE_MORE);
        photoReviewUserService.addListener(this);
        photoReviewUserService.execute(page);
    }

    private void getMatchPage() {
        MatchLiveService photoReviewUserService = new MatchLiveService(FLAG_PAGE_ONE);
        photoReviewUserService.addListener(this);
        photoReviewUserService.execute(0);
    }

    private void getMatchRefresh() {
        MatchLiveService photoReviewUserService = new MatchLiveService(FLAG_REFRESH);
        photoReviewUserService.addListener(this);
        photoReviewUserService.execute(0);
    }

    private void addPhotoToList(boolean isClearList) {
        if (isClearList) {
            //Clear current photo list
            matchModelList.clear();
            //Clear admob are added before
            admobCount = ADMOB_INIT_POSITION;
        }
        //Add new photo list just loaded
        matchModelList.addAll(matchModelListTemp);
        //Add admob to show list
        addAdmobToList(matchModelListTemp);
    }


    @Override
    public void onResultAvailable(Object... objects) {
        matchModelListTemp = (List<MatchModel>) objects[2];
        if (FLAG_PAGE_MORE == (int) objects[1]) {
            if (matchModelList.size() > 0) {
                //Remove loading item
                matchModelList.remove(matchModelList.size() - 1);
                matchAdapter.notifyItemRemoved(matchModelList.size());
                //Add list photo had just loaded and admob to list
                addPhotoToList(false);
                //count page
                page++;
                //Hide load more progress
                matchAdapter.setLoaded();
            }
        } else if (FLAG_PAGE_ONE == (int) objects[1]) {
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
            default:
                break;

        }
    }
}