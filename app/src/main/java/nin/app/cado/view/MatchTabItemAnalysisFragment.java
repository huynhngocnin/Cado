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
import nin.app.cado.adapter.MatchAnalysisAdapter;
import nin.app.cado.model.MatchDetailAnalysisModel;
import nin.app.cado.model.MatchDetailTechModel;

/**
 * Created by NinHN on 9/19/2016.
 */
public class MatchTabItemAnalysisFragment extends Fragment {

    public static final String DATA_PARAM = "dataParam";

    private List<MatchDetailTechModel> matchDetailTechModels;
    private RecyclerView mRecyclerView;
    private MatchAnalysisAdapter matchAnalysisAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //here is your arguments
        Bundle bundle = getArguments();
        //here is your list array
        this.matchDetailTechModels = Parcels.unwrap(bundle.getParcelable(DATA_PARAM));
        return inflater.inflate(R.layout.match_tab_analysis, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_match_tab_analysis);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        matchAnalysisAdapter = new MatchAnalysisAdapter(getActivity(), matchDetailTechModels);
        mRecyclerView.setAdapter(matchAnalysisAdapter);
    }

}
