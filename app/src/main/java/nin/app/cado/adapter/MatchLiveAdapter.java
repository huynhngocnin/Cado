package nin.app.cado.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import nin.app.cado.R;
import nin.app.cado.holder.MatchLiveHolder;
import nin.app.cado.model.MatchDetailModel;

/**
 * Created by ninhn on 10/7/2016.
 */

public class MatchLiveAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<MatchDetailModel> matchDetailModels;


    public MatchLiveAdapter(Context context, List<MatchDetailModel> matchDetailModels) {
        this.context = context;
        this.matchDetailModels = matchDetailModels;
    }


    @Override
    public int getItemCount() {
        return this.matchDetailModels == null ? 0 : this.matchDetailModels.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.match_item_live, parent, false);
        return new MatchLiveHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MatchLiveHolder) holder).bind(this.context, this.matchDetailModels.get(position));
    }
}
