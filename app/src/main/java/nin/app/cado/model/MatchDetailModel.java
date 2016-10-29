package nin.app.cado.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by NinHuynh on 10/29/16.
 */

public class MatchDetailModel {
    @JsonProperty("Match")
    private MatchModel match;

    @JsonProperty("TechStatistic")
    private List<MatchDetailTechModel> techStatistic;

    @JsonProperty("DetailList")
    private List<MatchDetailAnalysisModel> detailList;

    public MatchModel getMatch() {
        return match;
    }


    public void setMatch(MatchModel match) {
        this.match = match;
    }


    public List<MatchDetailTechModel> getTechStatistic() {
        return techStatistic;
    }

    public void setTechStatistic(List<MatchDetailTechModel> techStatistic) {
        this.techStatistic = techStatistic;
    }

    public List<MatchDetailAnalysisModel> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<MatchDetailAnalysisModel> detailList) {
        this.detailList = detailList;
    }
}
