package com.example.tienda_virtual.fragments.data

data class Producto(
    val nombre: String,
    val codigoCategoria:Int,
    val descripcion: String,
    val precio: Double,
    val cantidad: Int,
    val imagenResId: Int
)
