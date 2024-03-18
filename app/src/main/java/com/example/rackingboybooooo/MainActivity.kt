package com.example.rackingboybooooo

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.rackingboybooooo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val MAX_TIME_RUNNING = 50000L
    private val STEP_RUNNING = 150L
    private var binding: ActivityMainBinding? = null
    private var timer: CountDownTimer = object : CountDownTimer(MAX_TIME_RUNNING, STEP_RUNNING) {
        override fun onTick(millisUntilFinished: Long) {
            binding?.let { result ->
                result.seekbar1.progress += (0..5).random()
                result.seekbar2.progress += (0..5).random()
                result.seekbar3.progress += (0..5).random()
                result.imAvatar.visibility = View.VISIBLE
                if (result.seekbar1.progress >= result.seekbar1.max) {
                    showToastAndCancel("Winner 1")
                } else if (result.seekbar2.progress >= result.seekbar2.max) {
                    showToastAndCancel("Winner 2")
                } else if (result.seekbar3.progress >= result.seekbar3.max) {
                    showToastAndCancel("Winner 3")
                }
                if (result.cbPlayer2.isEnabled) {
                    setZeroProgress()
                } else result.imPlay.visibility = View.INVISIBLE
            }
        }

        override fun onFinish() {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setOnClick()
        onCheckedChangeListener()
    }


    private fun showToastAndCancel(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        timer.cancel()
        enableCheckbox(true)
    }

    private fun enableCheckbox(isEnable: Boolean) {
        binding?.let { result ->
            result.cbPlayer1.isEnabled = isEnable
            result.cbPlayer2.isEnabled = isEnable
            result.cbPlayer3.isEnabled = isEnable
        }
    }

    private fun onCheckedChangeListener() {
        binding?.let { result ->
            result.cbPlayer1.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    result.cbPlayer2.isChecked = false
                    result.cbPlayer3.isChecked = false
                    result.imPlay.visibility = View.VISIBLE
                } else {
                    result.imPlay.visibility = View.INVISIBLE
                }
            }
            result.cbPlayer2.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    result.cbPlayer3.isChecked = false
                    result.cbPlayer1.isChecked = false
                    result.imPlay.visibility = View.VISIBLE
                } else {
                    result.imPlay.visibility = View.INVISIBLE
                }
            }
            result.cbPlayer3.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    result.cbPlayer1.isChecked = false
                    result.cbPlayer2.isChecked = false
                    result.imAvatar.visibility = View.VISIBLE
                } else {
                    result.imPlay.visibility = View.INVISIBLE
                }
            }
        }
    }


        private fun setOnClick() {
            binding?.let { result ->
                result.imPlay.setOnClickListener {
                    timer.start()
                    result.seekbar1.isEnabled = false
                    result.seekbar2.isEnabled = false
                    result.seekbar3.isEnabled = false
                    enableCheckbox(false)
                }
            }
        }

        private fun setZeroProgress() {
            binding?.let { result ->
                result.seekbar1.progress = 0
                result.seekbar2.progress = 0
                result.seekbar3.progress = 0
            }
        }
    }

