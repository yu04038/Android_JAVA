package com.example.bglocation;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import java.util.Timer;
import java.util.TimerTask;

public class GoogleService extends Service implements LocationListener {

    boolean isGPSEnable = false;
    boolean isNetworkEnable = false;
    double latitude, longitude;
    LocationManager locationManager;
    Location location;
    private Handler mHandler = new Handler();
    private Timer mTimer = null;
    long notify_interval = 10000;
    public static String str_receiver = "serviceTutorial.service.receiver";
    Intent intent;


    public GoogleService() {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mTimer = new Timer();
        //일정 시간(delay)이 지난 후에 일정 간격(period)으로 지정한 작업(task)을 수행한다.
        mTimer.schedule(new TimerTaskToGetLocation(), 5, notify_interval);

        intent = new Intent(str_receiver);
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private void fn_getLocation() {
        // 시스템 서비스를 이용해 받아온다.
        // 안드로이드 단말이 현재 위치를 확인하는 방법은 GPS를 통한 방법과 기지국을 통한 방법이 있다.

        // GPS를 이용하는 방식은 정확도가 높지만 실내에서는 사용이 불가능하고, 기지국을 이용하는 방식은 실내 사용이 가능하지만 정확도가 떨어진다.
        locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
        isGPSEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER); //GPS들로부터 현재 위치 확인
        isNetworkEnable = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER); //기지국들로부터 현재 위치 확인

        if (!isGPSEnable && !isNetworkEnable) { // 둘 다 안 된다면!

        } else {

            if (isNetworkEnable) {
                location = null;
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0, this);
                // 5초마다, 0미터 지날때마다, 해당 값을 갱신한다는 의미
                // 위치값을 판별하여 일정 미터 단위 움직임이 발생해도 리스너를 호출이 가능하다!
                if (locationManager != null){
                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if (location != null){

                        Log.e("latitude(Network)",location.getLatitude()+"");
                        Log.e("longitude(Network)",location.getLongitude()+"");

                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        fn_update(location);
                    }
                }

            }


            if (isGPSEnable){
                location = null;
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,0,this);
                if (locationManager!=null){
                    location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (location!=null){
                        Log.e("latitude(GPS)",location.getLatitude()+"");
                        Log.e("longitude(GPS)",location.getLongitude()+"");
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        fn_update(location);
                    }
                }
            }


        }

    }

    private class TimerTaskToGetLocation extends TimerTask{
        @Override
        public void run() {

            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    fn_getLocation();
                }
            });

        }
    }

    private void fn_update(Location location){
        intent.putExtra("latitude",location.getLatitude()+"");
        intent.putExtra("longitude",location.getLongitude()+"");
        sendBroadcast(intent);
    }


}