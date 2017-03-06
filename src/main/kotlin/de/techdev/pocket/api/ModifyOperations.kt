package de.techdev.pocket.api

/**
 * Operations used to modify [Item]s in Pocket
 *
 * @author Alexander Hanschke
 */
interface ModifyOperations {

    /**
     * archives an existing [Item]
     */
    fun archive(item: Item) = archive(listOf(item))

    /**
     * archives a collection of existing [Item]s
     */
    fun archive(items: Collection<Item>)

    /**
     * deletes an existing [Item]
     */
    fun delete(item: Item) = delete(listOf(item))

    /**
     * deletes a collection of existing [Item]s
     */
    fun delete(items: Collection<Item>)

}
