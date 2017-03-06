package de.techdev.pocket.api

/**
 * Operations used to retrieve [Item]s from Pocket
 *
 * @author Alexander Hanschke
 */
interface RetrieveOperations {

    /**
     * Retrieves existing [Item]s from Pocket
     * @param state only retrieve [Item]s with this [State]
     * @param sort sort [Item]s based on the sort direction
     * @param details specify whether all [Item] details shall be returned or only a minimum
     *
     * @return all existing [Item]s matching the specified parameters
     */
    fun items(state: State = State.UNREAD, sort: Sort = Sort.OLDEST, details: Details = Details.SIMPLE): Collection<Item> = emptyList()

}
