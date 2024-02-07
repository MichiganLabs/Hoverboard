package com.michiganlabs.hoverboard.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import org.junit.Assert.*
import org.junit.Test

/**
 * Moshi extension tests
 */
class MoshiTest {

    private val moshi = Moshi.Builder().build()

    @Test
    fun dumpObjectToJsonString() {
        val testObj = Player(97, "Hutchinson")

        assertEquals("{\"jersey\":97,\"lastName\":\"Hutchinson\"}", moshi.dump(testObj))
    }

    @Test
    fun loadObjectFromJsonString_Success() {
        val json = "{\"jersey\":26,\"lastName\":\"Gibbs\"}"
        val obj = moshi.load<Player>(json)

        assertEquals(26, obj.jerseyNumber)
        assertEquals("Gibbs", obj.lastName)
    }

    @Test
    fun loadObjectFromJsonString_Failure() {
        try {
            val json = "{\"jersey\":26,\"wrongName\":\"Gibbs\"}"
            moshi.load<Player>(json)

            fail("Failed to throw JsonDataException for missing 'lastName'")
        } catch (e: JsonDataException) {
            // Successfully threw JsonDataException
        }
    }

    @Test
    fun loadListOfObjectsFromJsonString() {
        val json = "[{\"jersey\":87,\"lastName\":\"LaPorta\"}, {\"jersey\":32,\"lastName\":\"Branch\"}]"
        val players: List<Player> = moshi.load(json)

        assertEquals(2, players.size)
    }

    @JsonClass(generateAdapter = true)
    internal data class Player(
        @Json(name = "jersey")
        val jerseyNumber: Int,

        @Json(name = "lastName")
        val lastName: String,
    )
}