package com.example.auratestapp.persistance.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.auratestapp.persistance.database.boots.BootModel
import com.example.auratestapp.persistance.database.boots.BootsDao

@Database(entities = [BootModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bootsDao(): BootsDao
}