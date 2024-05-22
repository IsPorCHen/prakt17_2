package com.bignerdranch.android.prakt17_2

import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class pinCode : AppCompatActivity() {
    lateinit var pincode: EditText

    private lateinit var pref: SharedPreferences
    private var firstCommit: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin_code)

        pincode = findViewById(R.id.pincode)

        pref = getPreferences(MODE_PRIVATE)
        firstCommit = pref.getBoolean("firstCommit", false)

        if (!firstCommit){
            val intent = Intent(this, PressPinCode::class.java)
            startActivity(intent)
        }
    }

    fun handler(v: View) {
        if (firstCommit) {
            if (pincode.text.toString().isEmpty()) {
                val alert = AlertDialog.Builder(this)
                    .setTitle("Ошибка")
                    .setMessage("Обнаружены пустые поля")
                    .setPositiveButton("OK", null)
                    .create()
                    .show()
            } else {
                when (v.id) {
                    R.id.pincode -> {
                        pref = getPreferences(MODE_PRIVATE)
                        val ed = pref.edit()
                        ed.putString("pincode", pincode.text.toString())
                        ed.apply()

                        val alert = AlertDialog.Builder(this)
                            .setTitle("Успешно")
                            .setMessage("Пинкод сохранен")
                            .setPositiveButton("OK", null)
                            .create()
                            .show()
                    }
                }
            }
            firstCommit = false
            pref = getPreferences(MODE_PRIVATE)
            val ed = pref.edit()
            ed.putBoolean("firstCommit", firstCommit)
            ed.apply()
        }
    }
}
