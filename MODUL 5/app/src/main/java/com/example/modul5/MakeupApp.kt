package com.example.modul5

import android.app.Application
import com.example.modul5.data.local.MakeupDatabase
import com.example.modul5.data.remote.RetrofitInstance
import com.example.modul5.data.repository.MakeupRepository
import com.example.modul5.util.ConnectivityObserver
import com.example.modul5.util.NetworkConnectivityObserver

class MakeupApp : Application() {
    private val database by lazy { MakeupDatabase.getDatabase(this) }
    val repository by lazy {
        MakeupRepository(
            apiService = RetrofitInstance.api,
            makeupDao = database.makeupDao()
        )
    }

    val connectivityObserver: ConnectivityObserver by lazy {
        NetworkConnectivityObserver(applicationContext)
    }
}