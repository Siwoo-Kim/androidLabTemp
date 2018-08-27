package android.siwoo.com.event;

import android.siwoo.com.androidlabtemp.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

public class LabTwo extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    List<View> views = new ArrayList<>();
    private TextView bellTextView;
    private TextView labelTextView;
    private CheckBox repeatCheckView;
    private CheckBox vibrateCheckView;
    private Switch switchView;
    private float initX;
    private long initTime;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            initX = event.getRawX();
        }else if(event.getAction() == MotionEvent.ACTION_UP) {
            float diff = initX - event.getRawX();
            if(diff<-30) {
                toasting("Sliding to right");
            }else if(diff>30) {
                toasting("Sliding to left");
            }
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            if(System.currentTimeMillis() - initTime > 3000) {
                toasting("종료하려면 한번 더 누르세요.");
                initTime = System.currentTimeMillis();
            }else{
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_two3);

        bellTextView = findViewById(R.id.bell_name);
        views.add(bellTextView);
        labelTextView = findViewById(R.id.label);
        views.add(labelTextView);
        repeatCheckView = findViewById(R.id.repeatCheck);
        views.add(repeatCheckView);
        vibrateCheckView = findViewById(R.id.vibrate);
        views.add(vibrateCheckView);
        switchView = findViewById(R.id.onOff);
        views.add(switchView);

        for(View view: views) {
            if(view instanceof TextView) {
                view.setOnClickListener(this);
            }
            if(view instanceof CompoundButton) {
                ((CompoundButton) view).setOnCheckedChangeListener(this);
            }
        }
    }

    @Override
    public void onClick(View view) {
        if(view == bellTextView) {
            toasting("Bell Text Clicked");
        }else if(view == labelTextView) {
            toasting("Label Text Clicked");
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton button, boolean isChecked) {
        if(button == repeatCheckView) {
            toasting("repeat checkbox is " + isChecked);
        }else if(button == vibrateCheckView) {
            toasting("vibrate checkbox is " + isChecked);
        }else if(button == switchView) {
            toasting("switch is " + isChecked);
        }
    }

    private void toasting(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
