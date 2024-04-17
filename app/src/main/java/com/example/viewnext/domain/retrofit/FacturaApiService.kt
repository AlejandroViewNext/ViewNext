package com.example.viewnext.domain.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface FacturaApiService {
    @GET("facturas")
    fun getFacturas(): Call<Factura.ApiResponse>
}
