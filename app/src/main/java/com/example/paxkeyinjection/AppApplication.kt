package com.example.paxkeyinjection

import android.app.Application
import okhttp3.HttpUrl
import smartpesa.sdk.network.NetworkSettings
import smartpesa.sdk.ota.OtaManager
import smartpesa.sdk.ota.OtaManagerConfig

/**
 * Created by $USER_NAME on 5/4/19.

 **/
class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val otaManagerConfig = OtaManagerConfig.Builder(applicationContext)
            .setNetworkSettings(
                NetworkSettings.Builder().url(
                    HttpUrl.Builder().host("mobey.uat.smartpesa.com").scheme(
                        "http"
                    ).build()
                ).build()
            ).build()

        OtaManager.init(otaManagerConfig)
    }
}