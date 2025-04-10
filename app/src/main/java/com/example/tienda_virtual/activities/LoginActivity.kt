package com.example.tienda_virtual.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.clase_nativas.utils.ToastAlert
import com.example.tienda_virtual.R

class LoginActivity: AppCompatActivity() {

    private lateinit var buttonRegister: Button
    private lateinit var buttonRecoveryPassword: Button
    private lateinit var buttonLogin: Button
    private lateinit var editTextEmail:EditText
    private lateinit var editTextPassword:EditText
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Log.d("LoginActivity", "onCreate: Iniciando Activity Login")

        //Inicializar las variables
        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)
        buttonRecoveryPassword = findViewById(R.id.tvForgotPasswordButton)
        buttonRegister = findViewById(R.id.tvRegisterButton)
        buttonLogin = findViewById(R.id.btnLogin)
        editTextEmail = findViewById(R.id.etUser_login)
        editTextPassword = findViewById(R.id.etPassword_login)

        // Redirigimos al formulario de recuperar contraseña
        buttonRecoveryPassword.setOnClickListener {
            val intent = Intent(this, PasswordRecoveryActivity::class.java)
            startActivity(intent) // Iniciar la segunda actividad
        }

        //Redirigimos al formulario de registro
        buttonRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent) // Iniciar la segunda actividad
        }

        //Validamos el inicio de sesión
        buttonLogin.setOnClickListener{
            if(validateLogin()){
                //redireccionamiento
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    fun validateLogin():Boolean{
        val email = editTextEmail.text.toString().trim()
        val password = editTextPassword.text.toString().trim()
        var isValid = true

        //Datos registrados
        val registeredEmail = sharedPreferences.getString("email", "")
        val registeredPassword = sharedPreferences.getString("password", "")



        // Validación de email
        if (email.isEmpty()) {
            editTextEmail.error = "El campo Email es requerido"
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.error = "Ingresa un email válido"
            isValid = false
        }

        //Validación contraseña
        if (password.isEmpty()) {
            editTextPassword.error = "El campo Contraseña es requerido"
            isValid = false
        }

        if(!isValid) {
            ToastAlert.show(
                this,
                "Hay errores en el formulario, por favor verifique cada uno para poder iniciar sesión."
            )
            return false
        }

        //Si los campos son validos, verificamos que sean iguales a los registrados
        Log.d("LoginActivity", "Correo registrado: $registeredEmail")
        Log.d("LoginActivity", "Correo ingresado: $email")
        if(!email.equals(registeredEmail)){
            ToastAlert.show(
                this,
                "El correo ingresado no se encuentra registrado"
            )
            return false
        }

        Log.d("LoginActivity", "Contraseña registrada: $registeredPassword")
        Log.d("LoginActivity", "Contraseña ingresada: $password")
        if(!password.equals(registeredPassword)){
            ToastAlert.show(
                this,
                "La contraseña ingresada no es correcta"
            )
            return false
        }

        return true
    }

    override fun onStart() {
        super.onStart()
        Log.d("LoginActivity", "onStart: LoginActivity está en primer plano")
    }

    //resume, pause, stop, destroy
    override fun onResume() {
        super.onResume()
        Log.d("LoginActivity", "onResume: LoginActivity está de vuelta")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LoginActivity", "onPause: LoginActivity está pausada");
    }

    override fun onStop() {
        super.onStop()
        Log.d("LoginActivity", "onStop: LoginActivity está pausada");
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LoginActivity", "onDestroy: LoginActivity está destruida");
    }
}