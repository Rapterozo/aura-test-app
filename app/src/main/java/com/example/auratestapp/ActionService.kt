package com.example.auratestapp

import android.app.Service
import android.content.Intent
import android.os.IBinder
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class ActionService : Service() {

    private var job: Job? = null
    private val bootsRepository by lazy { BootsRepository.getInstance(application as AuraApp) }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        job?.cancel()
        job = MainScope().launch {
            if (isActive) {
                //todo configure delay "It should be rescheduled based on the configuration and shouldn’t take into account the 15 minutes rule."
                delay(DELAY_15)
                runAction(intent, flags, startId)
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private suspend fun runAction(intent: Intent?, flags: Int, startId: Int) {
        val bootType = bootsRepository.getBootInfo()
        NotificationHelper.publish(this, bootType)
        //TODO rethink it
        onStartCommand(intent, flags, startId)
    }

    companion object {
        const val DELAY_15 = 15 * 60 * 1000L
    }

}