
package com.kit.test.phone;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.igeak.phone.kit.GeakKit;

public class MainActivity extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GeakKit.registerConnectStateChangeHandler(this, handler);
        findViewById(R.id.button_isSupport).setOnClickListener(this);
        findViewById(R.id.button_isConnect).setOnClickListener(this);
        findViewById(R.id.button_default_test).setOnClickListener(this);
        findViewById(R.id.button_custom_test).setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        GeakKit.unregisterConnectStateChangeHandler(this, handler);
        super.onDestroy();
    }

    @Override
    public void onClick(android.view.View v) {
        switch (v.getId()) {
            case R.id.button_isSupport:
                String support = GeakKit.isKitSupported(this) ? "支持Kit" : "不支持Kit";
                Toast.makeText(this, support, Toast.LENGTH_SHORT).show();
                break;
                
            case R.id.button_isConnect:
                String connect = GeakKit.isConnect(this) ? "已连接" : "未连接";
                Toast.makeText(this, connect, Toast.LENGTH_SHORT).show();
                break;

            case R.id.button_default_test:
                startActivity(new Intent(this, DefaultMsgActivity.class));
                break;

            case R.id.button_custom_test:
                startActivity(new Intent(this, CustomMsgActivity.class));
                break;
        }
    }

    GeakKit.ConnectStateChangeHandler handler = new GeakKit.ConnectStateChangeHandler() {

        @Override
        public void onConnectStateChange(Context context, int statePre, int stateNew) {
            Log.d("kit", "statepre:" + statePre + ", stateNew:" + stateNew);
        }
    };

}
