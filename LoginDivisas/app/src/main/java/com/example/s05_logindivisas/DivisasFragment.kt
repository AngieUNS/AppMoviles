package com.example.s05_logindivisas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.s05_logindivisas.databinding.FragmentDivisasBinding

class DivisasFragment : Fragment() {

    private var _binding: FragmentDivisasBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Infla el layout del fragmento
        _binding = FragmentDivisasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurando el Spinner con un adaptador personalizado
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.tipo_divisa,
            R.layout.spinner_item // Usar el diseño personalizado con texto blanco
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // Dropdown con estilo por defecto
        binding.spinnerDivisas.adapter = adapter

        // Manejar el evento de selección del Spinner
        binding.spinnerDivisas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // Aquí puedes manejar la selección del usuario
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
            binding.resultado.text = resultado?.toString() ?: "0.0"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Limpiar el binding para evitar fugas de memoria
    }
}
