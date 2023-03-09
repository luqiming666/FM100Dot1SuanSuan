package com.example.fm100dot1suansuan

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fm100dot1suansuan.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "FM 100.1 算、算、算了吧"
        binding.tvGameRule.text = "游戏规则：顺序使用前面4位数字进行加、减、乘、除四则运算，以使结果等于第5位数字。不考虑运算符优先级，不使用括号。"
        binding.btnGo.setOnClickListener {
            clickButtonGo()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun clickButtonGo() {
        binding.tvResult.text = ""
        binding.tvResult.setTextColor(Color.RED)

        val strNum = binding.etNumbers.text
        if (strNum.length != 5) {
            binding.tvResult.text = "请输入5位连续的数字！"
            return
        }

        // 将输入的数字由字符串转成整型
        val inputNumbers = intArrayOf(0, 0, 0, 0, 0)
        strNum.forEachIndexed { index, c ->
            inputNumbers[index] = c.code - '0'.code
        }

        var result12: Float
        var result23: Float
        var result34: Float
        for (i in 0..3) {
            result12 = stepForward(inputNumbers[0].toFloat(), inputNumbers[1].toFloat(), i)
            for (j in 0..3) {
                result23 = stepForward(result12, inputNumbers[2].toFloat(), j)
                for (k in 0..3) {
                    result34 = stepForward(result23, inputNumbers[3].toFloat(), k)
                    if (result34.toInt() == inputNumbers[4]) {
                        binding.tvResult.setTextColor(Color.GREEN)
                        binding.tvResult.text = "${inputNumbers[0]}${stepOperator(i)}" +
                                "${inputNumbers[1]}${stepOperator(j)}" +
                                "${inputNumbers[2]}${stepOperator(k)}" +
                                "${inputNumbers[3]} = ${inputNumbers[4]}"
                        return
                    }
                }
            }
        }

        binding.tvResult.text = "无解！题目出错了吧？！"
    }

    private fun stepForward(first: Float, second: Float, step: Int) : Float {
        when (step) {
            0 -> return first + second
            1 -> return first - second
            2 -> return first * second
            3 -> return first / second
        }
        return 0F
    }

    private fun stepOperator(step: Int) : String {
        return when (step) {
            0 -> " + "
            1 -> " - "
            2 -> " x "
            3 -> " ÷ "
            else -> "ERROR"
        }
    }
}