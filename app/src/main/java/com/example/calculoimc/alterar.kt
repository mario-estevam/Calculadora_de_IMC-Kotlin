package com.example.calculoimc

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.text.parseAsHtml
import androidx.databinding.DataBindingUtil
import com.example.calculoimc.databinding.ActivityAlterarBinding
import com.example.calculoimc.databinding.ActivityMainBinding


class alterar : AppCompatActivity() {

    lateinit var binding : ActivityAlterarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alterar)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_alterar)

        val param = intent.extras
        val texto = param?.getString("FLAG")

        if(texto.toString() == "peso"){
            val editP = param?.getString("Alterar_peso")
            binding.recebe.setText("peso")
            if(editP.toString()=="0,0"){
                binding.digiteNumero.setText("")
            }else{
                binding.digiteNumero.setText(editP)
            }

        }else{
            val editA = param?.getString("Alterar_altura")
            binding.recebe.setText("altura")
            if(editA.toString()=="0,0"){
                binding.digiteNumero.setText("")
            }else{
                binding.digiteNumero.setText(editA)
            }


        }

        binding.alterar.setOnClickListener{

            if(texto.toString() == "peso"){
                val intent = Intent()
                val parametro = Bundle()
                parametro.putString("AlteradoP",binding.digiteNumero.text.toString())
                parametro.putString("UP","peso")
                intent.putExtras(parametro)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }else{
                val intent = Intent()
                val parametro = Bundle()
                parametro.putString("AlteradoA",binding.digiteNumero.text.toString())
                parametro.putString("UP","altura")
                intent.putExtras(parametro)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }


        }



    }

    fun doFechar(v: View){
        val intent = Intent()
        intent.putExtra("dado","deu certo")
        setResult(Activity.RESULT_CANCELED, intent)
        finish()
    }

}