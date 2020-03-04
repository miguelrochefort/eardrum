package com.miguelrochefort.eardrum

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions


class MainActivity : AppCompatActivity() {

    class Constants {
        companion object {
            const val RECORDING_PERMISSIONS = 1
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView = findViewById<WebView>(R.id.webView)
        webView.loadUrl("https://github.com/miguelrochefort/eardrum/blob/master/README.md")
    }

    override fun onStart() {
        super.onStart()

        startAudioRecorderServiceWithPermissions()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    @AfterPermissionGranted(Constants.RECORDING_PERMISSIONS)
    private fun startAudioRecorderServiceWithPermissions() {
        val perms = arrayOf(
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        if (EasyPermissions.hasPermissions(this, *perms)) {
            startAudioRecorderService()
        } else {
            EasyPermissions.requestPermissions(
                this,
                getString(R.string.recording_permission_rationale),
                Constants.RECORDING_PERMISSIONS,
                *perms
            )
        }
    }

    private fun startAudioRecorderService() {
        val intent = Intent(this, AudioRecorderService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent)
        } else {
            startService(intent)
        }
    }
}
