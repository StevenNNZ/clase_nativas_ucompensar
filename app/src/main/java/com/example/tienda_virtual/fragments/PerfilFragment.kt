package com.example.tienda_virtual.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import androidx.fragment.app.Fragment
import com.example.clase_nativas.utils.ToastAlert
import com.example.tienda_virtual.R

class PerfilFragment : Fragment() {

    private lateinit var edtNombres: EditText
    private lateinit var edtApellidos: EditText
    private lateinit var edtCorreo: EditText
    private lateinit var edtTelefono: EditText
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var btnGuardarDatos: Button
    private lateinit var btnCerrarSesion: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_perfil, container, false);

        Log.d("UserProfileActivity", "onCreate: Iniciando Activity User Profile")

        //Inicializar las variables
        sharedPreferences = requireContext().getSharedPreferences("UserData", MODE_PRIVATE)
        edtNombres = view.findViewById(R.id.edtNombresPerfil)
        edtApellidos = view.findViewById(R.id.edtApellidosPerfil)
        edtCorreo = view.findViewById(R.id.edtCorreoPerfil)
        edtTelefono = view.findViewById(R.id.edtTelefonoPerfil)
        btnCerrarSesion = view.findViewById(R.id.btnCerrarSesion)
        btnGuardarDatos = view.findViewById(R.id.btnGuardarDatos)

        //Inicializamos los valores guardados
        cargarDatosUsuario()

        //Evento para cerrar sesión
        btnCerrarSesion.setOnClickListener {
            //finish()
        }

        //Evento para actualizar datos
        btnGuardarDatos.setOnClickListener {
            guardarDatosUsuario()
        }

        return view
    }


    fun cargarDatosUsuario(){
        val name = sharedPreferences.getString("name", "")
        val lastNames = sharedPreferences.getString("lastNames", "")
        val email = sharedPreferences.getString("email", "")
        val phone = sharedPreferences.getString("phone", "")

        edtNombres.setText(name)
        edtApellidos.setText(lastNames)
        edtCorreo.setText(email)
        edtTelefono.setText(phone)
    }

    private fun guardarDatosUsuario(){
        val editor = sharedPreferences.edit()
        editor.putString("name", edtNombres.text.toString().trim())
        editor.putString("lastNames", edtApellidos.text.toString().trim())
        editor.putString("email", edtCorreo.text.toString().trim())
        editor.putString("phone", edtTelefono.text.toString().trim())
        editor.apply()
        Log.d("Register Activity", "guardarDatosUsuario: Datos del usuario guardados")
        ToastAlert.show(requireContext(), "¡Datos actualizados correctamente!")
    }
}