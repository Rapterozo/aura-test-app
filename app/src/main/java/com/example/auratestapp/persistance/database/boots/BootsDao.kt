package com.example.auratestapp.persistance.database.boots

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BootsDao {
    @Query("SELECT * FROM bootmodel")
    suspend fun getBoots(): List<BootModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBootInfo(boot: BootModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBootInfos(boots: List<BootModel>)
}