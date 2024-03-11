package com.example.rackingboybooooo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.view.isVisible
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var p1: ImageView
    private lateinit var s1: SeekBar
    private lateinit var s2: SeekBar
    private lateinit var s3: SeekBar
    private lateinit var b1: CheckBox
    private lateinit var b2: CheckBox
    private lateinit var b3: CheckBox


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        play()
        enableCheckbox()
        s1.isEnabled = true
        s2.isEnabled = true
        s3.isEnabled = true


        val timer = object : CountDownTimer(60000, 300) {
            override fun onTick(millisUntilFinished: Long) {
                val rd = (0..5).random()
                val rd2 = (0..5).random()
                val rd3 = (0..5).random()

                s1.progress += rd
                s2.progress += rd2
                s3.progress += rd3

                if (s1.progress >= s1.max) {
                    this.cancel()
                    p1.visibility = View.VISIBLE
                    Toast.makeText(this@MainActivity, "Winner 1", Toast.LENGTH_SHORT).show()
                    enableCheckbox()

                } else if (s2.progress >= s2.max) {
                    this.cancel()
                    p1.visibility = View.VISIBLE
                    Toast.makeText(this@MainActivity, "Winner 2", Toast.LENGTH_SHORT).show()
                    enableCheckbox()

                } else if (s3.progress >= s3.max) {
                    this.cancel()
                    p1.visibility = View.VISIBLE
                    Toast.makeText(this@MainActivity, "Winner 3", Toast.LENGTH_SHORT).show()
                    enableCheckbox()

                }

            }

            override fun onFinish() {

            }
        }


        b1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                b2.isChecked = false
                b3.isChecked = false
                p1.visibility = View.VISIBLE
                p1.setOnClickListener {
                    s1.progress = 0
                    s2.progress = 0
                    s3.progress = 0
                    p1.visibility = View.INVISIBLE
                    timer.start()
                    UnenableCheckbox()
                }
            } else p1.visibility = View.INVISIBLE

        }

        b2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                b3.isChecked = false
                b1.isChecked = false
                p1.visibility = View.VISIBLE
                p1.setOnClickListener {
                    s1.progress = 0
                    s2.progress = 0
                    s3.progress = 0
                    p1.visibility = View.INVISIBLE
                    timer.start()
                    UnenableCheckbox()
                }
            } else p1.visibility = View.INVISIBLE

        }

        b3.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                b1.isChecked = false
                b2.isChecked = false
                p1.visibility = View.VISIBLE
                p1.setOnClickListener {
                    s1.progress = 0
                    s2.progress = 0
                    s3.progress = 0
                    p1.visibility = View.INVISIBLE
                    timer.start()
                    UnenableCheckbox()
                }
            } else p1.visibility = View.INVISIBLE
        }

    }



    private fun enableCheckbox() {
        b1.isEnabled = true
        b2.isEnabled = true
        b3.isEnabled = true
    }

    private fun UnenableCheckbox() {
        b1.isEnabled = false
        b2.isEnabled = false
        b3.isEnabled = false
    }

    private fun play() {
        s1 = findViewById(R.id.s1)
        s2 = findViewById(R.id.s2)
        s3 = findViewById(R.id.s3)
        p1 = findViewById(R.id.p1)
        b1 = findViewById(R.id.b1)
        b2 = findViewById(R.id.b2)
        b3 = findViewById(R.id.b3)
    }

}