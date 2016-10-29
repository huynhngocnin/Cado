package nin.app.cado.service;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import nin.app.cado.listener.TaskListener;
import nin.app.cado.model.MatchDetailResponseModel;

import static nin.app.cado.constant.ServiceConstant.SERVICE_GET_MATCH_DETAIL;

/**
 * Created by NinHN on 9/23/16.
 */
public class MatchDetailService extends AsyncTask<String, Void, MatchDetailResponseModel> {

    private List<TaskListener> myListeners = new ArrayList<>();

    public void addListener(TaskListener tl) {
        myListeners.add(tl);
    }

    @Override
    protected void onPostExecute(MatchDetailResponseModel matchDetailResponseModel) {
        super.onPostExecute(matchDetailResponseModel);
        for (TaskListener tl : myListeners) {
            tl.onResultAvailable(matchDetailResponseModel);
        }
    }

    @Override
    protected MatchDetailResponseModel doInBackground(String... params) {
        return callService(params[0], params[1]);
    }

    private MatchDetailResponseModel callService(String matchId, String date) {
        Log.d(getClass().toString(), "Service is called");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        try {
            Log.d(getClass().toString(), "Call service: " + String.format(SERVICE_GET_MATCH_DETAIL,matchId, date));
            MatchDetailResponseModel matchDetailResponseModel = restTemplate.getForObject(String.format(SERVICE_GET_MATCH_DETAIL,matchId, date) , MatchDetailResponseModel.class);
            return matchDetailResponseModel;
        } catch (Exception e) {
            Log.d(getClass().toString(), e.getMessage(), e);
        }
        return null;
    }
}
