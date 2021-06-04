package com.example.cocktailmaster.data.source.remote.api

import android.net.Uri

object APIConstant {

    const val SCHEME_API = "https"
    const val AUTHORITY = "www.thecocktaildb.com"

    const val API_CONTENT = "/api/json/v1/1/"
    const val API_RANDOM = "random.php"
    const val API_SEARCH = "search.php"
    const val API_FILTER = "filter.php"
    const val API_LIST = "list.php"
    const val API_LOOKUP = "lookup.php"
}

object APIQueryExtension {

    fun Uri.Builder.appendAuthorityAndContent(apiType: String): Uri.Builder {
        return scheme(APIConstant.SCHEME_API)
            .authority(APIConstant.AUTHORITY)
            .appendPath(APIConstant.API_CONTENT)
            .appendPath(apiType)
    }
}
