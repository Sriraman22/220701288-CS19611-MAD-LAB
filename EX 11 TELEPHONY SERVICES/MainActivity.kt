package com.example.telephonyservices

import android.os.Bundle
import android.content.Context
import android.content.pm.PackageManager
import android.telephony.TelephonyManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE_PHONE_STATE = 1000
    private lateinit var telephonyManager: TelephonyManager
    private lateinit var btGetTelephonyServices: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNetworkOperatorName: EditText = findViewById(R.id.etNetworkOperatorName)
        val etPhoneType: EditText = findViewById(R.id.etPhoneType)
        val etNetworkCountryISO: EditText = findViewById(R.id.etNetworkCountryISO)
        val etSIMCountryISO: EditText = findViewById(R.id.etSIMCountryISO)
        val etDeviceSoftwareVersion: EditText = findViewById(R.id.etDeviceSoftwareVersion)
        btGetTelephonyServices = findViewById(R.id.btGetTelephonyServices)

        telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        checkPermission()

        btGetTelephonyServices.setOnClickListener {
            val networkOperatorName = telephonyManager.networkOperatorName
            val phoneType: Int = telephonyManager.phoneType
            val networkCountryISO: String = telephonyManager.networkCountryIso
            val SIMCountryISO: String = telephonyManager.simCountryIso
            val deviceSoftwareVersion: String? = telephonyManager.deviceSoftwareVersion

            val strPhoneType = when (phoneType) {
                TelephonyManager.PHONE_TYPE_CDMA -> "CDMA"
                TelephonyManager.PHONE_TYPE_GSM -> "GSM"
                TelephonyManager.PHONE_TYPE_NONE -> "NONE"
                else -> "UNKNOWN"
            }

            etNetworkOperatorName.setText(networkOperatorName)
            etPhoneType.setText(strPhoneType)
            etNetworkCountryISO.setText(networkCountryISO)
            etSIMCountryISO.setText(SIMCountryISO)
            etDeviceSoftwareVersion.setText(deviceSoftwareVersion ?: "N/A")
        }
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.READ_PHONE_STATE), REQUEST_CODE_PHONE_STATE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PHONE_STATE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                btGetTelephonyServices.isEnabled = false // Disable button if no permission
            }
        }
    }
}
