package com.example.tienda_virtual.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import androidx.fragment.app.Fragment
import com.example.clase_nativas.utils.ToastAlert
import com.example.tienda_virtual.R
import com.example.tienda_virtual.fragments.data.GestionProductos
import com.example.tienda_virtual.fragments.data.Producto
import com.example.tienda_virtual.utils.FormatNumber
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.NumberFormat
import java.util.Locale


class ProductosFragment: Fragment() {

    private lateinit var containerFragment: LinearLayout
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_productos, container, false)
        this.containerFragment = view.findViewById(R.id.containerProducts)

        val productos = GestionProductos.productos;
        sharedPreferences = requireContext().getSharedPreferences("productos_pref", MODE_PRIVATE)

        productos.forEach { producto ->
            GestionProductos.agregarProducto(requireContext(), containerFragment, producto, sharedPreferences)
        }

        return view
    }


}