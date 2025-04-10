package com.example.tienda_virtual.fragments.data

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.clase_nativas.utils.ToastAlert
import com.example.tienda_virtual.R
import com.example.tienda_virtual.utils.FormatNumber
import com.google.gson.Gson

class GestionProductos {


    companion object {
        val gson = Gson()
        val productos = listOf(
            Producto(
                "Acetaminofem",
                1,
                "Alivia el dolor y la fiebre.",
                1300.00,
                5,
                R.drawable.acetaminofem_producto
            ),
            Producto(
                "GLUTA STACK",
                2,
                "Gluta Stack Tarro X 510Gr Frutos Rojos",
                53000.00,
                50,
                R.drawable.producto_nutricion
            ),
            Producto(
                "Winny",
                3,
                "Pañal Ultratrim Sec Etapa 4|XG Paquete X 50",
                67308.00,
                3,
                R.drawable.producto_maternidad
            ),
            Producto(
                "LA ROCHE POSAY",
                4,
                "Anthelios Uvmune 400 Spf 50+ Invisible Fluid Frasco x 50mL",
                118655.00,
                120,
                R.drawable.producto_dermocosmetico
            ),
            Producto(
                "SAVVY",
                5,
                "Proteina Veggie Powder Savvy Tarro X 630Gr Vainilla",
                156600.00,
                12,
                R.drawable.nutricion_deportiva
            ),
        )

        val categorias = listOf(
            Categoria("Analgésicos", 1,15,"Productos para aliviar el dolor y la fiebre", R.drawable.acetaminofem_producto),
            Categoria("Nutrición Deportiva", 2,10,"Suplementos para mejorar el rendimiento físico", R.drawable.producto_nutricion),
            Categoria("Cuidado del Bebé", 3,16,"Pañales y productos para el bienestar del bebé", R.drawable.producto_maternidad),
            Categoria("Dermocosmética", 4,8,"Protección y cuidado avanzado para la piel", R.drawable.producto_dermocosmetico),
            Categoria("Proteína Vegetal", 5,3,"Opciones saludables para una nutrición basada en plantas", R.drawable.nutricion_deportiva),
        )

        fun agregarProducto(
            context: Context,
            container: LinearLayout,
            producto: Producto,
            sharedPreferences: SharedPreferences
        ) {
            val inflater = LayoutInflater.from(context)
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
            boton.setOnClickListener {
                guardarProductoEnSharedPreferences(sharedPreferences, producto)
                ToastAlert.show(context, "El producto '${producto.nombre}' se ha agregado al carrito")
            }

            container.addView(productoView)
        }


        fun guardarProductoEnSharedPreferences(
            sharedPreferences: SharedPreferences,
            producto: Producto
        ) {
            val listaJson = sharedPreferences.getString("lista_productos", "[]")
            val productos = gson.fromJson(listaJson, Array<Producto>::class.java).toMutableList()

            productos.add(
                producto.copy(cantidad = 1)
            )

            sharedPreferences.edit().putString("lista_productos", gson.toJson(productos)).apply()
        }
    }
}