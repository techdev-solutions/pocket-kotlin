package de.techdev.pocket.api

/**
 * Determines how many information per [Item] will be transferred
 *
 * @author Alexander Hanschke
 */
enum class Details(internal val value: String) {

    /**
     * only return the titles and urls of each [Item]
     */
    SIMPLE("simple"),

    /**
     * return all data about each [Item], including tags, images, authors, videos and more
     */
    COMPLETE("complete")

}
