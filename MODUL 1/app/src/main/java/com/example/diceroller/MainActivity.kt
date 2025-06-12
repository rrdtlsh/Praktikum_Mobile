package com.example.diceroller

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import android.widget.ImageView
import com.example.diceroller.databinding.ActivityMainBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: DiceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.letRoll.setOnClickListener { roll() }

        updateDiceImage(binding.diceImage1, viewModel.diceValue1)
        updateDiceImage(binding.diceImage2, viewModel.diceValue2)
    }

    private fun roll() {
        viewModel.diceValue1 = diceroll1()
        viewModel.diceValue2 = diceroll2()

        if (viewModel.diceValue1 == viewModel.diceValue2) {
            showCustomToast("Selamat anda dapat dadu double!", Color.parseColor("#F0F0F0"), Color.BLACK)
        } else {
            showCustomToast("Anda belum beruntung!", Color.parseColor("#F0F0F0"), Color.BLACK)
        }
    }

    private fun diceroll1(): Int {
        val dice1 = Dice(6)
        val diceRoll1 = dice1.roll()

        updateDiceImage(binding.diceImage1, diceRoll1)
        return diceRoll1
    }

    private fun diceroll2(): Int {
        val dice2 = Dice(6)
        val diceRoll2 = dice2.roll()

        updateDiceImage(binding.diceImage2, diceRoll2)
        return diceRoll2
    }

    private fun updateDiceImage(imageView: ImageView, diceValue: Int) {
        val drawableResource = when (diceValue) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            6 -> R.drawable.dice_6
            else -> R.drawable.empty_dice
        }

        imageView.setImageResource(drawableResource)
        imageView.contentDescription = diceValue.toString()
    }

    private fun showCustomToast(message: String, backgroundColor: Int, textColor: Int) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
        val view = toast.view
        view?.background?.setColorFilter(backgroundColor, PorterDuff.Mode.SRC_IN)
        val text = view?.findViewById<TextView>(android.R.id.message)
        text?.setTextColor(textColor)
        toast.show()
    }
}

class Dice(private val numSides: Int) {
    fun roll(): Int = (1..numSides).random()
}

class DiceViewModel : ViewModel() {
    var diceValue1: Int = 0
    var diceValue2: Int = 0
}