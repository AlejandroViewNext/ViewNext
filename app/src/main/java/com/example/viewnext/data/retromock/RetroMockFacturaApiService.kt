package com.example.viewnext.data.retromock

import co.infinum.retromock.meta.Mock
import co.infinum.retromock.meta.MockResponse
import com.example.viewnext.data.retrofit.Facturas
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetroMockFacturaApiService {
    @Mock
    @MockResponse(body = "mock.json")
    @GET("resources")
    fun getFacturas(): Call<Facturas.ApiResponse>

    companion object {
        fun create(): RetroMockFacturaApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://viewnextandroid2.wiremockapi.cloud/facturas/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(RetroMockFacturaApiService::class.java)
        }
    }


}
