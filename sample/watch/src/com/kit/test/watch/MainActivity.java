
package com.kit.test.watch;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.kit.test");
        registerReceiver(mReceiver, filter);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }
    
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d("GeakKit", "action:" + action);
            
            String message = intent.getStringExtra("message");
            Log.d("GeakKit", "message:" + message);
            
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

}
