package com.example.viewnext.data.repository

import android.content.Context
import com.example.viewnext.R
import com.example.viewnext.data.retromock.DetallesData
import com.google.gson.Gson
import java.io.InputStreamReader
import javax.inject.Inject

class DetallesRepository @Inject constructor() {

    fun getDetallesData(context: Context): DetallesData {
        val inputStream = context.resources.openRawResource(R.raw.instalacion_data)
        val reader = InputStreamReader(inputStream)
        return Gson().fromJson(reader, DetallesData::class.java)
    }
}
