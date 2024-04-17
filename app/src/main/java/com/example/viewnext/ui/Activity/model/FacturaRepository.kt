package com.example.viewnext.ui.Activity.model

import com.example.viewnext.domain.retrofit.Factura
import com.example.viewnext.domain.retrofit.FacturaApiService
import com.example.viewnext.domain.room.FacturaDao
import com.example.viewnext.domain.room.FacturaEntity

class FacturaRepository(private val apiService: FacturaApiService, private val facturaDao: FacturaDao) {
/*
    suspend fun getFacturasFromApi(): List<Factura.Factura> {
        return try {
            val response = apiService.getFacturas()
            if (response.isSuccessful) {
                response.body()?.facturas ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun insertFacturasToDatabase(facturas: List<Factura.Factura>) {
        facturasDao.deleteAllFacturas()
        facturas.forEach { factura ->
            facturaDao.insertFactura(
                FacturaEntity(
                    fecha = factura.fecha,
                    importeOrdenacion = factura.importeOrdenacion,
                    descEstado = factura.descEstado
                )
            )
        }
    }

 */
}