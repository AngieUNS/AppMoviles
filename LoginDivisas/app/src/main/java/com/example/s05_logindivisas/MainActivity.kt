package com.example.s05_logindivisas


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.s05_logindivisas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Cargar el fragmento de login como el fragmento inicial
        if (savedInstanceState == null) {
            loadFragment(LoginFragment())
        }
    }

    // Función para cargar fragmentos
    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null) // Para permitir volver atrás
        transaction.commit()
    }

    // Esta función será llamada cuando se haga un login exitoso
    fun onLoginSuccess() {
        loadFragment(DivisasFragment())
    }
}
