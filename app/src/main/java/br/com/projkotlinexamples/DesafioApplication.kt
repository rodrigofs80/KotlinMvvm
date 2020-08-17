package br.com.projkotlinexamples

import android.app.Application
import br.com.projkotlinexamples.di.myModule
import com.facebook.stetho.Stetho
import org.koin.android.ext.android.startKoin

class DesafioApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, myModule)
        Stetho.initializeWithDefaults(this)
    }
}