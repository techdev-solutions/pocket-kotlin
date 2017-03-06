package de.techdev.pocket.api

import de.techdev.pocket.PocketTemplate

/**
 * The central interface to interact with the Pocket API
 *
 * @author Alexander Hanschke
 */
interface Pocket {

    companion object {
        /**
         * @param consumer the consumer key to connect to the Pocket API
         * @param access the access token to connect to the Pocket API
         */
        fun connect(consumer: String, access: String): Pocket = PocketTemplate(consumer, access)
    }

    /**
     * @return the operations to modify existing Items via the Pocket API
     */
    fun retrieveOperations(): RetrieveOperations

    /**
     * @return the operations to retrieve Items from the Pocket API
     */
    fun modifyOperations(): ModifyOperations

}
