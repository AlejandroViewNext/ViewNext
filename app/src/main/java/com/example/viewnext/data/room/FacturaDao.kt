package com.example.viewnext.data.room


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FacturaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFactura(factura: FacturaEntity)

    @Insert
    suspend fun insertAll(facturas: List<FacturaEntity>)

    @Query("SELECT * FROM facturas")
    suspend fun getFacturas(): List<FacturaEntity>

    @Query("DELETE FROM facturas")
    suspend fun deleteAllFacturas()
}
