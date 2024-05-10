package com.example.auratestapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class RebootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("RebootReceiver", "onReceive $context || $intent")
        //TODO "Restore the notification with the updated information on the boot event ONLY IF the notification was present before the (re)boot."
        val bootsRepository = context?.let { BootsRepository.getInstance(context as AuraApp) } ?: return
        MainScope().launch(Dispatchers.IO) {
            bootsRepository.addBootEvent(System.currentTimeMillis())
        }
    }
}