package com.firsttry.lleans.LatihanActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity(), View.OnClickListener {
    //create variabble
    private  lateinit var edtLength: EditText
    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var btnCalculate: Button
    private lateinit var edtResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //insert the input to the variable
        edtHeight = findViewById(R.id.tinggi_balok)
        edtLength = findViewById(R.id.panjang_balok)
        edtWidth = findViewById(R.id.lebar_balok)
        edtResult = findViewById(R.id.hasil)
        btnCalculate = findViewById(R.id.hitung)

        btnCalculate.setOnClickListener(this)

        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT) as String
            hasil.text = result
        }
    }

    override fun onClick(p0: View?) {
        if (p0?.getId()  == R.id.hitung) {
            val inputLength = edtLength.text.toString().trim()
            val inputWidth = edtWidth.text.toString().trim()
            val inputHeight = edtHeight.text.toString().trim()
            var ifempty = false
            var ifdouble = false

            if(TextUtils.isEmpty(inputLength)){
                ifempty = true
                edtLength.setError("Ini tidak boleh kosong")
            }

            if(TextUtils.isEmpty(inputWidth)){
                ifempty = true
                edtWidth.setError("Ini tidak boleh kosong")
            }
            if(TextUtils.isEmpty(inputHeight)){
                ifempty = true
                edtHeight.setError("Ini tidak boleh kosong")
            }

            val length = inputLength.toDouble()
            val width = inputWidth.toDouble()
            val height = inputHeight.toDouble()

            if (length == null){
                ifdouble = true
                edtLength.setError("Ini tidak boleh kosong")
            }

            if (width == null){
                ifdouble = true
                edtWidth.setError("Ini tidak boleh kosong")
            }

            if (height == null){
                ifdouble = true
                edtHeight.setError("Ini tidak boleh kosong")
            }

            if(ifempty == false && ifdouble == false){
                val volume = (length*width*height)
                edtResult.setText(volume.toString())
            }
        }
    }
    private fun toDouble (str: String) :Double? {
        return try{
            str.toDouble()
        } catch (e: NumberFormatException) {
            null
        }
    }
    companion object {
        private const val STATE_RESULT = "state_result"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, hasil.text.toString())
    }
}