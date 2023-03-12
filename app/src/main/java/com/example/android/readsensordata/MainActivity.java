package com.example.android.readsensordata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensorLight;
    private Sensor mSensorProximity;
    private Sensor mSensorAmbientTemperature;
    private Sensor mSensorPressure;
    private Sensor mSensorRelativeHumidity;

    private TextView mTextSensorLight;
    private TextView mTextSensorProximity;
    private TextView mTextSensorAmbientTemperature;
    private TextView mTextSensorPressure;
    private TextView mTextSensorRelativeHumidity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        List<Sensor> sensorList = mSensorManager.getSensorList (Sensor.TYPE_ALL) ;
//        StringBuilder sensorText = new StringBuilder ();
//        for (Sensor currentSensor : sensorList) {
//            sensorText.append(currentSensor.getName())
//                .append(System.getProperty ("line.separator"));
//        }
//
//        TextView sensorTextView = findViewById(R.id.sensor_list) ;
//        sensorTextView.setText(sensorText);

        mTextSensorLight = findViewById(R.id.label_light);
        mTextSensorProximity = findViewById(R.id.label_proximity);
        mTextSensorAmbientTemperature = findViewById(R.id.label_ambient_temperature);
        mTextSensorPressure = findViewById(R.id.label_pressure);
        mTextSensorRelativeHumidity = findViewById(R.id.label_relative_humidity);

        mSensorLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mSensorProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mSensorAmbientTemperature = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        mSensorPressure = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        mSensorRelativeHumidity = mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);

        String sensor_error = "No sensor";

        if (mSensorLight == null) {
            mTextSensorLight.setText(sensor_error);
        }
        if (mSensorProximity == null) {
            mTextSensorProximity.setText(sensor_error);
        }
        if (mSensorAmbientTemperature == null) {
            mTextSensorAmbientTemperature.setText(sensor_error);
        }
        if (mSensorPressure == null) {
            mTextSensorPressure.setText(sensor_error);
        }
        if (mSensorRelativeHumidity == null) {
            mTextSensorRelativeHumidity.setText(sensor_error);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mSensorProximity != null) {
            mSensorManager.registerListener(this, mSensorProximity,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorLight != null) {
            mSensorManager.registerListener(this, mSensorLight,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorAmbientTemperature != null) {
            mSensorManager.registerListener(this, mSensorAmbientTemperature,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorPressure != null) {
            mSensorManager.registerListener(this, mSensorPressure,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorRelativeHumidity != null) {
            mSensorManager.registerListener(this, mSensorRelativeHumidity,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int sensorType = sensorEvent.sensor.getType();
        float currentValue = sensorEvent.values[0];

        switch (sensorType) {
            case Sensor.TYPE_LIGHT:
                mTextSensorLight.setText(String.format("Light sensor : %1$.2f", currentValue));
                changeBackgroundColor(currentValue);
                break;
            case Sensor.TYPE_PROXIMITY:
                mTextSensorProximity.setText(String.format("Proximity sensor : %1$.2f", currentValue));
                break;
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                mTextSensorAmbientTemperature.setText(String.format("Ambient temperature sensor : %1$.2f", currentValue));
                break;
            case Sensor.TYPE_PRESSURE:
                mTextSensorPressure.setText(String.format("Pressure sensor : %1$.2f", currentValue));
                break;
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                mTextSensorRelativeHumidity.setText(String.format("Relative Humidity sensor : %1$.2f", currentValue));
                break;
        }
    }

    private void changeBackgroundColor(float currentValue) {
        ConstraintLayout layout = findViewById(R.id.layout_constraint);
        if (currentValue <= 40000 && currentValue >= 20000) layout.setBackgroundColor(Color.parseColor("#FFACAC"));
        else if (currentValue < 20000 & currentValue >= 10) layout.setBackgroundColor(Color.parseColor("#7DB9B6"));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}