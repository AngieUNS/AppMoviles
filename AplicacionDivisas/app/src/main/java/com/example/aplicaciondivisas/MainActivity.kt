package com.example.aplicaciondivisas

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aplicaciondivisas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurando el Spinner con el array de opciones
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.tipo_divisa,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerDivisas.adapter = adapter

        // Manejar el evento de selección del Spinner
        binding.spinnerDivisas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedOption = parent.getItemAtPosition(position).toString()
                // Aquí puedes manejar la selección del usuario
                // Por ejemplo: Si selecciona "Soles a Dólares" o "Dólares a Soles"
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // No hacer nada si no se selecciona ninguna opción
            }
        }

        // Implementar la lógica para el botón de conversión
        binding.convertir.setOnClickListener {
            val monto = binding.editTextMonto.text.toString().toDoubleOrNull()
            val resultado = when (binding.spinnerDivisas.selectedItem.toString()) {
                "Soles a Dólares" -> monto?.let { it / 3.5 } // Ejemplo de tasa de conversión
                "Dólares a Soles" -> monto?.let { it * 3.5 }
                else -> 0.0
            }
            binding.resultado.text = resultado.toString()
           }
       }
}