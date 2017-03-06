package de.techdev.pocket

import de.techdev.pocket.api.Pocket
import de.techdev.pocket.api.PocketException
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExternalResource
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNull

class PocketTest {

    var server = MockWebServer()

    @Rule @JvmField
    val resource = object : ExternalResource() {
        override fun before() = server.start()
        override fun after()  = server.shutdown()
    }

    @Test
    fun `given a bad request, PocketException is thrown`() {
        server.enqueue(error(400, "Missing API Key"))

        assertFailsWith<PocketException> {
            pocket().retrieveOperations().items()
        }
    }

    @Test
    fun `given a valid request, item is retrieved`() {
        server.enqueue(item())

        val items = pocket().retrieveOperations().items()

        assertFalse(items.isEmpty())
        assertEquals("A Test Title", items.first().title)
    }

    @Test
    fun `given an item without title, no exception is thrown`() {
        server.enqueue(itemWithoutTitle())

        val items = pocket().retrieveOperations().items()

        assertFalse(items.isEmpty())
        assertNull(items.first().title)
    }

    @Test
    fun `given an item without title, url is used as label`() {
        server.enqueue(itemWithoutTitle())

        val items = pocket().retrieveOperations().items()

        assertEquals("https://techdev.de", items.first().label())
    }

    private fun pocket(): Pocket {
        val pocket = mock(Pocket::class.java)
        val transport = Components.transport("consumer", "access")
        `when`(pocket.modifyOperations()).thenReturn(ModifyTemplate(transport, server.url("/v3/send").toString()))
        `when`(pocket.retrieveOperations()).thenReturn(RetrieveTemplate(transport, server.url("/v3/get").toString()))

        return pocket
    }

    private fun error(code: Int, message: String): MockResponse {
        val response = MockResponse()
        response.setResponseCode(code)
        response.setHeader("X-Error", message)
        return response
    }

    private fun item(): MockResponse {
        val response = MockResponse()
        response.setResponseCode(200)
        response.setHeader("Content-Type", "application/json")
        response.setBody(
                """
                {
                    "status" : 1,
                    "list" : {
                        "229279689" : {
                            "item_id" : "229279689",
                            "resolved_title" : "A Test Title",
                            "time_added" : "1471869712",
                            "given_url" : "https://techdev.de"
                        }
                    }
                }
            """
        )

        return response
    }

    private fun itemWithoutTitle(): MockResponse {
        val response = MockResponse()
        response.setResponseCode(200)
        response.setHeader("Content-Type", "application/json")
        response.setBody(
                """
                {
                    "status" : 1,
                    "list" : {
                        "229279689" : {
                            "item_id" : "229279689",
                            "time_added" : "1471869712",
                            "given_url" : "https://techdev.de"
                        }
                    }
                }
            """
        )

        return response
    }

}
