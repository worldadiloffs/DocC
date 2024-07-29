package uz.itschool.docc.utils

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidLogger
import uz.itschool.docc.di.chatModule
import uz.itschool.docc.di.networkModule

class ChatGptBotApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@ChatGptBotApplication)
            // Load modules
            modules(
                listOf(
                    networkModule,
                    chatModule
                )
            )
        }
    }
}