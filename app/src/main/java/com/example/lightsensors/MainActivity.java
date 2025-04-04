package com.example.lightsensors;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    SensorManager sensorManager;
    Sensor lightsensor;
    SensorEventListener sensorEventListener;
    ConstraintLayout background;
    RadioGroup buttonGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lightsensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        background = findViewById(R.id.main);
        buttonGroup = findViewById(R.id.buttonGroup);

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.values[0] > 20000){

                    if(buttonGroup.getCheckedRadioButtonId() == R.id.blueButton){
                        background.setBackgroundColor(Color.BLUE);
                    } else if (buttonGroup.getCheckedRadioButtonId() == R.id.magentaButton) {
                        background.setBackgroundColor(Color.MAGENTA);
                    }

                }else{
                    background.setBackgroundColor(Color.WHITE);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };


    }
    @Override
    protected void onResume(){
        super.onResume();
        sensorManager.registerListener(sensorEventListener,lightsensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
    }
}