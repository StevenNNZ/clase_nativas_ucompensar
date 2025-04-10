package com.example.tienda_virtual.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import androidx.fragment.app.Fragment
import com.example.tienda_virtual.R
import com.example.tienda_virtual.fragments.data.Categoria
import com.example.tienda_virtual.fragments.data.GestionProductos

class CategoriasFragment: Fragment() {

    private lateinit var container: LinearLayout
    private lateinit var sharedPreferences: SharedPreferences

    val categorias = GestionProductos.categorias


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_categorias, container, false)
        this.container = view.findViewById(R.id.containerCategorias)

        sharedPreferences = requireContext().getSharedPreferences("productos_pref", MODE_PRIVATE)

        categorias.forEach { categoria ->
            agregarCategoria(categoria)
        }

        return view
    }

    private fun agregarCategoria(categoria: Categoria) {
        val inflater = LayoutInflater.from(requireContext())
        val productoView = inflater.inflate(R.layout.item_product, container, false)

        val imagen = productoView.findViewById<ImageView>(R.id.imageProduct)
        val nombre = productoView.findViewById<TextView>(R.id.titleProduct)
        val descripcion = productoView.findViewById<TextView>(R.id.descriptionProduct)
        val cantidad = productoView.findViewById<TextView>(R.id.cantidadProduct)
        val boton = productoView.findViewById<Button>(R.id.buttonProduct)


        boton.text = getString(R.string.ver_productos)

        imagen.setImageResource(categoria.imagenResId)
        nombre.text = categoria.nombre
        descripcion.text = categoria.descripcion
        cantidad.text = "Cantidad: ${categoria.cantidad}"
        boton.setOnClickListener {
            //Guardamos la categoria elegida y la mostramos en el fragment de productos
            sharedPreferences.edit().putInt("categoria_seleccionada", categoria.codigo).apply()
            sharedPreferences.edit().putString("categoria_seleccionada_nombre", categoria.nombre).apply()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, ProductosCategoriaFragment())
                .addToBackStack("CategoriasFragment")
                .commit()

        }

        container.addView(productoView)
    }
}