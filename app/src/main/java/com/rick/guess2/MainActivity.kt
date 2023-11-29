package com.rick.guess2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    val secretNumber = SecretNumber()
    var TAG = "MainActivity"

    lateinit var edNumber : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViews()
        Log.d(TAG, "secret:" + secretNumber.secret)
    }

    private fun findViews() {
        edNumber = findViewById(R.id.ed_number)
    }

    fun check(view : View){
        val n = edNumber.text.toString().toInt()
        val diff = secretNumber.validate(n)
        var message = "Yes, you got it"
//        println("number: $n")
        Log.d(TAG, "number: $n")

        if (diff < 0){
            message = "Bigger"
        }else if (diff > 0){
            message = "Smaller"
        }
//        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        AlertDialog.Builder(this)
            .setTitle("Message")
            .setMessage(message)
            .setPositiveButton("ok", null)
            .show()
    }
}