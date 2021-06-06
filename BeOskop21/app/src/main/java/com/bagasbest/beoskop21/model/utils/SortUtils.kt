package com.bagasbest.beoskop21.model.utils

import androidx.sqlite.db.SimpleSQLiteQuery

// based note room module
object SortUtils {
    const val NEWEST = "Newest"
    private const val OLDEST = "Oldest"
    private const val RANDOM = "Random"
    const val MOVIES_TB = "movies"
    const val SERIES_TB = "series"

    fun getSortedQuery(filter: String, tableName: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM $tableName ")
        when (filter) {
            NEWEST -> simpleQuery.append("ORDER BY id DESC")
            OLDEST -> simpleQuery.append("ORDER BY id ASC")
            RANDOM -> simpleQuery.append("ORDER BY RANDOM()")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}
