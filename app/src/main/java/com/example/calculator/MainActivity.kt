package com.example.calculator

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {


    private lateinit var resultTV: TextView
    private lateinit var resultTV2: TextView
    private lateinit var buttonOne: Button
    private lateinit var buttonTwo: Button
    private lateinit var buttonThree: Button
    private lateinit var buttonFour: Button
    private lateinit var buttonFive: Button
    private lateinit var buttonSix: Button
    private lateinit var buttonSeven: Button
    private lateinit var buttonEight: Button
    private lateinit var buttonNine: Button
    private lateinit var buttonplus: Button
    private lateinit var buttoneql: Button
    private lateinit var buttonC: Button
    private lateinit var buttonNeg: Button
    private lateinit var buttonDiv: Button
    private lateinit var buttonMul: Button
    private lateinit var buttonDot: Button
    private lateinit var buttonZero: Button
    private lateinit var buttonZero0: Button
    private lateinit var buttonDel: Button


    private var val1 = 0.00
    private var val2 = 0.00
    private var temp: String = ""
    private var opSign: String = ""
    var currentText: String = "" //will be used for value one as well as value 2
    var currentText2: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        resultTV = findViewById(R.id.editText)
        resultTV2 = findViewById(R.id.editText2)
        buttonOne = findViewById(R.id.btn_1)
        buttonTwo = findViewById(R.id.btn_2)
        buttonThree = findViewById(R.id.btn_3)
        buttonFour = findViewById(R.id.btn_4)
        buttonFive = findViewById(R.id.btn_5)
        buttonSix = findViewById(R.id.btn_6)
        buttonSeven = findViewById(R.id.btn_7)
        buttonEight = findViewById(R.id.btn_8)
        buttonNine = findViewById(R.id.btn_9)
        buttonplus = findViewById(R.id.btn_pls)
        buttoneql = findViewById(R.id.btn_equl)
        buttonC = findViewById(R.id.btn_C)
        buttonNeg = findViewById(R.id.btn_neg)
        buttonDiv = findViewById(R.id.btn_div)
        buttonMul = findViewById(R.id.btn_mul)
        buttonDot = findViewById(R.id.btn_dot)
        buttonZero = findViewById(R.id.btn_0)
        buttonZero0 = findViewById(R.id.btn_00)
        buttonDel = findViewById(R.id.btn_del)

        buttonOne.setOnClickListener() {
            concatAndValidate("1")

        }
        buttonTwo.setOnClickListener() {
            concatAndValidate("2")
        }
        buttonThree.setOnClickListener() {
            concatAndValidate("3")
        }

        buttonFour.setOnClickListener() {
            concatAndValidate("4")
        }
        buttonFive.setOnClickListener() {
            concatAndValidate("5")
        }
        buttonSix.setOnClickListener() {
            concatAndValidate("6")
        }
        buttonSeven.setOnClickListener() {
            concatAndValidate("7")
        }
        buttonEight.setOnClickListener() {
            concatAndValidate("8")
        }
        buttonNine.setOnClickListener() {
            concatAndValidate("9")
        }
        buttonZero.setOnClickListener()
        {
            concatAndValidate("0")
        }
        buttonDot.setOnClickListener() {
            //To stop the user from adding two decimals together and killing the app
            var count1 = currentText.count { ".".contains(it) }
            var count2 = currentText.count { "..".contains(it) }

            if (count1 < 2 && count2 == 0) {
                concatAndValidate(".")
            } else {
                return@setOnClickListener
            }

        }
        buttonZero0.setOnClickListener() {
            concatAndValidate("00")
        }

        buttonC.setOnClickListener() {
            emptyAll()

        }

        buttonplus.setOnClickListener() {

            operations("+")
        }
        buttonNeg.setOnClickListener() {

            operations("-")
        }
        buttonDiv.setOnClickListener() {
            operations("/")
        }
        buttonMul.setOnClickListener() {
            operations("*")
        }
        buttonDel.setOnClickListener() {

            // var x =  currentText.toString().toInt()
            /// x=x/10
            // currentText = x.toString()
            //  (temp+currentText).also { resultTV.text = it }
            //  resultTV.text =  temp+currentText
           if (opSign.toString().isEmpty())
           {
               currentText = currentText.toString().dropLast(1)
               resultTV.text = currentText.toString()
           }
            else
           {
               currentText2 = currentText2.toString().dropLast(1)
               resultTV.text =  currentText+ opSign+ currentText2.toString()
           }


        }
        buttoneql.setOnClickListener() {
            if (currentText.toString().isEmpty())
            {
                //so App doesn't crash when it cant compute empty values
                Toast.makeText(this, "Enter a value", Toast.LENGTH_SHORT).show()
                currentText = ""
            }
            else
            {
                val1 = currentText.toString().toDouble()
                val2 = currentText2.toString().toDouble()
                var result = 0.00
                if (opSign == "+") {
                    result = val1 + val2
                } else if (opSign == "-") {
                    result = val1 - val2
                } else if (opSign == "/") {
                    result = val1 / val2
                } else if (opSign == "*") {
                    result = val1 * val2
                } else {
                    //so whatever value is entered gets shown in result tab. if only one value was added val2 remains 0
                    result = val1 + val2
                }

                resultTV2.text = "= ${result}"
                currentText = ""
                temp = ""
            }
        }

    }

    private fun concatAndValidate(input: String) {
        val len: Int = currentText.length
        //Just so things remain tidy
        if (len > 12)
        {
            Toast.makeText(this, "Input Limit Exceeded", Toast.LENGTH_SHORT).show()
        }
        else
        {
           if(opSign.toString().isEmpty())
           {
               currentText += input
               resultTV.text =  currentText+currentText2
           }
            else
            {
               currentText2 += input
               resultTV.text =  currentText+opSign+currentText2
           }
        }

    }

    private fun operations(inputOP: String) {

        if (currentText =="") {
            //so App doesn't crash when it cant compute empty values
            Toast.makeText(this, "Enter a value", Toast.LENGTH_SHORT).show()
        }
        else{
          opSign = inputOP
          resultTV.text = currentText .toString()+ opSign.toString()
            currentText2="" //If the user changes the operator midway
      }

       }
    private fun emptyAll()
    {
        currentText = ""
        currentText2=""
        temp = ""
        resultTV.text = ""
        resultTV2.text = ""
        val1 = 0.0
        val2 = 0.0
        opSign=""
    }


    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("Tv1", resultTV.text.toString())
        outState.putString("Tv2", resultTV2.text.toString())

        super.onSaveInstanceState(outState)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        resultTV.text = savedInstanceState.getString("Tv1")
        resultTV2.text = savedInstanceState.getString("Tv2")

    }

}
