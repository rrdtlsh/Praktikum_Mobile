package com.example.tiptime

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.costOfService.setText(viewModel.costInput)
        binding.roundUpSwitch.isChecked = viewModel.roundUp

        viewModel.tipResult.observe(this) { tip ->
            binding.tipResult.text = getString(R.string.tip_amount) + ": " + tip
        }

        if (viewModel.costInput.isNotBlank()) {
            viewModel.calculateTip()
        }

        binding.calculateButton.setOnClickListener {
            val stringInTextField = binding.costOfService.text.toString()
            val cost = stringInTextField.toDoubleOrNull()

            if (cost == null || cost == 0.0) {
                binding.tipResult.text = ""
                binding.costOfService.error = "Masukkan angka yang valid dan lebih dari 0!"
                Toast.makeText(this, "Masukkan nilai yang valid dan lebih dari 0!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                binding.costOfService.error = null
            }

            val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
                R.id.option_twenty_percent -> 0.20
                R.id.option_eighteen_percent -> 0.18
                else -> 0.15
            }

            viewModel.costInput = stringInTextField
            viewModel.tipPercentage = tipPercentage
            viewModel.roundUp = binding.roundUpSwitch.isChecked

            viewModel.calculateTip()
        }
    }
}