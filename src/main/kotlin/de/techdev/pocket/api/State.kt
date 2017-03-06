package de.techdev.pocket.api

/**
 * Used to specify which state [Item]s have to be in
 */
enum class State(internal val value: String) {

    /**
     * only return unread [Item]s
     */
    UNREAD("unread"),

    /**
     * only return archived [Item]s
     */
    ARCHIVE("archive"),

    /**
     * return both unread and archived [Item]s
     */
    ALL("all")

}
