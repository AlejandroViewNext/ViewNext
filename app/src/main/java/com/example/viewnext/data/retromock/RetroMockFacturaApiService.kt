package com.example.viewnext.data.retromock

import co.infinum.retromock.meta.Mock
import co.infinum.retromock.meta.MockResponse
import com.example.viewnext.data.retrofit.Facturas
import retrofit2.Call
import retrofit2.http.GET

interface RetroMockFacturaApiService {
    @Mock
    @MockResponse(body = "mock.json")
    @GET("resources")
    fun getFacturas(): Call<Facturas.ApiResponse>
}
