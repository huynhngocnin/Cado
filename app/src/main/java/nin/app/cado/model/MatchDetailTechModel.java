package nin.app.cado.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.parceler.Parcel;

/**
 * Created by ninhn on 10/3/2016.
 */

@Parcel(Parcel.Serialization.BEAN)
public class MatchDetailTechModel{

    @JsonProperty("Kind")
    private int kind;

    @JsonProperty("Title")
    private String title;

    @JsonProperty("HomeValue")
    private String homeValue;

    @JsonProperty("GuestValue")
    private String guestValue;

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHomeValue() {
        return homeValue;
    }

    public void setHomeValue(String homeValue) {
        this.homeValue = homeValue;
    }

    public String getGuestValue() {
        return guestValue;
    }

    public void setGuestValue(String guestValue) {
        this.guestValue = guestValue;
    }

}
