package com.example.bodymassindex

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BodyMassIndexInfoActivity : AppCompatActivity() {

    private lateinit var headingTV: TextView
    private lateinit var pictureIV: ImageView
    private lateinit var recommendationsTV: TextView
    private lateinit var exitToHomeBTN: Button
    private var BMI = 0.0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bidy_mass_index_info)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        headingTV = findViewById(R.id.headingTV)
        pictureIV = findViewById(R.id.pictureIV)
        recommendationsTV = findViewById(R.id.recommendationsTV)
        exitToHomeBTN = findViewById(R.id.exit_to_homeBTN)
        exitToHomeBTN.setOnClickListener {
            finish()
        }

        launchSomeActivity.launch(intent)

    }

    private val launchSomeActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
            result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            BMI = calculateValues(
                data!!.getIntExtra("height", 0),
                data.getIntExtra("weight", 0))
            setValues(BMI)
            Toast.makeText(this, "$data", Toast.LENGTH_LONG).show()

        } else {

        }
    }

    private fun calculateValues(first: Int, second: Int): Double {
        var variable: Double = (9.99 * first) + (6.25 * second)
        return variable
    }

    private fun setValues(bmi: Double) {
        when {
            bmi < 17.9 -> {
                pictureIV.setImageResource(R.drawable.skinny)
                recommendationsTV.text = getText(R.string.tips_for_thin)
            }

            bmi in 18.0..24.9 -> {
                pictureIV.setImageResource(R.drawable.slim)
                recommendationsTV.text = getText(R.string.tips_for_normal)
            }

            bmi > 25.0 -> {
                pictureIV.setImageResource(R.drawable.full)
                recommendationsTV.text = getText(R.string.tips_for_overweight)
            }
        }

    }


}