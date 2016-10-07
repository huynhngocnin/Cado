package nin.app.cado.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import nin.app.cado.R;
import nin.app.cado.model.MatchTechModel;

/**
 * Created by ninhn on 9/7/2016.
 */

public class MatchAnalysisHolder extends RecyclerView.ViewHolder {

    private TextView txtHomeEvent;
    private TextView txtMatchEvent;
    private TextView txtGuestEvent;


    public MatchAnalysisHolder(View itemView) {
        super(itemView);
        txtHomeEvent = (TextView) itemView.findViewById(R.id.match_item_analysis_home_event_text);
        txtMatchEvent = (TextView) itemView.findViewById(R.id.match_item_analysis_event_text);
        txtGuestEvent = (TextView) itemView.findViewById(R.id.match_item_analysis_guest_event_text);
    }

    public void bind(final Context context, final MatchTechModel matchTechModel) {

        txtHomeEvent.setText(matchTechModel.getHomeValue());

        txtMatchEvent.setText(matchTechModel.getTitle());

        txtGuestEvent.setText(matchTechModel.getGuestValue());
    }
}
