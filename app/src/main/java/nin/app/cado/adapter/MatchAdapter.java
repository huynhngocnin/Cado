package nin.app.cado.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import nin.app.cado.R;
import nin.app.cado.holder.AdmobViewHolder;
import nin.app.cado.holder.LoadingViewHolder;
import nin.app.cado.holder.MatchHolder;
import nin.app.cado.listener.OnLoadMoreListener;
import nin.app.cado.listener.OnMatchItemClickListener;
import nin.app.cado.model.MatchResultModel;

/**
 * Created by ninhn on 4/9/2016.
 */
public class MatchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private final int VIEW_TYPE_ADMOB = 2;

    private Context context;
    private List<MatchResultModel> matchResultModels;

    private OnLoadMoreListener mOnLoadMoreListener;
    private OnMatchItemClickListener listener;

    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    public MatchAdapter(Context context, RecyclerView recyclerView, List<MatchResultModel> matchResultModels, OnMatchItemClickListener listener) {
        this.context = context;
        this.matchResultModels = matchResultModels;
        this.listener = listener;
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (mOnLoadMoreListener != null) {
                        mOnLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    public void setLoaded() {
        isLoading = false;
    }

    @Override
    public int getItemCount() {
        return matchResultModels == null ? 0 : matchResultModels.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (matchResultModels.get(position) != null) {
            if (!context.getString(R.string.banner_ad_unit_id).equals(matchResultModels.get(position).getId())) {
                return VIEW_TYPE_ITEM;
            } else {
                return VIEW_TYPE_ADMOB;
            }
        } else {
            return VIEW_TYPE_LOADING;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_ITEM) {
            view = LayoutInflater.from(this.context).inflate(R.layout.item_livescore, parent, false);
            return new MatchHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            view = LayoutInflater.from(this.context).inflate(R.layout.loading_item, parent, false);
            return new LoadingViewHolder(view);
        } else if (viewType == VIEW_TYPE_ADMOB) {
            view = LayoutInflater.from(this.context).inflate(R.layout.admob_item, parent, false);
            return new AdmobViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MatchHolder) {
            ((MatchHolder) holder).bind(this.context, matchResultModels.get(position), this.listener);
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        } else if (holder instanceof AdmobViewHolder) {
            //Dont handle
        }
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }
}
