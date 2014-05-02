package jp.co.voyagegroup.fluct.sample;

import jp.co.voyagegroup.android.fluct.jar.FluctInterstitial;
import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FluctSample extends Activity {

    private FluctInterstitial mFluctInterstitial;
    private final FluctInterstitialCallback mInterstitialCallback = new FluctInterstitialCallback();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fluct_sample);

        ApplicationInfo appliInfo = null;
        String interstitialMediaId = "";

        try {
            appliInfo = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);

            Bundle metaDataBundle = appliInfo.metaData;
            if (metaDataBundle != null
                    && metaDataBundle.get("FLUCT_MEDIA_ID_FOR_INTERSTITIAL") != null) {
                interstitialMediaId = metaDataBundle.get("FLUCT_MEDIA_ID_FOR_INTERSTITIAL").toString();
            }
        } catch (NameNotFoundException e) {}

        mFluctInterstitial = new FluctInterstitial(getApplicationContext(), interstitialMediaId);
        mFluctInterstitial.setFluctInterstitialCallback(mInterstitialCallback);

        Button showIntersitial = (Button)findViewById(R.id.showIntersitial);
        showIntersitial.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view) {
                mFluctInterstitial.showIntersitialAd();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_fluct_sample, menu);
        return true;
    }

    private final class FluctInterstitialCallback implements jp.co.voyagegroup.android.fluct.jar.FluctInterstitial.FluctInterstitialCallback {
        public void onReceiveAdInfo(int status) {
            Log.d("interstitialCallback", "" + status);
        }
    }
}
