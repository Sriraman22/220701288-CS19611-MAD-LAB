package com.example.sms_alert

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Request SMS permission at runtime
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECEIVE_SMS), 1)
        }

        val alert = findViewById<Button>(R.id.alert_Button)
        val clear = findViewById<Button>(R.id.clear_Button)
        val sms = findViewById<EditText>(R.id.edit_Text)
        val builder = AlertDialog.Builder(this)

        alert.setOnClickListener {
            val message = sms.text.toString()
            if (message.isNotEmpty()) {
                builder.setMessage(message).setTitle("New Message")
                builder.setCancelable(false)
                builder.setPositiveButton("OK") { _, _ ->
                    Toast.makeText(applicationContext, "AlertDialog Closed", Toast.LENGTH_LONG).show()
                }
                val alertBox = builder.create()
                alertBox.show()

                val intent = Intent(this, SmsAlert::class.java)
                intent.putExtra("sms", message)
                startActivity(intent)
            } else {
                Toast.makeText(applicationContext, "Type a Message in the Box", Toast.LENGTH_LONG).show()
            }
        }

        clear.setOnClickListener {
            sms.setText("")
        }
    }
}
