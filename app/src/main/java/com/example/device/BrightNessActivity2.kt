package com.example.device

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.example.device.databinding.ActivityBrightNess2Binding

class BrightNessActivity2 : AppCompatActivity() {
    private var birghtNessLevel = 0.0f
    private val binding by lazy { ActivityBrightNess2Binding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progres: Int, fromUser: Boolean) {
                if (fromUser){
                    birghtNessLevel = progres / 10f
                    binding.textView.text = birghtNessLevel.toString()
                    val layoutParams = window.attributes
                    layoutParams.screenBrightness = birghtNessLevel
                    window.attributes = layoutParams
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}


            override fun onStopTrackingTouch(seekBar: SeekBar?) {}


        })
    }
}