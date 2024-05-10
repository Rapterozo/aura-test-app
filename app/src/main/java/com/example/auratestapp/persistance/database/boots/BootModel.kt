package com.example.auratestapp.persistance.database.boots

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class BootModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val encounteredDate: Long,
    val handled: Boolean = false,
)