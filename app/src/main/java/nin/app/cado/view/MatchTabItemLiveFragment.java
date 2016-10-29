package nin.app.cado.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import nin.app.cado.R;
import nin.app.cado.adapter.MatchLiveAdapter;
import nin.app.cado.model.MatchDetailAnalysisModel;

/**
 * Created by NinHN on 9/19/2016.
 */
public class MatchTabItemLiveFragment extends Fragment {

    public static final String DATA_PARAM = "dataParam";

    private List<MatchDetailAnalysisModel> matchDetailAnalysisModels;
    private RecyclerView mRecyclerView;
    private MatchLiveAdapter matchLiveAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //here is your arguments
        Bundle bundle = getArguments();
        //here is your list array
        this.matchDetailAnalysisModels = Parcels.unwrap(bundle.getParcelable(DATA_PARAM));
        return inflater.inflate(R.layout.match_tab_live, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_match_tab_live);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        matchLiveAdapter = new MatchLiveAdapter(getActivity(), matchDetailAnalysisModels);
        mRecyclerView.setAdapter(matchLiveAdapter);
    }

}
