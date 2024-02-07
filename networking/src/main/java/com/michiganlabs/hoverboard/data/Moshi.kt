package com.michiganlabs.hoverboard.data

import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type

/**
 * Write the provided object to a JSON string
 *
 * @param obj to convert to JSON string
 * @return the JSON formatted String
 */
inline fun <reified T : Any> Moshi.dump(obj: T): String {
    return this.adapter(T::class.java).toJson(obj)
}

/**
 * Load an object of type T from a JSON String.
 * Example usage: moshi.load<Foo>(json)
 *
 * @param json the json string
 * @return the parsed object
 * @throws [JsonDataException] if the data does match the appropriate format
 */
@Throws
inline fun <reified T : Any> Moshi.load(json: String): T {
    return this.adapter(T::class.java).fromJson(json)!!
}

/**
 * Load a List<T> from a JSON String.
 * Example usage: moshi.load<List<Foo>>(json, Foo::class.java)
 *
 * @param json the json string
 * @param types the type to parse the string into
 * @return the parsed object
 * @throws [JsonDataException] if the data does match the appropriate format
 */
@Throws
inline fun <reified T : Any> Moshi.load(json: String, vararg types: Type): T {
    val adapterTypes = Types.newParameterizedType(T::class.java, *types)
    return this.adapter<T>(adapterTypes).fromJson(json)!!
}
