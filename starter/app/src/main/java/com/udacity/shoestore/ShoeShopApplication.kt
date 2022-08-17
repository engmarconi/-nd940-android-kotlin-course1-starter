package com.udacity.shoestore

import android.app.Application
import timber.log.Timber

public class ShoeShopApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}