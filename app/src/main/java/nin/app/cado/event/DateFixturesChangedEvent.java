package nin.app.cado.event;

/**
 * Created by NinHN on 9/27/16.
 */
public class DateFixturesChangedEvent {
    private String date;

    public DateFixturesChangedEvent(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
}
