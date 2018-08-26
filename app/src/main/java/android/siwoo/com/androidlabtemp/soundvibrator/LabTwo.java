package android.siwoo.com.androidlabtemp.soundvibrator;

import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.siwoo.com.androidlabtemp.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LabTwo extends AppCompatActivity implements View.OnClickListener {

    Map<Integer, Button> buttons = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_two2);

        buttons.put(R.id.vibrator_btn, (Button) findViewById(R.id.vibrator));
        buttons.put(R.id.beep_btn, (Button) findViewById(R.id.beeper));
        buttons.put(R.id.customizedSound, (Button) findViewById(R.id.customizedSound));

        for(Button button: buttons.values()) {
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        for(Integer integer: buttons.keySet()) {
            switch (integer) {
                case R.id.vibrator_btn:
                    Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                    vibrator.vibrate(1000);
                    return;
                case R.id.beep_btn:
                    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), notification);
                    ringtone.play();
                    return;
                case R.id.customizedSound:
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.fallbackring);
                    mediaPlayer.start();
                    return;
                    default: return;
            }
        }
    }

}
