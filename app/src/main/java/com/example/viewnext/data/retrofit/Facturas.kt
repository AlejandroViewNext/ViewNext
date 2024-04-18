package com.example.viewnext.data.retrofit

class Facturas {
    data class Factura(
        val descEstado: String,
        val importeOrdenacion: Double,
        val fecha: String
    )

    data class ApiResponse(
        val numFacturas: Int,
        val facturas: List<Factura>
    )

}