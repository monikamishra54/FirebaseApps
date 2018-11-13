package com.example.mgc.firebaseapps;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by mgc on 7/27/2017.
 */
public class MyFirebaseInstanceIDService extends com.google.firebase.iid.FirebaseInstanceIdService {
    private static final String TAG ="MyFirebaseIIDService";
    SharedPreferences sp;

    @Override
    public void onTokenRefresh() {

        //getting registration token
        String refreshedToken= FirebaseInstanceId.getInstance().getToken();

        //Displaying token in logcat
        Log.e(TAG,"Refreshed token:" + refreshedToken);

        sp=getSharedPreferences("MyToken", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("token",refreshedToken);
        editor.commit();




    }
}
