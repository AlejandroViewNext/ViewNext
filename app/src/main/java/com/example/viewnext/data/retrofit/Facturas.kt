package com.example.viewnext.data.retrofit

import kotlinx.serialization.Serializable

class Facturas {
    @Serializable
    data class Factura(
        val descEstado: String,
        val importeOrdenacion: Double,
        val fecha: String
    )

    @Serializable
    data class ApiResponse(
        val numFacturas: Int,
        val facturas: List<Factura>
    )

}