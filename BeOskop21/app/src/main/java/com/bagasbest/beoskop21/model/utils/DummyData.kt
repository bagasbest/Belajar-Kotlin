package com.bagasbest.beoskop21.model.utils

import com.bagasbest.beoskop21.R
import com.bagasbest.beoskop21.model.model.MovieModel

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

        return movie
    }
}