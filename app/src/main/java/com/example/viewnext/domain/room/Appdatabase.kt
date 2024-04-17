package com.example.viewnext.domain.room



import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FacturaEntity::class], version = 3) // Incrementa el número de versión a 2
abstract class AppDatabase : RoomDatabase() {
    abstract fun facturaDao(): FacturaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "factura_database"
                )
                    .fallbackToDestructiveMigration() // Esto eliminará y recreará la base de datos si ocurre una migración destructiva
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
