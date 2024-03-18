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
    private var binding: ActivityMainBinding ?= null
    private var timer: CountDownTimer = object : CountDownTimer(MAX_TIME_RUNNING, STEP_RUNNING) {
        override fun onTick(millisUntilFinished: Long) {
            if (binding != null) {
                binding!!.seekbar1.progress += (0..5).random()
                binding!!.seekbar2.progress += (0..5).random()
                binding!!.seekbar3.progress += (0..5).random()
                binding!!.imAvatar.visibility = View.VISIBLE
                if (binding!!.seekbar1.progress>= binding!!.seekbar1.max) {
                    showToastAndCancel("Winner 1")
                } else if (binding!!.seekbar2.progress >= binding!!.seekbar2.max) {
                    showToastAndCancel("Winner 2")
                } else if (binding!!.seekbar3.progress >= binding!!.seekbar3.max) {
                    showToastAndCancel("Winner 3")
                }
                if (binding!!.cbPlayer2.isEnabled) {
                    setZeroProgress()
                } else binding?.imPlay?.visibility = View.INVISIBLE
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
        binding?.cbPlayer1?.isEnabled = isEnable
        binding?.cbPlayer2?.isEnabled = isEnable
        binding?.cbPlayer3?.isEnabled = isEnable
    }

    private fun onCheckedChangeListener() {
        binding?.cbPlayer1?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding?.cbPlayer2?.isChecked = false
                binding?.cbPlayer3?.isChecked = false
                binding?.imPlay?.visibility = View.VISIBLE
            } else {
                binding?.imPlay?.visibility = View.INVISIBLE
            }
        }
        binding?.cbPlayer2?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding?.cbPlayer3?.isChecked = false
                binding?.cbPlayer1?.isChecked = false
                binding?.imPlay?.visibility = View.VISIBLE
            } else {
                binding?.imPlay?.visibility = View.INVISIBLE
            }
        }
        binding?.cbPlayer3?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding?.cbPlayer1?.isChecked = false
                binding?.cbPlayer2?.isChecked = false
                binding?.imAvatar?.visibility = View.VISIBLE
            } else {
                binding?.imPlay?.visibility = View.INVISIBLE
            }
        }
    }


    private fun setOnClick() {
        binding?.imPlay?.setOnClickListener {
            timer.start()
            binding?.seekbar1?.isEnabled = false
            binding?.seekbar2?.isEnabled = false
            binding?.seekbar3?.isEnabled = false
            enableCheckbox(false)
        }
    }

    private fun setZeroProgress() {
        binding?.seekbar1?.progress = 0
        binding?.seekbar2?.progress = 0
        binding?.seekbar3?.progress = 0
    }
}

