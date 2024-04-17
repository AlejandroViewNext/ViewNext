package com.example.viewnext.data.retrofit

import com.example.viewnext.data.retrofit.Factura
import retrofit2.Call
import retrofit2.http.GET

interface FacturaApiService {
    @GET("facturas")
    fun getFacturas(): Call<Factura.ApiResponse>
}
