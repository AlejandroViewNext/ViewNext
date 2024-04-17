package com.example.viewnext.data.retrofit

class Factura {
    data class Factura(
        val fecha: String,
        val importeOrdenacion: Double,
        val descEstado: String
    )

    data class ApiResponse(
        val numFacturas: Int,
        val facturas: List<Factura>
    )

}