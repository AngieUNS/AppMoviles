package com.example.appcalculadora

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appcalculadora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val operaciones = arrayOf("Suma", "Resta", "Multiplicación", "División")
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, operaciones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerOperaciones.adapter = adapter

        binding.btnCalcular.setOnClickListener {
            val num1 = binding.editTextNumber1.text.toString().toDoubleOrNull()
            val num2 = binding.editTextNumber2.text.toString().toDoubleOrNull()

            if (num1 != null && num2 != null) {
                val resultado = when (binding.spinnerOperaciones.selectedItem.toString()) {
                    "Suma" -> num1 + num2
                    "Resta" -> num1 - num2
                    "Multiplicación" -> num1 * num2
                    "División" -> if (num2 != 0.0) num1 / num2 else "No se puede dividir por cero"
                    else -> "Operación no válida"
                }
                binding.tvResultado.text = "Resultado: $resultado"
            } else {
                binding.tvResultado.text = "Por favor ingrese números válidos"
            }
        }
    }
}