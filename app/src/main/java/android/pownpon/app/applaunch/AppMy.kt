package android.pownpon.app.applaunch

import android.app.Application

class AppMy : Application() {

    companion object {
        private lateinit var app: AppMy
        fun getApp(): AppMy {
            return app;
        }
    }

    override fun onCreate() {

        app = this@AppMy

        super.onCreate()
    }
}