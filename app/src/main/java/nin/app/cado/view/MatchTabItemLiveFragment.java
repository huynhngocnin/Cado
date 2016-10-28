package nin.app.cado.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import nin.app.cado.R;
import nin.app.cado.adapter.MatchLiveAdapter;
import nin.app.cado.model.MatchDetailModel;

/**
 * Created by NinHN on 9/19/2016.
 */
public class MatchTabItemLiveFragment extends Fragment {

    private List<MatchDetailModel> matchDetailModels;
    private RecyclerView mRecyclerView;
    private MatchLiveAdapter matchLiveAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //here is your arguments
        Bundle bundle = getArguments();
        //here is your list array
        //this.matchDetailModels = (List<MatchDetailModel>) bundle.getSerializable("");
        return inflater.inflate(R.layout.match_tab_live, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //initRecyclerView();
    }

    private void initRecyclerView() {
        matchDetailModels = new ArrayList<>();
        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_match_tab_live);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        matchLiveAdapter = new MatchLiveAdapter(getActivity(), matchDetailModels);
        mRecyclerView.setAdapter(matchLiveAdapter);
    }

}
