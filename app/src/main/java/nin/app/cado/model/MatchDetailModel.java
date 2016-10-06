package nin.app.cado.model;

/**
 * Created by ninhn on 10/6/2016.
 */

public class MatchDetailModel {

    private String happenTime;
    private String homePlayer;
    private String guestPlayer;
    private int kind;
    private String homeSubIn;
    private String guestSubIn;
    private int homeKind;
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
