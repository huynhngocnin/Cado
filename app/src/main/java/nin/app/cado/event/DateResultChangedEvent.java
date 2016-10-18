package nin.app.cado.event;

/**
 * Created by NinHN on 9/27/16.
 */
public class DateResultChangedEvent {
    private String date;

    public DateResultChangedEvent(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
}
