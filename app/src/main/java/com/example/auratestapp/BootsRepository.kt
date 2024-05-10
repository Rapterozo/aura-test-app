package com.example.auratestapp

import com.example.auratestapp.DateHelper.stringDate
import com.example.auratestapp.DateHelper.stringTime
import com.example.auratestapp.persistance.database.AppDatabase
import com.example.auratestapp.persistance.database.boots.BootModel

class BootsRepository private constructor(private val appDatabase: AppDatabase) {

    var dismissCounter = 0

    suspend fun addBootEvent(time: Long) = appDatabase.bootsDao().addBootInfo(BootModel(encounteredDate = time))

    suspend fun getBootInfo(): Boot {
        val unhandled = appDatabase.bootsDao().getBoots()
        val size = unhandled.size
        return when (unhandled.size) {
            0 -> Boot.None
            1 -> Boot.One(unhandled.first().encounteredDate.stringTime() ?: "Error parse time")
            else -> {
                val lastTime = unhandled[size - 1].encounteredDate
                val priorLastTime = unhandled[size - 2].encounteredDate
                Boot.Multiple(lastTime - priorLastTime)
            }
        }
    }

    //TODO refactor to Flow
    suspend fun getBootsCountByDay(): List<Pair<String, Int>> {
        val boots = appDatabase.bootsDao().getBoots()
        val groups = boots.groupBy { it.encounteredDate.stringDate() }.filter { it.key != null }
        val map = groups.entries.associate {
            it.key!! to it.value.size
        }
        return map.toList()
    }

    companion object {
        private var instance: BootsRepository? = null

        fun getInstance(app: AuraApp) = if (instance == null) {
            BootsRepository(app.database)
        } else instance!!

    }
}