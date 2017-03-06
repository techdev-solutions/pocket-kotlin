package de.techdev.pocket

import de.techdev.pocket.api.ModifyOperations
import de.techdev.pocket.api.Pocket
import de.techdev.pocket.api.RetrieveOperations

internal class PocketTemplate(consumer: String, access: String) : Pocket {

    private val retrieveOperations: RetrieveOperations
    private val modifyOperations: ModifyOperations

    init {
        val transport = Components.transport(consumer, access)

        retrieveOperations = RetrieveTemplate(transport)
        modifyOperations = ModifyTemplate(transport)
    }

    override fun retrieveOperations() = retrieveOperations

    override fun modifyOperations() = modifyOperations

}
