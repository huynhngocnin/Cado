package nin.app.cado.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import nin.app.cado.R;
import nin.app.cado.listener.OnMatchItemClickListener;
import nin.app.cado.model.MatchModel;

/**
 * Created by ninhn on 9/27/2016.
 */

public class MatchHolder extends RecyclerView.ViewHolder {

    private LinearLayout linearLayout;

    private ImageView leagueFlag;
    private TextView leagueName;
    private TextView matchTime;
    private TextView matchDate;
    private ImageView homeFlag;
    private TextView homeName;
    private TextView homeScore;
    private ImageView guestFlag;
    private TextView guestName;
    private TextView guestScore;
    private TextView betHome;
    private TextView betOdds;
    private TextView betGuest;
    private ImageView matchFollow;

    public MatchHolder(View itemView) {
        super(itemView);
        linearLayout = (LinearLayout) itemView.findViewById(R.id.match_item);

        leagueFlag = (ImageView) itemView.findViewById(R.id.lives_img_league_flag);
        leagueName = (TextView) itemView.findViewById(R.id.lives_text_league_name);
        matchTime = (TextView) itemView.findViewById(R.id.lives_text_match_time);
        matchDate = (TextView) itemView.findViewById(R.id.lives_text_match_date);

        homeFlag = (ImageView) itemView.findViewById(R.id.lives_img_match_home_flag);
        homeName = (TextView) itemView.findViewById(R.id.lives_text_match_home_name);
        homeScore = (TextView) itemView.findViewById(R.id.lives_text_match_home_score);

        guestFlag = (ImageView) itemView.findViewById(R.id.lives_img_match_guest_flag);
        guestName = (TextView) itemView.findViewById(R.id.lives_text_match_guest_name);
        guestScore = (TextView) itemView.findViewById(R.id.lives_text_match_guest_score);

        betHome = (TextView) itemView.findViewById(R.id.lives_text_bet_home);
        betOdds = (TextView) itemView.findViewById(R.id.lives_text_bet_odds);
        betGuest = (TextView) itemView.findViewById(R.id.lives_text_bet_guest);

        matchFollow = (ImageView) itemView.findViewById(R.id.lives_img_match_follow);
    }

    public void bind(final Context context, final MatchModel matchModel, final OnMatchItemClickListener listener) {
        Picasso.with(itemView.getContext())
                .load(matchModel.getlId())
                .error(R.drawable.ic_league)
                .placeholder(R.drawable.ic_league)
                .into(leagueFlag);

        leagueName.setText(matchModel.getlFullName());

        leagueName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(matchModel, leagueName);
            }
        });

        matchTime.setText(matchModel.getStart());

        matchDate.setText(matchModel.getDate());

        Picasso.with(itemView.getContext())
                .load(matchModel.gethName())
                .error(R.mipmap.ic_flag_default)
                .placeholder(R.mipmap.ic_flag_default)
                .into(homeFlag);
        homeName.setText(matchModel.gethName());
        homeScore.setText(matchModel.gethScore());

        Picasso.with(itemView.getContext())
                .load(matchModel.getgName())
                .error(R.mipmap.ic_flag_default)
                .placeholder(R.mipmap.ic_flag_default)
                .into(guestFlag);
        guestName.setText(matchModel.getgName());
        guestScore.setText(matchModel.getgScore());

        betHome.setText(matchModel.gethMoney());
        betGuest.setText(matchModel.getgMoney());
        betOdds.setText(matchModel.getOdds());

//        //Check this match is followed?
//        if(true) {
//            Picasso.with(itemView.getContext())
//                    .load(R.drawable.ic_follow)
//                    .error(R.drawable.ic_follow)
//                    .placeholder(R.drawable.ic_follow)
//                    .into(matchFollow);
//        }else{
//            Picasso.with(itemView.getContext())
//                    .load(R.drawable.ic_followed)
//                    .error(R.drawable.ic_follow)
//                    .placeholder(R.drawable.ic_follow)
//                    .into(matchFollow);
//        }

        matchFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(matchModel, matchFollow);
            }
        });

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(matchModel, linearLayout);
            }
        });
    }
}
