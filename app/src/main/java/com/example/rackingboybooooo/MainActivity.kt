package com.example.rackingboybooooo

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.rackingboybooooo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var timer: CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        with(binding) {
            val a = 50000L
            val b = 150L
             timer = object : CountDownTimer(a, b) {
                override fun onTick(millisUntilFinished: Long) {

                    val rd = (0..5).random()
                    val rd2 = (0..5).random()
                    val rd3 = (0..5).random()

                    seekbar1.progress += rd
                    seekbar2.progress += rd2
                    seekbar3.progress += rd3
                    play1.visibility = View.VISIBLE
                    if (seekbar1.progress >= seekbar1.max) {
                        showToastAndCancel("Winner 1")
                    } else if (seekbar2.progress >= seekbar2.max) {
                        showToastAndCancel("Winner 2")
                    } else if (seekbar3.progress >= seekbar3.max) {
                        showToastAndCancel("Winner 3")
                    }
                    if(checkbox1.isEnabled ){
                        setZeroProgress()
                    }else play1.visibility = View.INVISIBLE
                }

                override fun onFinish() {

                }
            }

            play1.setOnClickListener {
                timer.start()
                seekbar1.isEnabled = false
                seekbar2.isEnabled = false
                seekbar3.isEnabled = false
                enableCheckbox(false)

            }

            checkbox1.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    checkbox2.isChecked = false
                    checkbox3.isChecked = false
                    play1.visibility = View.VISIBLE
                } else {
                    play1.visibility = View.INVISIBLE
                }
            }
            checkbox2.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    checkbox3.isChecked = false
                    checkbox1.isChecked = false
                    play1.visibility = View.VISIBLE
                } else {
                    play1.visibility = View.INVISIBLE
                }
            }
            checkbox3.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    checkbox1.isChecked = false
                    checkbox2.isChecked = false
                    play1.visibility = View.VISIBLE
                } else {
                    play1.visibility = View.INVISIBLE
                }
            }
        }
    }
    private fun showToastAndCancel(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        timer.cancel()
        enableCheckbox(true)
    }

    private fun enableCheckbox(isEnable: Boolean) {
        with(binding) {
            checkbox1.isEnabled = isEnable
            checkbox2.isEnabled = isEnable
            checkbox3.isEnabled = isEnable
        }
    }

    private fun setZeroProgress() {
        with(binding) {
            seekbar1.progress = 0
            seekbar2.progress = 0
            seekbar3.progress = 0
        }
    }
}
