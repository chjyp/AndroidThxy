package com.example.androidthxy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private ImageView iv;
    private boolean isRunning = false;
    private AnimationDrawable animationDrawable;
    private TextView textView;
    SurfaceView surfaceView;
    private GLSurfaceView glSurfaceView;
    private TextView tv_x, tv_y, tv_z;
    private Sensor s;
    private SensorManager sm;

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        tv_x.setText("x轴的加速度：" + x);
        tv_y.setText("y轴的加速度：" + y);
        tv_z.setText("z轴的加速度：" + z);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        glSurfaceView = new GLSurfaceView(this);
//        glSurfaceView.setRenderer(new CubeRenderer());
        //setContentView(glSurfaceView);
        setContentView(R.layout.sensor_main);
        tv_x = (TextView)findViewById(R.id.tv_x);
        tv_y = (TextView)findViewById(R.id.tv_y);
        tv_z = (TextView)findViewById(R.id.tv_z);
        sm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        s = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //initView();
//        setContentView(R.layout.layout_sensor);
//        textView = (TextView)findViewById(R.id.textView);
//        testSensor();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (s != null)
            sm.registerListener(this,s,SensorManager.SENSOR_DELAY_NORMAL);
   //     glSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (s != null)
            sm.unregisterListener(this);
     //   glSurfaceView.onPause();
    }

    private void initView() {
        animationDrawable = new AnimationDrawable();
        Drawable drawable1 = getResources().getDrawable(R.drawable.t1);
        Drawable drawable2 = getResources().getDrawable(R.drawable.t2);
        Drawable drawable3 = getResources().getDrawable(R.drawable.t3);
        Drawable drawable4 = getResources().getDrawable(R.drawable.t4);
        Drawable drawable5 = getResources().getDrawable(R.drawable.t5);
        animationDrawable.addFrame(drawable1, 850);
        animationDrawable.addFrame(drawable2, 850);
        animationDrawable.addFrame(drawable3, 850);
        animationDrawable.addFrame(drawable4, 850);
        animationDrawable.addFrame(drawable5, 850);
    }

    public void alphaAnim(View view){
        //Animation an = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_demo);
        iv = (ImageView)findViewById(R.id.imageView);
//        iv.startAnimation(an);
//        AnimationDrawable ad = (AnimationDrawable) iv.getDrawable();
        iv.setBackground(animationDrawable);
        if (!isRunning){
            animationDrawable.start();
            isRunning = true;
            ((Button)(view)).setText("停止动画");
        } else {
            animationDrawable.stop();
            isRunning = false;
            ((Button)(view)).setText("开始动画");
        }

    }

    private void testSensor() {
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> list = sm.getSensorList(Sensor.TYPE_ALL);
        for (Sensor s:list){
            textView.append(s.getType() + "," + s.getName() + "\n");
        }
    }
}
