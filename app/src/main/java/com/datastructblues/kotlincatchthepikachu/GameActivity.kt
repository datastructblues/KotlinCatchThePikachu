package com.datastructblues.kotlincatchthepikachu

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.Toast
import com.datastructblues.kotlincatchthepikachu.databinding.ActivityGameBinding
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    private lateinit var handler:Handler
    private lateinit var runnable: Runnable
    var score=0
    private lateinit var binding:ActivityGameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.scoreText.text="Score: $score"

        moveImage()
        timer()
    }
     fun clickPikachu(view: View){
        score++
        binding.scoreText.text="Score: $score"

    }
    fun timer(){
        object: CountDownTimer(10000,1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.timeText.text="Time: ${(millisUntilFinished /1000)+1} second"
            }

            override fun onFinish() {
                binding.timeText.text="Time Expired"
                handler.removeCallbacks(runnable)
                alert()
            }

        }.start()
    }
     fun alert(){
       val alert= AlertDialog.Builder(this@GameActivity)
        alert.setTitle("Restart game?")
        alert.setMessage("Are you sure to restart game?")
        alert.setPositiveButton("Yes",DialogInterface.OnClickListener { dialog, which ->
          val intent = intent
            startActivity(intent)
            finish()

        })
        alert.setNegativeButton("No"){ dialog, which ->
            Toast.makeText(this@GameActivity,"Game over",Toast.LENGTH_LONG).show()
            val intent =Intent(this@GameActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        alert.show()
    }
     fun moveImage(){
        val random = Random
        handler = Handler(mainLooper)
        runnable =object :Runnable {
            override fun run() {
                    val x = random.nextInt(800)
                    val y= random.nextInt(1000)
                    binding.pikachu.x=x.toFloat()
                    binding.pikachu.y=y.toFloat()
                    binding.scoreText.text="Score: $score"
                    handler.postDelayed(this,500)
             }
            }
        handler.post(runnable)
        }

    }
