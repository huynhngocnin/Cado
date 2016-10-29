package nin.app.cado.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import nin.app.cado.R;
import nin.app.cado.holder.MatchDetailAnalysisHolder;
import nin.app.cado.model.MatchDetailAnalysisModel;
import nin.app.cado.model.MatchDetailTechModel;

/**
 * Created by ninhn on 10/7/2016.
 */

public class MatchAnalysisAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<MatchDetailTechModel> matchDetailTechModels;


    public MatchAnalysisAdapter(Context context, List<MatchDetailTechModel> matchDetailTechModels) {
        this.context = context;
        this.matchDetailTechModels = matchDetailTechModels;
    }

    @Override
    public int getItemCount() {
        return this.matchDetailTechModels == null ? 0 : this.matchDetailTechModels.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.match_item_analysis, parent, false);
        return new MatchDetailAnalysisHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MatchDetailAnalysisHolder) holder).bind(this.context, this.matchDetailTechModels.get(position));
    }
}
