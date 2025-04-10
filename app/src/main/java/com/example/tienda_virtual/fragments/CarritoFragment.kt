package com.example.tienda_virtual.fragments

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
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.clase_nativas.utils.ToastAlert
import com.example.tienda_virtual.R
import com.example.tienda_virtual.fragments.data.Producto
import com.example.tienda_virtual.utils.FormatNumber
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.NumberFormat
import java.util.Locale

class CarritoFragment: Fragment() {


    private lateinit var container: LinearLayout
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var totalProducts: TextView
    private lateinit var cantidadProductos: TextView

    private val gson = Gson()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_carrito, container, false)
        this.container = view.findViewById(R.id.containerProductosCarrito)

        sharedPreferences = requireContext().getSharedPreferences("productos_pref", MODE_PRIVATE)

        Log.d("ProductsFragment", sharedPreferences.getString("lista_productos", "[]") ?: "Lista vacía")

        val productos = obtenerProductos();
        val totalCantidad = productos.sumOf { it.cantidad }
        val total = productos.sumOf { it.precio * it.cantidad }
        totalProducts = view.findViewById(R.id.totalProducts)
        cantidadProductos = view.findViewById(R.id.cantidadProductos)


        totalProducts.text = "Total: ${FormatNumber.formatearPrecio(total)}"
        cantidadProductos.text = "Cantidad: ${totalCantidad}"

        productos.forEach { producto ->
            agregarProducto(producto)
        }

        return view
    }

    private fun agregarProducto(producto: Producto) {
        val inflater = LayoutInflater.from(requireContext())
        val productoView = inflater.inflate(R.layout.item_product, container, false)

        val imagen = productoView.findViewById<ImageView>(R.id.imageProduct)
        val nombre = productoView.findViewById<TextView>(R.id.titleProduct)
        val descripcion = productoView.findViewById<TextView>(R.id.descriptionProduct)
        val precio = productoView.findViewById<TextView>(R.id.valueProduct)
        val cantidad = productoView.findViewById<TextView>(R.id.cantidadProduct)
        val boton = productoView.findViewById<Button>(R.id.buttonProduct)

        imagen.setImageResource(producto.imagenResId)
        nombre.text = producto.nombre
        descripcion.text = producto.descripcion
        precio.text = FormatNumber.formatearPrecio(producto.precio)
        cantidad.text = "Cantidad: ${producto.cantidad}"
        boton.text = "Eliminar"
        boton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.rojo));
        boton.setOnClickListener {
            eliminarProducto(producto.nombre)
        }

        container.addView(productoView)
    }


    fun obtenerProductos(): List<Producto> {
        val listaJson = sharedPreferences.getString("lista_productos", "[]")
        val type = object : TypeToken<List<Producto>>() {}.type
        return gson.fromJson(listaJson, type) ?: emptyList()
    }

    private fun actualizarVista() {
        // Limpiar la vista
        container.removeAllViews()

        // Volver a obtener los productos y actualizar los totales
        val productos = obtenerProductos()
        val totalCantidad = productos.sumOf { it.cantidad }
        val total = productos.sumOf { it.precio * it.cantidad }

        totalProducts.text = "Total: ${FormatNumber.formatearPrecio(total)}"
        cantidadProductos.text = "Cantidad: ${totalCantidad}"

        // Volver a agregar los productos visibles
        productos.forEach { agregarProducto(it) }
    }


    private fun eliminarProducto(nombreProducto: String) {
        val listaJson = sharedPreferences.getString("lista_productos", "[]")
        val productos = gson.fromJson(listaJson, Array<Producto>::class.java).toMutableList()

        // Filtrar la lista eliminando el producto con el nombre dado
        val nuevaLista = productos.filter { it.nombre != nombreProducto }

        // Guardar la nueva lista actualizada
        sharedPreferences.edit().putString("lista_productos", gson.toJson(nuevaLista)).apply()
        ToastAlert.show(requireContext(), "El producto '${nombreProducto}' se ha eliminado del carrito")

        actualizarVista()
    }

}