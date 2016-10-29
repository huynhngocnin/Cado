package nin.app.cado.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import nin.app.cado.R;
import nin.app.cado.model.MatchDetailAnalysisModel;
import nin.app.cado.model.MatchDetailModel;
import nin.app.cado.model.MatchDetailTechModel;

/**
 * Created by ninhn on 9/7/2016.
 */

public class MatchDetailAnalysisHolder extends RecyclerView.ViewHolder {

    private TextView txtHomeEvent;
    private TextView txtMatchEvent;
    private TextView txtGuestEvent;


    public MatchDetailAnalysisHolder(View itemView) {
        super(itemView);
        txtHomeEvent = (TextView) itemView.findViewById(R.id.match_item_analysis_home_event_text);
        txtMatchEvent = (TextView) itemView.findViewById(R.id.match_item_analysis_event_text);
        txtGuestEvent = (TextView) itemView.findViewById(R.id.match_item_analysis_guest_event_text);
    }

    public void bind(final Context context, final MatchDetailTechModel matchDetailTechModel) {

        txtHomeEvent.setText(matchDetailTechModel.getHomeValue());

        txtMatchEvent.setText(matchDetailTechModel.getTitle());

        txtGuestEvent.setText(matchDetailTechModel.getGuestValue());
    }
}
