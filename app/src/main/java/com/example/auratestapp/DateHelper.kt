package com.example.auratestapp

import java.text.SimpleDateFormat
import java.util.Date

object DateHelper {

    private val formatTime = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
    private val formatDate = SimpleDateFormat("dd/MM/yyyy")
    fun Long.stringTime(): String? = runCatching {
        formatTime.format(Date(this))
    }.getOrNull()

    fun Long.stringDate(): String? = runCatching {
        formatDate.format(Date(this))
    }.getOrNull()
}