package com.rick.guess2

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rick.guess2.databinding.ActivityMaterialBinding

class MaterialActivity : AppCompatActivity() {

    /*private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMaterialBinding*/
    val secretNumber = SecretNumber()
    val TAG = MainActivity ::class.java.simpleName

    lateinit var edNumber : EditText
    lateinit var fab: FloatingActionButton
    lateinit var toolbar: Toolbar
    lateinit var counter: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)

        findViews()
        counter.setText(secretNumber.count.toString())
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.replay_game))
                .setMessage(getString(R.string.are_you_sure))
                .setPositiveButton(getString(R.string.ok), {dialog, which ->
                    secretNumber.reset()
                    counter.setText(secretNumber.count.toString())
                    edNumber.setText("")
                })
                .setNeutralButton(getString(R.string.cancel), null)
                .show()
        }

        Log.d(TAG, "secret:" + secretNumber.secret)
    }

    private fun findViews() {
        edNumber = findViewById(R.id.ed_number)
        toolbar = findViewById(R.id.toolbar)
        fab = findViewById(R.id.fab)
        counter = findViewById(R.id._counter)
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
            .setPositiveButton(getString(R.string.ok), null)
            .show()
    }
}