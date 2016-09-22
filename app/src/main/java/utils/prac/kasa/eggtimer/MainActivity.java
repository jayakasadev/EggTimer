package utils.prac.kasa.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SeekBar timerBar;
    private TextView text;
    private boolean counterIsActive;
    private MediaPlayer mediaPlayer;
    private Button button;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerBar = (SeekBar)findViewById(R.id.timerSeekBar);
        text = (TextView)findViewById(R.id.textView);
        button = (Button)findViewById(R.id.controllerButton);

        timerBar.setMax(600);
        timerBar.setProgress(30);

        timerBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void startTimer(View view){

        if(!counterIsActive){
            counterIsActive = true;
            timerBar.setEnabled(false);
            button.setText("Stop");

            //adding the 100 to cut down on a delay in the timing
            new CountDownTimer(timerBar.getProgress() * 1000+100, 1000){

                @Override
                public void onTick(long timeLeft) {
                    updateTimer((int)(timeLeft/1000));
                }

                @Override
                public void onFinish() {
                    text.setText("00:00");
                    //Log.i("Finished", "Time is up");

                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.alarmsound);
                    mediaPlayer.start();
                }
            }.start();
        }
        else{
            counterIsActive = false;

            //text
        }
    }

    private void updateTimer(int progress){
        int minutes  = progress / 60;
        int seconds = progress - minutes * 60;

        text.setText(String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
    }
}
