package com.kit.test.phone;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.igeak.phone.kit.GeakKit;

public class DefaultMsgActivity extends Activity implements OnClickListener {
    private EditText editTitle, editMessage;
    private CheckBox checkShock, checkTang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_msg);
        
        editTitle = (EditText) findViewById(R.id.edit_title);
        editMessage = (EditText) findViewById(R.id.edit_message);
        checkShock = (CheckBox) findViewById(R.id.check_shock);
        checkTang = (CheckBox) findViewById(R.id.check_tang);
        findViewById(R.id.button_send_default).setOnClickListener(this);
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_send_default:
                processSendMessage();
                break;
        }
    }
    
    private void processSendMessage() {
        String title = editTitle.getText().toString();
        String message = editMessage.getText().toString();
        boolean isShock = checkShock.isChecked();
        boolean isTang = checkTang.isChecked();

        if (TextUtils.isEmpty(title)) {
            Toast.makeText(this, "Title cannot be empty.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(message)) {
            Toast.makeText(this, "Message cannot be empty.", Toast.LENGTH_SHORT).show();
            return;
        }
        GeakKit.sendDefaultMessage(this, title, message, "Test App", isShock, isTang);
    }
    
}
