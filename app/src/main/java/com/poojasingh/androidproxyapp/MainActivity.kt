package com.poojasingh.androidproxyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.ActivityNotFoundException

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.R.id





class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = intent
        val action = intent?.action
        val data = intent?.data

        watchYoutubeVideo("https://www.youtube.com/watch?v=_17j283asda")
        Thread.sleep(5000)
        data?.let {
            watchYoutubeVideo(it.toString()) }
    }

    fun watchYoutubeVideo(id: String) {
        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("$id"))
        val list = id.split("&")

        if (list.size <= 1) {
            startActivity(webIntent)
        } else {
            val playlist = list[1].split("=")
            val playlistId = playlist[1]
//        val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:MNJPaHRpn_k"))

            val uri = Uri.parse("https://www.youtube.com/playlist?list=$playlistId")
            val appIntent = Intent(Intent.ACTION_VIEW)
            appIntent.data = uri
            appIntent.setClassName("com.google.android.youtube", "com.google.android.youtube.app.froyo.phone.PlaylistActivity")
            try {
                startActivity(appIntent)
            } catch (ex: ActivityNotFoundException) {
                startActivity(webIntent)
            }
        }
    }
}