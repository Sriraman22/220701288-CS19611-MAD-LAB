package com.example.sms_alert

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Telephony
import android.widget.Toast

class SmsReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action.equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
            val bundle = intent.extras
            if (bundle != null) {
                val pdus = bundle.get("pdus") as Array<*>
                for (pdu in pdus) {
                    val smsMessage = Telephony.Sms.Intents.getMessagesFromIntent(intent)[0]
                    val messageBody = smsMessage.messageBody

                    // Show as a Toast
                    Toast.makeText(context, "SMS Received: $messageBody", Toast.LENGTH_LONG).show()

                    // Launch your SmsAlert Activity
                    val alertIntent = Intent(context, SmsAlert::class.java)
                    alertIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    alertIntent.putExtra("sms", messageBody)
                    context.startActivity(alertIntent)
                }
            }
        }
    }
}
