package com.hkm.curier

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var txtValue : TextView
    private lateinit var txtResult : TextView
    private lateinit var ddFrom : Spinner
    private lateinit var ddTo : Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtValue = findViewById(R.id.txt_value)
        txtResult = findViewById(R.id.txt_result)
        setSpinners()
    }

    private fun setSpinners() {
        ddFrom = findViewById(R.id.dd_fromCurrency)
        ddTo = findViewById(R.id.dd_toCurrency)
        // Create an ArrayAdapter using the currency values
        ArrayAdapter(this,
            android.R.layout.simple_spinner_item,
            Currency.Locale.values()
        ).also { a ->
            // Specify the layout to use when the list of choices appears
            a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            ddFrom.adapter = a
            ddTo.adapter = a
        }
    }

    fun convert(v : View) {
        val valueText = txtValue.text.toString()
        try {
            val currentValue = valueText.toDouble()
            val fromLocale = ddFrom.selectedItem as Currency.Locale
            val targetLocale = ddTo.selectedItem as Currency.Locale
            txtResult.text = Currency(currentValue, fromLocale)
                                .convertTo(targetLocale).getValue().toString()
        }catch (ex: Exception) {
            Toast.makeText(this,
                "The value is not valid",
                Toast.LENGTH_LONG).show()
        }
    }
}