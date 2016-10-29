package nin.app.cado.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.parceler.Parcel;

/**
 * Created by ninhn on 10/6/2016.
 */

@Parcel(Parcel.Serialization.BEAN)
public class MatchDetailAnalysisModel{

    @JsonProperty("HappenTime")
    private String happenTime;

    @JsonProperty("HomePlayer")
    private String homePlayer;

    @JsonProperty("GuestPlayer")
    private String guestPlayer;

    @JsonProperty("Kind")
    private int kind;

    @JsonProperty("HomeSubIn")
    private String homeSubIn;

    @JsonProperty("GuestSubIn")
    private String guestSubIn;

    @JsonProperty("HomeKind")
    private int homeKind;

    @JsonProperty("GuestKind")
    private int guestKind;

    public String getHappenTime() {
        return happenTime;
    }

    public void setHappenTime(String happenTime) {
        this.happenTime = happenTime;
    }

    public String getHomePlayer() {
        return homePlayer;
    }

    public void setHomePlayer(String homePlayer) {
        this.homePlayer = homePlayer;
    }

    public String getGuestPlayer() {
        return guestPlayer;
    }

    public void setGuestPlayer(String guestPlayer) {
        this.guestPlayer = guestPlayer;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public String getHomeSubIn() {
        return homeSubIn;
    }

    public void setHomeSubIn(String homeSubIn) {
        this.homeSubIn = homeSubIn;
    }

    public String getGuestSubIn() {
        return guestSubIn;
    }

    public void setGuestSubIn(String guestSubIn) {
        this.guestSubIn = guestSubIn;
    }

    public int getHomeKind() {
        return homeKind;
    }

    public void setHomeKind(int homeKind) {
        this.homeKind = homeKind;
    }

    public int getGuestKind() {
        return guestKind;
    }

    public void setGuestKind(int guestKind) {
        this.guestKind = guestKind;
    }

}
