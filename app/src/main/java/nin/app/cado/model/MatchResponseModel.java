package nin.app.cado.model;

import java.util.List;

/**
 * Created by ninhn on 9/28/2016.
 */

public class MatchResponseModel {
    private String message;
    private List<MatchResultModel> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<MatchResultModel> getResult() {
        return result;
    }

    public void setResult(List<MatchResultModel> result) {
        this.result = result;
    }
}
