package de.techdev.pocket

import com.fasterxml.jackson.annotation.JsonProperty
import de.techdev.pocket.api.Item

internal class RetrieveResponse(@JsonProperty("list") val items: Map<Int, Item>)

internal class ModifyResponse(@JsonProperty("status") val status: Int)