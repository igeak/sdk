package com.kit.test.phone;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.igeak.phone.kit.GeakKit;

public class CustomMsgActivity extends Activity implements OnClickListener {
    private EditText editAction, editMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_msg);
        
        editAction = (EditText) findViewById(R.id.edit_action);
        editMessage = (EditText) findViewById(R.id.edit_message);
        findViewById(R.id.button_send_custom).setOnClickListener(this);
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_send_custom:
                processSendMessage();
                break;
        }
    }
    
    private void processSendMessage() {
        String action = editAction.getText().toString();
        String message = editMessage.getText().toString();

        Log.d("GeakKit", "action:" + action + ", message:" + message);
        if (TextUtils.isEmpty(action)) {
            Toast.makeText(this, "Action cannot be empty.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(message)) {
            Toast.makeText(this, "Message cannot be empty.", Toast.LENGTH_SHORT).show();
            return;
        }
        GeakKit.sendCustomMessage(this, action, message);
    }
    
}
