package com.bagasbest.beoskop21.model.utils

import com.bagasbest.beoskop21.R
import com.bagasbest.beoskop21.model.model.MovieModel
import com.bagasbest.beoskop21.model.model.SeriesModel

object DummyData {

    fun generateDummyMovie(): List<MovieModel> {
        val movie = ArrayList<MovieModel>()

        movie.add(
            MovieModel(
                "A Star Is Born",
                R.drawable.m_poster_a_start_is_born,
                "5 OCTOBER 2018",
                "2 Hours 16 Minute",
                "R",
                75,
                "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                "Bradley Cooper",
            )
        )
        movie.add(
            MovieModel(
                "Alita: Battle Angel",
                R.drawable.m_poster_alita,
                "14 FEBRUARY 2019",
                "2 Hours 2 Minute",
                "PG-13",
                72,
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "Robert Rodriguez",
            )
        )
        movie.add(
            MovieModel(
                "Aquaman",
                R.drawable.m_poster_aquaman,
                "21 DECEMBER 2019",
                "2 Hours 23 Minute",
                "PG-13",
                69,
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "James Wan",
            )
        )
        movie.add(
            MovieModel(
                "Aquaman",
                R.drawable.m_poster_aquaman,
                "21 DECEMBER 2019",
                "2 Hours 23 Minute",
                "PG-13",
                69,
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "James Wan",
            )
        )
        movie.add(
            MovieModel(
                "Bohemian Rhapsody",
                R.drawable.m_poster_bohemian,
                "2 NOVEMBER 2018",
                "2 Hours 15 Minute",
                "PG-13",
                80,
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "Anthony McCarten",
            )
        )
        movie.add(
            MovieModel(
                "Cold Pursuit",
                R.drawable.m_poster_bohemian,
                "8 FEBRUARY 2019",
                "2 Hours 15 Minute",
                "R",
                57,
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                "Hans Petter Moland",
            )
        )
        movie.add(
            MovieModel(
                "Creed II",
                R.drawable.m_poster_creed,
                "21 NOVEMBER 2018",
                "2 Hours 10 Minute",
                "PG-13",
                69,
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                "Steven Caple Jr.",
            )
        )
        movie.add(
            MovieModel(
                "Fantastic Beasts: The Crimes of Grindelwald",
                R.drawable.m_poster_crimes,
                "16 NOVEMBER 2018",
                "2 Hours 14 Minute",
                "PG-13",
                69,
                "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                "David Yates",
            )
        )
        movie.add(
            MovieModel(
                "Glass",
                R.drawable.m_poster_glass,
                "18 JANUARY 2019",
                "2 Hours 9 Minute",
                "PG-13",
                67,
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                "M. Night Shyamalan",
            )
        )
        movie.add(
            MovieModel(
                "How to Train Your Dragon",
                R.drawable.m_poster_how_to_train,
                "26 MARCH 2010",
                "1 Hours 38 Minute",
                "PG",
                78,
                "As the son of a Viking leader on the cusp of manhood, shy Hiccup Horrendous Haddock III faces a rite of passage: he must kill a dragon to prove his warrior mettle. But after downing a feared dragon, he realizes that he no longer wants to destroy it, and instead befriends the beast – which he names Toothless – much to the chagrin of his warrior father",
                "Chris Sanders",
            )
        )
        movie.add(
            MovieModel(
                "Avengers: Infinity War",
                R.drawable.m_poster_infinity_war,
                "27 APRIL 2018",
                "2 Hours 29 Minute",
                "PG-13",
                83,
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "Joe Russo",
            )
        )
        return movie
    }

    fun generateDummyTvSeries(): List<SeriesModel> {
        val series = ArrayList<SeriesModel>()

        series.add(
            SeriesModel(
                "Doom Patrol",
                R.drawable.t_poster_doom_patrol,
                2019,
                "Sci-Fi & Fantasy, Comedy, Drama",
                "49 Minutes",
                "TV-MA",
                76,
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                "Jeremy Carver",
                "HBO GO",
            )
        )
        series.add(
            SeriesModel(
                "Fairy Tail",
                R.drawable.t_poster_fairytail,
                2009,
                "Action & Adventure, Animation, Comedy, Sci-Fi & Fantasy",
                "24 Minutes",
                "TV-14",
                78,
                "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn't just any ordinary kid, he's a member of one of the world's most infamous mage guilds: Fairy Tail.",
                "フェアリーテイル",
                "NETFLIX",
            )
        )
        series.add(
            SeriesModel(
                "Family Guy",
                R.drawable.t_poster_family_guy,
                1999,
                "Animation, Comedy",
                "22 Minutes",
                "TV-14",
                70,
                "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                "Seth MacFarlane",
                "NETFLIX"
            )
        )
        series.add(
            SeriesModel(
                "The Flash",
                R.drawable.t_poster_flash,
                2014,
                "Drama, Sci-Fi & Fantasy",
                "44 Minutes",
                "TV-14",
                77,
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "Greg Berlanti, Geoff Johns, Andrew Kreisberg",
                "NETFLIX"
            )
        )
        series.add(
            SeriesModel(
                "Gotham",
                R.drawable.t_poster_gotham,
                2014,
                "Drama, Crime, Sci-Fi & Fantasy",
                "43 Minutes",
                "TV-14",
                75,
                "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                "Bruno Heller",
                "NETFLIX"
            )
        )
        series.add(
            SeriesModel(
                "Grey's Anatomy",
                R.drawable.t_poster_grey_anatomy,
                2015,
                "Drama",
                "43 Minutes",
                "TV-14",
                82,
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "Shonda Rhimes",
                "NETFLIX"
            )
        )
        series.add(
            SeriesModel(
                "Hanna",
                R.drawable.t_poster_hanna,
                2019,
                "Action & Adventure, Drama",
                "50 Minutes",
                "TV-MA",
                75,
                "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                "David Farr",
                "Prime video"
            )
        )
        series.add(
            SeriesModel(
                "Marvel's Iron Fist",
                R.drawable.t_poster_iron_fist,
                2017,
                "Action & Adventure, Drama, Sci-Fi & Fantasy",
                "55 Minutes",
                "TV-MA",
                66,
                "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
                "Scott Buck",
                "NETFLIX"
            )
        )
        series.add(
            SeriesModel(
                "Shameless",
                R.drawable.t_poster_shameless,
                2011,
                "Drama, Comedy",
                "57 Minutes",
                "TV-MA",
                80,
                "Chicagoan Frank Gallagher is the proud single dad of six smart, industrious, independent kids, who without him would be... perhaps better off. When Frank's not at the bar spending what little money they have, he's passed out on the floor. But the kids have found ways to grow up in spite of him. They may not be like any family you know, but they make no apologies for being exactly who they are.",
                "John Wells, Paul Abbott",
                "NETFLIX"
            )
        )
        series.add(
            SeriesModel(
                "Supergirl",
                R.drawable.t_poster_supergirl,
                2015,
                "Drama, Sci-Fi & Fantasy, Action & Adventure",
                "42 Minutes",
                "TV-14",
                72,
                "Twenty-four-year-old Kara Zor-El, who was taken in by the Danvers family when she was 13 after being sent away from Krypton, must learn to embrace her powers after previously hiding them. The Danvers teach her to be careful with her powers, until she has to reveal them during an unexpected disaster, setting her on her journey of heroism.",
                "Greg Berlanti, Ali Adler, Andrew Kreisberg",
                "NETFLIX"
            )
        )
        return series
    }
}