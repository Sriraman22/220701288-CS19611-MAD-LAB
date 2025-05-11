package com.example.sms_alert
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SmsAlert : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sms_alert)

        val showmsg = findViewById<TextView>(R.id.showmsg)
        val extras = intent.extras
        showmsg.text = extras?.getString("sms")
    }
}
