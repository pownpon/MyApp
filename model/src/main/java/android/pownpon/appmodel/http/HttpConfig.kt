package android.pownpon.appmodel.http

import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import java.io.Reader
import java.util.concurrent.TimeUnit
import kotlin.reflect.full.memberProperties

object HttpConfig {
    private val okHttpClient: OkHttpClient = OkHttpClient
        .Builder()
        .callTimeout(5000L, TimeUnit.SECONDS)
        .build()

    val client
        get() = okHttpClient

    private val gson = Gson()

    fun <T> toObj(json: String, classOfT: Class<T>): T {
        return gson.fromJson(json, classOfT)
    }

    fun <T> toObj(reader: Reader, classOfT: Class<T>): T {
        return gson.fromJson(reader, classOfT)
    }

    fun toJson(obj: Any): String {
        return gson.toJson(obj)
    }

    val jsonType = "application/json; charset=utf-8".toMediaType()

    fun generateGetUrl(url: String, params: Any): String {
        val kls = params::class
        if (kls.isData) {
            val sb = StringBuilder(url).apply {
                append("?")
                kls.memberProperties.forEach {
                    val value = it.call(params)
                    if (null != value && !value.toString().isNullOrEmpty())
                        append("${it.name}=$value&")
                }
            }
            return sb.toString()
        } else if (params is Map<*, *>) {
            val sb = StringBuilder(url).apply {
                append("?")
                params.forEach {
                    if (null != it.value && !it.value.toString().isNullOrEmpty())
                        append("${it.key}=${it.value}&")
                }
            }
            return sb.toString()
        } else {
            return url
        }
    }
}