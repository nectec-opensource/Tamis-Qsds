package th.or.nectec.app.qsdscenter;

import android.app.Application;
import th.qsds.app.tamis.in.th.qsdscenter.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class LauncherApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/TF_Phethai.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
