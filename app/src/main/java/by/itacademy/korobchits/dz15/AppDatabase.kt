package by.itacademy.korobchits.dz15

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import by.itacademy.korobchits.dao.PoiDao
import by.itacademy.korobchits.dz9.entity.Poi

@Database(entities = [Poi::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null)
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "name")
                    .fallbackToDestructiveMigration()
                    .build()

            return instance!!
        }
    }

    abstract fun getPoiDao(): PoiDao
}