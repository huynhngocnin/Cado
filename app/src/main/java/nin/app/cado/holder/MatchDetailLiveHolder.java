package nin.app.cado.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import nin.app.cado.R;
import nin.app.cado.model.MatchDetailAnalysisModel;
import nin.app.cado.model.MatchDetailTechModel;

/**
 * Created by ninhn on 9/7/2016.
 */

public class MatchDetailLiveHolder extends RecyclerView.ViewHolder {

    private ImageView imgHomeEvent;
    private TextView txtHomeEvent;
    private TextView txtMatchEvent;
    private ImageView imgGuestEvent;
    private TextView txtGuestEvent;


    public MatchDetailLiveHolder(View itemView) {
        super(itemView);
        imgHomeEvent = (ImageView) itemView.findViewById(R.id.match_item_live_home_event_icon);
        txtHomeEvent = (TextView) itemView.findViewById(R.id.match_item_live_home_event_text);

        txtMatchEvent = (TextView) itemView.findViewById(R.id.match_item_live_event_text);

        imgGuestEvent = (ImageView) itemView.findViewById(R.id.match_item_live_guest_event_icon);
        txtGuestEvent = (TextView) itemView.findViewById(R.id.match_item_live_guest_event_text);
    }

    public void bind(final Context context, final MatchDetailAnalysisModel matchDetailAnalysisModel) {
//        Picasso.with(itemView.getContext())
//                .load(matchDetailAnalysisModel.getHomeKind()) //Load image theo kind
//                .error(R.mipmap.ic_ball_goal)
//                .placeholder(R.mipmap.ic_ball_goal)
//                .into(imgHomeEvent);

        txtHomeEvent.setText(matchDetailAnalysisModel.getHomePlayer());

        txtMatchEvent.setText(matchDetailAnalysisModel.getHappenTime());

//        Picasso.with(itemView.getContext())
//                .load(matchDetailAnalysisModel.getGuestKind()) //Load image theo kind
//                .error(R.mipmap.ic_ball_goal)
//                .placeholder(R.mipmap.ic_ball_goal)
//                .into(imgGuestEvent);

        txtGuestEvent.setText(matchDetailAnalysisModel.getGuestPlayer());
    }
}
