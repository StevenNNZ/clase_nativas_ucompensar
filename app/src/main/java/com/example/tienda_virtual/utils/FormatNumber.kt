package com.example.tienda_virtual.utils

import java.text.NumberFormat
import java.util.Locale

class FormatNumber {

    companion object {
        fun formatearPrecio(precio: Double): String {
            val formato = NumberFormat.getCurrencyInstance(Locale("es", "CO"))
            return formato.format(precio)
        }

        fun limpiarPrecio(precioFormateado: String): Double {
            // Elimina lo que no sea dígito o punto
            val limpio = precioFormateado.replace(Regex("[^\\d.]"), "")
            return limpio.toDoubleOrNull() ?: 0.0
        }
    }
}