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

public class LabOne extends AppCompatActivity implements View.OnClickListener {
    Button vibratorBtn;
    Button beepBtn;
    Button customizedSoundBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_one2);

        vibratorBtn = findViewById(R.id.vibrator_btn);
        beepBtn = findViewById(R.id.beep_btn);
        customizedSoundBtn = findViewById(R.id.customSound_btn);

        vibratorBtn.setOnClickListener(this);
        beepBtn.setOnClickListener(this);
        customizedSoundBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == vibratorBtn) {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(1000);
        } else if(view == beepBtn) {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), notification);
            ringtone.play();
        } else if(view == customizedSoundBtn) {
            MediaPlayer player = MediaPlayer.create(this, R.raw.fallbackring);
            player.start();
        }
    }
}
