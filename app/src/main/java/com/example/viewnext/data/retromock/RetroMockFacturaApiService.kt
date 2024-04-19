package com.example.viewnext.data.retromock

import co.infinum.retromock.meta.Mock
import co.infinum.retromock.meta.MockResponse
import com.example.viewnext.data.retrofit.Facturas
import retrofit2.Call
import retrofit2.http.GET

interface RetroMockFacturaApiService {
    @Mock
    @MockResponse(body = """
        {
            "numFacturas": 8,
            "facturas": [
                {
                    "descEstado": "Pendiente de pago",
                    "importeOrdenacion": 1.56,
                    "fecha": "01/07/2000"
                },
                {
                    "descEstado": "Pagada",
                    "importeOrdenacion": 25.14,
                    "fecha": "01/07/2000"
                },
                {
                    "descEstado": "Pagada",
                    "importeOrdenacion": 22.69,
                    "fecha": "01/07/2000"
                },
                {
                    "descEstado": "Pendiente de pago",
                    "importeOrdenacion": 12.84,
                    "fecha": "01/07/2000"
                },
                {
                    "descEstado": "Pagada",
                    "importeOrdenacion": 35.16,
                    "fecha": "01/07/2000"
                },
                {
                    "descEstado": "Pagada",
                    "importeOrdenacion": 18.27,
                    "fecha": "01/07/2000"
                },
                {
                    "descEstado": "Pendiente de pago",
                    "importeOrdenacion": 61.17,
                    "fecha": "01/07/2000"
                },
                {
                    "descEstado": "Pagada",
                    "importeOrdenacion": 37.18,
                    "fecha": "01/07/2000"
                }
            ]
        }
    """)
    @GET("/")
    fun getFacturas(): Call<Facturas.ApiResponse> // Se puede utilizar cualquier tipo de respuesta que necesites aquí
}
