package android.siwoo.com.androidlabtemp.layout;

import android.siwoo.com.androidlabtemp.R;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class LabSeven extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_seven);
        TabHost tabHost = findViewById(R.id.host);
        tabHost.setup();
        setupTab(tabHost, "tab1", R.drawable.tab_icon1, R.id.tab1);
        setupTab(tabHost, "tab2", R.drawable.tab_icon2, R.id.tab2);
        setupTab(tabHost, "tab3", R.drawable.tab_icon3, R.id.tab3);
    }

    public void setupTab(TabHost tabHost, String tabName, int tabImageId, int tabContentId) {
        TabHost.TabSpec tabSpec = tabHost.newTabSpec(tabName);
        tabSpec.setIndicator(null, ResourcesCompat.getDrawable(getResources(), tabImageId, null));
        tabSpec.setContent(tabContentId);
        tabHost.addTab(tabSpec);
    }
}
