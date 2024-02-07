package com.michiganlabs.hoverboard.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type

inline fun <reified T : Any> Moshi.dump(obj: T): String {
    return this.adapter(T::class.java).toJson(obj)
}

@Throws
inline fun <reified T : Any> Moshi.load(json: String): T {
    return this.adapter(T::class.java).fromJson(json)!!
}

//@Throws
//inline fun <reified T : Any> Moshi.load(json: String, vararg types: Type): T {
//    val adapterTypes = Types.newParameterizedType(T::class.java, *types)
//    return this.adapter<T>(adapterTypes).fromJson(json)!!
//}
