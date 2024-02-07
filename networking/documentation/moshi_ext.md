# Moshi Extensions

Hoverboard Networking provides a couple of quality of life improvements for [Moshi](https://github.com/square/moshi) to go to and from models to JSON strings in Kotlin.


## Reading JSON into Object
Loading a model from JSON is as simple as
```kotlin
val foo = moshi.load<Bar>(jsonString)
```

Although loading an object is simple, loading a type that contains a generic can be frustrating. One of the most common examples of this will be attempting to read a `List<T>` from JSON. Done incorrectly this will result in an error message similiar to 
```
java.lang.ClassCastException: class com.squareup.moshi.LinkedHashTreeMap cannot be cast to class com.xxx.yyy.zzz (com.squareup.moshi.LinkedHashTreeMap...
``` 

To ease this, the [`load` function]() may also take in a `Type` to define the generic. This will setup the parameterized types for you.
```kotlin
@JsonClass(generateAdapter = true)
internal data class Player(
    @Json(name = "jersey")
    val jerseyNumber: Int,

    @Json(name = "lastName")
    val lastName: String,
)

private fun readList(): List<Player> {
    val json = "[{\"jersey\":87,\"lastName\":\"LaPorta\"}, {\"jersey\":32,\"lastName\":\"Branch\"}]"
    return moshi.load(json, Player::class.java)
}
```

## Dumping objects into JSON Strings
Dumping a model to JSON is as simple as
```kotlin
moshi.dump(foo)
```

