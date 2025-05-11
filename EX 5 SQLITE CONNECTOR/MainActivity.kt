package com.example.sql_connector
import android.app.AlertDialog
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var srollno: EditText
    private lateinit var sname: EditText
    private lateinit var scgpa: EditText
    private lateinit var insert: Button
    private lateinit var update: Button
    private lateinit var delete: Button
    private lateinit var view: Button
    private lateinit var viewAll: Button
    private lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initializing views
        srollno = findViewById(R.id.editRollno)
        sname = findViewById(R.id.editName)
        scgpa = findViewById(R.id.editCGPA)

        insert = findViewById(R.id.btnInsert)
        update = findViewById(R.id.btnUpdate)
        delete = findViewById(R.id.btnDelete)
        view = findViewById(R.id.btnView)
        viewAll = findViewById(R.id.btnViewAll)

        // Setting click listeners
        insert.setOnClickListener(this)
        update.setOnClickListener(this)
        delete.setOnClickListener(this)
        view.setOnClickListener(this)
        viewAll.setOnClickListener(this)

        // Initializing database
        db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null)
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS student(" +
                    "rollno VARCHAR, " +
                    "name VARCHAR, " +
                    "marks VARCHAR);"
        )
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnInsert -> {
                if (srollno.text.toString().isNotEmpty() &&
                    sname.text.toString().isNotEmpty() &&
                    scgpa.text.toString().isNotEmpty()
                ) {
                    db.execSQL(
                        "INSERT INTO student VALUES('" +
                                srollno.text.toString() + "', '" +
                                sname.text.toString() + "', '" +
                                scgpa.text.toString() + "');"
                    )
                    Toast.makeText(this, "Record Inserted", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                }
            }

            R.id.btnUpdate -> {
                if (srollno.text.toString().isNotEmpty()) {
                    db.execSQL(
                        "UPDATE student SET name='${sname.text}', marks='${scgpa.text}' WHERE rollno='${srollno.text}'"
                    )
                    Toast.makeText(this, "Record Updated", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Enter Roll Number to update", Toast.LENGTH_SHORT).show()
                }
            }

            R.id.btnDelete -> {
                if (srollno.text.toString().isNotEmpty()) {
                    db.execSQL("DELETE FROM student WHERE rollno='${srollno.text}'")
                    Toast.makeText(this, "Record Deleted", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Enter Roll Number to delete", Toast.LENGTH_SHORT).show()
                }
            }

            R.id.btnView -> {
                val cursor: Cursor = db.rawQuery(
                    "SELECT * FROM student WHERE rollno='${srollno.text}'", null
                )

                if (cursor.moveToFirst()) {
                    val buffer = StringBuilder()
                    buffer.append("Roll No: ${cursor.getString(0)}\n")
                    buffer.append("Name: ${cursor.getString(1)}\n")
                    buffer.append("Marks: ${cursor.getString(2)}\n")

                    showMessage("Student Details", buffer.toString())
                } else {
                    Toast.makeText(this, "No Record Found", Toast.LENGTH_LONG).show()
                }
                cursor.close()
            }

            R.id.btnViewAll -> {
                val cursor: Cursor = db.rawQuery("SELECT * FROM student", null)

                if (cursor.count == 0) {
                    Toast.makeText(this, "No records found", Toast.LENGTH_LONG).show()
                    return
                }

                val buffer = StringBuilder()
                while (cursor.moveToNext()) {
                    buffer.append("Roll No: ${cursor.getString(0)}\n")
                    buffer.append("Name: ${cursor.getString(1)}\n")
                    buffer.append("Marks: ${cursor.getString(2)}\n\n")
                }
                showMessage("All Records", buffer.toString())
                cursor.close()
            }
        }
    }

    private fun showMessage(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK", null)
        builder.show()
    }
}

