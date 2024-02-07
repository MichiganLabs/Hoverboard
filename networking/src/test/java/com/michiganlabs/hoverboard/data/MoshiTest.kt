package com.michiganlabs.hoverboard.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Test

/**
 * Moshi extension tests
 */
@Suppress("MagicNumber")
class MoshiTest {

    private val moshi = Moshi.Builder().build()

    /**
     * Verify successful dumping of object to String
     */
    @Test
    fun dumpObjectToJsonString() {
        val testObj = Player(97, "Hutchinson")

        assertEquals("{\"jersey\":97,\"lastName\":\"Hutchinson\"}", moshi.dump(testObj))
    }

    /**
     * Verify successful reading of JSON String into object
     */
    @Test
    fun loadObjectFromJsonString_Success() {
        val json = "{\"jersey\":26,\"lastName\":\"Gibbs\"}"
        val obj = moshi.load<Player>(json)

        assertEquals(26, obj.jerseyNumber)
        assertEquals("Gibbs", obj.lastName)
    }

    /**
     * Verify [JsonDataException] is thrown when JSON String does not have valid data
     */
    @Test
    fun loadObjectFromJsonString_Failure() {
        try {
            val json = "{\"jersey\":26,\"wrongName\":\"Gibbs\"}"
            moshi.load<Player>(json)

            fail("Failed to throw JsonDataException for missing 'lastName'")
        } catch (e: JsonDataException) {
            print(e.localizedMessage)
        }
    }

    /**
     * Verify successfully parsing a List from a JSON String
     */
    @Test
    fun loadListOfObjectsFromJsonString() {
        val json = "[{\"jersey\":87,\"lastName\":\"LaPorta\"}, {\"jersey\":32,\"lastName\":\"Branch\"}]"
        val players: List<Player> = moshi.load(json, Player::class.java)

        assertEquals(2, players.size)

        assertEquals(87, players[0].jerseyNumber)
        assertEquals("LaPorta", players[0].lastName)

        assertEquals(32, players[1].jerseyNumber)
        assertEquals("Branch", players[1].lastName)
    }

    @JsonClass(generateAdapter = true)
    internal data class Player(
        @Json(name = "jersey")
        val jerseyNumber: Int,

        @Json(name = "lastName")
        val lastName: String,
    )
}
