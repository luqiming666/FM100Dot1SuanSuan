package com.example.fm100dot1suansuan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fm100dot1suansuan.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGo.setOnClickListener {
            clickButtonGo()
        }
    }

    private fun clickButtonGo() {
        binding.tvResult.text = ""

        val strNum = binding.etNumbers.text
        if (strNum.length != 5) {
            binding.tvResult.text = "请输入5位连续的数字！"
            return
        }

    }
}