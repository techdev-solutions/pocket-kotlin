package de.techdev.pocket.api

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Duration
import java.time.Instant

/**
 * Describes an item in Pocket
 * @property id the identifier as assigned by Pocket
 * @property added the time this [Item] was added (in seconds since 01.01.1970)
 * @property url the URL to access this [Item]
 * @property title the title of the corresponding article (if any)
 *
 * @author Alexander Hanschke
 */
data class Item(
        @JsonProperty("item_id") val id: Long,
        @JsonProperty("time_added") val added: Long,
        @JsonProperty("given_url") val url: String,
        @JsonProperty("resolved_title") val title: String?) {

    /**
     * @return a human-readable label
     */
    fun label(): String {
        if (title.isNullOrEmpty()) {
            return url
        }

        return title!!
    }

    /**
     * @return the age of this [Item] in days
     */
    fun age(): Long {
        val instant = Instant.ofEpochSecond(added)
        return Duration.between(instant, Instant.now()).toDays()
    }

}
