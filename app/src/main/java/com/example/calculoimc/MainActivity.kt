package com.example.calculoimc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import com.example.calculoimc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        val activityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            when(it.resultCode){
                RESULT_OK->{
                    val param = it.data?.extras

                    val chegou = param?.getString("UP")
                    if(chegou.toString() == "peso"){
                        val texto = param?.getString("AlteradoP")
                        binding.peso.setText(texto)
                        val recebe:String = texto.toString()
                        guardarValor(recebe)
                    }else{
                        val altura = param?.getString("AlteradoA")
                        binding.altura.setText(altura)

                    }
                }
                RESULT_CANCELED ->{
                    Toast.makeText(this, "cancelou", Toast.LENGTH_SHORT).show()
                }

            }
        }


        binding.alterarPeso.setOnClickListener{

            val intent = Intent(this, alterar::class.java)
            val parametro = Bundle()
            parametro.putString("Alterar_peso",binding.peso.text.toString())
            parametro.putString("FLAG", "peso")
            intent.putExtras(parametro)
            activityResult.launch(intent)

        }

        binding.alterarAltura.setOnClickListener{

            val intent = Intent(this, alterar::class.java)
            val parametro = Bundle()
            parametro.putString("Alterar_altura",binding.altura.text.toString())
            parametro.putString("FLAG", "altura")
            intent.putExtras(parametro)
            activityResult.launch(intent)

        }

        binding.calcular.setOnClickListener {
           val x:String= binding.peso.text.toString()
            val y:String =  binding.altura.text.toString()
            val alt:Double = y.toDouble()/100
            val alturaReal:Double = alt*alt
            val imc:Double = x.toDouble()/alturaReal
            binding.resultado.text = imc.toString()
            if(imc<16){
                  binding.aperte.text = "Magreza Grave"
            }else if(imc<=16 || imc<17){
                binding.aperte.text = "Magreza moderada"
            }else if(imc<=17 || imc<18.5){
                binding.aperte.text = "Magreza leve"
            }else if(imc<=18.5 || imc<25){
                binding.aperte.text = "Saúdavel"
            }else if(imc<=25 || imc<30){
                binding.aperte.text = "Sobrepeso"
            }else if(imc<=30 || imc<35){
                binding.aperte.text = "Obesidade grau 1"
            }else if(imc<=35 || imc<40){
                binding.aperte.text = "Obesidade grau 2, severa"
            }else if( imc>40){
                binding.aperte.text = "Obesidade grau 3, mórbida"
            }
            Toast.makeText(this, imc.toString(), Toast.LENGTH_SHORT).show()
        }

    }

        fun guardarValor(recebe: String) {
                val guardou:String = recebe
                Log.e("guardou?", guardou)
                binding.peso.setText(guardou)

        }








    override fun onStart() {
        super.onStart()
        Log.i("fun", "onstart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("fun", "onresume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("fun", "onpause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("fun", "onstop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("fun", "onrestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("fun", "ondestroy")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("fun", "onRestore")
        binding.resultado.text = savedInstanceState.getString("valor Resultado")
        binding.aperte.text = savedInstanceState.getString("valor aperte")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i("fun", "onSave")
        outState.putString("valor Resultado", binding.resultado.text.toString())
        outState.putString("valor aperte", binding.aperte.text.toString())

    }

}
