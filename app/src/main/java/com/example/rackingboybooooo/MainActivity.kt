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
                this.seekbar1.progress += (0..5).random()
                this.seekbar2.progress += (0..5).random()
                this.seekbar3.progress += (0..5).random()
                this.imAvatar.visibility = View.VISIBLE
                if (this.seekbar1.progress >= this.seekbar1.max) {
                    showToastAndCancel("Winner 1")
                } else if (this.seekbar2.progress >= this.seekbar2.max) {
                    showToastAndCancel("Winner 2")
                } else if (this.seekbar3.progress >= this.seekbar3.max) {
                    showToastAndCancel("Winner 3")
                }
                if (this.cbPlayer2.isEnabled) {
                    setZeroProgress()
                } else this.imPlay.visibility = View.INVISIBLE
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
            this.cbPlayer1.isEnabled = isEnable
            this.cbPlayer2.isEnabled = isEnable
            this.cbPlayer3.isEnabled = isEnable
        }
    }

    private fun onCheckedChangeListener() {
        binding?.apply {
            this.cbPlayer1.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    this.cbPlayer2.isChecked = false
                    this.cbPlayer3.isChecked = false
                    this.imPlay.visibility = View.VISIBLE
                } else {
                    this.imPlay.visibility = View.INVISIBLE
                }
            }
            this.cbPlayer2.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    this.cbPlayer3.isChecked = false
                    this.cbPlayer1.isChecked = false
                    this.imPlay.visibility = View.VISIBLE
                } else {
                    this.imPlay.visibility = View.INVISIBLE
                }
            }
            this.cbPlayer3.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    this.cbPlayer1.isChecked = false
                    this.cbPlayer2.isChecked = false
                    this.imAvatar.visibility = View.VISIBLE
                } else {
                    this.imPlay.visibility = View.INVISIBLE
                }
            }
        }
    }


        private fun setOnClick() {
            binding?.apply  {
                this.imPlay.setOnClickListener {
                    timer.start()
                    this.seekbar1.isEnabled = false
                    this.seekbar2.isEnabled = false
                    this.seekbar3.isEnabled = false
                    enableCheckbox(false)
                }
            }
        }

        private fun setZeroProgress() {
            binding?.apply {
                this.seekbar1.progress = 0
                this.seekbar2.progress = 0
                this.seekbar3.progress = 0
            }
        }
    }
