package de.techdev.pocket.api

/**
 * Determines how Items are sorted when retrieved from the Pocket API
 *
 * @author Alexander Hanschke
 */
enum class Sort(internal val value: String) {

    /**
     * return [Item]s in order of newest to oldest
     */
    NEWEST("newest"),

    /**
     * return [Item]s in order of oldest to newest
     */
    OLDEST("oldest"),

    /**
     * return [Item]s in order of title alphabetically
     */
    TITLE("title"),

    /**
     * return [Item]s in order of url alphabetically
     */
    SITE("site")

}
