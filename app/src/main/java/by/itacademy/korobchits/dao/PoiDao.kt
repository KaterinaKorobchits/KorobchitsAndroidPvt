package by.itacademy.korobchits.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.itacademy.korobchits.dz9.entity.POI_TABLE_NAME
import by.itacademy.korobchits.dz9.entity.Poi

@Dao
interface PoiDao {

    @Query("SELECT * FROM $POI_TABLE_NAME")
    fun get(): List<Poi>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: List<Poi>)
}