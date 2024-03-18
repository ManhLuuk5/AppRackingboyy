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
            binding?.apply {
                seekbar1.progress += (0..5).random()
                seekbar2.progress += (0..5).random()
                seekbar3.progress += (0..5).random()
                imAvatar.visibility = View.VISIBLE
                if (this.seekbar1.progress >= this.seekbar1.max) {
                    showToastAndCancel("Winner 1")
                } else if (this.seekbar2.progress >= this.seekbar2.max) {
                    showToastAndCancel("Winner 2")
                } else if (this.seekbar3.progress >= this.seekbar3.max) {
                    showToastAndCancel("Winner 3")
                }
                if (this.cbPlayer2.isEnabled) {
                    setZeroProgress()
                } else imPlay.visibility = View.INVISIBLE
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
        binding?.apply {
            cbPlayer1.isEnabled = isEnable
            cbPlayer2.isEnabled = isEnable
            cbPlayer3.isEnabled = isEnable
        }
    }

    private fun onCheckedChangeListener() {
        binding?.apply {
            cbPlayer1.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    cbPlayer2.isChecked = false
                    cbPlayer3.isChecked = false
                    imPlay.visibility = View.VISIBLE
                } else {
                    imPlay.visibility = View.INVISIBLE
                }
            }
            cbPlayer2.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    cbPlayer3.isChecked = false
                    cbPlayer1.isChecked = false
                    imPlay.visibility = View.VISIBLE
                } else {
                    imPlay.visibility = View.INVISIBLE
                }
            }
            cbPlayer3.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    cbPlayer1.isChecked = false
                    cbPlayer2.isChecked = false
                    imAvatar.visibility = View.VISIBLE
                } else {
                    imPlay.visibility = View.INVISIBLE
                }
            }
        }
    }


        private fun setOnClick() {
            binding?.apply  {
                imPlay.setOnClickListener {
                    timer.start()
                    seekbar1.isEnabled = false
                    seekbar2.isEnabled = false
                    seekbar3.isEnabled = false
                    enableCheckbox(false)
                }
            }
        }

        private fun setZeroProgress() {
            binding?.apply {
                seekbar1.progress = 0
                seekbar2.progress = 0
                seekbar3.progress = 0
            }
        }
    }
