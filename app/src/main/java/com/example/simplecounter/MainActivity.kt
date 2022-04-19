package com.example.simplecounter

import android.app.Activity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView


class MainActivity : Activity() {
    //Declare a variable to hold count down timer's paused status
    private var isPaused = false

    //Declare a variable to hold count down timer's paused status
    private var isCanceled = false

    //Declare a variable to hold CountDownTimer remaining time
    private var timeRemaining: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Get reference of the XML layout's widgets
        val tView = findViewById<View>(R.id.tv) as TextView
        val btnStart: Button = findViewById<View>(R.id.btn_start) as Button
        val btnPause: Button = findViewById<View>(R.id.btn_pause) as Button
        val btnResume: Button = findViewById<View>(R.id.btn_resume) as Button
        val btnCancel: Button = findViewById<View>(R.id.btn_cancel) as Button

        //Initially disabled the pause, resume and cancel button
        btnPause.isEnabled = false
        btnResume.isEnabled = false
        btnCancel.isEnabled = false


        //Set a Click Listener for start button
        btnStart.setOnClickListener {
            isPaused = false
            isCanceled = false

            //Disable the start and pause button
            btnStart.isEnabled = false
            btnResume.isEnabled = false
            //Enabled the pause and cancel button
            btnPause.isEnabled = true
            btnCancel.isEnabled = true
            val timer: CountDownTimer
            val millisInFuture: Long = 30000 //30 seconds
            val countDownInterval: Long = 1000 //1 second


            //Initialize a new CountDownTimer instance
            timer = object : CountDownTimer(millisInFuture, countDownInterval) {
                override fun onTick(millisUntilFinished: Long) {
                    //do something in every tick
                    if (isPaused || isCanceled) {
                        //If the user request to cancel or paused the
                        //CountDownTimer we will cancel the current instance
                        cancel()
                    } else {
                        //Display the remaining seconds to app interface
                        //1 second = 1000 milliseconds
                        tView.text = "" + millisUntilFinished / 1000
                        //Put count down timer remaining time in a variable
                        timeRemaining = millisUntilFinished
                    }
                }

                override fun onFinish() {
                    //Do something when count down finished
                    tView.text = "Done"

                    //Enable the start button
                    btnStart.isEnabled = true
                    //Disable the pause, resume and cancel button
                    btnPause.isEnabled = false
                    btnResume.isEnabled = false
                    btnCancel.isEnabled = false
                }
            }.start()
        }

        //Set a Click Listener for pause button
        btnPause.setOnClickListener {
            //When user request to pause the CountDownTimer
            isPaused = true

            //Enable the resume and cancel button
            btnResume.isEnabled = true
            btnCancel.isEnabled = true
            //Disable the start and pause button
            btnStart.isEnabled = false
            btnPause.isEnabled = false
        }


        //Set a Click Listener for resume button
        btnResume.setOnClickListener {
            //Disable the start and resume button
            btnStart.isEnabled = false
            btnResume.isEnabled = false
            //Enable the pause and cancel button
            btnPause.isEnabled = true
            btnCancel.isEnabled = true

            //Specify the current state is not paused and canceled.
            isPaused = false
            isCanceled = false

            //Initialize a new CountDownTimer instance
            val millisInFuture = timeRemaining
            val countDownInterval: Long = 1000
            object : CountDownTimer(millisInFuture, countDownInterval) {
                override fun onTick(millisUntilFinished: Long) {
                    //Do something in every tick
                    if (isPaused || isCanceled) {
                        //If user requested to pause or cancel the count down timer
                        cancel()
                    } else {
                        tView.text = "" + millisUntilFinished / 1000
                        //Put count down timer remaining time in a variable
                        timeRemaining = millisUntilFinished
                    }
                }

                override fun onFinish() {
                    //Do something when count down finished
                    tView.text = "Done"
                    //Disable the pause, resume and cancel button
                    btnPause.isEnabled = false
                    btnResume.isEnabled = false
                    btnCancel.isEnabled = false
                    //Enable the start button
                    btnStart.isEnabled = true
                }
            }.start()

            //Set a Click Listener for cancel/stop button
            btnCancel.setOnClickListener {
                //When user request to cancel the CountDownTimer
                isCanceled = true

                //Disable the cancel, pause and resume button
                btnPause.isEnabled = false
                btnResume.isEnabled = false
                btnCancel.isEnabled = false
                //Enable the start button
                btnStart.isEnabled = true

                //Notify the user that CountDownTimer is canceled/stopped
                tView.text = "CountDownTimer Canceled/stopped."
            }
        }

        //Set a Click Listener for cancel/stop button
        btnCancel.setOnClickListener {
            //When user request to cancel the CountDownTimer
            isCanceled = true

            //Disable the cancel, pause and resume button
            btnPause.isEnabled = false
            btnResume.isEnabled = false
            btnCancel.isEnabled = false
            //Enable the start button
            btnStart.isEnabled = true

            //Notify the user that CountDownTimer is canceled/stopped
            tView.text = "CountDownTimer Canceled/stopped."
        }
    }

}