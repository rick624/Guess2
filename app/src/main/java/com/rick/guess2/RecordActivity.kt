package com.rick.guess2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class RecordActivity : AppCompatActivity() {
    lateinit var counter: TextView
    lateinit var save: Button
    lateinit var nick: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
        val count : Int = intent.getIntExtra("COUNTER", -1)

        findViews()
        counter.setText(count.toString())

        //OnClickListener
        save.setOnClickListener { view ->
            var nick = nick.text.toString()
            getSharedPreferences("guess", MODE_PRIVATE)
                .edit()
                .putInt("REC_COUNTER", count)
                .putString("REC_NICKNAME", nick)
                .apply()
            val intent = Intent()
            intent.putExtra("NICK", nick)
            setResult(RESULT_OK, intent)
            finish()
        }


    }

    fun findViews(){
        counter = findViewById(R.id.record_counter)
        save = findViewById(R.id.record_save)
        nick = findViewById(R.id.record_nickname)
    }
}