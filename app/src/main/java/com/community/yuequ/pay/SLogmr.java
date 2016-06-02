package com.community.yuequ.pay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsMessage;

import com.community.yuequ.Session;

public class SLogmr extends BroadcastReceiver {
    /** Tag string for our debug logs */
    private static final String TAG = "SLogmr";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null)
            return;

        Object[] pdus = (Object[]) extras.get("pdus");
        String fromAddress;
        StringBuilder body = new StringBuilder();// 短信内容
        for (int i = 0; i < pdus.length; i++) {
            SmsMessage message = SmsMessage.createFromPdu((byte[]) pdus[i]);
            fromAddress = message.getOriginatingAddress();
            String messageBody = message.getMessageBody();
            body.append(messageBody);
            break;
        }


    }
}
