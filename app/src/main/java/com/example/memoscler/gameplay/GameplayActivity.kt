package com.example.memoscler.gameplay

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.GridLayout
import androidx.appcompat.app.ActionBar.LayoutParams
import androidx.appcompat.app.AppCompatActivity
import com.example.memoscler.R
import com.example.memoscler.databinding.ActivityGameplayBinding
import com.example.memoscler.models.CardModel

class GameplayActivity : AppCompatActivity() {

    lateinit var binding: ActivityGameplayBinding
    lateinit var cards: MutableList<CardModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameplayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.coordinateView.columnCount = 5
        binding.coordinateView.rowCount = 5

        cards = mutableListOf()

        populateGridLayout(binding.coordinateView, 5, 5)
        generateCardsAndTheirCoordinates(5,5)
    }

    private fun populateGridLayout(gridLayout: GridLayout, gridSizeX: Int, gridSizeY: Int) {
        for (x in 0 until gridSizeX) {
            for (y in 0 until gridSizeY) {
                val button = Button(this)
                val params = GridLayout.LayoutParams().apply {
                    columnSpec = GridLayout.spec(x)
                    rowSpec = GridLayout.spec(y)
                }
                button.layoutParams = params
                button.text = "($x, $y)"
                gridLayout.addView(button)
            }
        }
    }


    private fun generateCardsAndTheirCoordinates(gridSizeX: Int, gridSizeY: Int) {
        for (x in 1 until gridSizeX + 1){
            for (y in 1 until gridSizeY + 1){
                //coordinates.add(Pair(x, y))
                val card = CardModel(
                    coordinateX = x,
                    coordinateY = y,
                    state = false
                )
                cards.add(card)
            }
        }
        Log.d("LIST OF CARDS", cards.toString())
    }




}