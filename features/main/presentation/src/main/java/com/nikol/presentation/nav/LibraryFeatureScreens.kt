package com.nikol.presentation.nav

import android.net.Uri
import com.nikol.domain.model.Movie
import org.json.JSONObject

sealed class LibraryFeatureScreens(val route: String) {
    data object LibraryScreen : LibraryFeatureScreens(route = "library_screen")
    data object AddScreen : LibraryFeatureScreens(route = "add_screen")
    data object DetailScreen : LibraryFeatureScreens(route = "detail_screen/{movieJson}") {
        fun withObject(movie: Movie): String {
            val json = JSONObject().apply {
                put("id", movie.id)
                put("title", movie.title)
                put("year", movie.year ?: JSONObject.NULL)
                put("description", movie.description)
                put("imageUrl", movie.imageUrl)
                put("rating", movie.rating ?: JSONObject.NULL)
            }.toString()
            return "detail_screen/${Uri.encode(json)}"
        }

        fun parseArguments(jsonString: String?): Movie {
            requireNotNull(jsonString) { "Movie JSON data is missing" }
            val decodedJson = Uri.decode(jsonString)
            val json = JSONObject(decodedJson)

            return Movie(
                id = json.getInt("id"),
                title = json.getString("title"),
                year = if (json.isNull("year")) null else json.getInt("year"),
                description = json.getString("description"),
                imageUrl = json.getString("imageUrl"),
                rating = if (json.isNull("rating")) null else json.getDouble("rating")
            )
        }
    }

    companion object {
        const val NAVIGATION_ROUTE = "library_feature_navigation"
        val startScreenRoute = LibraryScreen.route
    }
}