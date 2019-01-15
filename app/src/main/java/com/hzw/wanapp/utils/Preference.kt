package com.hzw.wanapp.utils

import android.content.Context
import android.content.SharedPreferences
import com.hzw.wanapp.app.App
import com.orhanobut.logger.Logger
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.reflect.KProperty

@Suppress("UNCHECKED_CAST", "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class Preference<T>(private val name: String, private val default: T) {

    companion object {
        private const val fileName = "WanApp"
        private val prefs: SharedPreferences by lazy {
            App.context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        }

        fun clearPreference() {
            prefs.edit().clear().apply()
        }

        fun clearPreference(key: String) {
            prefs.edit().remove(key).apply()
        }

        fun contains(key: String): Boolean {
            return prefs.contains(key)
        }

        fun getAll(): Map<String, *> {
            return prefs.all
        }
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return getSharedPreferences(name, default)
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        pushSharedPreferences(name, value)
    }

    private fun pushSharedPreferences(name: String, value: T) = with(prefs.edit()) {
        when (value) {
            is Int -> putInt(name, value)
            is Long -> putLong(name, value)
            is Float -> putFloat(name, value)
            is Boolean -> putBoolean(name, value)
            is String -> putString(name, value)
            else -> putString(name, serialize(value))
        }.apply()
    }

    private fun getSharedPreferences(name: String, default: T): T = with(prefs) {
        val res: Any = when (default) {
            is Int -> getInt(name, default)
            is Long -> getLong(name, default)
            is Float -> getFloat(name, default)
            is Boolean -> getBoolean(name, default)
            is String -> getString(name, default)
            else -> deSerialization(getString(name, serialize(default)))
        }
        res as T
    }

    private fun <A> deSerialization(string: String): A {
        val redStr = URLDecoder.decode(string, "UTF-8")
        val byteArrayInputStream = ByteArrayInputStream(redStr.toByteArray(charset("ISO-8859-1")))
        val objectInputStream = ObjectInputStream(byteArrayInputStream)
        val obj = objectInputStream.readObject() as A
        objectInputStream.close()
        byteArrayInputStream.close()
        return obj
    }

    private fun <A> serialize(obj: A): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        val objectOutputStream = ObjectOutputStream(byteArrayOutputStream)
        objectOutputStream.writeObject(obj)
        var serStr = byteArrayOutputStream.toString("ISO-8859-1")
        Logger.i("serStr = $serStr")
        serStr = URLEncoder.encode(serStr, "UTF-8")
        objectOutputStream.close()
        byteArrayOutputStream.close()
        return serStr
    }


}