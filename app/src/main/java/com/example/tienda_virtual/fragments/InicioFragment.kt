package com.example.tienda_virtual.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tienda_virtual.R

class InicioFragment: Fragment() {

    private lateinit var btnProductos: Button;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_inicio, container, false);
        btnProductos = view.findViewById(R.id.btn_productos);

        btnProductos.setOnClickListener{
            findNavController().navigate(R.id.productosFragment)
        }

        return view
    }
}