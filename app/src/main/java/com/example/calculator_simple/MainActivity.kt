package com.example.calculator_simple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import java.lang.ArithmeticException


class MainActivity : AppCompatActivity() {
    private var tvResult: TextView? = null
    private var lastDigit:Boolean = false
    private var lastDot:Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvResult = findViewById(R.id.tvResultOfIO)
    }
    fun buttonClick(view:View){
        tvResult?.append((view as Button).text)
        lastDigit = true
        lastDot = false
    }
    fun buttonCLRClick(view:View){
        tvResult?.text=""
    }
    fun buttonDot(view:View){
        if(lastDigit && !lastDot){
            tvResult?.append(".")
            lastDigit = false
            lastDot = true
        }
    }
    fun buttonOperator(view:View){
        tvResult?.text?.let{
            if(lastDigit && !isOperatorAdded(it.toString())){
                tvResult?.append((view as Button).text)
                lastDot = false
                lastDigit = false
            }
        }
    }
    private fun isOperatorAdded(value : String) : Boolean{
        return if(value.startsWith("-")){
            false
        }
        else{
            value.contains("+") || value.contains("*") || value.contains("/")
        }
    }
    fun removeZeroAfterTheDot(value : String) : String{
        var result = value
        if(result.endsWith(".0")){
            result = result.substring(0,result.length-2)
        }
        return result
    }
    fun buttonEqual(view : View){
        if(lastDigit){
            var textOnScreen = tvResult?.text.toString()
            var prefix : String = ""
            try{
                if(textOnScreen.startsWith("-")){
                    prefix = "-"
                    textOnScreen = textOnScreen.substring(1)
                }
                if(textOnScreen.contains("-")) {
                    var split = textOnScreen.split("-")
                    var first = split[0]
                    var sec = split[1]

                    if (prefix.isNotEmpty()) {
                        first = prefix + first
                    }
                    val result = first.toDouble() - sec.toDouble()
                    tvResult?.text = removeZeroAfterTheDot(result.toString())
                }
                else if(textOnScreen.contains("+")) {
                    var split = textOnScreen.split("+")
                    var first = split[0]
                    var sec = split[1]

                    if (prefix.isNotEmpty()) {
                        first = prefix + first
                    }
                    val result = first.toDouble() + sec.toDouble()
                    tvResult?.text = removeZeroAfterTheDot(result.toString())
                }
                else if(textOnScreen.contains("*")) {
                    var split = textOnScreen.split("*")
                    var first = split[0]
                    var sec = split[1]

                    if (prefix.isNotEmpty()) {
                        first = prefix + first
                    }
                    val result = first.toDouble() * sec.toDouble()
                    tvResult?.text = removeZeroAfterTheDot(result.toString())
                }
                else if(textOnScreen.contains("/")) {
                    var split = textOnScreen.split("/")
                    var first = split[0]
                    var sec = split[1]

                    if (prefix.isNotEmpty()) {
                        first = prefix + first
                    }
                    val result = first.toDouble() / sec.toDouble()
                    tvResult?.text = removeZeroAfterTheDot(result.toString())
                }
            }
            catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }

}