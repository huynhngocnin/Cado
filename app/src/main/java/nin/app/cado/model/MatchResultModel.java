package nin.app.cado.model;

import java.util.List;

/**
 * Created by ninhn on 9/28/2016.
 */

public class MatchResultModel {
    private LeagueModel league;
    private List<MatchModel> matchs;

    public LeagueModel getLeague() {
        return league;
    }

    public void setLeague(LeagueModel league) {
        this.league = league;
    }

    public List<MatchModel> getMatchs() {
        return matchs;
    }

    public void setMatchs(List<MatchModel> matchs) {
        this.matchs = matchs;
    }
}
