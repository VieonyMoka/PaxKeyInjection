package com.example.paxkeyinjection

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import smartpesa.sdk.devices.SpTerminal
import smartpesa.sdk.ota.OtaManager
import smartpesa.sdk.ota.OtaUpdateListener
import smartpesa.sdk.ota.OtaUpdateType
import smartpesa.sdk.ota.error.SpOtaException
import smartpesa.sdk.scanner.TerminalScanningCallback

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName
    private var  otaManager: OtaManager ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    fun initialiseOtaManager(view: View){
        otaManager = OtaManager.get()
        otaManager?.scan(object: TerminalScanningCallback{
            override fun onDeviceListRefresh(p0: MutableCollection<SpTerminal>?) {

                Log.d(TAG, "first device  ${p0?.first()}")
                performKeyInjection(p0?.first())
            }

            override fun onScanStopped() {
            }

            override fun onBluetoothPermissionDenied(p0: Array<out String>?) {
            }

            override fun onScanTimeout() {
            }

            override fun onEnablingBluetooth(p0: String?) {
            }

        })
    }

    fun performKeyInjection(terminal: SpTerminal?){
        otaManager?.startOtaUpdate(terminal, object : OtaUpdateListener{
            override fun onOtaUpdateProgress(p0: Int) {
                Log.d(TAG, "Ota Upadte Progress $p0")
            }

            override fun onStartOtaUpdate(p0: String?) {
                Log.d(TAG, "Ota Start Update $p0")
            }

            override fun onOtaDeviceDisconnected() {
                Log.d(TAG, "Ota Device Disconnected")
            }

            override fun onOtaUpdateCompleted() {
                Log.d(TAG, "Ota Update Completed")
            }

            override fun onError(p0: SpOtaException?) {
                Log.d(TAG, "Error $p0")
            }

            override fun onOtaDeviceConnected(p0: SpTerminal?) {
                Log.d(TAG, "Ota Device Connected $p0")
            }

            override fun onPromptSelectOtaUpdateType(p0: MutableList<OtaUpdateType>?) {
                Log.d(TAG, "Prompt Select $p0")
            }
        })
    }
}
