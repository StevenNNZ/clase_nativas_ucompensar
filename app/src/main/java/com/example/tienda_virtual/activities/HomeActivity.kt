package com.example.tienda_virtual.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tienda_virtual.R

class HomeActivity : AppCompatActivity() {
    private lateinit var buttonLogin: Button
    private lateinit var buttonRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        Log.d("HomeActivity", "onCreate: Iniciando Activity Home")

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializamos variables
        buttonLogin = findViewById(R.id.start_button)
        buttonRegister = findViewById(R.id.register_button)

        // Redirigimos al formulario de login
        buttonLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent) // Iniciar la segunda actividad
        }

        //Redirigimos al formulario de registro
        buttonRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent) // Iniciar la segunda actividad
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("HomeActivity", "onStart: HomeActivity está en primer plano")
    }

    //resume, pause, stop, destroy
    override fun onResume() {
        super.onResume()
        Log.d("HomeActivity", "onResume: HomeActivity está de vuelta")
    }

    override fun onPause() {
        super.onPause()
        Log.d("HomeActivity", "onPause: HomeActivity está pausada");
    }

    override fun onStop() {
        super.onStop()
        Log.d("HomeActivity", "onStop: HomeActivity está pausada");
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("HomeActivity", "onDestroy: HomeActivity está destruida");
    }
}