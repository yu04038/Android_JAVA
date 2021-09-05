package com.example.kakaologin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.media.MediaSession2;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.inputmethod.InputMethod;

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.KakaoSDK;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;

import java.util.logging.Logger;

public class LoginActivity extends AppCompatActivity {

    private SessionCallback callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //카카오톡 로그인 init
        if (KakaoSDK.getAdapter() == null) {
            KakaoSDK.init(new GlobalApplication.KakaoSDKAdapter());
        }

        /**
         * 로그인 버튼을 클릭했을 시 access token을 요청하도록 설정한다!
         *
         * @param savedInstanceState 기존 session 정보가 저장된 객
         */
        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);
        Session.getCurrentSession().checkAndImplicitOpen();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }

    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {
            redirectSignUpActivity();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if (exception != null) {
                Log.e("exception", String.valueOf(exception));
            }
        }
    }

    protected void redirectSignUpActivity() {
        //로그인이 완료된 후 이동하는 액티비티 지정
        final Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

