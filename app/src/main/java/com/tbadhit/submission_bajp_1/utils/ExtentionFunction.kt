package com.tbadhit.submission_bajp_1.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.io.Serializable

fun ImageView.loadImage(url: String?) {
    Glide.with(this.context)
        .load(url)
        .into(this)
}

inline fun <reified T : Activity> Context.startActivity(vararg params: Pair<String, Any?>) {
    val intent = Intent(this, T::class.java)
    if (params.isNotEmpty()) fillIntentArguments(intent, params)
    this.startActivity(intent)
}

fun fillIntentArguments(intent: Intent, params: Array<out Pair<String, Any?>>) {
    params.forEach {
        when (val value = it.second) {
            null -> intent.putExtra(it.first, null as Serializable?)
            is Int -> intent.putExtra(it.first, value)
            is String -> intent.putExtra(it.first, value)
            is Serializable -> intent.putExtra(it.first, value)
            is Bundle -> intent.putExtra(it.first, value)
            is Parcelable -> intent.putExtra(it.first, value)
            else -> throw Exception("Intent extra ${it.first} has wrong type ${value.javaClass.name}")
        }
        return@forEach
    }
}