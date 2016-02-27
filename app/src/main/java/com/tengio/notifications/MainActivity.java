package com.tengio.notifications;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.tengio.cpn.CpnActivityReceiver;
import com.tengio.cpn.CpnInAppListener;

public class MainActivity extends AppCompatActivity {

    private CpnActivityReceiver<PushNotification> cpnActivityReceiver = new CpnActivityReceiver<>();
    private TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        message = (TextView) findViewById(R.id.notification_message);

        //TODO using this to register the
        RegistrationIntentService.start(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cpnActivityReceiver.register(this, new CpnInAppListener<PushNotification>() {
            @Override
            public void onReceived(PushNotification notification) {
                message.setText(notification.getMessage());
            }
        });
    }

    @Override
    protected void onPause() {
        cpnActivityReceiver.unregister(this);
        super.onPause();
    }
}
