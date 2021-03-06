package com.drawers.banklib.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import com.drawers.banklib.client.MessageListener;

public class MessageBroadcastReceiver extends BroadcastReceiver {

  private MessageListener messageListener;

  public MessageBroadcastReceiver() {
  }

  public void attach(MessageListener messageListener) {
    this.messageListener = messageListener;
  }

  public void detach() {
    this.messageListener = null;
  }

  @SuppressWarnings({"deprecation", "ConstantConditions"})
  @Override public void onReceive(Context context, Intent intent) {
    Bundle bundle = intent.getExtras();
    SmsMessage[] smsMessages = null;

    if (bundle != null) {
      Object[] pdus = (Object[]) bundle.get("pdus");
      smsMessages = new SmsMessage[pdus.length];

      for (int i = 0; i < smsMessages.length; i++) {
        smsMessages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
        String sender = smsMessages[i].getOriginatingAddress();
        String message = smsMessages[i].getMessageBody();
        if (messageListener != null) {
          messageListener.onMessageReceived(sender, message);
        }
      }
    }
  }
}
