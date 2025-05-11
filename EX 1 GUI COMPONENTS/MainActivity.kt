package com.example.madlab

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.madlab.ui.theme.MADLABTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var editText1 = findViewById<EditText>(R.id.input1)
        var editText2 = findViewById<EditText>(R.id.input2)
        var editText3 = findViewById<EditText>(R.id.output1)
        var btn_add = findViewById<Button>(R.id.add)
        var btn_sub = findViewById<Button>(R.id.sub)
        var btn_mul = findViewById<Button>(R.id.mul)
        var btn_div = findViewById<Button>(R.id.div)
        var btn_mod = findViewById<Button>(R.id.mod)

        btn_add.setOnClickListener(){
            var input1 : String = editText1.text.toString()
            var input2 : String = editText2.text.toString()

            var btn_res_add = input1.toInt()+input2.toInt()
            editText3.setText(btn_res_add.toString())
            Toast.makeText(this,"Addition Successful", Toast.LENGTH_SHORT).show()
        }
        btn_sub.setOnClickListener(){
            var input1 : String = editText1.text.toString()
            var input2 : String = editText2.text.toString()

            var btn_res_sub = input1.toInt()-input2.toInt()
            editText3.setText(btn_res_sub.toString())
            Toast.makeText(this,"Subtraction Successful", Toast.LENGTH_SHORT).show()
        }
        btn_mul.setOnClickListener(){
            var input1 : String = editText1.text.toString()
            var input2 : String = editText2.text.toString()

            var btn_res_mul = input1.toInt()*input2.toInt()
            editText3.setText(btn_res_mul.toString())
            Toast.makeText(this,"Multiplication Successful", Toast.LENGTH_SHORT).show()
        }
        btn_div.setOnClickListener(){
            var input1 : String = editText1.text.toString()
            var input2 : String = editText2.text.toString()

            var btn_res_div = input1.toFloat()/input2.toFloat()
            editText3.setText(btn_res_div.toString())
            Toast.makeText(this,"Division Successful", Toast.LENGTH_SHORT).show()
        }
        btn_mod.setOnClickListener(){
            var input1 : String = editText1.text.toString()
            var input2 : String = editText2.text.toString()

            var btn_res_mod = input2.toInt()%input1.toInt()
            editText3.setText(btn_res_mod.toString())
            Toast.makeText(this,"Modulo Successful", Toast.LENGTH_SHORT).show()
        }


    }
}

