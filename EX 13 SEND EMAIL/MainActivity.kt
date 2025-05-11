package com.example.sendemail

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etEmail: EditText = findViewById(R.id.etEmail)
        val etSubject: EditText = findViewById(R.id.etSubject)
        val etMessage: EditText = findViewById(R.id.etMessage)
        val btSend: Button = findViewById(R.id.btSend)

        btSend.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val subject = etSubject.text.toString().trim()
            val message = etMessage.text.toString().trim()

            if (email.isNotEmpty() && subject.isNotEmpty() && message.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                    putExtra(Intent.EXTRA_SUBJECT, subject)
                    putExtra(Intent.EXTRA_TEXT, message)
                    type = "message/rfc822"
                }
                startActivity(Intent.createChooser(intent, "Choose an Email client:"))
            } else {
                // Show simple error if fields are empty
                android.widget.Toast.makeText(this, "Please fill all fields", android.widget.Toast.LENGTH_SHORT).show()
            }
        }
    }
}
