package mx.skylabs.petagram;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by sky on 24/05/17.
 */

public class NotificationIDTokenService extends FirebaseInstanceIdService {


    private static final String TAG = "FIREBASE_TOKEN";
    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        enviarTokenRegistro(token);
    }

    private void enviarTokenRegistro(String token) {
        Log.d(TAG,token);
    }
}
