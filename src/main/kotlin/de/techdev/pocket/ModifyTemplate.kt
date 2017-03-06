package de.techdev.pocket

import de.techdev.pocket.api.Item
import de.techdev.pocket.api.ModifyOperations

internal class ModifyTemplate(private val transport: Transport) : ModifyOperations {

    private var endpoint = "https://getpocket.com/v3/send"

    constructor(transport: Transport, endpoint: String) : this(transport) {
        this.endpoint = endpoint
    }

    override fun archive(items: Collection<Item>) {
        execute(items.map { Action.Archive(it) })
    }

    override fun delete(items: Collection<Item>) {
        execute(items.map { Action.Delete(it) })
    }

    private fun execute(actions: Collection<Action>) {
        transport.post<ModifyResponse>(ModifyRequest(actions), endpoint)
    }
}
