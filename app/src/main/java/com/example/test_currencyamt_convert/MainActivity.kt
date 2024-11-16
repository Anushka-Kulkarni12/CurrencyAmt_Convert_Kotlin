package com.example.test_currencyamt_convert

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnConvert = findViewById<Button>(R.id.btnCon)
        val txtTextView = findViewById<TextView>(R.id.txtTextView)

        val defaultAmount = 100.0
        txtTextView.text = "Amount: $defaultAmount"

        btnConvert.setOnClickListener {
            showCurrencyDialog(defaultAmount) { convertedAmount ->

                txtTextView.text = "Converted Amount: $convertedAmount"
            }
        }
    }

    private fun showCurrencyDialog(amount: Double, onCurrencyConverted: (Double) -> Unit) {
        // Inflate the custom dialog layout
        val dialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        val rgCurrency = dialogView.findViewById<RadioGroup>(R.id.rdGroup)

        rgCurrency.setOnCheckedChangeListener { _, checkedId ->
            val conversionRate = when (checkedId) {
                R.id.rbCan -> 1.0
                R.id.rbUsa -> 0.75
                R.id.rbUk -> 0.58
                R.id.rbChina -> 5.36
                else -> 1.0
            }

            val convertedAmount = amount * conversionRate
            onCurrencyConverted(convertedAmount)

            dialog.dismiss()
        }
        
        dialog.show()
    }
}
