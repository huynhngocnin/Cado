package nin.app.cado.service;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import nin.app.cado.listener.TaskListener;
import nin.app.cado.model.MatchResponseModel;

import static nin.app.cado.constant.ServiceConstant.SERVICE_GET_MATCH_FIXTURES;

/**
 * Created by NinHN on 9/23/16.
 */
public class MatchFixturesService extends AsyncTask<String, Void, MatchResponseModel> {

    private List<TaskListener> myListeners = new ArrayList<>();

    public void addListener(TaskListener tl) {
        myListeners.add(tl);
    }

    private int type;

    public MatchFixturesService(int type) {
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
    protected MatchResponseModel doInBackground(String... params) {
        return callService(params[0]);
    }

    private MatchResponseModel callService(String date) {
        Log.d(getClass().toString(), "Service is called");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        try {
            MatchResponseModel matchResponseModel = restTemplate.getForObject(SERVICE_GET_MATCH_FIXTURES +
                    date, MatchResponseModel.class);
            return matchResponseModel;
        } catch (Exception e) {
            Log.d(getClass().toString(), e.getMessage(), e);
        }
        return null;
    }
}
