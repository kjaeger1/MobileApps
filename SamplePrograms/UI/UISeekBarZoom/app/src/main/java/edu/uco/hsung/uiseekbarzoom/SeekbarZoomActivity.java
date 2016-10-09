package edu.uco.hsung.uiseekbarzoom;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ZoomControls;

public class SeekbarZoomActivity extends Activity {

    private SeekBar volumeControl;
    private ZoomControls zoomControls;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar_zoom);

        volumeControl = (SeekBar) findViewById(R.id.volume_bar);
        volumeControl.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int progressChanged = 0;
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        Toast.makeText(getApplicationContext(),
                                "Volume:"+progressChanged,
                                Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress,
                                                  boolean fromUser) {
                        progressChanged = progress;
                    }
                });

        imageView = (ImageView) findViewById(R.id.image_view);
        zoomControls = (ZoomControls) findViewById(R.id.zoom_control);
        zoomControls.setOnZoomInClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setScaleX(imageView.getScaleX() + 1);
                imageView.setScaleY(imageView.getScaleY() + 1);
            }
        });

        zoomControls.setOnZoomOutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setScaleX(imageView.getScaleX() - 1);
                imageView.setScaleY(imageView.getScaleY() - 1);
            }
        });
    }
}
