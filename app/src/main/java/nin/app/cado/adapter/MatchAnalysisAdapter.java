package nin.app.cado.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import nin.app.cado.R;
import nin.app.cado.holder.MatchAnalysisHolder;
import nin.app.cado.model.MatchTechModel;

/**
 * Created by ninhn on 10/7/2016.
 */

public class MatchAnalysisAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<MatchTechModel> matchTechModels;


    public MatchAnalysisAdapter(Context context, List<MatchTechModel> matchTechModels) {
        this.context = context;
        this.matchTechModels = matchTechModels;
    }

    @Override
    public int getItemCount() {
        return this.matchTechModels == null ? 0 : this.matchTechModels.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.match_item_analysis, parent, false);
        return new MatchAnalysisHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MatchAnalysisHolder) holder).bind(this.context, this.matchTechModels.get(position));
    }
}
