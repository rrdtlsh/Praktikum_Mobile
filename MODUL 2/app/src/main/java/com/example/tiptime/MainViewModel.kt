package com.example.tiptime

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import kotlin.math.ceil

class MainViewModel : ViewModel() {
    private val _tipResult = MutableLiveData<String>()
    val tipResult: LiveData<String> = _tipResult

    var costInput: String = ""
    var tipPercentage: Double = 0.15
    var roundUp: Boolean = false

    fun calculateTip() {
        val cost = costInput.toDoubleOrNull()
        if (cost == null) {
            _tipResult.value = ""
            return
        }

        var tip = tipPercentage * cost
        if (roundUp) {
            tip = ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        _tipResult.value = formattedTip
    }
}