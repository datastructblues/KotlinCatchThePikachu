package com.datastructblues.kotlincatchthepikachu

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.datastructblues.kotlincatchthepikachu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var music:MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        music =MediaPlayer.create(this,R.raw.pokemontheme)
    }

    fun start(view: View){
        val intent = Intent(this@MainActivity,GameActivity::class.java)
        startActivity(intent)
        music.stop()
        finish()
    }
    fun exit(view:View){
        finish()
    }
    fun pauseMusic(view:View){
        music.pause()
    }
    fun startMusic(view:View){
        music.start()
    }
}