package com.example.ex_5

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editUName: EditText
    private lateinit var editIDNo: EditText
    private lateinit var btnValidate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initializing views
        editUName = findViewById(R.id.editName)
        editIDNo = findViewById(R.id.editIDNo)
        btnValidate = findViewById(R.id.btnValidate)

        // Setting click listener
        btnValidate.setOnClickListener {
            validateInput()
        }
    }

    private fun validateInput() {
        val uName = editUName.text.toString().trim()
        val idNo = editIDNo.text.toString().trim()

        // Validation
        when {
            uName.isEmpty() || idNo.isEmpty() -> {
                Toast.makeText(this, "Please enter all values", Toast.LENGTH_LONG).show()
            }
            !uName.matches(Regex("[a-zA-Z]+")) -> {
                Toast.makeText(this, "Please enter only alphabets for the name", Toast.LENGTH_LONG).show()
            }
            !idNo.matches(Regex("\\d{4}")) -> {
                Toast.makeText(this, "Please enter a four-digit number for ID", Toast.LENGTH_LONG).show()
            }
            else -> {
                Toast.makeText(this, "Validation Successful", Toast.LENGTH_LONG).show()
            }
        }
    }
}
