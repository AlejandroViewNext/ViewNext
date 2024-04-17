package com.example.viewnext.ui.Activity.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.viewnext.domain.retrofit.Factura
import com.example.viewnext.ui.Activity.model.FacturaRepository
import kotlinx.coroutines.*
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.launch

class FacturaViewModel(private val repository: FacturaRepository) : ViewModel() {
/*
    val facturas = MutableLiveData<List<Factura.Factura>>()
    val errorMessage = MutableLiveData<String>()

    fun loadFacturas() {
        viewModelScope.launch {
            try {
                val facturasFromApi = repository.getFacturasFromApi()
                repository.insertFacturasToDatabase(facturasFromApi)
                facturas.value = facturasFromApi
            } catch (e: Exception) {
                errorMessage.value = "Error al cargar los datos"
            }
        }
    }
    /
 */
}
