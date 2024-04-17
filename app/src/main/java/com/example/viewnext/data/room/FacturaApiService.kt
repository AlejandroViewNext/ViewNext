package com.example.viewnext.data.room

import retrofit2.Call
import retrofit2.http.GET

interface FacturaApiService {
    @GET("facturas")
    fun getFacturas(): Call<List<FacturaEntity>>
}
