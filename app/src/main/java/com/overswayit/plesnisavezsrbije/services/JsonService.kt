package com.overswayit.plesnisavezsrbije.services

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Array
import java.util.*

/**
 * Created by lazarristic on 2019-07-11.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
object JsonService {

    private val gson: Gson
    private val parser: JsonParser

    init {
        gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()

        parser = JsonParser()
    }

    fun <T> toJson(`object`: T): String {
        return gson.toJson(`object`)
    }

    fun <T> fromJson(jsonString: String?, classy: Class<T>): T? {
        if (jsonString == null) {
            return null
        }

        val je = parser.parse(jsonString)
        return fromJson(je, classy)
    }

    fun <T> fromJson(je: JsonElement, classy: Class<T>): T {
        return gson.fromJson(je, classy)
    }

    fun <T> arrayListFromJson(jsonString: String, elemClass: Class<T>): ArrayList<T> {
        return arrayListFromJson(parser.parse(jsonString), elemClass)
    }

    fun <T> arrayListFromJson(je: JsonElement, elemClass: Class<T>): ArrayList<T> {
        val ts = fromJson(je, Array.newInstance(elemClass, 0).javaClass) as kotlin.Array<T>
        return ArrayList(listOf(*ts))
    }

    fun fromMap(map: Map<String, Any>): String {
        return gson.toJson(map)
    }

    fun getProperty(jsonString: String, propertyName: String): String? {
        val je = parser.parse(jsonString)

        if (je.isJsonObject) {
            val obj = (je as JsonObject).get(propertyName)

            if (obj != null) {
                return obj.asString
            }
        }

        return null
    }

    fun optProperty(jsonString: String, propertyName: String): String? {
        try {
            return getProperty(jsonString, propertyName)
        } catch (e: Exception) {
            // ignore
        }

        return null
    }

    fun parseJson(jsonString: String): JsonElement? {
        try {
            return parser.parse(jsonString)
        } catch (e: Exception) {
            // ignore
        }

        return null
    }

    fun getProperty(jsonObject: JsonObject, vararg properties: String): JsonElement? {
        var result: JsonElement? = jsonObject

        try {

            for (p in properties) {
                result = (result as JsonObject).get(p)
            }

        } catch (e: Exception) {
            result = null
        }

        return result
    }

    fun parseAsStringToObjectHashMap(element: JsonElement): HashMap<String, Any>? {
        val type = object : TypeToken<HashMap<String, Any>>() {

        }.type
        return gson.fromJson<HashMap<String, Any>>(element, type)
    }

    fun asString(element: JsonElement?, defaultVal: String): String {
        try {
            return element!!.asString
        } catch (e: Exception) {
            return defaultVal
        }

    }

    fun asBoolean(element: JsonElement, defaultVal: Boolean): Boolean {
        try {
            return element.asBoolean
        } catch (e: Exception) {
            return defaultVal
        }

    }

    fun asInt(element: JsonElement, defaultVal: Int?): Int? {
        try {
            return element.asInt
        } catch (e: Exception) {
            return defaultVal
        }

    }

    fun asDouble(element: JsonElement, defaultVal: Double): Double {
        try {
            return element.asDouble
        } catch (e: Exception) {
            return defaultVal
        }

    }

    fun asJsonObject(element: JsonElement?): JsonObject? {
        return if (element != null && element.isJsonObject) {
            element.asJsonObject
        } else null

    }

    fun asJsonObject(map: HashMap<String, Any>): JsonObject {
        return gson.toJsonTree(map).asJsonObject
    }

    fun asJsonArray(element: JsonElement?): JsonArray? {
        return if (element != null && element.isJsonArray) {
            element.asJsonArray
        } else null

    }

    fun asHashMap(parentKey: String, json: JsonObject): HashMap<String, Any?> {
        val temp = HashMap<String, Any?>()

        for ((key, value) in json.entrySet()) {
            if (value.isJsonObject) {
                temp[key] = asHashMap("$key.", asJsonObject(value)!!)
            } else if (value.isJsonArray) {
                val jsonArray = value.asJsonArray
                val listOfMaps = ArrayList<HashMap<String, Any?>>()

                for (element in jsonArray) {
                    val jsonObject = element.asJsonObject
                    listOfMaps.add(asHashMap("", jsonObject))
                }

                temp[key] = listOfMaps
            } else {
                val primitive: JsonPrimitive
                if (value.isJsonPrimitive) {
                    primitive = value.asJsonPrimitive
                } else if (value.isJsonNull) {
                    temp[key] = null
                    continue
                } else {
                    val something = value.asString
                    primitive = Gson().fromJson(something, JsonPrimitive::class.java)
                }

                if (primitive.isBoolean) {
                    temp[key] = value.asBoolean
                } else {
                    temp[key] = value.asString
                }
            }
        }

        return temp
    }

}