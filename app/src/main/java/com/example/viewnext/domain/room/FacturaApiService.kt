package com.example.viewnext.domain.room

import retrofit2.Call
import retrofit2.http.GET

interface FacturaApiService {
    @GET("facturas")
    fun getFacturas(): Call<List<FacturaEntity>>
}
