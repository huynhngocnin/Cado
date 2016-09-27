package nin.app.cado.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import nin.app.cado.R;

/**
 * Created by NinHN on 9/29/16.
 */
public class AdmobViewHolder extends RecyclerView.ViewHolder {
    public AdView mAdView;

    public AdmobViewHolder(View view) {
        super(view);
        mAdView = (AdView) view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
    }
}
