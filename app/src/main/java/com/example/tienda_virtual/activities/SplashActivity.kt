package com.example.tienda_virtual.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.tienda_virtual.R
import com.example.tienda_virtual.activities.HomeActivity

class SplashActivity:AppCompatActivity() {
    private val SPLASH_TIME_OUT:Long = 1500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Log.d("SplashACtivity", "onCreate: Iniciando Activity Splash")

        // Espera 2 segundos y abre MainActivity
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            finish() // Cierra SplashActivity para que no vuelva atrás
        }, SPLASH_TIME_OUT)
    }

    override fun onStart() {
        super.onStart()
        Log.d("SplashActivity", "onStart: SplashActivity está en primer plano")
    }

    //resume, pause, stop, destroy
    override fun onResume() {
        super.onResume()
        Log.d("SplashActivity", "onResume: SplashActivity está de vuelta")
    }

    override fun onPause() {
        super.onPause()
        Log.d("SplashActivity", "onPause: SplashActivity está pausada");
    }

    override fun onStop() {
        super.onStop()
        Log.d("SplashActivity", "onStop: SplashActivity está pausada");
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SplashActivity", "onDestroy: SplashActivity está destruida");
    }
}