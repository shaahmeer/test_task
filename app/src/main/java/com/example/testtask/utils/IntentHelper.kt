package com.example.testtask.utils



import android.content.Context
import android.content.Intent

object IntentHelper {


    fun startActivity(context: Context, targetActivity: Class<*>) {
        val intent = Intent(context, targetActivity)
        context.startActivity(intent)
    }


}
