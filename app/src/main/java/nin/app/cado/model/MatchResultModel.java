package nin.app.cado.model;

/**
 * Created by ninhn on 9/28/2016.
 */

public class MatchResultModel extends MatchModel{
    private String lId;
    private String lName;
    private String lFullName;
    private String lColor;

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
}
