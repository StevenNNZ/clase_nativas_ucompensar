package com.example.tienda_virtual.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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
    private lateinit var txtNombres: TextView
    private lateinit var txtApellidos: TextView
    private lateinit var txtCorreo: TextView
    private lateinit var txtTelefono: TextView
    private var isEditing = false

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
        txtNombres = view.findViewById(R.id.txtNombrePerfil)
        txtApellidos = view.findViewById(R.id.txtApellidoPerfil)
        txtCorreo = view.findViewById(R.id.txtCorreoPerfil)
        txtTelefono = view.findViewById(R.id.txtTelefonoPerfil)

        //ocultamos los edit text
        mostrarModoEdicion(false);


        //Inicializamos los valores guardados
        cargarDatosUsuario()

        //Evento para cerrar sesión
        btnCerrarSesion.setOnClickListener {
            //finish()
        }

        //Evento para actualizar datos
        btnGuardarDatos.setOnClickListener {
            if (!isEditing) {
                // Activar edición
                isEditing = true
                btnGuardarDatos.text =  getString(R.string.userProfileEditButton)

                mostrarModoEdicion(true)
            } else {
                // Guardar datos
                guardarDatosUsuario()
                isEditing = false
                btnGuardarDatos.text = getString(R.string.editar_datos)

                mostrarModoEdicion(false)
                cargarDatosUsuario()
            }
        }


        return view
    }

    private fun mostrarModoEdicion(editar: Boolean) {
        val visEdit = if (editar) View.VISIBLE else View.GONE
        val visTexto = if (editar) View.GONE else View.VISIBLE

        edtNombres.visibility = visEdit
        edtApellidos.visibility = visEdit
        edtCorreo.visibility = visEdit
        edtTelefono.visibility = visEdit

        txtNombres.visibility = visTexto
        txtApellidos.visibility = visTexto
        txtCorreo.visibility = visTexto
        txtTelefono.visibility = visTexto
    }



    fun cargarDatosUsuario() {
        val name = sharedPreferences.getString("name", "")
        val lastNames = sharedPreferences.getString("lastNames", "")
        val email = sharedPreferences.getString("email", "")
        val phone = sharedPreferences.getString("phone", "")

        txtNombres.text = "Nombres: $name"
        txtApellidos.text = "Apellidos: $lastNames"
        txtCorreo.text = "Correo: $email"
        txtTelefono.text = "Teléfono: $phone"

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