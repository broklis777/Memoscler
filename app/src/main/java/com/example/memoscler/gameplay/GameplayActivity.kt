package com.example.memoscler.gameplay

import android.os.Bundle
import android.util.Log
import android.widget.GridLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.memoscler.customviews.cardview.CardViewElement
import com.example.memoscler.databinding.ActivityGameplayBinding
import com.example.memoscler.models.CardModel

class GameplayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameplayBinding
    private val viewModel: GameplayViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameplayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Define grid size
        val gridSizeX = 5
        val gridSizeY = 5

        // Initialize cards in ViewModel
        viewModel.generateCardsAndTheirCoordinates(gridSizeX, gridSizeY)
        viewModel.pickRandomCards(7)

        // Observe the cards and populate the grid layout when they are updated
        viewModel.cards.observe(this) { updatedCards ->
            Log.d("Updated Cards", updatedCards.toString())
            populateGridLayout(binding.coordinateView, updatedCards, gridSizeX, gridSizeY)
            showCardsForDuration(updatedCards, 3000L)
        }
    }


    private fun populateGridLayout(gridLayout: GridLayout, cards: List<CardModel>, gridSizeX: Int, gridSizeY: Int) {
        gridLayout.columnCount = gridSizeX
        gridLayout.rowCount = gridSizeY
        gridLayout.post {
            val totalWidth = gridLayout.width
            val totalHeight = gridLayout.height

            val buttonWidth = totalWidth / gridSizeX
            val buttonHeight = buttonWidth

            var ind = 0
            for (y in 0 until gridSizeX) {
                for (x in 0 until gridSizeY) {
                    val cardViewElement = CardViewElement(gridLayout.context)
                    val params = GridLayout.LayoutParams().apply {
                        width = buttonWidth
                        height = buttonHeight
                        columnSpec = GridLayout.spec(x)
                        rowSpec = GridLayout.spec(y)

                    }

                    cardViewElement.layoutParams = params

                    // Bindojam datus ar CardViewElement
                    val card = cards.find { it.index == ind }
                    if (card != null) {
                        cardViewElement.bindData(card)
                    }

                    cardViewElement.setOnClickListener {
                        Log.d("CARD PRESSED", "Card at index $ind pressed.")
                        card?.let {
                            if (it.state) {
                                Log.d("CARD ACTION", "Card state: ${it.state}, index: ${it.index}")
                            }
                        }

                    }

                    gridLayout.addView(cardViewElement)
                    ind++
                }
            }
        }
    }

    private fun showCardsForDuration(cards: List<CardModel>, duration: Long) {
        binding.coordinateView.post {
            for (index in cards.indices) {
                val card = cards[index]
                val cardView = binding.coordinateView.getChildAt(card.index) as? CardViewElement
                if (card.state) {
                    cardView?.showBlue()
                }
            }
            binding.coordinateView.postDelayed({
                for (index in cards.indices) {
                    val cardView = binding.coordinateView.getChildAt(index) as? CardViewElement
                    cardView?.hideBlue()
                }
            }, duration)
        }
    }



}

