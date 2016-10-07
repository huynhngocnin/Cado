package nin.app.cado.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import nin.app.cado.R;
import nin.app.cado.model.MatchDetailModel;

/**
 * Created by ninhn on 9/7/2016.
 */

public class MatchLiveHolder extends RecyclerView.ViewHolder {

    private ImageView imgHomeEvent;
    private TextView txtHomeEvent;
    private TextView txtMatchEvent;
    private ImageView imgGuestEvent;
    private TextView txtGuestEvent;


    public MatchLiveHolder(View itemView) {
        super(itemView);
        imgHomeEvent = (ImageView) itemView.findViewById(R.id.match_item_live_home_event_icon);
        txtHomeEvent = (TextView) itemView.findViewById(R.id.match_item_live_home_event_text);

        txtMatchEvent = (TextView) itemView.findViewById(R.id.match_item_live_event_text);

        imgGuestEvent = (ImageView) itemView.findViewById(R.id.match_item_live_guest_event_icon);
        txtGuestEvent = (TextView) itemView.findViewById(R.id.match_item_live_guest_event_text);
    }

    public void bind(final Context context, final MatchDetailModel matchDetailModel) {
        Picasso.with(itemView.getContext())
                .load(matchDetailModel.getHomeKind()) //Load image theo kind
                .error(R.mipmap.ic_ball_goal)
                .placeholder(R.mipmap.ic_ball_goal)
                .into(imgHomeEvent);

        txtHomeEvent.setText(matchDetailModel.getHomePlayer());

        txtMatchEvent.setText(matchDetailModel.getHappenTime());

        Picasso.with(itemView.getContext())
                .load(matchDetailModel.getGuestKind()) //Load image theo kind
                .error(R.mipmap.ic_ball_goal)
                .placeholder(R.mipmap.ic_ball_goal)
                .into(imgGuestEvent);

        txtGuestEvent.setText(matchDetailModel.getGuestPlayer());
    }
}
