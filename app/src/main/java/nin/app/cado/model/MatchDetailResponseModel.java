package nin.app.cado.model;


import java.util.List;

/**
 * Created by NinHuynh on 10/29/16.
 */

public class MatchDetailResponseModel {
    private String message;
    private MatchDetailModel result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MatchDetailModel getResult() {
        return result;
    }

    public void setResult(MatchDetailModel result) {
        this.result = result;
    }

}
