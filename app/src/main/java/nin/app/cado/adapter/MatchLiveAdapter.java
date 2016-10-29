package nin.app.cado.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import nin.app.cado.R;
import nin.app.cado.holder.MatchDetailLiveHolder;
import nin.app.cado.model.MatchDetailAnalysisModel;
import nin.app.cado.model.MatchDetailTechModel;

/**
 * Created by ninhn on 10/7/2016.
 */

public class MatchLiveAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<MatchDetailAnalysisModel> matchDetailAnalysisModels;


    public MatchLiveAdapter(Context context, List<MatchDetailAnalysisModel> matchDetailAnalysisModels) {
        this.context = context;
        this.matchDetailAnalysisModels = matchDetailAnalysisModels;
    }


    @Override
    public int getItemCount() {
        return this.matchDetailAnalysisModels == null ? 0 : this.matchDetailAnalysisModels.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.match_item_live, parent, false);
        return new MatchDetailLiveHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MatchDetailLiveHolder) holder).bind(this.context, this.matchDetailAnalysisModels.get(position));
    }
}
