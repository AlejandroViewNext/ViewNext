package com.example.viewnext.ui.Activity.viewmodel.fragments


import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.viewnext.R
import com.example.viewnext.data.retromock.DetallesData
import com.google.gson.Gson
import java.io.InputStreamReader

class DetallesViewModel : ViewModel() {

    val detallesData: MutableLiveData<DetallesData> by lazy {
        MutableLiveData<DetallesData>()
    }

    fun loadData(context: Context) {
        val inputStream = context.resources.openRawResource(R.raw.instalacion_data)
        val reader = InputStreamReader(inputStream)
        val instalacionData = Gson().fromJson(reader, DetallesData::class.java)
        detallesData.value = instalacionData
    }
}
