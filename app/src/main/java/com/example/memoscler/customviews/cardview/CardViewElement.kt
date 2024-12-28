package com.example.memoscler.customviews.cardview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import com.example.memoscler.databinding.CardViewElementBinding
import com.example.memoscler.gameplay.GameplayViewModel
import com.example.memoscler.models.CardModel

class CardViewElement @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: CardViewElementBinding =
        CardViewElementBinding.inflate(LayoutInflater.from(context), this, true)

    private lateinit var viewModel: GameplayViewModel
    private var currentData: CardModel? = null

    fun bindData(data: CardModel) {
        currentData = data
    }

    init {
        binding.root.setOnClickListener {
            currentData?.let { data ->
                handleCardPressed(data)
            }
        }
    }

    private fun handleCardPressed(data: CardModel) {
        if (data.state) {
            Log.d("Pressed in custom view element", data.index.toString())
            binding.unflipedGreyCard.visibility = GONE
            binding.flipedRightGreenCard.visibility = VISIBLE
        } else {
            Log.d("Pressed in custom view element", data.index.toString())
            binding.unflipedGreyCard.visibility = GONE
            binding.flipedWrongRedCard.visibility = VISIBLE
        }
    }


}











/*
class CustomViewAndEditElement @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: CustomViewAndEditElementBinding

    init {
        // Inflate the layout with view binding
        binding = CustomViewAndEditElementBinding.inflate(LayoutInflater.from(context), this, true)
        setupListeners()
    }


    private fun setupListeners() {
        //
        binding.customTextView.setOnClickListener {
            binding.customTextView.visibility = View.GONE
            binding.customEditText.visibility = View.VISIBLE
            binding.customEditText.setText(binding.customTextView.text.toString())
            binding.customEditText.requestFocus()
        }

        binding.customEditText.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                // Update the TextView's text and hide EditText
                //binding.customTextView.text = binding.customEditText.text.toString()
                binding.customEditText.visibility = View.GONE
                binding.customTextView.visibility = View.VISIBLE
            }
        }
    }
    fun setText(text: String) {
        binding.customTextView.text = text
    }

}
*/