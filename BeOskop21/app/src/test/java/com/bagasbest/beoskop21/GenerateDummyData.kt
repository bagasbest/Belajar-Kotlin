package com.bagasbest.beoskop21

import com.bagasbest.beoskop21.model.source.remote.response.ItemList
import com.bagasbest.beoskop21.model.source.remote.response.TvSeriesDetail

object GenerateDummyData {
    fun getDummyRemoteMovie(): List<ItemList> =
        arrayListOf(
            ItemList(
                567189,
                "/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
                "Tom Clancy's Without Remorse",
                "An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife.",
                "2021-04-29",
                7.3,
                808,

                "",
                ""
            )
        )

    fun getDummyRemoteTvSeries(): List<ItemList> =
        arrayListOf(
            ItemList(
                88396,
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "",
                7.9,
                5455,
                "The Falcon and the Winter Soldier",
                "2021-03-19"
            )
        )

    fun getDummyRemoteTvSeriesDetail(): TvSeriesDetail =
        TvSeriesDetail(
            88396,
            "The Falcon and the Winter Soldier",
            "2021-03-19",
            "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
            7.9,
            5455,
            "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
            )
}