package nin.app.cado.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nin.app.cado.R;

/**
 * Created by NinHN on 9/19/2016.
 */
public class MatchTabItemGeneralFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //here is your arguments
        Bundle bundle = getArguments();
        //here is your list array

        return inflater.inflate(R.layout.match_tab_general, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }


}
