package de.techdev.pocket

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody


internal class Transport(private val client: OkHttpClient, private val mapper: ObjectMapper, private val consumer: String, private val access: String) {

    private val json: MediaType = MediaType.parse("application/json; charset=utf-8")

    inline fun <reified T: Any> post(payload: PocketRequest, endpoint: String): T {

        payload.access = access
        payload.consumer = consumer

        val body = RequestBody.create(json, mapper.writeValueAsString(payload))
        val request = Request.Builder().url(endpoint).post(body).build()
        val response = client.newCall(request).execute()

        response.validate()

        return mapper.readValue(response.body().string(), object : TypeReference<T>() {})
    }

}
