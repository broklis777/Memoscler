package com.example.memoscler.gameplay

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memoscler.models.CardModel
import kotlinx.coroutines.launch
import kotlin.time.Duration

class GameplayViewModel : ViewModel() {

    private val _cards = MutableLiveData<MutableList<CardModel>>(mutableListOf())
    val cards: LiveData<MutableList<CardModel>> get() = _cards

    init {
        _cards.value = mutableListOf()
    }

    fun generateCardsAndTheirCoordinates(gridSizeX: Int, gridSizeY: Int) {
        val cardList = mutableListOf<CardModel>()
        var ind = 0
        for (y in 1 until gridSizeX + 1){
            for (x in 1 until gridSizeY + 1){
                val card = CardModel(
                    index = ind,
                    coordinateX = x,
                    coordinateY = y,
                    state = false
                )
                cardList.add(card)
                ind++
            }
        }
        _cards.value = cardList
        Log.d("LIST OF CARDS", _cards.toString())
    }

    fun pickRandomCards(numberOfRandomCards: Int) {
        /*for (cardPlace in 0 until numberOfRandomCards){
            var randomCard: Int
            do {
                randomCard = (0 until listOfCards.size).random()
            } while (listOfCards[randomCard].state)
            listOfCards[randomCard].state = true
        }*/
        val currentCards = _cards.value ?: return
        val shuffledCards = currentCards.toMutableList().apply { shuffle() }
        for (i in 0 until numberOfRandomCards) {
            shuffledCards[i].state = true
        }
        _cards.value = shuffledCards
    }

    /*fun revealCardsForDuration(cards: LiveData<MutableList<CardModel>>, duration: Long) {
        viewModelScope.launch {
            (activity as? GameplayActivity)?.showCards(cards, duration)
        }
    }*/
}