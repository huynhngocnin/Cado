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
import nin.app.cado.adapter.MatchAnalysisAdapter;
import nin.app.cado.model.MatchTechModel;

/**
 * Created by NinHN on 9/19/2016.
 */
public class MatchTabItemAnalysisFragment extends Fragment {

    private List<MatchTechModel> matchTechModels;
    private RecyclerView mRecyclerView;
    private MatchAnalysisAdapter matchAnalysisAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //here is your arguments
        Bundle bundle = getArguments();
        //here is your list array
        this.matchTechModels = (List<MatchTechModel>) bundle.getSerializable("");
        return inflater.inflate(R.layout.match_tab_analysis, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initRecyclerView();
    }

    private void initRecyclerView() {
        matchTechModels = new ArrayList<>();
        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_match_tab_analysis);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        matchAnalysisAdapter = new MatchAnalysisAdapter(getActivity(), matchTechModels);
        mRecyclerView.setAdapter(matchAnalysisAdapter);
    }

}
