package nin.app.cado.service;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import nin.app.cado.listener.TaskListener;
import nin.app.cado.model.MatchResponseModel;

import static nin.app.cado.constant.ServiceConstant.SERVICE_GET_MATCH_LIVE;

/**
 * Created by NinHN on 9/23/16.
 */
public class MatchLiveService extends AsyncTask<Integer, Void, MatchResponseModel> {

    private List<TaskListener> myListeners = new ArrayList<>();

    public void addListener(TaskListener tl) {
        myListeners.add(tl);
    }

    private int type;

    public MatchLiveService(int type) {
        this.type = type;
    }

    @Override
    protected void onPostExecute(MatchResponseModel matchResponseModel) {
        super.onPostExecute(matchResponseModel);
        for (TaskListener tl : myListeners) {
            tl.onResultAvailable(this.type, matchResponseModel);
        }
    }

    @Override
    protected MatchResponseModel doInBackground(Integer... params) {
        return callService();
    }

    private MatchResponseModel callService() {
        Log.d(getClass().toString(), "Service is called");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        try {
            MatchResponseModel matchResponseModel = restTemplate.getForObject(SERVICE_GET_MATCH_LIVE
                    //+ CONDITION_START + CONDITION_PAGE + page
                    , MatchResponseModel.class);
            return matchResponseModel;
        } catch (Exception e) {
            Log.d(getClass().toString(), e.getMessage(), e);
        }
        return null;
    }
}
