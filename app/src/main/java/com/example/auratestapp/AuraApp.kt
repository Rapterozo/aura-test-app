package com.example.auratestapp

import android.app.Application
import android.content.Intent
import androidx.room.Room
import com.example.auratestapp.persistance.database.AppDatabase

class AuraApp: Application() {

    //TODO DI
    lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()
        NotificationHelper.addMainChannel(this)
        database = Room.databaseBuilder(this, AppDatabase::class.java, DB_NAME).build()
        startService(Intent(this, ActionService::class.java))
    }

    companion object {
        const val DB_NAME = "app_database"
    }
}