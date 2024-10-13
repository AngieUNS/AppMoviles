package com.example.s05_logindivisas


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.s05_logindivisas.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val predefinedUsername = "angie"
    private val predefinedPassword = "1234"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ViewCompat para el padding adecuado en los insets
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configuramos el botón para iniciar sesión
        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Por favor llena todos los campos", Toast.LENGTH_LONG).show()
            } else {
                // Lógica de autenticación
                if (username == predefinedUsername && password == predefinedPassword) {
                    // Notificar a la actividad que el login fue exitoso
                    (activity as MainActivity).onLoginSuccess()
                } else {
                    // Mostrar error de autenticación
                    Toast.makeText(requireContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
