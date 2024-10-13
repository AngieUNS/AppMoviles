package com.example.proyecto4

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Toast
import com.example.proyecto4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // 1. Declaramos la variable binding
    private lateinit var binding: ActivityMainBinding

    private val predefinedUsername = "angie"
    private val predefinedPassword = "1234"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 2. Inicializamos Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ViewCompat para el padding adecuado en los insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 3. Configuramos el botón para iniciar sesión
        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor llena todos los campos", Toast.LENGTH_LONG).show()
            } else {
                // 4. Lógica de Autenticación
                if (username == predefinedUsername && password == predefinedPassword) {
                    // Si las credenciales coinciden, continuamos a la siguiente actividad
                    val intent = Intent(this, SecondActivity::class.java)
                    intent.putExtra("username", username)

                    startActivity(intent)
                } else {
                    // Si las credenciales no coinciden, mostramos un error
                    Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
