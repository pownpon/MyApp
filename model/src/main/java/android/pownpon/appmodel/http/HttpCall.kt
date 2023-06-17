package android.pownpon.appmodel.http

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import okhttp3.FormBody
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okio.BufferedSource
import okio.ByteString
import java.io.File
import java.io.InputStream
import java.io.Reader

fun <T> get(url: String, classOfT: Class<T>): Flow<T> {
    val request = Request.Builder()
        .url(url)
        .get()
        .build()
    return call(request, classOfT)
}

fun <T> post(url: String, body: Any, classOfT: Class<T>): Flow<T> {
    val body = when (body) {
        is ByteString -> body.toRequestBody()
        is ByteArray -> body.toRequestBody()
        is File -> body.asRequestBody()
        else -> HttpConfig.toJson(body).toRequestBody(HttpConfig.jsonType)
    }
    val request = Request.Builder()
        .url(url)
        .post(body)
        .build()
    return call(request, classOfT)
}

fun <T> form(url: String, params: Map<String, String>, classOfT: Class<T>): Flow<T> {
    val body = FormBody.Builder().run {
        params.forEach {
            add(it.key, it.value)
        }
        build()
    }
    val request = Request.Builder()
        .url(url)
        .post(body)
        .build()
    return call(request, classOfT)
}

fun <T> multipart(url: String, params: Map<String, Any>, classOfT: Class<T>): Flow<T> {
    val body = MultipartBody.Builder().run {
        setType(MultipartBody.FORM)
        params.forEach {
            when (val value = it.value) {
                is String -> addFormDataPart(it.key, value)
                is ByteString -> addFormDataPart(it.key, null, value.toRequestBody())
                is ByteArray -> addFormDataPart(it.key, null, value.toRequestBody())
                is File -> addFormDataPart(it.key, value.name, value.asRequestBody())
                else -> addPart(HttpConfig.toJson(value).toRequestBody())
            }
        }
        build()
    }
    val request = Request.Builder()
        .url(url)
        .post(body)
        .build()
    return call(request, classOfT)
}


private fun <T> call(request: Request, classOfT: Class<T>): Flow<T> {
    return flow {
        //发送请求
        val response = HttpConfig.client.newCall(request).execute()
        emit(response)
    }.map {
        //处理请求返回的结果
        if (it.isSuccessful) {
            it.body ?: throw HttpException("没有返回数据")
        } else {
            throw HttpException("请求失败")
        }
    }.map {
        //转换为所需要的数据
        when (classOfT.name) {
            String::class.java.name -> it.string() as T
            Reader::class.java.name -> it.charStream() as T
            ByteArray::class.java.name -> it.bytes() as T
            InputStream::class.java.name -> it.byteStream() as T
            ByteString::class.java.name -> it.byteString() as T
            BufferedSource::class.java.name -> it.source() as T
            else -> {
                HttpConfig.toObj(it.charStream(), classOfT)
            }
        }
    }.flowOn(Dispatchers.IO)
}
