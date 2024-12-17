package com.example.memoscler.gameplay

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.memoscler.R
import com.example.memoscler.databinding.ActivityGameplayBinding

class GameplayActivity : AppCompatActivity() {

    lateinit var binding: ActivityGameplayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameplayBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_gameplay)


    }

    private fun generateCoordinates(gridSizeX: Int, gridSizeY: Int): List<Pair<Int, Int>> {
        val coordinates = mutableListOf<Pair<Int, Int>>()
        for (x in 0 until gridSizeX + 1){
            for (y in 0 until gridSizeY + 1){
                coordinates.add(Pair(x, y))
            }
        }
        return coordinates
    }




}