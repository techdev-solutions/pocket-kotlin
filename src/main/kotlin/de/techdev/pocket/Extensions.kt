package de.techdev.pocket

import de.techdev.pocket.api.PocketException
import okhttp3.Response

internal fun Response.validate() {
    if (isSuccessful.not()) {
        throw PocketException("${header("X-Error")} [status ${code()}]")
    }
}
