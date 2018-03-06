package com.solmin.sample
import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

/**
 * Created by h_sol on 2018/02/27.
 */
class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        // Fresco initialize
        Fresco.initialize(this)
    }
}