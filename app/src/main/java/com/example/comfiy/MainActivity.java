package com.example.comfiy;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.icu.text.IDNA;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        printKeyHash();
    }

    public void printKeyHash(){
        try{
            PackageInfo packageInfo = getPackageManager().getPackageInfo("com.example.comfiy", PackageManager.GET_SIGNATURES);
            for (Signature signature : packageInfo.signatures){
                MessageDigest messageDigest =MessageDigest.getInstance("SHA");
                messageDigest.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(messageDigest.digest(),Base64.DEFAULT));
            }
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
    }
}
