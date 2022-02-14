package com.example.realmdatabase.di

import com.example.realmdatabase.ContactRepository
import com.example.realmdatabase.ContactRepositoryImpl
import com.example.realmdatabase.MainViewModel
import com.example.realmdatabase.Presenter
import io.realm.Realm
import io.realm.RealmConfiguration
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<Realm> {
        Realm.init(androidApplication())

        val configuration = RealmConfiguration.Builder()
            .name("todo.db")
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(0)
            .allowWritesOnUiThread(true)
            .allowQueriesOnUiThread(true)
            .build()

        Realm.setDefaultConfiguration(configuration)

        Realm.getDefaultInstance()
    }

    single<ContactRepository> {
        ContactRepositoryImpl(realm = get())
    }

    viewModel {
        MainViewModel(contactRepository = get())
    }

    single {
        Presenter(contactRepository = get())
    }
}