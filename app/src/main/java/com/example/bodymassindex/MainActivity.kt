package com.example.bodymassindex

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var heightET: EditText
    private lateinit var weightET: EditText
    private lateinit var calculateBTN: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        heightET = findViewById(R.id.heightET)
        weightET = findViewById(R.id.weightET)
        calculateBTN = findViewById(R.id.calculateBTN)
        calculateBTN.setOnClickListener {
            if (heightET.text != null &&
                weightET.text != null &&
                heightET.text.matches(Regex("^[0-9]+$")) &&
                weightET.text.matches(Regex("^[0-9]+$"))) {

                val intent = Intent(this, BodyMassIndexInfoActivity::class.java)
                intent.putExtra("height", heightET.text.toString().toInt())
                intent.putExtra("weight", weightET.text.toString().toInt())
                startActivity(intent)
            }
        }

    }
}