package com.rick.guess2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MaterialActivity : AppCompatActivity() {

    /*private lateinit var appBarConfiguration: AppBarConfiguration
        private lateinit var binding: ActivityMaterialBinding*/
    val secretNumber = SecretNumber()
    val TAG = MaterialActivity ::class.java.simpleName

    lateinit var edNumber : EditText
    lateinit var fab: FloatingActionButton
    lateinit var toolbar: Toolbar
    lateinit var counter: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
        setContentView(R.layout.activity_material)

        findViews()
        counter.setText(secretNumber.count.toString())
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            replay()
        }
        Log.d(TAG, "secret:" + secretNumber.secret)

        val count = getSharedPreferences("guess", Context.MODE_PRIVATE)
            .getInt("REC_COUNTER", -1)
        val nick = getSharedPreferences("guess", Context.MODE_PRIVATE)
            .getString("REC_NICKNAME", null)
        Log.d(TAG, "data: " + count + "/" + nick)
    }

    private fun replay() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.replay_game))
            .setMessage(getString(R.string.are_you_sure))
            .setPositiveButton(getString(R.string.ok), { dialog, which ->
                secretNumber.reset()
                counter.setText(secretNumber.count.toString())
                edNumber.setText("")
            })
            .setNeutralButton(getString(R.string.cancel), null)
            .show()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    private fun findViews() {
        edNumber = findViewById(R.id.ed_number)
        toolbar = findViewById(R.id.toolbar)
        fab = findViewById(R.id.fab)
        counter = findViewById(R.id.record_counter)
    }

    fun check(view : View){
        val n = edNumber.text.toString().toInt()
        val diff = secretNumber.validate(n)
        var message = getString(R.string.yes_you_got_it)
//        println("number: $n")
        Log.d(TAG, "number: $n")

        if (diff < 0){
            message = getString(R.string.bigger)
        }else if (diff > 0){
            message = getString(R.string.smaller)
        }
        counter.setText(secretNumber.count.toString())
//        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_title))
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok), {dialog, which ->
                if (diff == 0){
                    val intent = Intent(this, RecordActivity ::class.java)
                    intent.putExtra("COUNTER", secretNumber.count)
//                    startActivity(intent)
                    resultLauncher.launch(intent)
                }
            })
            .show()
    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }*/
    //註冊一個 callBack 回來要處理的 API，registerForActivityResult()，就是取代以前的 onActivityResult()的用法
    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val nickname = result.data?.getStringExtra("NICK")
            Log.d(TAG, "resultdata: " + nickname)
            replay()
        }
    }
}