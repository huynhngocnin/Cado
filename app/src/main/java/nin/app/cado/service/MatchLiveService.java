package nin.app.cado.service;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nin.app.cado.constant.CommonConstant;
import nin.app.cado.listener.TaskListener;
import nin.app.cado.model.MatchModel;

import static nin.app.cado.constant.ServiceConstant.CONDITION_START;
import static nin.app.cado.constant.ServiceConstant.CONDITION_PAGE;
import static nin.app.cado.constant.ServiceConstant.SERVICE_GET_MATCH_LIVE;

/**
 * Created by NinHN on 9/23/16.
 */
public class MatchLiveService extends AsyncTask<Integer, Void, List<MatchModel>> {

    private List<TaskListener> myListeners = new ArrayList<TaskListener>();

    public void addListener(TaskListener tl) {
        myListeners.add(tl);
    }

    private int type;

    public MatchLiveService(int type) {
        this.type = type;
    }

    @Override
    protected void onPostExecute(List<MatchModel> photoModels) {
        super.onPostExecute(photoModels);
        for (TaskListener tl : myListeners) {
            tl.onResultAvailable(type, this.type, photoModels);
        }
    }

    @Override
    protected List<MatchModel> doInBackground(Integer... params) {
        return callService(params[0]);
    }

    private List<MatchModel> callService(int page) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        try {
            ResponseEntity<MatchModel[]> responseEntity = restTemplate.getForEntity(SERVICE_GET_MATCH_LIVE + CONDITION_START +
                    CONDITION_PAGE + page, MatchModel[].class);
            MatchModel[] matchModels = responseEntity.getBody();
            List<MatchModel> matchModelList = Arrays.asList(matchModels);
            return matchModelList;
        } catch (Exception e) {
            Log.d(getClass().toString(), e.getMessage(), e);
        }
        return CommonConstant.ARRAY_EMPTY;
    }
}
