package nin.app.cado.listener;

import android.view.View;

import nin.app.cado.model.MatchModel;

/**
 * Created by NinHN on 4/8/16.
 */
public interface OnMatchItemClickListener {
    void onItemClick(MatchModel matchModel, View type);
}
