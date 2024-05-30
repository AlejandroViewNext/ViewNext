package com.example.viewnext.ui.Activity.viewmodel.fragments


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.viewnext.data.repository.DetallesRepository
import com.example.viewnext.data.retromock.DetallesData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetallesViewModel @Inject constructor(
    private val detallesRepository: DetallesRepository
) : ViewModel() {

    private val _detallesData = MutableLiveData<DetallesData>()
    val detallesData: LiveData<DetallesData> get() = _detallesData

    fun loadData(context: Context) {
        val data = detallesRepository.getDetallesData(context)
        _detallesData.value = data
    }
}
