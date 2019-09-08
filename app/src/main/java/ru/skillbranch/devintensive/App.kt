package ru.skillbranch.devintensive

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import ru.skillbranch.devintensive.repositories.PreferencesRepository

class App : Application() {
    companion object {
        private var instance: App? = null
        fun applicationContext() = instance!!.applicationContext
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        PreferencesRepository.getAppTheme().also {
            AppCompatDelegate.setDefaultNightMode(it)
//            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        }
    }
}
