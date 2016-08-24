package kiosk.qsds.app.tamis.in.th.tamislauncherforqsds;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LauncherActivity extends AppCompatActivity implements View.OnClickListener {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_launcher);
        Button qsdslink = (Button) findViewById(R.id.qsds_link);
        Button knowledge = (Button) findViewById(R.id.knowledge);
        Button buy = (Button) findViewById(R.id.buy);
        Button sell = (Button) findViewById(R.id.sell);

        qsdslink.setOnClickListener(this);
        knowledge.setOnClickListener(this);
        buy.setOnClickListener(this);
        sell.setOnClickListener(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override public void onClick(View view) {

        Intent browserIntent = new Intent(LauncherActivity.this, WebViewActivity.class);
        switch (view.getId()) {
            case R.id.sell:
                browserIntent.putExtra(WebViewActivity.URL_EXTRA,
                        "http://www.thaisilkmarket.in.th/marketplace-info/viewsale");
                startActivity(browserIntent);
                break;
            case R.id.buy:
                browserIntent.putExtra(WebViewActivity.URL_EXTRA,
                        "http://www.thaisilkmarket.in.th/marketplace-info/viewpurchase");
                startActivity(browserIntent);
                break;
            case R.id.knowledge:
                String appPackageName = "org.videolan.vlc";

                if (isPackageInstalled(appPackageName, getApplicationContext())) {
                    browserIntent = getPackageManager().getLaunchIntentForPackage(appPackageName);
                    startActivity(browserIntent);
                } else {
                    try {
                        startActivity(
                                new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                    }
                }

                break;
            case R.id.qsds_link:
                browserIntent.putExtra(WebViewActivity.URL_EXTRA, "http://qsds.go.th/newqsds/Index_web.php");
                startActivity(browserIntent);
                break;
        }
    }

    private boolean isPackageInstalled(String packageName, Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
