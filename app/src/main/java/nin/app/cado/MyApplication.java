package nin.app.cado;

import android.app.Application;

import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

/**
 * Created by NinHN on 4/7/16.
 */
public class MyApplication extends Application {
    public static final String TAG = MyApplication.class
            .getSimpleName();

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        Picasso p = new Picasso.Builder(getApplicationContext())
                .memoryCache(new LruCache(1024 * 1024 * 50))
                .build();
        Picasso.setSingletonInstance(p);

        System.setProperty("http.keepAlive", "false");
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }


}
