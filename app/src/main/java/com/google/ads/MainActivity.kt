package com.google.ads

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = intent
        val action = intent?.action
        val data = intent?.data

        watchUnavailableVideo("https://www.youtube.com/watch?v=_17j283asda")
        Thread.sleep(5000)
        Log.d("TESTING", "sleep over")
        data?.let {
            Log.d("TESTING", "calling method")
            watchYoutubeVideo(it.toString()) }
    }

    private fun watchUnavailableVideo(id: String) {
        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("$id"))
        startActivity(webIntent)
    }

    fun watchYoutubeVideo(id: String) {
        Log.d("TESTING", "i'm working - $id")
        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("$id"))

        val list = id?.split("&list=")
        Log.d("TESTING", "working list - ${list} AND ${list?.size}")

        val playlist = list[1]?.split("&")
        val playlistId = playlist[0]

        Log.d("TESTING", "playList id is - $playlistId")
        val uri = Uri.parse("https://www.youtube.com/playlist?list=$playlistId")
        val appIntent = Intent(Intent.ACTION_VIEW)
        appIntent.data = uri
        appIntent.setClassName("com.google.android.youtube", "com.google.android.youtube.app.froyo.phone.PlaylistActivity")
        try {
            startActivity(appIntent)
            finish()
        } catch (ex: ActivityNotFoundException) {
            startActivity(webIntent)
            finish()
        }
    }
}