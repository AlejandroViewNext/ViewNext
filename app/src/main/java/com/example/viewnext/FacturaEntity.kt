package com.example.viewnext

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "facturas")
data class FacturaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val fecha: String,
    val importeOrdenacion: Double,
    val descEstado: String
)
