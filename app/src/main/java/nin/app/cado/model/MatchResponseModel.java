package nin.app.cado.model;

/**
 * Created by ninhn on 9/28/2016.
 */

public class MatchResponseModel {
    private String message;
    private MatchResultModel result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MatchResultModel getResult() {
        return result;
    }

    public void setResult(MatchResultModel result) {
        this.result = result;
    }
}
