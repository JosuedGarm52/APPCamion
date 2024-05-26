package com.example.camionapi.utils

import android.content.Context
import android.widget.Toast

object MensajeRapido {

    private var appContext: Context? = null // Store the application context

    fun initialize(context: Context) {
        appContext = context.applicationContext
    }

    fun mostrar(mensaje: String) {
        appContext?.let { context ->
            val toast = Toast.makeText(context, mensaje, Toast.LENGTH_SHORT)
            toast.show()
        }
    }
}