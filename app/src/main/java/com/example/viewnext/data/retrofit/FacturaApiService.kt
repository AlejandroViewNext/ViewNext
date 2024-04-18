package com.example.viewnext.data.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface FacturaApiService {
    @GET("facturas")
    fun getFacturas(): Call<Facturas.ApiResponse>

}
