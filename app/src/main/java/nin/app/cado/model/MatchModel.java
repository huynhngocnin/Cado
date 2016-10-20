package nin.app.cado.model;

import java.util.List;

/**
 * Created by ninhn on 9/27/2016.
 */

public class MatchModel {
    private String id;
    private String league_id;
    private String date;
    private String start;
    private String status;
    private String hName;
    private String gName;
    private String hScore;
    private String gScore;
    private String hOrder;
    private String gOrder;
    private String odds;
    private String hMoney;
    private String gMoney;
    private String hHalfScore;
    private String gHalfScore;
    private String hRed;
    private String gRed;
    private String hYellow;
    private String gYellow;
    private String lId;
    private String lName;
    private String lFullName;
    private String lColor;

    private List TechStatistic;
    private List DetailList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLeague_id() {
        return league_id;
    }

    public void setLeague_id(String league_id) {
        this.league_id = league_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String gethName() {
        return hName;
    }

    public void sethName(String hName) {
        this.hName = hName;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public String gethScore() {
        return hScore;
    }

    public void sethScore(String hScore) {
        this.hScore = hScore;
    }

    public String getgScore() {
        return gScore;
    }

    public void setgScore(String gScore) {
        this.gScore = gScore;
    }

    public String gethOrder() {
        return hOrder;
    }

    public void sethOrder(String hOrder) {
        this.hOrder = hOrder;
    }

    public String getgOrder() {
        return gOrder;
    }

    public void setgOrder(String gOrder) {
        this.gOrder = gOrder;
    }

    public String getOdds() {
        return odds;
    }

    public void setOdds(String odds) {
        this.odds = odds;
    }

    public String gethMoney() {
        return hMoney;
    }

    public void sethMoney(String hMoney) {
        this.hMoney = hMoney;
    }

    public String getgMoney() {
        return gMoney;
    }

    public void setgMoney(String gMoney) {
        this.gMoney = gMoney;
    }

    public String gethHalfScore() {
        return hHalfScore;
    }

    public void sethHalfScore(String hHalfScore) {
        this.hHalfScore = hHalfScore;
    }

    public String getgHalfScore() {
        return gHalfScore;
    }

    public void setgHalfScore(String gHalfScore) {
        this.gHalfScore = gHalfScore;
    }

    public String gethRed() {
        return hRed;
    }

    public void sethRed(String hRed) {
        this.hRed = hRed;
    }

    public String getgRed() {
        return gRed;
    }

    public void setgRed(String gRed) {
        this.gRed = gRed;
    }

    public String gethYellow() {
        return hYellow;
    }

    public void sethYellow(String hYellow) {
        this.hYellow = hYellow;
    }

    public String getgYellow() {
        return gYellow;
    }

    public void setgYellow(String gYellow) {
        this.gYellow = gYellow;
    }

    public String getlId() {
        return lId;
    }

    public void setlId(String lId) {
        this.lId = lId;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getlFullName() {
        return lFullName;
    }

    public void setlFullName(String lFullName) {
        this.lFullName = lFullName;
    }

    public String getlColor() {
        return lColor;
    }

    public void setlColor(String lColor) {
        this.lColor = lColor;
    }

    public List getTechStatistic() {
        return TechStatistic;
    }

    public void setTechStatistic(List techStatistic) {
        TechStatistic = techStatistic;
    }

    public List getDetailList() {
        return DetailList;
    }

    public void setDetailList(List detailList) {
        DetailList = detailList;
    }
}
