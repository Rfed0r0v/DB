package com.example.realmdatabase

import android.app.Application
import com.example.realmdatabase.di.appModule
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmSet
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        val configuration = RealmConfiguration.Builder()
            .name( "todo.db")
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(0)
            .allowWritesOnUiThread(true)
            .allowQueriesOnUiThread(true)
            .build()

        Realm.setDefaultConfiguration(configuration)

        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}