package com.example.clase_nativas

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class UserProfileActivity: AppCompatActivity() {

    private lateinit var textViewFullName: TextView
    private lateinit var textViewEmail: TextView
    private lateinit var textViewPhone: TextView
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var buttonLogout: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        Log.d("UserProfileActivity", "onCreate: Iniciando Activity User Profile")

        //Inicializar las variables
        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)
        textViewFullName = findViewById(R.id.tvFullName)
        textViewEmail = findViewById(R.id.tvEmail)
        textViewPhone = findViewById(R.id.tvPhone)
        buttonLogout = findViewById(R.id.btnLogout)


        //Inicializamos los valores guardados
        initData()

        //Evento para cerrar sesión
        buttonLogout.setOnClickListener(){
            finish()
        }

    }

    fun initData(){
        val name = sharedPreferences.getString("name", "")
        val lastNames = sharedPreferences.getString("lastNames", "")
        val email = sharedPreferences.getString("email", "")
        val phone = sharedPreferences.getString("phone", "")

        textViewFullName.text = getString(R.string.full_name_format, name, lastNames)
        textViewEmail.text = email
        textViewPhone.text = phone
    }

    override fun onStart() {
        super.onStart()
        Log.d("UserProfileActivity", "onStart: UserProfileActivity está en primer plano")
    }

    //resume, pause, stop, destroy
    override fun onResume() {
        super.onResume()
        Log.d("UserProfileActivity", "onResume: UserProfileActivity está de vuelta")
    }

    override fun onPause() {
        super.onPause()
        Log.d("UserProfileActivity", "onPause: UserProfileActivity está pausada");
    }

    override fun onStop() {
        super.onStop()
        Log.d("UserProfileActivity", "onStop: UserProfileActivity está pausada");
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("UserProfileActivity", "onDestroy: UserProfileActivity está destruida");
    }
}